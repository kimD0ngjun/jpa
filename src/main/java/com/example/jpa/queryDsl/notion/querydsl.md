# 0. 들어가기에 앞서

현재 QueryDSL은 2025년 1월 11일 기준, 8개월 전에서 **5.1 버전**을 기점으로 업데이트가 중단된 상태다. 길다면 길고 짧다면 짧은 기간의 감감무소식이어서 향후 지원이 어떻게 될 지는 모르는 상황이고 자바는 계속 버전 업데이트가 이뤄지고 있기 때문에 일단은 학습에 우선점을 더 두고 공부를 한다.

요약하자면 신속하게 공부 마무리하기.

# 1. QueryDSL 개론

## 1) 정의

Java 기반의 **타입 안정성 기반** SQL, JPQL, MongoDB, JDO 등을 작성할 수 있는 **도메인 특화 언어**로, 주로 JPA와 같이 사용되며 쿼리를 동적으로 생성하면서 컴파일 시점에 문법 검증이 가능하다.

>#### (1) 컴파일 시점의 오류 검증
>
>IDE의 문법 검증 등을 통해, 혹은 빌드 시점에서 문법 에러를 검증함으로써 런타임 시점에 에러가 발생하지 않는다.
>
>#### (2) 메소드 체이닝
>
>`select()`, `where()`, `from()` 등 SQL과 유사한 문법을 메소드 체이닝으로 구현한다.
>
>#### (3) 동적 쿼리 지원
>
>`BooleanBuilder`와 `Expressions`를 사용하여 동적 쿼리를 작성할 수 있다.
>
>#### (4) SQL 추상화를 통한 JPQL 자동 생성
>
>JPQL을 자바 코드로 표현하여 이를 SQL로 자동으로 변환한다.


JPA에서 엔티티의 `usernmae` 필드와 `email` 필드를 통해 해당하는 레코드를 테이블에서 조회해오는 것은 `findByUsernameAndEmail()` 메소드 등을 통해 간단히 해결할 수 있다. 다만 JPA만으로는 복잡한 쿼리(`join` 문법이나 복수의 테이블을 엮어 원하는 조건을 **검색**할 때)를 표현하는 데에 무리가 있다.

QueryDSL은 이 점을 보완할 수 있는데, 위에서 언급한 동적 쿼리를 지원하고 가독성이 높아지면서 직관성이 확보된다.

위의 특징들을 직접 실습을 통해 활용해볼 예정

# 2. QueryDSL 설정

## 1) 개발 환경

>- 자바 모듈 버전 : OpenJDK 21
>- 프레임워크 : Spring Boot 3.4
>- 빌드 환경 : Gradle
>- 주요 의존성 : Spring Data JPA
>- RDBMS : MySQL
>- IDE : 인텔리제이 얼티메이트 에디션

## 2) build.gradle 설정

### (1) QueryDSL 버전 변수 세팅

```groovy
buildscript {
    ext {
        queryDslVersion = "5.0.0"
    }
}
```

### (2) QueryDSL 의존성 추가

```groovy
dependencies {
    // querydsl 추가(스프링부트 3 이상에서의 QueryDSL 패키지 정의)
    implementation "com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta"
    annotationProcessor "com.querydsl:querydsl-apt:${queryDslVersion}:jakarta"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"
}
```

스프링부트 3 이상에서는 `jakarta` 패키지를 사용한다. 또한, 어노테이션 프로세싱 툴(APT)를 통해 Q클래스를 생성해야 한다.

### (3) QueryDSL 소스 코드 경로 세팅

```groovy
def querydslDir = "$buildDir/generated/querydsl"

sourceSets {
    main.java.srcDirs += [ querydslDir ]
}

tasks.withType(JavaCompile) {
    options.annotationProcessorGeneratedSourcesDirectory = file(querydslDir)
}

clean.doLast {
    file(querydslDir).deleteDir()
}
```

Q클래스 생성 경로를 `querydslDir` 변수에 담는다. 이때 그 경로는 `build/generated/querydsl`이다. 또한 Q클래스를 포함한 코드 생성 경로룰 소스 경로에 추가하고(`sourceSets` 블록) 어노테이션 프로세서가 생성한 코드를 `querydslDir`에 저장한다(`JavaCompile` 태스크). 마지막으로 `gradle clean` 실행 시, 생성된 Q클래스를 전부 제거한다.

### (4) 설정 완료 후 작동 순서

>1. `gradle build` 실행
>2. `annotationProcessor`가 엔티티 기반으로 **Q클래스**를 생성
>3. `build/generated/querydsl` 경로에 Q클래스 파일이 생성
>4. `sourceSets`에 해당 경로를 포함시켜 컴파일 시점에 QueryDSL을 사용 가능

## 3) 기타 추가 설정

### (1) 인텔리제이 어노테이션 프로세서 세팅

<img width="80%" alt="인텔리제이어노테이션프로세서세팅" src="https://github.com/user-attachments/assets/3cdb737a-eeab-4fd5-bb24-99054ef99dd3" />

이 설정은 **어노테이션 프로세서**를 Gradle 자체가 아닌 **인텔리제이 자체 빌드 시스템에서 활성화**하도록 설정한 것이다.

### (2) Gradle 기반 자바 컴파일

<img width="35%" alt="gradleQ클래스생성" src="https://github.com/user-attachments/assets/944d88d2-1bd0-4bcf-b782-d4f750b2ead7" />

`querydsl-apt`를 Gradle에서 직접 사용해서 지정했던 경로에 Q클래스를 생성한다. 새로운 엔티티 클래스가 추가될 때마다 빌드를 해주면 자동으로 경로에 맞춰 Q클래스가 추가된다.

### (3) build 패키지 내부 확인

<img width="35%" alt="Q클래스생성확인" src="https://github.com/user-attachments/assets/78be7bd9-0817-4b65-afc4-25951cb67236" />

`generated/querydsl/com/example/엔티티 패키지들` 하위에 엔티티 클래스를 기반으로 Q클래스가 생성된 것을 확인할 수 있다.

## 4) 테스트 생성 및 수정 확인

### (1) 큐클래스 생성 테스트

임시 엔티티 클래스를 생성해서, 터미널에서 `./gradlew compileJava` 명령어를 실행해서 `build` 패키지의 해당 경로에서 큐클래스가 생성되는지 확인한다.

```java
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
```

<img width="80%" alt="터미널 :gradlew compileJava기반생성" src="https://github.com/user-attachments/assets/2c09722d-d98b-4110-bef2-98ebb9f19900" />

<img width="80%" alt="큐클래스확인" src="https://github.com/user-attachments/assets/6a412f3e-5b2a-45a3-9af5-2dcadccd570d" />

### (2) 큐클래스 수정 테스트

엔티티 클래스를 수정한 경우, `./gradlew clean compileJava` 명령어를 기반으로 큐클래스를 전부 지우고 다시 컴파일을 수행한다.

```java
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;
    
    @Column(name = "content")
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
```

<img width="80%" alt="터미널기반클린컴파일자바" src="https://github.com/user-attachments/assets/8ac083e4-34da-4324-a7c4-6a9852bb1d04" />

<img width="80%" alt="큐클래스수정" src="https://github.com/user-attachments/assets/d5d74aca-4984-405a-8aba-fd900f35a3d1" />

### (3) 실행 테스트

실행 결과, `genrated` 패키지에 엔티티 패키지 구조대로 큐클래스들이 생성\
다만 거기서 `java.lang.Exception: exception just for purpose of providing stack trace` 예외가 발생\
이것에 대한 해결책은 https://mirr-coding.tistory.com/66 링크 참조

추가로 `src`의 `genrated` 패키지에 큐클래스가 생성됐다고 해서 달라지는 것은 없음\
엔티티 클래스의 수정이 발생하면 `./gradlew clean compileJava` 후, 재실행하면 그것에 맞춰 큐클래스도 수정

# 3. QueryDSL 예제 연습