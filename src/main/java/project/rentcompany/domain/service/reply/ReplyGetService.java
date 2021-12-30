package project.rentcompany.domain.service.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.reply.ReplyGetDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.reply.Reply;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.repository.reply.ReplyRepository;
import project.rentcompany.utils.ResponseFormat;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyGetService {

    private final ReplyRepository replyRepository;
    private final BoardsRepository boardsRepository;

    /**
     * 댓글 반환
     * @param bno: 해당 게시글 댓글
     * @return 해당 게시글의 모든 댓글
     */
    @Transactional(readOnly = true)
    public ResponseFormat<List<ReplyGetDto>> replyGet(Long bno){

        Boards boards = boardsRepository.findByBno(bno);

        if(boards == null){
            return ResponseFormat.fail("해당 게시물은 존재하지 않습니다.");
        }
        List<Reply> replyList = replyRepository.findByBoards(boards);
        ArrayList<ReplyGetDto> replyGetDtoArrayList = new ArrayList<>();

        for(Reply reply: replyList){
            ReplyGetDto replyGetDto = ReplyGetDto.builder()
                    .content(reply.getContent())
                    .bno(reply.getBoards().getBno())
                    .name(reply.getUsers().getName())
                    .build();

            replyGetDtoArrayList.add(replyGetDto);
        }

        return ResponseFormat.ok(replyGetDtoArrayList);
    }
}
