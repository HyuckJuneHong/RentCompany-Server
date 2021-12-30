package project.rentcompany.repository.cars;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.cars.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long>, CarsRepositoryCustom {

    Boolean existsByCarNumber(String carNumber);    //넘버 유무
    Cars findByCarNumber(String carNumber);         //해당 넘버 찾기
    Cars findByCno(Long cno);
}
