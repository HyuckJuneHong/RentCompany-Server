package project.rentcompany.domain.service.rented;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.repository.rentable.RentAbleRepository;
import project.rentcompany.repository.rented.RentedRepository;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RentDeleteService {

    private final RentedRepository rentedRepository;

    /**
     * 기한이 끝난 렌트 자동차 삭제.
     */
    @Transactional
    public void rentAutoDelete() {

        List<Rented> rented = rentedRepository.fetchByEndDate();

        rentedRepository.deleteAll(rented);
    }
}
