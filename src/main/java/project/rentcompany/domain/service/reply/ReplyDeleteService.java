package project.rentcompany.domain.service.reply;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.reply.ReplyDeleteDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.reply.Reply;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.repository.reply.ReplyRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyDeleteService {

    private final ReplyRepository replyRepository;
    private final UsersRepository usersRepository;
    private final BoardsRepository boardsRepository;

    /**
     * 해당 댓글 삭제
     * @param replyDeleteDto: 댓글 데이터
     * @return success(code=1, check=true)
     */
    public ResponseFormat replyDelete(ReplyDeleteDto replyDeleteDto){

        Users users = usersRepository.findByIdentity(replyDeleteDto.getIdentity());
        Boards boards = boardsRepository.findByBno(replyDeleteDto.getBno());
        Reply reply = replyRepository.findByPnoAndBoards(replyDeleteDto.getPno(), boards);

        if(boards==null){
            return ResponseFormat.fail("해당 게시글은 존재하지 않습니다.");
        }
        if(reply == null){
            return ResponseFormat.fail("해당 댓글은 존재하지 않습니다.");
        }
        if(users == null){
            return ResponseFormat.fail("로그인을 하셔야만 댓글을 삭제할 수 있습니다.");
        }

        if(!reply.getUsers().getIdentity().equals(replyDeleteDto.getIdentity())){
            return ResponseFormat.fail("자신의 댓글만 삭제할 수 있습니다.");
        }

        replyRepository.delete(reply);

        return ResponseFormat.ok();
    }
}
