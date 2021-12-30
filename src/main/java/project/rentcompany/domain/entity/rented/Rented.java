package project.rentcompany.domain.entity.rented;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.domain.entity.users.Users;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "tbl_rented")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rented {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private LocalDate startDate;
    private LocalDate endDate;

    private Long totalDate;     //렌트 총 날짜(end-start)
    private Long totalPrice;    //렌트 총 비용(totalDate*cars(dayPrice))

    @ManyToOne
    @JoinColumn(name="uno")
    private Users users;

    @OneToOne
    @JoinColumn(name="cno")
    private Cars cars;

    @ManyToOne
    @JoinColumn(name="ino")
    private Insurance insurance;

    @Builder
    public Rented(LocalDate startDate, LocalDate endDate, Long totalDate, Long totalPrice,
                  Users users, Cars cars, Insurance insurance) {

        this.startDate = startDate; //yyyy-MM-dd
        this.endDate = endDate;     //yyyy-MM-dd

        this.users = users;
        this.cars = cars;
        this.insurance = insurance;

        this.totalDate = totalDate;
        this.totalPrice = totalPrice;
    }

    public void updateDate(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
