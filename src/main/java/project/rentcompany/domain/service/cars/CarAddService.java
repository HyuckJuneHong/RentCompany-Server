package project.rentcompany.domain.service.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.cars.CarAddDto;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.repository.cars.CarsRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class CarAddService{

    private final CarsRepository carsRepository;

    /**
     * 자동차 추가 메소드
     * @param carAddDto: 추가할 자동차 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat carAdd(CarAddDto carAddDto){

        if(carAddDto.getCarNumber() == null || carAddDto.getCarName() == null ||
            carAddDto.getCarKinds() == null || carAddDto.getDayPrice() == null ||
            carAddDto.getEnergyType() == null || carAddDto.getOptionType() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않았습니다.");
        }

        Boolean isExist = carsRepository.existsByCarNumber(carAddDto.getCarNumber());
        if(isExist){
            return ResponseFormat.fail("해당 자동차 넘버는 이미 존재합니다.");
        }

        Cars cars = Cars.builder()
                .carNumber(carAddDto.getCarNumber())
                .carKinds(carAddDto.getCarKinds())
                .carName(carAddDto.getCarName())
                .dayPrice(carAddDto.getDayPrice())
                .energyType(carAddDto.getEnergyType())
                .optionType(carAddDto.getOptionType())
                .build();

        carsRepository.save(cars);

        return ResponseFormat.ok();
    }

    /**
     * carNumber 유무 확인 메소드
     * @param carNumber: 해당 차 넘버
     * @return (중복X: ok(1, true, null) / 중복O: fail(2, false, description)
     */
    public ResponseFormat carCheck(String carNumber){

        Boolean isExist = carsRepository.existsByCarNumber(carNumber);
        if(isExist){
            return ResponseFormat.fail("해당 자동차 넘버는 이미 존재합니다.");
        }
        return ResponseFormat.ok();
    }

}
