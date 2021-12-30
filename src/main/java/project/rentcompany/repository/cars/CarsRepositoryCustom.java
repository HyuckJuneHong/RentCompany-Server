package project.rentcompany.repository.cars;

import project.rentcompany.domain.entity.cars.Cars;

import java.util.List;

public interface CarsRepositoryCustom {

    List<Cars> fetchBySearch(String search);
}
