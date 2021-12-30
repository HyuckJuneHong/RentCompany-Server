package project.rentcompany.domain.service.insurance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.insurance.InsureUpdateDto;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.repository.insurance.InsuranceRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class InsureUpdateService {

    private final InsuranceRepository insuranceRepository;

    /**
     * 해당 보험 업데이트
     * @param insureUpdateDto: 업데이트할 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat insureUpdate(InsureUpdateDto insureUpdateDto){

        if(insureUpdateDto.getType() == null || insureUpdateDto.getPrice() == null ||
                insureUpdateDto.getSelfPay() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않았습니다.");
        }

        Insurance insurance = insuranceRepository.findByType(insureUpdateDto.getType());

        if(insurance == null){
            return ResponseFormat.fail("해당 보험은 존재하지 않습니다.");
        }


        insurance.insureUpdate(insureUpdateDto.getPrice(), insureUpdateDto.getSelfPay());

        insuranceRepository.save(insurance);

        return ResponseFormat.ok();
    }

}