package project.rentcompany.domain.service.users;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.users.UsersUpdateDto;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersUpdateService {

    private final UsersRepository usersRepository;

    /**
     * 회원 정보 변경
     * @param usersUpdateDto: 변경할 회원 정보 데이터
     * @return 성공: ok(1, true ,null) / 실패: fail(2, false, description)
     */
    public ResponseFormat userUpdate(UsersUpdateDto usersUpdateDto) {

        if(usersUpdateDto.getIdentity() == null || usersUpdateDto.getPassword() == null ||
                usersUpdateDto.getCellPhone() == null || usersUpdateDto.getName() == null ||
                usersUpdateDto.getAddress() == null || usersUpdateDto.getBirth() == null ||
                usersUpdateDto.getGander() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않으셨습니다.");
        }

        Users user = usersRepository.findByIdentity(usersUpdateDto.getIdentity());

        if(user == null){
            return ResponseFormat.fail("해당 아이디는 존재하지 않습니다.");
        }
        if(!usersUpdateDto.getPassword().equals(user.getPassword())){
            return ResponseFormat.fail("비밀번호가 일치하지 않습니다.");
        }

        Boolean isExistName = usersRepository.existsByName(usersUpdateDto.getName());
        Boolean isExistCellPhone = usersRepository.existsByCellPhone(usersUpdateDto.getCellPhone());

        if (isExistCellPhone && !usersUpdateDto.getCellPhone().equals(user.getCellPhone())) {
            return ResponseFormat.fail("이미 등록된 전화번호입니다.");
        }
        if(isExistName && !usersUpdateDto.getName().equals(user.getName())){
            return ResponseFormat.fail("이미 등록된 닉네임입니다.");
        }

        user.userUpdate(usersUpdateDto.getName(), usersUpdateDto.getBirth(),
                usersUpdateDto.getGander(), usersUpdateDto.getCellPhone(), usersUpdateDto.getAddress());

        usersRepository.save(user);

        return ResponseFormat.ok();
    }
}
