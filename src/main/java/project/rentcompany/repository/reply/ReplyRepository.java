package project.rentcompany.repository.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.reply.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Reply findByPnoAndBoards(Long pno, Boards boards);

    List<Reply> findByBoards(Boards boards);
}
