package project.rentcompany.controller.rented;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.rented.RentDto;
import project.rentcompany.domain.dto.rented.RentUpdateDto;
import project.rentcompany.domain.dto.rented.RentedGetDto;
import project.rentcompany.domain.service.rented.RentDeleteService;
import project.rentcompany.domain.service.rented.RentGetService;
import project.rentcompany.domain.service.rented.RentService;
import project.rentcompany.domain.service.rented.RentUpdateService;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@RestController
@RequestMapping("/rentCompany")
@RequiredArgsConstructor
public class RentedController {

    private final RentService rentService;
    private final RentGetService rentGetService;
    private final RentUpdateService rentUpdateService;
    private final RentDeleteService rentDeleteService;

    //렌트하기
    @PostMapping("/rentAble/rent")
    public ResponseFormat rent(@RequestBody RentDto rentDto){
        return rentService.rent(rentDto);
    }

    //유저가 렌트한 렌트카 가져오기
    @PostMapping("/rented/get")
    public ResponseEntity<ResponseFormat<List<RentedGetDto>>> rentedList(@RequestParam("identity") String identity){
        return ResponseEntity.ok().body(rentGetService.rentedGet(identity));
    }

    //렌트 수정
    @PutMapping("/rented/update")
    public ResponseFormat rentedUpdate(@RequestBody RentUpdateDto rentUpdateDto){
        return rentUpdateService.rentedUpdate(rentUpdateDto);
    }

    //렌트 삭제
    @DeleteMapping("/update/delete")
    public void rentedDelete(){
        rentDeleteService.rentAutoDelete();
    }
}
