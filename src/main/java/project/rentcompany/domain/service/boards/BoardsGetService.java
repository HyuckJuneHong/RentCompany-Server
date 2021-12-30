package project.rentcompany.domain.service.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.boards.BoardsGetDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class BoardsGetService {

    private final BoardsRepository boardsRepository;

    public ResponseFormat<BoardsGetDto> boardsGet(Long bno){

        Boards boards = boardsRepository.findByBno(bno);

        if(boards == null){
            return ResponseFormat.fail("해당 게시물은 존재하지 않습니다.");
        }

        boards.viewsIncrease();
        boardsRepository.save(boards);

        BoardsGetDto boardsGetDto = BoardsGetDto.builder()
                .title(boards.getTitle())
                .content(boards.getContent())
                .localDate(boards.getCreateDate())
                .views(boards.getViews())
                .name(boards.getUsers().getName())
                .build();

        return ResponseFormat.ok(boardsGetDto);
    }

}
