package project.rentcompany.repository.boards;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import project.rentcompany.domain.entity.boards.Boards;
import project.rentcompany.domain.entity.boards.QBoards;
import project.rentcompany.domain.entity.users.QUsers;

import java.util.List;

@Component
public class BoardsRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QBoards qBoards = QBoards.boards;
    private final QUsers qUsers = QUsers.users;

    //querydsl를 사용하기 위해 꼭 있어야 할 생성자
    public BoardsRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory){
        super(Boards.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Boards> fetchBySearch(String search){
        return setFetchJoinQuery()
                .where(searchCheck(search))
                .fetch();
    }

    public BooleanExpression searchCheck(String search){
        return (this.qBoards.title.contains(search)
                .or(this.qBoards.content.contains(search))
                .or(this.qBoards.users.identity.contains(search))
                .or(this.qBoards.users.name.contains(search)));
    }

    public JPAQuery<Boards> setFetchJoinQuery(){
        return jpaQueryFactory
                .selectFrom(this.qBoards)
                .innerJoin(this.qBoards.users, this.qUsers)
                .fetchJoin();
    }
}
