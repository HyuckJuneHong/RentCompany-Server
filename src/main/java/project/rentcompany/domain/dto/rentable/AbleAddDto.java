package project.rentcompany.domain.dto.rentable;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AbleAddDto {

    private LocalDate startDesired;    //yyyy-MM-dd
    private LocalDate endDesired;


}

