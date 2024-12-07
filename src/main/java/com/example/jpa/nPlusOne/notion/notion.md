# N+1 문제

## 1. 개념

### (1) JPA 개념 정리
JPA는 자바 애플리케이션에서 데이터베이스와 객체와의 매핑 처리를 위한 **ORM 기술 표준**이다. 기술 표준이라 함은 인터페이스이기 때문에 이를 구현하는 프레임워크가 존재하는데 대표적인 게 Hibernate이다.
### (2) N+1 문제 원인
JPA의 기능 중 데이터베이스 테이블의 외래 키 전략을 객체 단계에서 표현하기 위한 **연관관계 매핑**과, 이 연관된 데이터를 일괄 조회하는 것이 아닌 필요할 때에만 조회함으로써 성능 효율을 추구하는 **지연 로딩**이 있는데 이 둘 때문에 N+1 문제라는 이슈가 발생한다.

## 2. 코드 기반 탐구

### 1) 테스트

#### (1) 테스트 환경 세팅 

연관관계를 설정한 두 개의 엔티티를 생성한다.

```java
@Entity
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team") // ~ToMany: LAZY
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this); // 양방향 연관 관계 동기화
    }
}
```
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL) // ~ToOne: EAGER
    @JoinColumn(name = "team_id") // 외래 키(팀)를 관리하는 측(멤버)에게 부여
    private Team team;

    public Member(String name) {
        this.name = name;
    }
}
```
`Tean` 엔티티와 `Member` 엔티티는 1:N 단방향 관계를 맺는다.<br />
이것은 데이터베이스에서도 외래키 설정을 확인할 수 있다.

<img width="667" alt="외래키설정확인" src="https://github.com/user-attachments/assets/bc83a33f-e3c8-47c5-a5d7-b5cf458fa1fe">

1:N에서 N에 해당하는 `Member`가, 1에 해당하는 `Team`의 ID를 외래 키로 가지고 있음을 확인할 수 있다.

#### (2) 테스트 코드 작성

현재 데이터베이스(MySQL)에는 다음과 같은 테이블들이 있다.

<img width="671" alt="팀,멤버필드확인" src="https://github.com/user-attachments/assets/4c66645c-1035-4d5a-80c1-49c5e815c812">

여기서 만약 이런 경우가 생길 수 있다.

>**팀의 멤버**들을 조회

현재 위의 테이블은 팀 따로, 멤버 따로 제시되어 있으니 아마 해당 경우가 원하는 테이블의 모습은 아래와 같을 것이다.

<img width="502" alt="조인문" src="https://github.com/user-attachments/assets/f24840a9-6a38-4afe-ae9f-c9bce2669790">

위의 테이블처럼 팀 정보와 그 팀에 해당하는 멤버들을 정보를 일괄 조회할 수 있다.<br />
이것을 JPA의 데이터베이스 객체 매핑 장점을 활용해서 자바 레벨에서도 코드로 작성할 수 있다.

```java
public void findAllMembersByTeamRepo() {
    List<String> list = teamRepository.findAll().stream()
            .flatMap(team -> team.getMembers().stream()
                    .map(member -> team.getName() + ": " + member.getName()))
            .toList();

    System.out.println("결과: " + list);
}
```

이것을 실행하면 다음과 같이 hibernate 쿼리 출력 로그와 결과 로그를 확인할 수 있다.

<img width="935" alt="결과 출력 로그" src="https://github.com/user-attachments/assets/176515d3-0c06-4da9-bb38-40b1f8c33379">

### 2) 탐구

#### (1) 왜 문제가 되는가?

이게 문제가 되는 이유는, **쿼리가 중복 발신**되기 때문이다.<br />
노란색 박스는 아래처럼 팀 테이블의 팀 ID와 이름을 조회한다.

>``select t1_0.team_id,t1_0.team_name from team t1_0``

초록색 박스는 아래처럼 멤버 테이블에서 외래 키에 대응되는 팀의 ID 조건부로 팀 정보와 멤버 정보를 같이 조회한다.

>``select m1_0.team_id,m1_0.member_id,m1_0.member_name from member m1_0 where m1_0.team_id=?``

사실 JPA 입장으로 봤을 때는 코드 로직을 해석해보면 충분히 납득이 간다.<br />
아래처럼 순서가 이뤄지고 이것은 머릿속으로도 충분히 그림이 그려지기 때문이다.

>1. 일단 팀들을 전부 조회한다 - 노란색 박스
>2. 1번 결과로부터 팀 엔티티별로 멤버들을 조회한다 - 초록색 박스

이렇게 JPA 입장에서 원하는 결과를 조회하는 데에는 문제가 없지만, 문제는 데이터베이스다.<br />
위의 결과를 그저 딱 한 번의 쿼리로도 충분히 조회할 수 있기 때문이다.

> `select t.team_id, t.team_name, m.member_id, m.member_name from team t left join member m on t.team_id = m.team_id;`

<img width="505" alt="leftjoin" src="https://github.com/user-attachments/assets/85aeab52-41b3-416b-a17c-54bf02ce759b">

쿼리 문법 중 **`join`** 문법을 활용하면 두 테이블의 데이터를 동시에 가져올 수 있어서 두 번의 테이블 스캔을 한 번으로 줄임으로써 한 번의 네트워크 호출로 원하는 결과를 반환할 수 있게 된다.<br />
하지만 JPA는 **join**을 활용하지 않고 일일이 모든 테이블을 스캔하면서 `where`로 조건부 스캔을 중복하고 있다. 지금이야 팀이 3개밖에 안되지만 팀이 많아질 수록 이 중복되는 횟수 역시 비례해서 증가하게 될 것이다.

즉, N+1 문제라는 것은 **1번의 부모 조회** + **N번의 연관된 자식 조회**가 발생하는 이슈라고 할 수 있다.

#### (2) 왜 이렇게 설계됐는지 - 지연 로딩의 관점

이런 문제가 발생하게 된 배경에는 **지연 로딩**이 있다.<br />
지연 로딩은 엔티티를 사용하는 시점까지 데이터 로드를 미루는 기법이다. 즉, 데이터베이스에서 즉시 데이터를 가져오지 않고 필요할 때 데이터를 로드하는 방식이다. 이를 통해 현재 시점에서 불필요한 데이터 로드를 방지하면서 초기 로딩 속도를 빠르게 할 수 있다.<br />

아까 `Team` 엔티티를 보면

```java
@Entity
@Getter
@NoArgsConstructor
public class Team {
    
    // ...
 
    @OneToMany(mappedBy = "team") // ~ToMany: LAZY
    private List<Member> members = new ArrayList<>();
    
    // ...
```

1:N 연관관계를 맺은 `Member` 엔티티 객체를 참조하고 있음을 알 수 있다.<br />
즉, 만약 `TeamRepository`를 통해 `Team` 엔티티 인스턴스 객체를 조회하는 과정에서 `MemberRepository`에서 연관관계에 대응되는 해당 `Member` 엔티티 인스턴스들이 조회되면서 엔티티 내부에 있는 모든 정보들을 일괄적으로 함께 조회해야 되는 상황이 된다.<br />

이것을 구체화된 객체 참조가 아닌, **프록시 객체**로 대체해 최소한의 정보만을 제공하다가, `getter`, `toString()` 등을 통해 해당 필드의 구체적인 정보를 요구할 때 그제서야 쿼리를 발신하면서 실제 내용인 `List` 타입의 변수 내용이 반환된다.

>`~ToMany` 어노테이션은 지연 로딩이 디폴트고, `~ToOne` 어노테이션은 즉시 로딩이 디폴트다.
> 디버깅 과정에서 찰나의 순간에 *Collecting data...* 라는 문구가 확인되는데, 프록시 객체를 실제 객체로 가져오는 과정에서 표기되는 문구로 추정
> 
> <img width="956" alt="스크린샷 2024-12-07 오후 3 38 01" src="https://github.com/user-attachments/assets/8219542a-0cb3-499e-9710-7af83fcb0d79">

#### (3) N+1 문제에 대한 고찰

**지연 로딩의 관점**<br />
N+1 문제는 JPA, 나아가 ORM 설계상의 하자가 아니다. 지연 로딩을 통해 굳이 현시점에 호출이 불필요하나, 나중에 호출의 여지가 있는 연관관계 자식 데이터는 프록시 객체로 남겨뒀다가 호출할 때 실체화된 객체 호출 코드를 짬으로써 효율을 추구한다. 이게 곧 **지연 로딩**의 존재 의의라고 볼 수 있다.

다만, 상기의 내용을 우선하다보니 부모 데이터를 부르면서 동시에 자식 데이터의 내용들을 일괄 조회하려는 경우를 챙기지 못하는 것이다. 그렇기 때문에 fetch join 등이 N+1의 설계상 하자의 해결책이라기보다는 그저 개발 환경에 맞춰서 취사선택해야하는 전략 중 하나라는 관점이 옳을 것이다. 개발 상황에 따라 데이터 조회 전략이 다르기 때문이다.

**ORM의 관점**<br />
조금 더 생각해보자면, 객체지향에는 **참조**라는 개념이 있다. 하지만 데이터베이스에는 참조라는 개념이 없다. 그리고 ORM은 객체와 데이터베이스를 매핑하는 기술이다.

객체지향 주소를 바탕으로 참조값이 가리키는 객체를 알 수 있다. 즉, 해당 객체의 내부 내용(클래스의 필드나 메소드)들을 전부 끌고오지 않아도 주소만 알고 있으면 해당 객체로 넘어가서 구체화된 정보들을 확인할 수 있게 된다.

하지만 데이터베이스에는 참조라는 개념이 없이, 단순히 외래 키를 통해 해당 스키마의 필드가 어떤 부모 스키마의 필드에게 연관관계로써 종속됐는지 직접 확인(쿼리 호출)을 해야 되기 때문에 이것을 ORM 취지에 따라 객체와 데이터베이스 매핑을 이뤄내는 과정에서 생긴 이슈라고 볼 수 있다.

## 3. 해결 방안

### 1) 로딩 시점 변경 (x)

위의 설명에 따르면 지연 로딩이 마치 N+1 문제의 원인인 것처럼 보일 수 있다. 그렇다고 즉시 로딩으로 바꿔서 해결할 수 있을까?

```java
@Entity
@Getter
@NoArgsConstructor
public class Team {
    
    // ...
 
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Member> members = new ArrayList<>();
    
    // ...
```

지연 로딩을 즉시 로딩으로 설정을 바꾸고 디버깅을 해서 쿼리 출력 과정을 살펴본다.

| 지연 로딩                                                                                                | 즉시 로딩                                                                                                                                    |
|------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| <img width="1440" alt="지연로딩" src="https://github.com/user-attachments/assets/216a937c-0af9-464f-abab-f128309fe219"> | <img width="1440" alt="즉시로딩은N+1해결책x" src="https://github.com/user-attachments/assets/f625a61a-db30-4a5b-917e-50a427c9dfac"> |
                                                                                                                                                                                                                                             
보면 `Team` 엔티티 인스턴스의 내부 정보들을 조회해 올 때, 지연 로딩은 우선 `Team` 엔티티에 대응되는 team 스키마의 필드들을 호출하는 데에 그치지만, 즉시 로딩은 team 스키마 필드와 더불어 연관관계(데이터베이스 입장에서는 외래 키로써 부여된)를 맺고 있는 `Member` 엔티티 인스턴스들까지 한 번에 조회해 온다.<br />
기존의 지연 로딩에서도 중복되는 쿼리 발신이 N+1 문제로 발생했었고 즉시 로딩이라고 나아지진 않는다. 결국 연관관계 필드의 로딩 시점을 조절하는 것만으로는 해결책이 될 수 없다.

### 2) 