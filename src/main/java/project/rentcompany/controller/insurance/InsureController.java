package project.rentcompany.controller.insurance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.insurance.InsureCreateDto;
import project.rentcompany.domain.dto.insurance.InsureGetDto;
import project.rentcompany.domain.dto.insurance.InsureUpdateDto;
import project.rentcompany.domain.service.insurance.InsureCreateService;
import project.rentcompany.domain.service.insurance.InsureDeleteService;
import project.rentcompany.domain.service.insurance.InsureGetService;
import project.rentcompany.domain.service.insurance.InsureUpdateService;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@RestController
@RequestMapping("/rentCompany")
@RequiredArgsConstructor
public class InsureController {

    private final InsureCreateService insureCreateService;
    private final InsureGetService insureGetService;
    private final InsureUpdateService insureUpdateService;
    private final InsureDeleteService insureDeleteService;

    //보험 생성
    @PostMapping("/admin/insure/create")
    public ResponseFormat insureCreate(@RequestBody InsureCreateDto insureCreateDto){
        return insureCreateService.insureCreate(insureCreateDto);
    }

    //보험하나에 대한 정보 반환
    @GetMapping("/insure/get")
    public ResponseEntity<ResponseFormat<InsureGetDto>> insureGet(@RequestParam("type") String type){
        return ResponseEntity.ok().body(insureGetService.insureGet(type));
    }
    //보험 리스트 정보 반환
    @GetMapping("/insure")
    public ResponseEntity<List<InsureGetDto>> insureGetList(){
        return ResponseEntity.ok().body(insureGetService.insureGetList());
    }

    //보험 수정
    @PutMapping("/admin/insure/update")
    public ResponseFormat insureUpdate(@RequestBody InsureUpdateDto insureUpdateDto){
        return insureUpdateService.insureUpdate(insureUpdateDto);
    }

    @DeleteMapping("/admin/insure/update/delete")
    public ResponseFormat insureDelete(@RequestParam("type") String type){
        return insureDeleteService.insureDelete(type);
    }
}
