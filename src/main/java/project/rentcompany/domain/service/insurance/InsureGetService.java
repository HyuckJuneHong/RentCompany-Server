package project.rentcompany.domain.service.insurance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.insurance.InsureGetDto;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.repository.insurance.InsuranceRepository;
import project.rentcompany.utils.ResponseFormat;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InsureGetService {

    private final InsuranceRepository insuranceRepository;

    /**
     * 보험 데이터 응답
     * @param type: 보고 싶은 보험 타입
     * @return insureGetDto
     */
    @Transactional(readOnly = true)
    public ResponseFormat<InsureGetDto> insureGet(String type){

        Insurance insurance = insuranceRepository.findByType(type);

        if(insurance == null){
            return ResponseFormat.fail("해당 정보는 없습니다.");
        }

        InsureGetDto insureGetDto = InsureGetDto.builder()
                .price(insurance.getPrice())
                .selfPay(insurance.getSelfPay())
                .type(type)
                .build();

        return ResponseFormat.ok(insureGetDto);
    }

    /**
     * 보험 전체 리스트 반환 메소드
     * @return insureGetList
     */
    @Transactional(readOnly = true)
    public List<InsureGetDto> insureGetList(){

        List<Insurance> insureList = insuranceRepository.findAll();
        ArrayList<InsureGetDto> insureGetList = new ArrayList<>();

        if(insureList.isEmpty()){
            return null;
        }

        for(Insurance insurance: insureList){
            InsureGetDto insureGetDto = InsureGetDto.builder()
                    .type(insurance.getType())
                    .price(insurance.getPrice())
                    .selfPay(insurance.getSelfPay())
                    .build();

            insureGetList.add(insureGetDto);
        }

        return insureGetList;
    }

}
