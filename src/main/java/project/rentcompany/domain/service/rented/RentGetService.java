package project.rentcompany.domain.service.rented;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.dto.rented.RentedGetDto;
import project.rentcompany.domain.entity.rented.Rented;
import project.rentcompany.domain.entity.users.Users;
import project.rentcompany.repository.rented.RentedRepository;
import project.rentcompany.repository.users.UsersRepository;
import project.rentcompany.utils.ResponseFormat;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RentGetService {

    private final RentedRepository rentedRepository;
    private final UsersRepository usersRepository;

    /**
     * 해당 유저가 렌트한 자동차 반환.
     * @param identity
     * @return RentedGetDtos
     */
    @Transactional(readOnly = true)
    public ResponseFormat<List<RentedGetDto>> rentedGet(String identity){

        Users users = usersRepository.findByIdentity(identity);
        List<Rented> renteds = rentedRepository.findByUsers(users);
        ArrayList<RentedGetDto> rentedGetDtos = new ArrayList<>();

        if(users == null){
            return ResponseFormat.fail("로그인을 하셔야합니다.");
        }

        for(Rented rented: renteds){
            RentedGetDto rentedGetDto = RentedGetDto.builder()
                    .startDate(rented.getStartDate())
                    .endDate(rented.getEndDate())
                    .carName(rented.getCars().getCarName())
                    .insureType(rented.getInsurance().getType())
                    .totalDate(rented.getTotalDate())
                    .totalPrice(rented.getTotalPrice())
                    .build();

            rentedGetDtos.add(rentedGetDto);
        }

        return ResponseFormat.ok(rentedGetDtos);

    }

}
