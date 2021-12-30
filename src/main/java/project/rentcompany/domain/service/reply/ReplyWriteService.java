package project.rentcompany.domain.service.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.reply.ReplyWriteDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.reply.Reply;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.repository.reply.ReplyRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class ReplyWriteService {

    private final ReplyRepository replyRepository;
    private final BoardsRepository boardsRepository;
    private final UsersRepository usersRepository;

    /**
     * 댓글 쓰기
     * @param replyWriteDto: 댓글 데이터
     * @return: success(code=1, check=true) fail(code=2, check=false, description=String)
     */
    public ResponseFormat replyWrite(ReplyWriteDto replyWriteDto){

        if(replyWriteDto.getContent() == null ){
            return ResponseFormat.fail("댓글을 작성하지 않으셨습니다.");
        }

        Boards boards = boardsRepository.findByBno(replyWriteDto.getBno());
        Users users = usersRepository.findByIdentity(replyWriteDto.getIdentity());

        if(boards == null){
            return ResponseFormat.fail("해당 게시글은 존재하지 않습니다.");
        }
        if(users == null){
            return ResponseFormat.fail("로그인을 하셔야만 댓글을 달 수 있습니다.");
        }

        Reply reply = Reply.builder()
                .content(replyWriteDto.getContent())
                .boards(boards)
                .users(users)
                .build();

        replyRepository.save(reply);

        return ResponseFormat.ok();
    }
}
