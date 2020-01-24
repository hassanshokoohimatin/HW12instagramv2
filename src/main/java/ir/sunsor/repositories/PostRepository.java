package ir.sunsor.repositories;

import ir.sunsor.config.CrudRepository;
import ir.sunsor.entities.Post;

public class PostRepository extends CrudRepository<Post , Long> {

    private static PostRepository postRepository;

    private PostRepository(){}

    public static PostRepository getInstance(){
        if (postRepository==null)
            postRepository = new PostRepository();
        return postRepository;
    }


    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }
}
