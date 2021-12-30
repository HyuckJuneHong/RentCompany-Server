package project.rentcompany.repository.boards;

import project.rentcompany.domain.entity.boards.Boards;

import java.util.List;

public interface BoardsRepositoryCustom {

    List<Boards> fetchBySearch(String search);
}
