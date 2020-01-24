package ir.sunsor.repositories;

import ir.sunsor.config.CrudRepository;
import ir.sunsor.entities.Comment;

public class CommentRepository extends CrudRepository<Comment, Long> {

    private static CommentRepository commentRepository;

    private CommentRepository(){}

    public static CommentRepository getInstance(){
        if (commentRepository==null)
            commentRepository = new CommentRepository();
        return commentRepository;
    }


    @Override
    protected Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
