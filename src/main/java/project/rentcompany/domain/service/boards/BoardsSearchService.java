package project.rentcompany.domain.service.boards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.repository.boards.BoardsRepository;
import project.rentcompany.utils.ResponseFormat;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardsSearchService {

    private final BoardsRepository boardsRepository;

    /**
     *  각 검색어에 해당하는 게시글을 찾는 메소드들
     * @param search: 검색어
     * @return 각 검색어를 포함하는 모든 게시글.
     */
    public List<Boards> boardsSearch(String search){

        List<Boards> boardsList = boardsRepository.fetchBySearch(search);

        return boardsList;
    }

}
