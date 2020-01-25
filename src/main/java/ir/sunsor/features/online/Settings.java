package ir.sunsor.features.online;

import ir.sunsor.entities.User;
import ir.sunsor.repositories.UserRepository;
import ir.sunsor.share.AuthenticationService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Settings {


    static UserRepository userRepository = UserRepository.getInstance();

    public void settings(User user){
        Scanner scanner = new Scanner(System.in);
        int command = 1;
        while (command != 4){
            System.out.println("1.edit account\n2.change password\n3.delete account\n4.home page");
            command = scanner.nextInt();
            if (command==1){
                System.out.println("edit first name or * to skip : ");
                String firstName = scanner.next();
                if ( ! firstName.equals("*") )
                    user.setFirstName(firstName);
                System.out.println("edit last name or * to skip : ");
                String lastName = scanner.next();
                if ( ! lastName.equals("*") )
                    user.setLastName(lastName);
                System.out.println("edit email or * to skip : ");
                String email = scanner.next();
                if ( ! email.equals("*") ){
                    user.setEmail(takeValidEmail(email));
                }
                System.out.println("edit mobile number or * to skip : ");
                String mobileNumber = scanner.next();
                if ( ! mobileNumber.equals("*") ){
                    user.setMobileNumber(takeValidMobileNumber(mobileNumber));
                }
                System.out.println("edit username or * to skip : ");
                String username = scanner.next();
                if ( ! username.equals("*") ){
                    user.setUsername(takeValidUsername(username));
                }
                System.out.println("write about yourself : ");

                System.out.println("edit country or * to skip : ");
                String country = scanner.next();
                if ( ! country.equals("*") )
                    user.getAbout().setCountry(country);
                System.out.println("edit city or * to skip : ");
                String city = scanner.next();
                if ( ! city.equals("*") )
                    user.getAbout().setCity(city);
                System.out.println("edit favorites or * to skip : ");
                String favorites = scanner.nextLine();
                if ( ! favorites.equals("*") )
                    user.getAbout().setFavorites(favorites);
                System.out.println("edit status(married or etc) or * to skip : ");
                String status = scanner.next();
                if ( ! status.equals("*") )
                    user.getAbout().setStatus(status);
                System.out.println("edit description(write everything to want) or * to skip : ");
                String description = scanner.nextLine();
                if ( ! description.equals("*") )
                    user.getAbout().setDescription(description);

                userRepository.update(user);
                System.out.println("successful edition...");
            }
            if (command==2){
                System.out.println("enter your current password : ");
                String oldPassword = scanner.next();
                if (userRepository.findById(user.getId()).getPassword().equals(oldPassword)){
                    System.out.println("enter new password");
                    String newPassword = scanner.next();
                    System.out.println("reenter the password");
                    String reNewPassword = scanner.next();
                    if (newPassword.equals(reNewPassword)) {
                        user.setPassword(newPassword);
                        userRepository.update(user);
                        System.out.println("password changed successfully...");
                    }else{
                        System.out.println("two entered passwords were not the same...try later");
                    }

                }else{
                    System.out.println("wrong password...try later");
                }
            }
            if (command==3){
                System.out.println("are you sure to delete your account? (yes or no)");
                String response = scanner.next();
                if (response.equals("yes")){
                    userRepository.remove(user);
                    System.out.println("your account deleted...");
                    AuthenticationService.getInstance().setLoginUser(null);
                    break;
                }
            }
            if (command==4){
                System.out.println("back to main menu...");
            }
        }
    }
    public static String takeValidEmail(String email){
        Scanner scanner = new Scanner(System.in);
        List<String> emails = userRepository.findAll().stream().
                map(u -> u.getEmail()).collect(Collectors.toList());
        while(emails.contains(email)){
            System.out.println("enter a valid email : ");
            email = scanner.next();
        }
        return email;
    }

    public static String takeValidMobileNumber(String mobileNumber){
        Scanner scanner = new Scanner(System.in);
        List<String> mobileNumbers = userRepository.findAll().stream().
                map(u -> u.getMobileNumber()).collect(Collectors.toList());
        while(mobileNumbers.contains(mobileNumber)){
            System.out.println("enter a valid mobile number : ");
            mobileNumber = scanner.next();
        }
        return mobileNumber;
    }

    public static String takeValidUsername(String username){
        Scanner scanner = new Scanner(System.in);
        List<String> usernames = userRepository.findAll().stream().
                map(u->u.getUsername()).collect(Collectors.toList());
        while(usernames.contains(username)){
            System.out.println("enter a valid username : ");
            username = scanner.next();
        }
        return username;
    }
}
