package project.rentcompany.domain.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.users.LoginCheckDto;
import project.rentcompany.domain.dto.users.SignInDto;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final UsersRepository usersRepository;

    /**
     *
     * @param signInDto
     * @return 성공: code=1, check=true / 실패: code=2, check=false, description: 해당 문자열.
     */
    public ResponseFormat signIn(SignInDto signInDto){

        Users users = usersRepository.findByIdentity(signInDto.getIdentity());

        if(users == null) {
            return ResponseFormat.fail("회원 정보가 없습니다.");
        }
        if(!users.getPassword().equals(signInDto.getPassword())){
           return ResponseFormat.fail("비밀번호가 일치하지 않습니다.");
        }

        return ResponseFormat.ok();
    }


    /**
     * 로그인이 되어있는지 체크
     * @param loginCheckDto: 가지고 다닐 아이디 정보
     * @return 성공: code=1, check=true / 실패: code=2, check=false, description=해당 문자열
     */
    public ResponseFormat loginCheck(LoginCheckDto loginCheckDto){
        if(loginCheckDto.getIdentity() == null){
            return ResponseFormat.fail("로그인을 해야합니다.");
        }
        return ResponseFormat.ok();
    }

}
