package project.rentcompany.repository.rentable;

import project.rentcompany.domain.entity.rentable.RentAble;

import java.util.List;

public interface RentAbleRepositoryCustom {

    List<RentAble> fetchBySearch(String search);
}
