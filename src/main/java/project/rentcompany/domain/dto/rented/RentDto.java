package project.rentcompany.domain.dto.rented;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private String identity;
    private Long cno;
    private String type;
}
