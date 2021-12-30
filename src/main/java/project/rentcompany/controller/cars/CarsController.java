package project.rentcompany.controller.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.cars.CarAddDto;
import project.rentcompany.domain.dto.cars.CarGetDto;
import project.rentcompany.domain.service.cars.CarAddService;
import project.rentcompany.domain.service.cars.CarDeleteService;
import project.rentcompany.domain.service.cars.CarGetService;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@RestController
@RequestMapping("/rentCompany")
@RequiredArgsConstructor
public class CarsController {

    private final CarAddService carAddService;
    private final CarDeleteService carDeleteService;
    private final CarGetService carGetService;

    //자동차 추가
    @PostMapping("/admin/car/add")
    public ResponseFormat carAdd(@RequestBody CarAddDto carAddDto){
        return carAddService.carAdd(carAddDto);
    }

    @PostMapping("/admin/car/add/check")
    public ResponseFormat carNumberCheck(@RequestParam("carNumber") String carNumber){
        return carAddService.carCheck(carNumber);
    }

    //자동차 삭제
    @DeleteMapping("/admin/car/delete")
    public ResponseFormat carDelete(@RequestParam("carNumber") String carNumber){
        return carDeleteService.carDelete(carNumber);
    }

    //모든 자동차 가져오기
    @GetMapping("/cars")
    public ResponseEntity<List<CarGetDto>> carGetList(){
        return ResponseEntity.ok().body(carGetService.carGetList());
    }

    //검색 자동차 가져오기
    @GetMapping("/cars/search")
    public ResponseEntity<List<CarGetDto>> carSearchList(@RequestParam("search") String search){
        return ResponseEntity.ok().body(carGetService.carSearchGet(search));
    }

    //해당 넘버 자동차 가져오기
    @GetMapping("/cars/carNumber")
    public ResponseEntity<ResponseFormat<CarGetDto>> carGet(@RequestParam("carNumber") String carNumber){
        return ResponseEntity.ok().body(carGetService.carGet(carNumber));
    }

}
