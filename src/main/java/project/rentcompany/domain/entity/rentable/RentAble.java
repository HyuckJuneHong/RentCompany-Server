package project.rentcompany.domain.entity.rentable;

import lombok.*;
import project.rentcompany.domain.entity.cars.Cars;

import javax.persistence.*;
import java.time.LocalDate;

//AccessLevel.PROTECTED: 기본 생성자의 접근 제어를 PROTECTED로 설정해놓게 되면 무분별한 객체 생성에 대해 한번 더 체크할 수 있는 수단
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_rentAble")
@Entity
@AllArgsConstructor
public class RentAble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;

    private LocalDate startDesired;
    private LocalDate endDesired;

    @OneToOne
    @JoinColumn(name="cno")
    private Cars cars;

    @Builder
    public RentAble(LocalDate startDesired, LocalDate endDesired, Cars cars) {

        this.startDesired = startDesired;
        this.endDesired = endDesired;
        this.cars = cars;
    }
}


