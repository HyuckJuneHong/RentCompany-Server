package project.rentcompany.domain.dto.driveLicense;

import lombok.Getter;

@Getter
public class RegisterDto {

    private String identity;

    private String licenseNumber;   //면허 번호(11-11-111111-11)
    private String type;            //1종, 2종
    private String update_term;     //갱신 날짜
}
