package project.rentcompany.domain.dto.users;


import lombok.Getter;

@Getter
public class passwordUpdateDto {

    private String identity;
    private String password;
    private String newPassword;
    private String checkPassword;
}
