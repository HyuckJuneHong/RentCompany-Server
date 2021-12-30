package project.rentcompany.domain.service.rentable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.rentable.AbleGetDto;
import project.rentcompany.domain.entity.rentable.RentAble;
import project.rentcompany.repository.rentable.RentAbleRepository;
import project.rentcompany.utils.ResponseFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbleGetService {

    private final RentAbleRepository rentAbleRepository;

    /**
     * 현재 렌트 가능한 자동차 리스트 반환
     * @return 해당 자동차 리스트
     */
    public ResponseFormat<List<AbleGetDto>> ableGet(){

        List<RentAble> rentAbles = rentAbleRepository.findAll();
        ArrayList<AbleGetDto> ableGetDtoList = new ArrayList<>();

        for(RentAble rentAble: rentAbles){
            AbleGetDto ableGetDto = AbleGetDto.builder()
                    .startDesired(rentAble.getStartDesired())
                    .endDesired(rentAble.getEndDesired())
                    .cars(rentAble.getCars())
                    .build();

            ableGetDtoList.add(ableGetDto);
        }

        return ResponseFormat.ok(ableGetDtoList);
    }


    /**
     * 검색어에 해당하는 렌트가능한 차 반환
     * @param search: 검색어
     * @return 해당 자동차 리스트
     */
    public ResponseFormat<List<AbleGetDto>> ableSearchGet(String search){

        List<RentAble> rentAbles;
        ArrayList<AbleGetDto> ableGetDtoList = new ArrayList<>();

        if(search == null){
            rentAbles = rentAbleRepository.findAll();
        }else{
            rentAbles = rentAbleRepository.fetchBySearch(search);
        }

        for(RentAble rentAble: rentAbles){
            AbleGetDto ableGetDto = AbleGetDto.builder()
                    .startDesired(rentAble.getStartDesired())
                    .endDesired(rentAble.getEndDesired())
                    .cars(rentAble.getCars())
                    .build();

            ableGetDtoList.add(ableGetDto);
        }

        return ResponseFormat.ok(ableGetDtoList);
    }

}

