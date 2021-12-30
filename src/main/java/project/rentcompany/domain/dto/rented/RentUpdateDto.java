package project.rentcompany.domain.dto.rented;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentUpdateDto {

    private Long rno;
    private LocalDate startDesired;    //yyyy-MM-dd
    private LocalDate endDesired;
}
