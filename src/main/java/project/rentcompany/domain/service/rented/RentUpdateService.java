package project.rentcompany.domain.service.rented;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.rented.RentUpdateDto;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.repository.rented.RentedRepository;
import project.rentcompany.utils.ResponseFormat;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentUpdateService {

    private final RentedRepository rentedRepository;

    public ResponseFormat rentedUpdate(RentUpdateDto rentUpdateDto){

        if(rentUpdateDto.getStartDesired() == null || rentUpdateDto.getEndDesired() == null||
            rentUpdateDto.getRno() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않았습니다.");
        }

        List<Rented> rentedDelete = rentedRepository.fetchByEndDate();  //기한 끝난 렌트카를 찾음
        rentedRepository.deleteAll(rentedDelete);                       //기한 끝난 렌트카 삭제

        if(LocalDate.now().isAfter(rentUpdateDto.getStartDesired()) ||
                LocalDate.now().equals(rentUpdateDto.getStartDesired())){
            return ResponseFormat.fail("해당 날짜로 수정이 불가능합니다.");
        }

        Rented rented = rentedRepository.findByRno(rentUpdateDto.getRno()); //바꾸고 싶은 렌트카 정보가 들어있음.

        List<Rented> rentedList = rentedRepository                  //해당 기간에 렌트가 불가능한 자동차를 찾음.
                .fetchByBetweenNot(rentUpdateDto.getStartDesired(), rentUpdateDto.getEndDesired());

        if(rentedList.contains(rented)){    //렌트 불가능한 리스트에서 자기 자신은 제거
            rentedList.remove(rented);
        }

        for(Rented check: rentedList){      //자기 자신을 제외한 해당 자동차가 존재한다면,
            if(check.getCars().equals(rented.getCars())){
                return ResponseFormat.fail("이 자동차는 해당 날짜로 변경할 수 없습니다.");
            }
        }

        rented.updateDate(rentUpdateDto.getStartDesired(), rentUpdateDto.getEndDesired());
        rentedRepository.save(rented);

        return ResponseFormat.ok();
    }
}
