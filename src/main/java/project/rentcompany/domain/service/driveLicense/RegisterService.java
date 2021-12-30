package project.rentcompany.domain.service.driveLicense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.rentcompany.domain.dto.driveLicense.RegisterDto;
import project.rentcompany.domain.entity.driveLicense.DriveLicense;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.driveLicense.DriveLicenseRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final DriveLicenseRepository driveLicenseRepository;
    private final UsersRepository usersRepository;

    /**
     * 운전면허증 등록 메소드
     * @param registerDto: 운전면허증 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat register(RegisterDto registerDto) {

        if(registerDto.getIdentity() == null || registerDto.getLicenseNumber()==null ||
            registerDto.getType() == null || registerDto.getUpdate_term() == null){
            return ResponseFormat.fail("필수 입력 사항을 입력하지 않으셨습니다.");
        }

        Users user = usersRepository.findByIdentity(registerDto.getIdentity());

        if(user == null) {
            return ResponseFormat.fail("로그인을 하셔야합니다.");
        }

        Boolean isExistUser = driveLicenseRepository.existsByUsers(user);
        Boolean isExistLicense = driveLicenseRepository.existsByLicenseNumber(registerDto.getLicenseNumber());

        if(isExistUser){
            return ResponseFormat.fail("해당 유저는 이미 면허증이 등록되어 있습니다.");
        }
        if(isExistLicense){
            return ResponseFormat.fail("해당 면허증은 이미 등록된 면허증입니다.");
        }

        DriveLicense driveLicenseBuild = DriveLicense.builder()
                .licenseNumber(registerDto.getLicenseNumber())
                .update_term(registerDto.getUpdate_term())
                .type(registerDto.getType())
                .users(user)
                .build();

        driveLicenseRepository.save(driveLicenseBuild);

        return ResponseFormat.ok();

    }

    /**
     * 면허증 중복 확인 메소드
     * @param licenseNumber: 해당 회원의 운전면허증 등록 여부.
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat checkDriveLicense(String licenseNumber) {

        Boolean ixExist = driveLicenseRepository.existsByLicenseNumber(licenseNumber);

        if(ixExist){
            return ResponseFormat.fail("이 면허증은 중복되었습니다.");
        }
        return ResponseFormat.ok();

    }

}
