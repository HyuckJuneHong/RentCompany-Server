package project.rentcompany.repository.cars;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.domain.entity.cars.QCars;

import java.util.List;

@Component
public class CarsRepositoryCustomImpl extends QuerydslRepositorySupport implements CarsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QCars qCars = QCars.cars;

    public CarsRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory){
        super(Cars.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
     * 검색어에 해당하는 모든 자동차 반환
     * @param search: 검색어
     * @return Cars Table
     */
    @Override
    public List<Cars> fetchBySearch(String search){
        return jpaQueryFactory
                .selectFrom(this.qCars)
                .where(searchCheck(search))
                .fetch();
    }

    public BooleanExpression searchCheck(String search){
        return (this.qCars.carKinds.contains(search)
                .or(this.qCars.carName.contains(search))
                .or(this.qCars.energyType.contains(search))
                .or(this.qCars.optionType.contains(search)));
    }

}
