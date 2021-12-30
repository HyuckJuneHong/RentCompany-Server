package project.rentcompany.domain.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.users.UsersGetDto;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersGetService {

    private final UsersRepository usersRepository;

    /**
     * 회원 정보 관람
     * @param identity : 보고싶은 회원 정보
     * @return UsersGetDto 데이터
     */
    @Transactional(readOnly = true) //오직 읽기만 가능.
    public ResponseFormat<UsersGetDto> userGet(String identity){

        Users user = usersRepository.findByIdentity(identity);

        if(user == null){
            return ResponseFormat.fail("해당 정보는 없습니다.");
        }

        UsersGetDto usersGetBuild = UsersGetDto.builder()
                .identity(identity)
                .name(user.getName())
                .birth(user.getBirth())
                .gander(user.getGander())
                .cellPhone(user.getCellPhone())
                .address(user.getAddress())
                .build();

        return ResponseFormat.ok(usersGetBuild);
    }
}
