package project.rentcompany.repository.rented;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.domain.entity.users.Users;

import javax.swing.text.StyledEditorKit;
import java.util.List;

//RentedRepositoryCustom을 상속받지 않으면, 일일이 다 의존성을 연결해줘야하는 번거로움 때문에, 다중 상속을 받음.
public interface RentedRepository extends JpaRepository<Rented, Long>, RentedRepositoryCustom{

    List<Rented> findByUsers(Users users);
    Rented findByRno(Long rno);

}
