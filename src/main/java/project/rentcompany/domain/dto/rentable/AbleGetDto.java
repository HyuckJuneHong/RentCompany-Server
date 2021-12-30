package project.rentcompany.domain.dto.rentable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import project.rentcompany.domain.entity.cars.Cars;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class AbleGetDto {

    private LocalDate startDesired;
    private LocalDate endDesired;
    private Cars cars;

}
