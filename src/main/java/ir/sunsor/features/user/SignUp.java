package ir.sunsor.features.user;

import ir.sunsor.entities.User;
import ir.sunsor.repositories.UserRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SignUp {

    static UserRepository userRepository = UserRepository.getInstance();
    static List<String> emails = userRepository.findAll().stream().map(u->u.getEmail()).collect(Collectors.toList());
    static List<String> mobileNumbers = userRepository.findAll().stream().map(u->u.getMobileNumber()).collect(Collectors.toList());
    static List<String> usernames = userRepository.findAll().stream().map(u->u.getUsername()).collect(Collectors.toList());

    Scanner scanner = new Scanner(System.in);

    public void signUp(){
        System.out.println("enter first name : ");
        String firstName = scanner.next();
        System.out.println("enter last name : ");
        String lastName = scanner.next();
        System.out.println("enter email address : ");
        String email = scanner.next();
        System.out.println("enter mobile number : ");
        String mobileNumber = scanner.next();
        if(check(email , mobileNumber)) {
            System.out.println("enter username : ");
            String username = scanner.next();
            if (checkUsername(username)) {
                System.out.println("enter password : ");
                String password = scanner.next();
                User user = new User(firstName, lastName , email , mobileNumber , username , password);
                userRepository.save(user);
                System.out.println("signed up successfully...");

            }else{
                System.out.println("this username already exist...try again");
            }
        }else{
            System.out.println("this email or mobile number already exist...try again...");
        }
    }

    public static boolean check(String email , String mobileNumber){

        if (emails.contains(email) || mobileNumbers.contains(mobileNumber))
            return false;
        return true;
    }
    public static boolean checkUsername(String username){

        if (usernames.contains(username))
            return false;
        return true;
    }
}
