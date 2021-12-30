package project.rentcompany.domain.entity.cars;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tbl_cars")
@NoArgsConstructor
@AllArgsConstructor
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    @Column(unique = true)
    private String carNumber;

    private String carName;
    private String carKinds;    //소형, 중형, 대형
    private String energyType;  //휘발유, 경유, 전기
    private String optionType;  //블랙박스&후방카메라 등등
    private Long dayPrice;   //일당 가격

    @Builder
    public Cars(String carNumber, String carName, String carKinds,
                String energyType, String optionType, Long dayPrice) {

        this.carNumber = carNumber;
        this.carName = carName;
        this.carKinds = carKinds;
        this.energyType = energyType;
        this.optionType = optionType;
        this.dayPrice = dayPrice;
    }
}
