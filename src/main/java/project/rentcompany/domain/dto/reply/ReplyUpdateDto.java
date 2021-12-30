package project.rentcompany.domain.dto.reply;

import lombok.Getter;

@Getter
public class ReplyUpdateDto {

    private String identity;
    private Long bno;
    private Long pno;
    private String content;
}
