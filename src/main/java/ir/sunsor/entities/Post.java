package ir.sunsor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Table @Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String type;
    private String time;
    private int likesCount;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{" + id + "}{" +
                " title= " + title +
                " type= " + type +
                " time= " + time +"( " + likesCount + "likes" + " )" +
                " user= " + user.getUsername() + "}";
    }
}
