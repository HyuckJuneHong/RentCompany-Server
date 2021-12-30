package project.rentcompany.controller.driveLicense;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.rentcompany.domain.dto.driveLicense.DriveLicenseRequestDto;
import project.rentcompany.domain.dto.driveLicense.DriveLicenseResponseDto;
import project.rentcompany.domain.dto.driveLicense.RegisterDto;
import project.rentcompany.domain.service.driveLicense.DriveLicenseDeleteService;
import project.rentcompany.domain.service.driveLicense.DriveLicenseGetService;
import project.rentcompany.domain.service.driveLicense.RegisterService;
import project.rentcompany.utils.ResponseFormat;

@RestController
@RequestMapping("/rentCompany/user")
@RequiredArgsConstructor
public class DriveLicenseController {

    private final DriveLicenseGetService driveLicenseGetService;
    private final RegisterService registerService;
    private final DriveLicenseDeleteService driveLicenseDeleteService;

    //운전 면허증 등록
    @PostMapping("/register")
    public ResponseFormat register(@RequestBody RegisterDto registerDto){
        return registerService.register(registerDto);
    }

    //면허 등록 시 해당 면허증 번호 중복 확인
    @GetMapping("/register/check")
    public ResponseEntity<ResponseFormat> checkDriveLicense(@RequestParam("licenseNumber") String licenseNumber){
        return ResponseEntity.ok().body(registerService.checkDriveLicense(licenseNumber));
    }

    //해당 유저 면허증 정보 반환
    @PostMapping("/license/check/get")
    public ResponseEntity<ResponseFormat<DriveLicenseResponseDto>> licenseResponse(@RequestBody DriveLicenseRequestDto requestDto){
            return ResponseEntity.ok().body(driveLicenseGetService.driveLicenseResponse(requestDto));
    }

    //해당 유저 면허증 여부 확인
    @PostMapping("/license/check")
    public ResponseFormat licenseRequest(@RequestBody DriveLicenseRequestDto requestDto){
        return driveLicenseGetService.checkUserDriveLicense(requestDto);
    }

    @DeleteMapping("/license/delete")
    public ResponseFormat licenseDelete(@RequestParam("identity") String identity){
        return driveLicenseDeleteService.licenseDelete(identity);
    }
}