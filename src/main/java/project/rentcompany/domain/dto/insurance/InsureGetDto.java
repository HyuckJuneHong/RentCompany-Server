package project.rentcompany.domain.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InsureGetDto {

    private String type;
    private Long price;
    private Long selfPay;
}
