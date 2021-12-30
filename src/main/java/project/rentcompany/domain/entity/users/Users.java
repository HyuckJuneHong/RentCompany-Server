package project.rentcompany.domain.entity.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.rentcompany.domain.entity.driveLicense.DriveLicense;

import javax.persistence.*;

@Getter
@Entity
@Table(name="tbl_users")
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;

    @Column(unique = true)
    private String identity;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String cellPhone;

    private String password;
    private String birth;
    private String gander;
    private String address;

    //정보 수정 메소드
    public void userUpdate(String name, String birth, String gander, String cellPhone, String address){

        this.name = name;
        this.birth = birth;
        this.gander = gander;
        this.cellPhone = cellPhone;
        this.address = address;
    }

    //비밀번호 변경 메소드
    public void passwordUpdate(String password){
        this.password = password;
    }

    @Builder
    public Users(String identity, String password, String name, String birth,
                 String gander, String cellPhone, String address) {

        this.identity = identity;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.gander = gander;
        this.cellPhone = cellPhone;
        this.address = address;
    }
}
