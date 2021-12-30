package project.rentcompany.domain.service.rentable;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.rentable.AbleAddDto;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.domain.entity.rentable.RentAble;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.repository.cars.CarsRepository;
import project.rentcompany.repository.rentable.RentAbleRepository;
import project.rentcompany.repository.rented.RentedRepository;
import project.rentcompany.utils.ResponseFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class AbleAddService {

    private final RentAbleRepository rentAbleRepository;
    private final CarsRepository carsRepository;
    private final RentedRepository rentedRepository;

    /**
     * 해당 날짜에 Rent가 가능한 모든 자동차를 추가하는 메소드
     * @param ableAddDto: 원하는 날짜 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat ableAdd(AbleAddDto ableAddDto){

        List<Rented> rentedDelete = rentedRepository.fetchByEndDate(); //기한 끝난 렌트카 삭제
        rentedRepository.deleteAll(rentedDelete);

        Long totalDate = ChronoUnit.DAYS.between(ableAddDto.getStartDesired(), ableAddDto.getEndDesired());

        if(totalDate < 0L){
            return ResponseFormat.fail("렌트 기간을 잘못 입력하셨습니다.");
        }

        if(LocalDate.now().isAfter(ableAddDto.getStartDesired()) ||
                LocalDate.now().equals(ableAddDto.getStartDesired())){
            return ResponseFormat.fail("해당 날짜는 예약이 불가능합니다.");
        }


        List<Rented> rentedAllList = rentedRepository.findAll();    //렌트가 된 모든 자동차
        List<Rented> rentedList = rentedRepository                  //렌트가 됐지만 해당 기간에 가능한 자동차를 찾음.
                .fetchByBetween(ableAddDto.getStartDesired(), ableAddDto.getEndDesired());

        List<Cars> carsList = carsRepository.findAll();             //전체 자동차 리스트
        List<Cars> ableList = new ArrayList<>();                    //렌트가 가능한 차를 담을 리스트

        rentAbleRepository.deleteAll();     //추가 하기전 해당 테이블 초기화

        for(Rented rent: rentedAllList){        //렌트가 된 모든 자동차 중
            if(!rentedList.contains(rent)){     //렌트가 가능한 자동차에 포함되지 않으면
                carsList.remove(rent.getCars());//전체 차에서 제거
            }
        }

         ableList.addAll(carsList); //가능한 차만 들어있는 자동차 리스트를 추가.

        if(ableList.isEmpty()){
            return ResponseFormat.fail("해당 날짜에 렌트 가능한 자동차는 존재하지 않습니다.");
        }

        for(Cars ableCars: ableList){
            RentAble rentAble = RentAble.builder()
                    .startDesired(ableAddDto.getStartDesired())
                    .endDesired(ableAddDto.getEndDesired())
                    .cars(ableCars)
                    .build();

            rentAbleRepository.save(rentAble);
        }
        return ResponseFormat.ok();
    }

}
