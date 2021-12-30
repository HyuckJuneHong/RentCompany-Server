package project.rentcompany.domain.service.driveLicense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.driveLicense.DriveLicenseRequestDto;
import project.rentcompany.domain.dto.driveLicense.DriveLicenseResponseDto;
import project.rentcompany.domain.entity.driveLicense.DriveLicense;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.driveLicense.DriveLicenseRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class DriveLicenseGetService {

    private final UsersRepository usersRepository;
    private final DriveLicenseRepository driveLicenseRepository;

    /**
     * 해당 유저 운전 면허증 데이터 반환
     * @param identity: 유저의 아이디
     * @return 면허증 데이터
     */
    @Transactional(readOnly = true)
    public ResponseFormat<DriveLicenseResponseDto> driveLicenseResponse(DriveLicenseRequestDto requestDto){

        Users user = usersRepository.findByIdentity(requestDto.getIdentity());
        DriveLicense driveLicense = driveLicenseRepository.findByUsers(user);

        if(user == null) {
            return ResponseFormat.fail("로그인을 하셔야합니다.");
        }

        if(!user.getPassword().equals(requestDto.getPassword())){
            return ResponseFormat.fail("패스워드가 일치하지 않습니다.");
        }

        if(driveLicense == null){
            return ResponseFormat.fail("해당 유저는 면허가 없습니다.");
        }


        DriveLicenseResponseDto responseDto = DriveLicenseResponseDto.builder()
                    .identity(requestDto.getIdentity())
                    .licenseNumber(driveLicense.getLicenseNumber())
                    .type(driveLicense.getType())
                    .update_term(driveLicense.getUpdate_term())
                    .build();

        return ResponseFormat.ok(responseDto);
    }

    /**
     * 회원의 운전면허증 여부 확인 메소드
     * @param requestDto: 운전면허증 여부 확인할 회원 아이디와 패스워드 데이터
     * @return (성공: ok(1, true, null) / 실패: fail(2, false, description)
     */
    public ResponseFormat checkUserDriveLicense(DriveLicenseRequestDto requestDto){

        Users user = usersRepository.findByIdentity(requestDto.getIdentity());

        if(user == null) {
            return ResponseFormat.fail("로그인을 하셔야합니다.");
        }

        if(!user.getPassword().equals(requestDto.getPassword())){
            return ResponseFormat.fail("패스워드가 일치하지 않습니다.");
        }

        boolean isDriverLicense = driveLicenseRepository.existsByUsers(user);

        if(!isDriverLicense) {
            return ResponseFormat.fail("면허가 존재하지 않습니다.");
        }

        return ResponseFormat.ok();
    }
}
