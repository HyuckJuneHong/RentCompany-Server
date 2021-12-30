package project.rentcompany.controller.rentable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.rentable.AbleAddDto;
import project.rentcompany.domain.dto.rentable.AbleGetDto;
import project.rentcompany.domain.service.rentable.AbleAddService;
import project.rentcompany.domain.service.rentable.AbleGetService;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@RestController
@RequestMapping("/rentCompany/rentAble")
@RequiredArgsConstructor
public class RentAbleController {

    private final AbleAddService ableAddService;
    private final AbleGetService ableGetService;

    //렌트 가능한 자동차 생성
    @PostMapping
    public ResponseFormat ableAdd(@RequestBody AbleAddDto ableAddDto){
        return ableAddService.ableAdd(ableAddDto);
    }

    //렌트 가능한 자동차 반환
    @GetMapping("/get")
    public ResponseEntity<ResponseFormat<List<AbleGetDto>>> ableGet(){
        return ResponseEntity.ok().body(ableGetService.ableGet());
    }

    @GetMapping("/get/search")
    public ResponseEntity<ResponseFormat<List<AbleGetDto>>> ableSearchList(@RequestParam("search") String search){
        return ResponseEntity.ok().body(ableGetService.ableSearchGet(search));
    }
}
