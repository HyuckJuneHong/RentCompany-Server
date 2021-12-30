package project.rentcompany.repository.rented;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import project.rentcompany.domain.entity.cars.QCars;
import project.rentcompany.domain.entity.insurance.QInsurance;
import project.rentcompany.domain.entity.rented.QRented;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.domain.entity.users.QUsers;

import java.time.LocalDate;
import java.util.List;

@Component
public class RentedRepositoryCustomImpl extends QuerydslRepositorySupport implements RentedRepositoryCustom{

    private final JPAQueryFactory jpaQuery;

    //일반 엔티티는 querydsl를 사용할 수 없어, Q도메인을 생성
    private final QRented qRented = QRented.rented;
    private final QUsers qUsers = QUsers.users;
    private final QCars qCars = QCars.cars;
    private final QInsurance qInsurance = QInsurance.insurance;

    //querydsl를 사용하기 위해 꼭 있어야 할 생성자
    public RentedRepositoryCustomImpl(JPAQueryFactory jpaQuery){
        super(Rented.class);
        this.jpaQuery = jpaQuery;
    }

    /**
     * Rented와 연관관계를 가진 테이블 조인
     * @return (이너 조인)
     */
    private JPAQuery<Rented> setFetchJoinQuery() {
        return jpaQuery
                .selectFrom(this.qRented)
                .innerJoin(this.qRented.cars, this.qCars)
                .innerJoin(this.qRented.users, this.qUsers)
                .innerJoin(this.qRented.insurance, this.qInsurance)
                .fetchJoin();
    }


    /**
     * 사용자가 원하는 해당 날짜에 렌트가 가능한 렌트된 자동차들 반환
     * @param startDesired
     * @param endDesired
     * @return
     */
    @Override
    public List<Rented> fetchByBetween(LocalDate startDesired, LocalDate endDesired){
        return setFetchJoinQuery()
                .where(betweenCheck(startDesired, endDesired))
                .fetch();
    }

    //렌트가 되는 자동차 찾기.
    public BooleanExpression betweenCheck(LocalDate startDesired, LocalDate endDesired){
        return (this.qRented.startDate.gt(startDesired).and(this.qRented.startDate.gt(endDesired)))
                .or(this.qRented.endDate.lt(startDesired).and(this.qRented.endDate.lt(endDesired)));
    }

    /**
     * 렌트된 자동차 중 해당 날짜에 렌트가 불가능한 자동차 반환
     * @param startDesired
     * @param endDesired
     * @return
     */
    @Override
    public List<Rented> fetchByBetweenNot(LocalDate startDesired, LocalDate endDesired){
        return setFetchJoinQuery()
                .where(betweenNotCheck(startDesired, endDesired))
                .fetch();
    }

    //렌트가 안되는 자동차 찾기
    public BooleanExpression betweenNotCheck(LocalDate startDesired, LocalDate endDesired){
        return ((this.qRented.startDate.loe(startDesired)
                .and(this.qRented.endDate.goe(startDesired)))
                .or(this.qRented.startDate.loe(endDesired)
                        .and(this.qRented.endDate.goe(endDesired)))
                .or(this.qRented.startDate.gt(startDesired)
                        .and(this.qRented.endDate.lt(endDesired))));
    }

    /**
     * 렌트된 자동차 중 기한이 끝난 자동차들. 반환
     * @return
     */
    @Override
    public List<Rented> fetchByEndDate(){
        return setFetchJoinQuery()
                .where(endDateCheck())
                .fetch();
    }

    public BooleanExpression endDateCheck(){
        return this.qRented.endDate.lt(LocalDate.now());
    }

}
