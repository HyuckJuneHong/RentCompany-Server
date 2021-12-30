package project.rentcompany.domain.service.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.boards.BoardsWriteDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BoardWriteService {

    private final BoardsRepository boardsRepository;
    private final UsersRepository usersRepository;

    /**
     * 게시글 작성
     * @param boardWriteDto(작성 데이터)
     * @return 성공: code=1, check=true / 실패: code=2, check=false, description=해당 문자열
     */
    public ResponseFormat boardWrite(BoardsWriteDto boardWriteDto){

        if(boardWriteDto.getIdentity() == null){
            return ResponseFormat.fail("로그인을 해야만 게시글 작성이 가능합니다.");
        }

        if(boardWriteDto.getTitle() == null){
            return ResponseFormat.fail("게시글 제목은 필수 입력 조건입니다.");
        }

        Users users = usersRepository.findByIdentity(boardWriteDto.getIdentity());

        if(users == null){
            return ResponseFormat.fail("해당 회원은 존재하지 않습니다.");
        }

        Boards boards = Boards.builder()
                .content(boardWriteDto.getContent())
                .title(boardWriteDto.getTitle())
                .views(0L)
                .createDate(LocalDate.now())
                .users(users)
                .build();

        boardsRepository.save(boards);

        return ResponseFormat.ok();
    }

}
