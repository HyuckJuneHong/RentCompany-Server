package project.rentcompany.domain.entity.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.users.Users;

import javax.persistence.*;
import java.util.List;

@Getter
@Table(name="tbl_reply")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String content;

    @ManyToOne
    @JoinColumn(name="uno")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "bno")
    private Boards boards;

    @Builder
    public Reply(String content, Users users, Boards boards) {
        this.content = content;
        this.users = users;
        this.boards = boards;
    }

    public void replyUpdate(String content){
        this.content = content;
    }
}
