package project.rentcompany.domain.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UsersUpdateDto {
    private String identity;

    @NotNull(message="비밀번호를 입력해주세요.")
    private String password;

    private String name;
    private String birth;
    private String gander;
    private String cellPhone;
    private String address;
}
