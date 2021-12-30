package project.rentcompany.repository.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import project.rentcompany.domain.entity.insurance.Insurance;
import project.rentcompany.utils.ResponseFormat;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    Boolean existsByType(String type);
    Insurance findByType(String type);

}
