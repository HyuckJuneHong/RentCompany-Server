package project.rentcompany.domain.service.rented;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.rented.RentDto;
import project.rentcompany.domain.entity.cars.Cars;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.domain.entity.rentable.RentAble;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.cars.CarsRepository;
import project.rentcompany.repository.driveLicense.DriveLicenseRepository;
import project.rentcompany.repository.insurance.InsuranceRepository;
import project.rentcompany.repository.rentable.RentAbleRepository;
import project.rentcompany.repository.rented.RentedRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RentService {

    private final RentedRepository rentedRepository;
    private final UsersRepository usersRepository;
    private final CarsRepository carsRepository;
    private final InsuranceRepository insuranceRepository;
    private final DriveLicenseRepository driveLicenseRepository;
    private final RentAbleRepository rentAbleRepository;

    /**
     * 렌트 혹은 예약이 됐으면 추가하는 메소드
     * @param rentDto: 렌트, 예약 된 날짜 데이터와 렌트 유저, 자동차, 보험 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat rent(RentDto rentDto){

        if(rentDto.getIdentity() == null || rentDto.getEndDate() == null ||
            rentDto.getStartDate() == null || rentDto.getCno() == null ||
            rentDto.getType() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않았습니다.");
        }

        Users users = usersRepository.findByIdentity(rentDto.getIdentity());
        Boolean isExistLicense = driveLicenseRepository.existsByUsers(users);

        if(users == null){
            return ResponseFormat.fail("로그인을 하셔야만 렌트가 가능합니다.");
        }
        if (!isExistLicense){
            return ResponseFormat.fail("면허증이 등록되어야만 렌트가 가능합니다.");
        }

        Cars cars = carsRepository.findByCno(rentDto.getCno());
        Insurance insurance = insuranceRepository.findByType(rentDto.getType());
        Boolean isExistAble = rentAbleRepository.existsByCars(cars);

        if(cars == null){
            return ResponseFormat.fail("해당 자동차는 존재하지 않습니다.");
        }
        if(insurance == null){
            return ResponseFormat.fail("해당 보험은 존재하지 않습니다.");
        }
        if(!isExistAble){
            return ResponseFormat.fail("해당 자동차는 렌트가 불가능합니다.");
        }

        Long totalDate = ChronoUnit.DAYS.between(rentDto.getStartDate(), rentDto.getEndDate());

        Rented rented = Rented.builder()
                .startDate(rentDto.getStartDate())
                .endDate(rentDto.getEndDate())
                .totalDate(totalDate)
                .totalPrice((totalDate * cars.getDayPrice()) + insurance.getPrice())
                .users(users)
                .cars(cars)
                .insurance(insurance)
                .build();

        rentedRepository.save(rented);

        return ResponseFormat.ok();
    }
}
