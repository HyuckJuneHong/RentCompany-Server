package project.rentcompany.domain.dto.insurance;

import lombok.Getter;

@Getter
public class InsureCreateDto {

    private String type;    //보험1, 보험2, 보험3... 등
    private Long price;     //5000, 20000, 30000
    private Long selfPay;   //25만, 10만, 5만
}
