# Springboot-REST-API
[springboot 기반 REST API 개발](https://www.inflearn.com/course/spring_rest-api/) 강의를 보며 공부하는 repo

이 강좌에서는 다음의 다양한 스프링 기술을 사용하여 REST API를 개발합니다.

* 스프링 프레임워크
* 스프링 부트
* 스프링 데이터 JPA
* 스프링 HATEOAS
* 스프링 REST Docs
* 스프링 시큐리티 OAuth2

테스트 주도 개발(TDD)로 진행합니다.

<hr>

## REST API

REST API라고 부를 수 있으려면 

Self-Descriptive Message와 HATEOAS(Hypermedia as the engine of application state)를 만족해야 한다.
* Self-Descriptive 해결법
    * 미디어 타입을 정의하고, IANA에 등록하고 그 미디어타입을 리소스 리턴할 때 Content-Type으로 사용한다
    * profile 링크 헤더를 추가한다 
        * 브라우저들이 아직 스팩지원을 잘 안한다
        * 대안으로 HAL의 링크데이터에 profile링크 추가

* HATEOAS 해결방법
    * 데이터에 링크 제공 => HAL 사용
    * 링크 헤더나 Location을 제공

* HAL 이란
Hypertext Application Language

## 스프링부트 프로젝트

추가한 의존성 

* Web
* JPA
* HATEOAS
* REST Docs
* H2
* PostgrsSQL
* Lombok

1. 스프링부트 핵심 원리  
* 의존성 설정  
* 자동설정(@EnableAutoConfiguration)  
* 내장 웹 서버  
* 독립적으로 실행가능한 JAR  

2. Event 생성 API 구현 

    2-1. Event 도메인 구현
    * Lombok annotation은 Meta annotation에서 동작하지 않는다 => Custom annotation에 Lombok annotation을 사용하는 것은 의미가 없음
    * @EqualsAndHashCode
        * of 값으로 앤티티간의 연관관계(상호 참조하는)것은 추가하면 안됨
        * @EqualsAndHashCode로 구현한 코드 안에서 스택오버플로우가 발생할 수 있음
        * @Data 가 포함하는 어노테이션엔 EqualsAndHashCode 가 있는데 모든 엔티티를 다 써서 구현하기 때문에 순환참조 문제로 스택오버플로우 발생할 수 있음. 따라서 엔티티 위에 @Data는 사용하지 않는게 좋음

    2-2. 비즈니스 로직
    
