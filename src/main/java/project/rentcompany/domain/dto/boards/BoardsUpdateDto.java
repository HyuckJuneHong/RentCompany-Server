package project.rentcompany.domain.dto.boards;

import lombok.Getter;

@Getter
public class BoardsUpdateDto {

    Long bno;
    private String content;
    private String title;
    private String identity;

}
