package project.rentcompany.domain.service.driveLicense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.driveLicense.DriveLicenseRequestDto;
import project.rentcompany.domain.entity.driveLicense.DriveLicense;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.driveLicense.DriveLicenseRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

@Service
@RequiredArgsConstructor
@Transactional
public class DriveLicenseDeleteService {

    private final DriveLicenseRepository driveLicenseRepository;
    private final UsersRepository usersRepository;

    public ResponseFormat licenseDelete(String identity){


        Users user = usersRepository.findByIdentity(identity);

        if(user == null) {
            return ResponseFormat.fail("로그인을 하셔야합니다.");
        }

        DriveLicense driveLicense = driveLicenseRepository.findByUsers(user);

        if(driveLicense == null){
            return ResponseFormat.fail("해당 유저는 면허증이 등록되어 있지 않습니다.");
        }

        driveLicenseRepository.delete(driveLicense);

        return ResponseFormat.ok();
    }
}
