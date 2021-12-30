package project.rentcompany.domain.dto.boards;

import lombok.Getter;
import project.rentcompany.domain.entity.users.Users;

import java.time.LocalDate;

@Getter
public class BoardsWriteDto {

    private String title;
    private String content;
    private String identity;
}
