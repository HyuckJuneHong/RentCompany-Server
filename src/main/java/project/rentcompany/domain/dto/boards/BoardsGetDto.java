package project.rentcompany.domain.dto.boards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import project.rentcompany.domain.entity.users.Users;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class BoardsGetDto {

    private String title;
    private String content;
    private LocalDate localDate;
    private Long views;
    private String name;
}
