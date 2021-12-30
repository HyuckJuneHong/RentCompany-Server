package project.rentcompany.domain.dto.insurance;

import lombok.Getter;

@Getter
public class InsureUpdateDto {

    private String type;
    private Long price;
    private Long selfPay;

}
