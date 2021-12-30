package project.rentcompany.domain.service.insurance;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.repository.insurance.InsuranceRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class InsureDeleteService {

    private final InsuranceRepository insuranceRepository;

    /**
     * 해당 보험 삭제
     * @param type: 보험 타입 이름
     * @return (성공: ok(1, true, null) )
     */
    public ResponseFormat insureDelete(String type){

        Insurance insurance = insuranceRepository.findByType(type);
        if(insurance == null){
            return ResponseFormat.fail("해당 보험은 없습니다.");
        }

        insuranceRepository.delete(insurance);

        return ResponseFormat.ok();
    }
}
