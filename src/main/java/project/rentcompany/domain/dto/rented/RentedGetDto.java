package project.rentcompany.domain.dto.rented;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class RentedGetDto {

    private LocalDate startDate;
    private LocalDate endDate;

    private Long totalDate;     //렌트 총 날짜(end-start)
    private Long totalPrice;    //렌트 총 비용(totalDate*cars(dayPrice))

    private String carName;
    private String insureType;


}
