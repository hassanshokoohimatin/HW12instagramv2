package ir.sunsor.features.post;

import ir.sunsor.entities.Comment;
import ir.sunsor.entities.Post;
import ir.sunsor.entities.User;
import ir.sunsor.repositories.CommentRepository;
import ir.sunsor.repositories.PostRepository;
import ir.sunsor.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SeeAndLikePostsOfAnAccount {

    static PostRepository postRepository = PostRepository.getInstance();
    static CommentRepository commentRepository = CommentRepository.getInstance();
    static UserRepository userRepository = UserRepository.getInstance();
    Scanner scanner = new Scanner(System.in);
    public void seeAndLike(List<Post> posts , User viewer){
        for (Post p : posts){
            System.out.println(p);
            System.out.printf("%s\t\t%s\t\t%s","like","comment","next");
            String status = scanner.next();
            if (status.equals("like")){

                if (p.getLikers().contains(viewer)) {
                    System.out.println("you liked this post before...");
                    System.out.println("do you want to dislike?...1.yes...2.no");
                    int choice = scanner.nextInt();
                    if (choice==1) {
                        p.getLikers().remove(viewer);
                        p.setLikesCount(p.getLikesCount()-1);
                        postRepository.update(p);
                    }
                }else
                    {
                    p.getLikers().add(viewer);
                    p.setLikesCount(p.getLikesCount()+1);
                    postRepository.update(p);
                 }
            }
            if (status.equals("comment")){
                System.out.println("enter your comment : ");
                Comment comment = new Comment();
                String content = scanner.next();
                comment.setContent(content);
                comment.setTime(new Date().toString());
                comment.setUser(viewer);
                comment.setPost(p);
                commentRepository.save(comment);
                p.getComments().add(comment);
                postRepository.update(p);
                viewer.getUserComments().add(comment);
                userRepository.update(viewer);
            }
        }
    }
}
