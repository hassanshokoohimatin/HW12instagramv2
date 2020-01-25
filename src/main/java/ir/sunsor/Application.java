package ir.sunsor;

import ir.sunsor.entities.User;
import ir.sunsor.features.user.SignIn;
import ir.sunsor.features.user.SignUp;
import ir.sunsor.repositories.CommentRepository;
import ir.sunsor.repositories.PostRepository;
import ir.sunsor.repositories.UserRepository;
import ir.sunsor.share.AuthenticationService;

import java.util.Scanner;

public class Application {

    static UserRepository userRepository = UserRepository.getInstance();
    static PostRepository postRepository = PostRepository.getInstance();
    static CommentRepository commentRepository = CommentRepository.getInstance();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("sign up\nsign in\nexit");
        String command = "";
        while (!command.equals("exit")) {
            command = scanner.nextLine();

            if (command.equals("signup")) {
                User user = AuthenticationService.getInstance().getLoginUser();
                if (user == null) {
                    SignUp signUp = new SignUp();
                    signUp.signUp();
                } else {
                    System.out.println("signing out...");
                    AuthenticationService.getInstance().setLoginUser(null);
                    System.out.println("signed out successfully\nyou can sign in now");
                }
            }

            if (command.equals("signin")) {
                if(AuthenticationService.getInstance().getLoginUser() == null){
                    System.out.println("enter username : ");
                    String username = scanner.next();
                    System.out.println("enter password : ");
                    String password = scanner.next();
                    SignIn signIn = new SignIn();
                    User user = signIn.signIn(username,password);
                    if (user == null){
                        System.out.println("wrong username or password");
                    }else {
                        AuthenticationService.getInstance().setLoginUser(user);
                        System.out.println("you are signed in");
                        OnlineUser onlineUser = new OnlineUser();
                        onlineUser.online(user);
                    }
                }else {
                    System.out.println("signing out...");
                    AuthenticationService.getInstance().setLoginUser(null);
                    System.out.println("signed out successfully\nyou can sign in now");
                }
            }
            if (command.equals("exit")) {
                AuthenticationService.getInstance().setLoginUser(null);
                System.out.println("END");
            }
        }
    }
}
