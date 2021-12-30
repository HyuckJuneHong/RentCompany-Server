package project.rentcompany.domain.dto.reply;

import lombok.Getter;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.users.Users;

@Getter
public class ReplyWriteDto {

    private Long bno;
    private String content;
    private String identity;
}
