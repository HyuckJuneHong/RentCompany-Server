package project.rentcompany.domain.dto.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UsersGetDto {

    private String identity;
    private String name;
    private String birth;
    private String gander;
    private String cellPhone;
    private String address;
}
