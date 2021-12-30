package project.rentcompany.domain.entity.boards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.rentcompany.domain.entity.reply.Reply;
import project.rentcompany.domain.entity.users.Users;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tbl_boards")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Boards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;
    private Long views;
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name ="uno")
    private Users users;

    public void boardsUpdate(String title, String content){

        this.title = title;
        this.content = content;
        this.createDate = LocalDate.now();
    }

    public void viewsIncrease(){
        this.views += 1;
    }

    @Builder
    public Boards(String title, String content, Long views, LocalDate createDate, Users users) {
        this.title = title;
        this.content = content;
        this.views = views;
        this.createDate = createDate;
        this.users = users;
    }
}
