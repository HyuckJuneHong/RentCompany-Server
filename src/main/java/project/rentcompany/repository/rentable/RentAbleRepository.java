package project.rentcompany.repository.rentable;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.domain.entity.rentable.RentAble;

import java.util.List;

public interface RentAbleRepository extends JpaRepository<RentAble, Long>, RentAbleRepositoryCustom {

    Boolean existsByCars(Cars cars);

}
