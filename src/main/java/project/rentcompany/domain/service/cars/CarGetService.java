package project.rentcompany.domain.service.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.cars.CarGetDto;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.repository.cars.CarsRepository;
import project.rentcompany.utils.ResponseFormat;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CarGetService {

    private final CarsRepository carsRepository;

    /**
     * 해당하는 넘버 자동차 반환
     * @param carNumber: 차 넘버
     * @return CarGetDto
     */
    @Transactional(readOnly = true)
    public ResponseFormat<CarGetDto> carGet(String carNumber){

        Cars cars = carsRepository.findByCarNumber(carNumber);

        if(cars == null){
            return ResponseFormat.fail("해당 정보는 없습니다.");
        }

        CarGetDto carGetDto = CarGetDto.builder()
                .carNumber(carNumber)
                .carKinds(cars.getCarKinds())
                .carName(cars.getCarName())
                .energyType(cars.getEnergyType())
                .dayPrice(cars.getDayPrice())
                .optionType(cars.getOptionType())
                .build();

        return ResponseFormat.ok(carGetDto);
    }

    /**
     * 해당하는 search 모든 리스트 반환
     * @param search: 자동차 이름
     * @return List<CarGetDto>
     */
    @Transactional(readOnly = true)
    public List<CarGetDto> carSearchGet(String search){

        List<Cars> carsList = carsRepository.fetchBySearch(search);
        ArrayList<CarGetDto> carsGetList = new ArrayList<>();

        if(carsList.isEmpty()){
            return null;
        }

        for(Cars cars: carsList){
            CarGetDto carGetDto = CarGetDto.builder()
                    .carNumber(cars.getCarNumber())
                    .carKinds(cars.getCarKinds())
                    .carName(cars.getCarName())
                    .energyType(cars.getEnergyType())
                    .dayPrice(cars.getDayPrice())
                    .optionType(cars.getOptionType())
                    .build();

            carsGetList.add(carGetDto);
        }
        return carsGetList;
    }

    /**
     * 모든 자동차 리스트 반환
     * @return List<CarGetDto>
     */
    @Transactional(readOnly = true)
    public List<CarGetDto> carGetList(){

        List<Cars> carsList = carsRepository.findAll();
        ArrayList<CarGetDto> carsGetList = new ArrayList<>();

        if(carsList.isEmpty()){
            return null;
        }

        for(Cars cars: carsList){
            CarGetDto carGetDto = CarGetDto.builder()
                    .carNumber(cars.getCarNumber())
                    .carKinds(cars.getCarKinds())
                    .carName(cars.getCarName())
                    .energyType(cars.getEnergyType())
                    .dayPrice(cars.getDayPrice())
                    .optionType(cars.getOptionType())
                    .build();

            carsGetList.add(carGetDto);
        }

        return carsGetList;
    }
}
