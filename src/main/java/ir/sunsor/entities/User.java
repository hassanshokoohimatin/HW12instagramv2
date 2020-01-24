package ir.sunsor.entities;

import ir.sunsor.entities.embeddables.About;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Table @Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;

    public User(String firstName, String lastName, String email, String mobileNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
    }

    @Embedded
    private About about;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> userComments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_follower" , joinColumns = {@JoinColumn(name = "user_id")} , inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    private List<User> followers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_following" , joinColumns = {@JoinColumn(name = "user_id")} , inverseJoinColumns = {@JoinColumn(name = "following_id")})
    private List<User> followings = new ArrayList<>();

    @Override
    public String toString() {
        return "User(" + id + "){" +
                " firstName= " + firstName +
                " lastName= " + lastName +
                " username= " + username + " }";
    }
}
