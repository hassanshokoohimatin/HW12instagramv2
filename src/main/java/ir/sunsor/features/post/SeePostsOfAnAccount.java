package ir.sunsor.features.post;

import ir.sunsor.entities.Post;
import ir.sunsor.entities.User;
import ir.sunsor.repositories.PostRepository;
import ir.sunsor.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeePostsOfAnAccount {

    static UserRepository userRepository = UserRepository.getInstance();
    static PostRepository postRepository = PostRepository.getInstance();

    public List<Post> getPosts(User user){
        List<Post> posts = new ArrayList<>();
        posts = postRepository.findAll().stream().filter(p->p.getUser().getId()==user.getId()).
                collect(Collectors.toList());
        return posts;
    }
}
