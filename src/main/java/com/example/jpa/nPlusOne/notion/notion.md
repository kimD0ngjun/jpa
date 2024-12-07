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

서비스에서 팀들을 조회하면서 각 멤버들의 이름을 일괄 조회하는 코드를 작성한다.

```java
@Transactional
@Service
@RequiredArgsConstructor
public class TeamMemberService {
    private final TeamRepository teamRepository;

    public void findAllMembers() {
        List<String> list = teamRepository.findAll().stream()
                .flatMap(team -> team.getMembers().stream())
                .map(Member::getName)
                .toList();
    }

    public void findParticularMembers() {
        Team team = teamRepository.findById(1L).orElseThrow();

        List<String> list = team.getMembers().stream()
                .map(Member::getName)
                .toList();
    }
}
```

`findAllMembers()` 메소드는 전체 팀들을 조회하면서 각 팀의 멤버들 이름을 조회하는 메소드이고, `findParticularMembers()` 메소드는 특정 팀(ID : `1L`)을 조회하면서 해당 팀의 멤버들 이름을 조회하는 메소드이다.<br/>
각각의 메소드들을 호출했을 때, hibernate가 어떻게 쿼리를 발신하는지를 확인하면서 N+1 문제를 직접 확인한다.

#### (3) 테스트 실행 결과

`findAllMembers()` 메소드를 실행하고 출력되는 쿼리문을 확인하면 아래와 같다.

<img width="925" alt="전체_팀_멤버들_조회_N+1" src="https://github.com/user-attachments/assets/18b22a69-7e28-4441-9776-2d2b76244fd9">

`findParticularMembers()` 메소드를 실행하고 출력되는 쿼리문을 확인하면 아래와 같다.

<img width="925" alt="특정_팀_멤버_조회_N+1" src="https://github.com/user-attachments/assets/6030d688-a43e-4a63-998e-29e8f2feb849">

### 2) 탐구

### (1) 왜 문제가 되는가?

우리가 원하는 것에 대해 다시 확인을 해보자.<br />
일단 직접 데이터베이스에 접근해서 테이블을 확인하고 우리가 원하는 결과 조회를 확인한다.

현재 데이터베이스(MySQL)에는 다음과 같은 테이블들이 있다.

<img width="671" alt="팀,멤버필드확인" src="https://github.com/user-attachments/assets/4c66645c-1035-4d5a-80c1-49c5e815c812">

여기서 만약 이런 경우가 생길 수 있다.

>**팀의 멤버**들을 조회

현재 위의 테이블은 팀 따로, 멤버 따로 제시되어 있으니 아마 해당 경우가 원하는 테이블의 모습은 아래와 같을 것이다.

<img width="502" alt="조인문" src="https://github.com/user-attachments/assets/f24840a9-6a38-4afe-ae9f-c9bce2669790">

위의 테이블처럼 팀 정보와 그 팀에 해당하는 멤버들을 정보를 일괄 조회할 수 있다.<br />
다시 `findAllMembers()` 메소드에 있는 코드 로직을 면밀히 살펴보자.

```java
public void findAllMembers() {
    List<String> list = teamRepository.findAll().stream()
            .flatMap(team -> team.getMembers().stream())
            .map(Member::getName)
            .toList();
}
```


---

*N+1 문제에 대한 고찰*

1. JPA, 나아가 ORM 설계상의 하자가 아니다. 지연 로딩을 통해 굳이 현시점에 호출이 불필요하나, 나중에 호출의 여지가 있는 연관관계 자식 데이터는 추상화로 남겨뒀다가 호출할 때 코드를 짬으로써 효율을 추구한다.
2. 다만, 1번의 취지를 우선하다보니 부모 데이터를 부르면서 동시에 자식 데이터의 내용들을 일괄 조회하려는 경우를 챙기지 못하는 것이다.
3. 그렇기 때문에 fetch join 등이 N+1의 설계상 하자의 해결책이라는 관점보다는 그저 개발 환경에 맞춰서 취사선택해야하는 전략 중 하나라는 관점이 옳을 것이다. 개발 상황에 따라 1번이 우선될지 2번이 우선될지는 모르기 때문이다.