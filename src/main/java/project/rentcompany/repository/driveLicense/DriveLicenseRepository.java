package project.rentcompany.repository.driveLicense;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.driveLicense.DriveLicense;
import project.rentcompany.domain.entity.users.Users;

public interface DriveLicenseRepository extends JpaRepository<DriveLicense, Long> {


    Boolean existsByUsers(Users users);
    Boolean existsByLicenseNumber(String licenseNumber);

    DriveLicense findByUsers(Users user);

}
