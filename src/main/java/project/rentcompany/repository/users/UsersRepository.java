package project.rentcompany.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.users.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByIdentity(String identity);      //해당 아이디 찾기
    Boolean existsByIdentity(String identity);  //아이디 중복 여부
    Boolean existsByName(String name);  //아이디 중복 여부
    Boolean existsByCellPhone(String cellPhone);  //아이디 중복 여부

}
