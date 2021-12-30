package project.rentcompany.domain.service.users;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.users.passwordUpdateDto;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordUpdateService {

    private final UsersRepository usersRepository;

    /**
     * 패스워드 변경
     * @param usersPasswordDto: 패스워드 변경에 대한 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat passwordUpdate(passwordUpdateDto usersPasswordDto){

        Users users = usersRepository.findByIdentity(usersPasswordDto.getIdentity());

        if(users == null){
            return ResponseFormat.fail("회원이 존재하지 않습니다.");
        }
        if(!users.getPassword().equals(usersPasswordDto.getPassword())){
            return ResponseFormat.fail("비밀번호가 일치하지 않습니다.");
        }
        if(users.getPassword().equals(usersPasswordDto.getNewPassword())){
            return ResponseFormat.fail("기존 비밀번호와 새로운 비밀번호가 일치합니다.");
        }
        if(!usersPasswordDto.getNewPassword().equals(usersPasswordDto.getCheckPassword())){
            return ResponseFormat.fail("새로운 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        users.passwordUpdate(usersPasswordDto.getNewPassword());
        usersRepository.save(users);

        return ResponseFormat.ok();
    }


}
