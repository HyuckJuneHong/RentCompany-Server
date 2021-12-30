package project.rentcompany.domain.service.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.boards.BoardsDeleteDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardsDeleteService {

    private final BoardsRepository boardsRepository;

    /**
     * 게시글 삭제
     * @param boardsDeleteDto: 게시글 삭제 데이터
     * @return success(code=1, check=true) / fail(code=2, check=false, description=parameter)
     */
    public ResponseFormat boardsDelete(BoardsDeleteDto boardsDeleteDto){

        Boards boards = boardsRepository.findByBno(boardsDeleteDto.getBno());

        if(boardsDeleteDto.getIdentity() == null){
            return ResponseFormat.fail("로그인을 하셔야합니다.");
        }
        if(boards == null){
            return ResponseFormat.fail("해당 게시글은 존재하지 않습니다.");
        }
        if(boards.getUsers().getIdentity().equals(boardsDeleteDto.getIdentity())){
            return ResponseFormat.fail("게시글 본인만 게시글을 삭제할 수 있습니다.");
        }

        boardsRepository.delete(boards);

        return ResponseFormat.ok();
    }
}
