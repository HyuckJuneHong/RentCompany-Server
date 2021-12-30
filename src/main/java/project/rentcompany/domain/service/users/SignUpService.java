package project.rentcompany.domain.service.users;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.users.SignUpDto;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UsersRepository usersRepository;

    /**
     * 회원가입
     * @param signUpDto: 회원가입 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat signUp(SignUpDto signUpDto){

        if(signUpDto.getIdentity() == null || signUpDto.getPassword() == null ||
                signUpDto.getCellPhone() == null || signUpDto.getName() == null ||
                signUpDto.getAddress() == null || signUpDto.getBirth() == null ||
                signUpDto.getGander() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않으셨습니다.");
        }

        Boolean isExistIdentity = usersRepository.existsByIdentity(signUpDto.getIdentity());
        Boolean isExistName = usersRepository.existsByName(signUpDto.getName());
        Boolean isExistCellPhone = usersRepository.existsByCellPhone(signUpDto.getCellPhone());

        if(isExistIdentity){
            return ResponseFormat.fail("아이디가 중복되었습니다.");
        }
        if (isExistCellPhone) {
            return ResponseFormat.fail("이미 등록된 전화번호입니다.");
        }
        if(isExistName){
            return ResponseFormat.fail("이미 등록된 닉네임입니다.");
        }

        Users userBuild = Users.builder()
                .identity(signUpDto.getIdentity())
                .password(signUpDto.getPassword())
                .name(signUpDto.getName())
                .birth(signUpDto.getBirth())
                .gander(signUpDto.getGander())
                .cellPhone(signUpDto.getCellPhone())
                .address(signUpDto.getAddress())
                .build();

        usersRepository.save(userBuild);

        return ResponseFormat.ok();
    }

    /**
     * 아이디 중복체크
     * @param identity: user의 아이디
     * @return (중복X: ok(1, true, null) / 중복O: fail(2, false, description)
     */
    public ResponseFormat checkIdentity(String identity){
        Boolean isExgist = usersRepository.existsByIdentity(identity);

        if(isExgist){
            return ResponseFormat.fail("중복된 아이디가 있습니다.");
        }else{
            return ResponseFormat.ok();
        }
    }

}
