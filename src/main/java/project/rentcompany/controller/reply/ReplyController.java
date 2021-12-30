package project.rentcompany.controller.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.reply.ReplyDeleteDto;
import project.rentcompany.domain.dto.reply.ReplyGetDto;
import project.rentcompany.domain.dto.reply.ReplyUpdateDto;
import project.rentcompany.domain.dto.reply.ReplyWriteDto;
import project.rentcompany.domain.service.reply.ReplyDeleteService;
import project.rentcompany.domain.service.reply.ReplyGetService;
import project.rentcompany.domain.service.reply.ReplyUpdateService;
import project.rentcompany.domain.service.reply.ReplyWriteService;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@RestController
@RequestMapping("/rentCompany/boards/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyWriteService replyWriteService;
    private final ReplyGetService replyGetService;
    private final ReplyUpdateService replyUpdateService;
    private final ReplyDeleteService replyDeleteService;

    //댓글 등록
    @PostMapping
    public ResponseFormat replyWrite(@RequestBody ReplyWriteDto replyWriteDto){
        return replyWriteService.replyWrite(replyWriteDto);
    }

    //댓글 반환
    @GetMapping
    public ResponseEntity<ResponseFormat<List<ReplyGetDto>>> replyGet(@RequestParam("bno") Long bno){
        return ResponseEntity.ok().body(replyGetService.replyGet(bno));
    }

    //댓글 수정
    @PutMapping
    public ResponseFormat replyUpdate(@RequestBody ReplyUpdateDto replyUpdateDto){
        return replyUpdateService.replyUpdate(replyUpdateDto);
    }

    //댓글 삭제
    @PostMapping("/delete")
    public ResponseFormat replyDelete(@RequestBody ReplyDeleteDto replyDeleteDto){
        return replyDeleteService.replyDelete(replyDeleteDto);
    }


}
