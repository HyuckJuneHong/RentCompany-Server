package project.rentcompany.domain.service.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.boards.BoardsUpdateDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardsUpdateService {

    private final BoardsRepository boardsRepository;

    /**
     * 게시글 수정
     * @param boardsUpdateDto: (수정 데이터)
     * @return success(code=1, check=true) fail(code=2, check=false, description=parameter)
     */
    public ResponseFormat boardsUpdate(BoardsUpdateDto boardsUpdateDto){

        Boards boards = boardsRepository.findByBno(boardsUpdateDto.getBno());

        if(boardsUpdateDto.getIdentity()==null){
            return ResponseFormat.fail("로그인을 하셔야 합니다.");
        }
        if(boards == null){
            return ResponseFormat.fail("해당 게시물은 존재하지 않습니다.");
        }
        if(boards.getUsers().getIdentity().equals(boardsUpdateDto.getIdentity())){
            return ResponseFormat.fail("게시글 본인만 게시글 수정이 가능합니다.");
        }

        boards.boardsUpdate(boardsUpdateDto.getTitle(), boardsUpdateDto.getContent());

        boardsRepository.save(boards);

        return ResponseFormat.ok();
    }
}
