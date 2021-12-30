package project.rentcompany.controller.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.boards.BoardsDeleteDto;
import project.rentcompany.domain.dto.boards.BoardsGetDto;
import project.rentcompany.domain.dto.boards.BoardsUpdateDto;
import project.rentcompany.domain.dto.boards.BoardsWriteDto;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.service.boards.*;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentCompany/boards")
public class BoardsController {

    private final BoardWriteService boardWriteService;
    private final BoardsGetService boardsGetService;
    private final BoardsSearchService boardsSearchService;
    private final BoardsUpdateService boardsUpdateService;
    private final BoardsDeleteService boardsDeleteService;

    //게시글 작성
    @PostMapping("/write")
    public ResponseFormat boardWrite(@RequestBody BoardsWriteDto boardsWriteDto){
        return boardWriteService.boardWrite(boardsWriteDto);
    }

    //게시글 읽기
    @GetMapping("get")
    public ResponseEntity<ResponseFormat<BoardsGetDto>> boardGet(@RequestParam("bno") Long bno){
        return ResponseEntity.ok().body(boardsGetService.boardsGet(bno));
    }

    //검색어를 포함하는 게시글들 가져오기
    @GetMapping
    public ResponseEntity<List<Boards>> boardsSearch(@RequestParam("search") String search){
        return ResponseEntity.ok().body(boardsSearchService.boardsSearch(search));
    }

    //해당 게시글 수정
    @PutMapping("/update")
    public ResponseFormat boardsUpdate(@RequestBody BoardsUpdateDto boardsUpdateDto){
        return boardsUpdateService.boardsUpdate(boardsUpdateDto);
    }

    //해당 게시글 삭제
    @DeleteMapping("/update/delete")
    public ResponseFormat boardsDelete(@RequestBody BoardsDeleteDto boardsDeleteDto){
        return boardsDeleteService.boardsDelete(boardsDeleteDto);
    }
}
