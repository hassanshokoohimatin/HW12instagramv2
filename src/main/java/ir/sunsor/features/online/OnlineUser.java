package ir.sunsor.features.online;

import ir.sunsor.entities.Post;
import ir.sunsor.entities.User;
import ir.sunsor.features.follow.Follow;
import ir.sunsor.features.follow.UnFollow;
import ir.sunsor.features.post.SeeAndLikePostsOfAnAccount;
import ir.sunsor.features.post.SeePostsOfAnAccount;
import ir.sunsor.features.search.Search;
import ir.sunsor.repositories.PostRepository;
import ir.sunsor.repositories.UserRepository;
import ir.sunsor.share.AuthenticationService;

import java.util.*;
import java.util.stream.Collectors;

public class OnlineUser {

    static UserRepository userRepository = UserRepository.getInstance();
    static PostRepository postRepository = PostRepository.getInstance();

    public void online(User user){
        Scanner scanner = new Scanner(System.in);
        int command = 1;
        while ( command != 10) {
            System.out.println("1.send a post\n2.see your posts\n3.edit a post\n4.remove a post" +
                    "\n5.settings\n6.search people by username\n7.search posts based on maximum likes\n8.Following\n9.unFollowing" +
                    "10.sign out");
            command = scanner.nextInt();
            if (command == 1){
                System.out.println("enter post title : ");
                String title = scanner.next();
                System.out.println("enter post content : ");
                String content = scanner.next();
                System.out.println("browse a file : ");
                String type = scanner.next();
                String time = new Date().toString();
                Post post = new Post(null,title,content,type,time,0,user,null,null);
                postRepository.save(post);
            }
            if (command == 2){
                List<Post> posts = postRepository.findAll().stream().filter(post -> post.getUser().getId()==user.getId())
                        .collect(Collectors.toList());
                if (posts.size()==0)
                    System.out.println("you dont have any posts yet...");
                else{
                    for (Post p : posts){
                        System.out.println(p);
                    }
                }
            }
            if (command == 3){
                System.out.println("here is your posts...");
                List<Post> posts = postRepository.findAll().stream().filter(p->p.getUser().getId()==user.getId())
                        .collect(Collectors.toList());
                if (posts.size()==0)
                    System.out.println("you dont have any posts...");
                else {
                    for (Post p : posts)
                        System.out.println(p);
                }

                System.out.println("which post do you want to edit? enter id : ");
                int id = scanner.nextInt();
                Post post = posts.get(id-1);
                System.out.printf("%s%s%s","title : ",post.getTitle()," (enter new title or * to skip) : ");
                String title = scanner.next();
                if ( ! title.equals("*") )
                    post.setTitle(title);
                System.out.printf("%s%s%s","content : ",post.getContent()," (enter new content or * to skip) : ");
                String content = scanner.next();
                if ( ! content.equals("*"))
                    post.setContent(content);
                System.out.printf("%s%s%s","file : ",post.getType()," (enter new file or * to skip) : ");
                String file = scanner.next();
                if ( ! file.equals("*"))
                    post.setType(file);
                postRepository.update(post);
                System.out.println("your post edited successfully...");
            }
            if (command == 4){
                System.out.println("here is your posts...");
                List<Post> posts = postRepository.findAll().stream().filter(p->p.getUser().getId()==user.getId())
                        .collect(Collectors.toList());
                if (posts.size()==0)
                    System.out.println("you dont have any posts...");
                else {
                    for (Post p : posts)
                        System.out.println(p);
                }

                System.out.println("which post do you want to remove? enter id : ");
                Long id = scanner.nextLong();
                postRepository.removeById(id);
                System.out.println("your post removed successfully...");
            }
            if (command == 5){
                Settings settings = new Settings();
                settings.settings(user);
                if (AuthenticationService.getInstance().getLoginUser()==null)
                    break;
            }
            if (command == 6){
                System.out.println("enter the username to be searched...");
                String username = scanner.next();
                List<User> foundUser = userRepository.findAll().stream().filter(u->u.getUsername().equals(username)).
                        collect(Collectors.toList());
                if (foundUser.size()==0)
                    System.out.println("there is no any account with this username");
                else{
                    System.out.println(foundUser.get(0));
                    System.out.println("press any key to see posts of this username or * to skip : ");
                    String choice = scanner.next();
                    if ( ! choice.equals("*") ){
                        SeePostsOfAnAccount see = new SeePostsOfAnAccount();
                        List<Post> posts = see.getPosts(foundUser.get(0));
                        if (posts.size()==0)
                            System.out.println("this username doesn't have any posts");
                        else{
                            SeeAndLikePostsOfAnAccount seeAndLike = new SeeAndLikePostsOfAnAccount();
                            seeAndLike.seeAndLike(posts , user);
                        }
                    }

                }
            }
            if (command == 7){
                List<Integer> likes = postRepository.findAll().stream().map(p->p.getLikesCount()).
                        collect(Collectors.toList());
                int maxLike = Collections.max(likes);
                List<Post> maxLikedPosts = postRepository.findAll().stream().filter(p->p.getLikesCount() == maxLike)
                        .collect(Collectors.toList());
                for (Post p : maxLikedPosts){
                    System.out.println(p);

                }
            }
            if (command==8){

                System.out.println("search an account for following\n1.search by username\n2.search by name : ");
                int choice = scanner.nextInt();
                if (choice==1){
                    System.out.println("enter username for search : ");
                    String username = scanner.next();
                    Search searchByUsername = new Search();
                    User foundUser = searchByUsername.search(username);
                    if (foundUser==null)
                        System.out.println("there is no such an account");
                    else
                        System.out.println(foundUser);
                    System.out.println();
                    System.out.println("follow\t\t\tskip");
                    String manner = scanner.next();
                    if (manner.equals("follow")){
                        Follow.follow(user , foundUser);
                        System.out.println("added to followings");
                    }

                }
                if (choice==2){
                    System.out.println("enter first name : ");
                    String firstName = scanner.next();
                    System.out.println("enter last name : ");
                    String lastName = scanner.next();
                    Search searchByName = new Search();
                    List<User> foundUsers = searchByName.search(firstName,lastName);
                    if (foundUsers.size()==0)
                        System.out.println("there is no any users with this name...");
                    else{
                        for (User u : foundUsers)
                            System.out.println(u);
                    }
                    System.out.println();
                    System.out.println("follow\t\t\tskip");
                    String manner = scanner.next();
                    if (manner.equals("follow")){

                        System.out.println("which user do you want to follow? enter id : ");
                        Long id = scanner.nextLong();
                        User following = userRepository.findById(id);
                        Follow.follow(user , following);
                        System.out.println("added to followings");
                    }
                }
            }
            if (command==9){

                System.out.println("here is your followings");
                for (User u : userRepository.findById(user.getId()).getFollowings()){
                    System.out.println(u);
                }

                System.out.println("which user do you want to unFollow? enter id or 0 to skip : ");
                Long choice = scanner.nextLong();
                if (choice!=0){
                    User following = userRepository.findById(choice);
                    UnFollow.unFollow(user , following);
                    System.out.println("the selected user removed from your followings");
                }
            }
            if (command == 10){
                AuthenticationService.getInstance().setLoginUser(null);
            }
        }
    }
}
