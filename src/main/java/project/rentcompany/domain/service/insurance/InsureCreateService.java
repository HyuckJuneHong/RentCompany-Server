package project.rentcompany.domain.service.insurance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.insurance.InsureCreateDto;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.repository.insurance.InsuranceRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class InsureCreateService {

    private final InsuranceRepository insuranceRepository;

    /**
     * 보험 추가
     * @param insureCreateDto: 보험 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat insureCreate(InsureCreateDto insureCreateDto){

        if(insureCreateDto.getType() == null || insureCreateDto.getPrice() == null ||
            insureCreateDto.getSelfPay() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않았습니다.");
        }

        Boolean isExistType = insuranceRepository.existsByType(insureCreateDto.getType());


        if(isExistType){
            return ResponseFormat.fail("해당 보험 타입은 존재합니다.");
        }

        Insurance insurance = Insurance.builder()
                .price(insureCreateDto.getPrice())
                .selfPay(insureCreateDto.getSelfPay())
                .type(insureCreateDto.getType())
                .build();

        insuranceRepository.save(insurance);

        return ResponseFormat.ok();

    }

    /**
     * 보험 타입 유무 확인
     * @param type: 보험 타입 이름.
     * @return (중복X: ok(1, true, null) / 중복O: fail(2, false, description)
     */
    public ResponseFormat insureCheck(String type){

        Boolean isExist = insuranceRepository.existsByType(type);

        if(isExist){
            return ResponseFormat.fail("해당 보험 타입은 존재합니다.");
        }
        return ResponseFormat.ok();
    }
}
