package project.rentcompany.repository.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.boards.Boards;

import java.util.List;

public interface BoardsRepository extends JpaRepository<Boards, Long>, BoardsRepositoryCustom{

    Boards findByBno(Long bno);

    List<Boards> findByTitleLike(String title);
    List<Boards> findByContentLike(String content);
    List<Boards> findByUsers_IdentityLike(String identity);
    List<Boards> findByUsers_NameLike(String name);
}
