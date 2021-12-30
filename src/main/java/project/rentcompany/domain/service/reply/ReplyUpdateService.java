package project.rentcompany.domain.service.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.reply.ReplyUpdateDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.reply.Reply;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.repository.reply.ReplyRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
//@Transactional : 트랜잭션 말그대로 컴퓨터가 스스로 찾아서 저장해서 save를 안해줘도 컴퓨터가 스스로 찾아 데이터 저장 및 변경 (덜 안전)
public class ReplyUpdateService {

    private final ReplyRepository replyRepository;
    private final BoardsRepository boardsRepository;
    private final UsersRepository usersRepository;

    /**
     * 댓글 수정
     * @param replyUpdateDto: 댓글 데이터
     * @return success(code=1, check=true) fail(code=2, check=false, description="String")
     */
    public ResponseFormat replyUpdate(ReplyUpdateDto replyUpdateDto){

        if(replyUpdateDto.getContent() == null){
            return ResponseFormat.fail("수정할 댓글을 입력하세요.");
        }

        Boards boards = boardsRepository.findByBno(replyUpdateDto.getBno());
        Users users = usersRepository.findByIdentity(replyUpdateDto.getIdentity());

        if(boards == null){
            return ResponseFormat.fail("해당 게시글은 존재하지 않습니다.");
        }
        if(users == null){
            return ResponseFormat.fail("로그인을 하셔야만 댓글을 수정할 수 있습니다.");
        }

        Reply reply = replyRepository.findByPnoAndBoards(replyUpdateDto.getPno(), boards);

        if(reply == null){
            return ResponseFormat.fail("해당 게시글에 이 댓글은 존재하지 않습니다.");
        }
        if(!reply.getUsers().getIdentity().equals(replyUpdateDto.getIdentity())){
            return ResponseFormat.fail("자신의 댓글만 수정할 수 있습니다.");
        }

        reply.replyUpdate(replyUpdateDto.getContent());
        replyRepository.save(reply);

        return ResponseFormat.ok();

    }
}
