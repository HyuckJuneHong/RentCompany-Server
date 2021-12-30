package project.rentcompany.domain.entity.driveLicense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.rentcompany.domain.entity.users.Users;

import javax.persistence.*;

@Getter
@Table(name = "tbl_driveLicense")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DriveLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno;

    @Column(unique = true)
    private String licenseNumber;   //면허 번호(11-11-111111-11)
    private String type;            //1종, 2종
    private String update_term;     //갱신 날짜

    @OneToOne
    @JoinColumn(name = "uno")
    private Users users;

    @Builder
    public DriveLicense(String licenseNumber, String type, String update_term, Users users) {
        this.licenseNumber = licenseNumber;
        this.type = type;
        this.update_term = update_term;
        this.users = users;
    }
}
