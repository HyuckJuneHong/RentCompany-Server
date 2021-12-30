package project.rentcompany.repository.rented;

import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.domain.entity.rented.Rented;

import java.time.LocalDate;
import java.util.List;

public interface RentedRepositoryCustom {

    List<Rented> fetchByBetween(LocalDate startDesired, LocalDate endDesired);
    List<Rented> fetchByBetweenNot(LocalDate startDesired, LocalDate endDesired);
    List<Rented> fetchByEndDate();

}
