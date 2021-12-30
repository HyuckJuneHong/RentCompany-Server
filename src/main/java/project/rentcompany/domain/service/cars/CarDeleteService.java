package project.rentcompany.domain.service.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.repository.cars.CarsRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class CarDeleteService {

    private final CarsRepository carsRepository;

    /**
     * 해당 넘버 자동차 삭제
     * @param carNumber: 삭제하고 싶은 자동차 넘버
     * @return (성공: ok(1, true, null)
     */
    public ResponseFormat carDelete(String carNumber){

        Cars cars = carsRepository.findByCarNumber(carNumber);

        if(cars == null){
            return ResponseFormat.fail("해당 자동차는 이미 존재하지 않습니다.");
        }

        carsRepository.delete(cars);

        return ResponseFormat.ok();
    }
}
