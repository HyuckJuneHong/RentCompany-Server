package project.rentcompany.domain.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReplyGetDto {

    private String content;
    private Long bno;
    private String name;
}
