package project.rentcompany.domain.entity.insurance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "tbl_insurance")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;   //Long은 null을 사용할 수 있기 때문에 long 보다는 Long을 하는게 더 좋다.

    @Column(unique = true)
    private String type;   //보험1, 보험2, 보험3

    private Long price;   //5000, 20000, 30000
    private Long selfPay; //25만, 10만, 5만

    public void insureUpdate(Long price, Long selfPay){
        this.price = price;
        this.selfPay = selfPay;
    }

    @Builder
    public Insurance(String type, Long price, Long selfPay) {
        this.type = type;
        this.price = price;
        this.selfPay = selfPay;
    }
}
