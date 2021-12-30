package project.rentcompany.domain.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.users.UsersRepository;

import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersDeleteService {

    private final UsersRepository usersRepository;

    /**
     * 회원 삭제
     * @param identity: 삭제할 회원 아이디
     * @return 성공: ok(1, true, null)
     */
    public ResponseFormat UserDelete(String identity){

        Users user = usersRepository.findByIdentity(identity);

        if(user == null){
            return ResponseFormat.fail("해당 아이디는 존재하지 않습니다.");
        }

        usersRepository.delete(user);

        return ResponseFormat.ok();
    }
}
