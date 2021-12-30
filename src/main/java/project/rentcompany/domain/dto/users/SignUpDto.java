package project.rentcompany.domain.dto.users;


import lombok.Getter;

@Getter
public class SignUpDto {

    private String identity;
    private String password;
    private String name;
    private String birth;
    private String gander;
    private String cellPhone;
    private String address;
}
