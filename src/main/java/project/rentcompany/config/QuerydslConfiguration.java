package project.rentcompany.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//설정값을 모아둔 패키지에 QuerydslConfiguration을 생성
//이 설정으로 이 프로젝트에서는 어느 곳에서나 JPAQueryFactory를 주입 받아 Querydsl을 사용할 수 있게 된다.
@Configuration
public class QuerydslConfiguration {

    @PersistenceContext //의존성 주입 즉, Autowired와 같은 기능인 것 같다.
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}


    /**
     * @PersistenceContext
     *      스프링에서 영속성 관리를 위해 Entity Manager가 존재한다.
     *      그래서 스프링이 처음 시작할 때, entity manager를 만들어서 빈으로 등록을 해둔다.
     *      entity manager는 @Autowired가 아니고 특별하게 @PersistenceContext라는 어노테이션으로 주입을 해줄 수 있다.
     *      근데 최신 스프링부트에서는 @Autowired로도 할 수 있다고 한다.
     */

    /**
     * <빈이란?>
     *  1. POJO(Plain Old Java Object)로써 Spring 애플리케이션을 구성하는 핵심 객체이다.
     *  2. Spring IoC 컨테이너(또는 DI 컨테이너)에 의해 생성 및 관리된다.
     *  3. class, id, scope, constructor-arg 등을 주요 속성으로 지닌다.
     */

    /**
     * @Bean: Bean으로 등록하고자 하는 것에 @Bean 어노테이션을 활용
     *      -클래스들을 xml에 일일이 적어서 등록한다고 하면 상당히 많은 시간을 차지할 것이고,
     *      -생산력 저하를 야기할 것이다. 그렇기 때문에 반드시 어노테이션을 활용한 Bean의 등록을 지향해야 한다.
     *      -어떤 임의의 클래스를 만들어서 @Bean 어노테이션을 붙인다고 되는 것이 아니고,
     *      -@Bean을 사용하는 클래스에는 반드시 @Configuration 어노테이션을 활용하여 해당 클래스에서
     *      -Bean을 등록하고자 함을 명시해주어야 한다
     *      <사용할 때?>
     *          @Bean 어노테이션의 경우 아래와 같은 상황에서 주로 사용한다.
     *          1. 개발자가 직접 제어가 불가능한 라이브러리를 활용할 때
     *          2. 초기에 설정을 하기 위해 활용할 때
     * @Configuration: 어노테이션을 명시하여 해당 클래스에서 1개 이상의 Bean을 생성하고 있음을 명시하고 있다
     *                 직접 개발한 클래스를 Bean으로 등록하고자 하는 경우에는 @Component 어노테이션을 활용하면 된다
     *                  예를 들어 폴더 생성, 파일 저장 등을 처리하기 위해 직접 개발한 FileUtils를 Bean으로 등록하고자 한다면
     *                  @Component를 사용하면 된다.
     *
     * <요약>
     *     [ @Bean, @Configuration ]
     *          - 개발자가 직접 제어가 불가능한 외부 라이브러리 또는 설정을 위한 클래스를 Bean으로 등록할 때 @Bean 어노테이션을 활용
     *          - 1개 이상의 @Bean을 제공하는 클래스의 경우 반드시 @Configuration을 명시해 주어야 함
     *      [ @Component ]
     *          - 개발자가 직접 개발한 클래스를 Bean으로 등록하고자 하는 경우 @Component 어노테이션을 활용
     * </요약>
     */

