package project.rentcompany.repository.rentable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import project.rentcompany.domain.entity.cars.QCars;
import project.rentcompany.domain.entity.rentable.QRentAble;
import project.rentcompany.domain.entity.rentable.RentAble;

import java.util.List;

@Component
public class RentAbleRepositoryCustomImpl extends QuerydslRepositorySupport implements RentAbleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QRentAble qRentAble = QRentAble.rentAble;
    private final QCars qCars = QCars.cars;

    public RentAbleRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory){
        super(RentAble.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<RentAble> fetchBySearch(String search){
        return setFetchJoinQuery()
                .where(searchCheck(search))
                .fetch();

    }

    public BooleanExpression searchCheck(String search){
        return (this.qRentAble.cars.carKinds.contains(search)
                .or(this.qRentAble.cars.carName.contains(search))
                .or(this.qRentAble.cars.optionType.contains(search))
                .or(this.qRentAble.cars.energyType.contains(search)));
    }

    public JPAQuery<RentAble> setFetchJoinQuery(){
        return jpaQueryFactory
                .selectFrom(this.qRentAble)
                .innerJoin(this.qRentAble.cars, qCars)
                .fetchJoin();
    }
}
