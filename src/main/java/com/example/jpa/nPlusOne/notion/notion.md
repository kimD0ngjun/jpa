# N+1 문제

## 1. 개념

### (1) JPA 개념 정리
JPA는 자바 애플리케이션에서 데이터베이스와 객체와의 매핑 처리를 위한 **ORM 기술 표준**이다. 기술 표준이라 함은 인터페이스이기 때문에 이를 구현하는 프레임워크가 존재하는데 대표적인 게 Hibernate이다.
### (2) N+1 문제 원인
JPA의 기능 중 데이터베이스 테이블의 외래 키 전략을 객체 단계에서 표현하기 위한 **연관관계 매핑**과, 이 연관된 데이터를 일괄 조회하는 것이 아닌 필요할 때에만 조회함으로써 성능 효율을 추구하는 **지연 로딩**이 있는데 이 둘 때문에 N+1 문제라는 이슈가 발생한다.

## 2. 탐구

### (1) 문제 배경 설정

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

### (2) 테스트 코드 작성

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