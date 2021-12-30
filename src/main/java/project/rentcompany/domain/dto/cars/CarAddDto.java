package project.rentcompany.domain.dto.cars;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class CarAddDto {

    private String carNumber;
    private String carName;
    private String carKinds;    //소형, 중형, 대형
    private String energyType;  //휘발유, 경유, 전기
    private String optionType;  //블랙박스&후방카메라 등등
    private Long dayPrice;      //일당 가격
}
