package ir.sunsor.features.user;

import ir.sunsor.entities.User;
import ir.sunsor.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SignIn {

    static UserRepository userRepository = UserRepository.getInstance();

    public User signIn(String username , String password){
        User user = null;
        List<User> users1 = userRepository.findAll().stream().filter(u -> u.getUsername().equals(username))
                .collect(Collectors.toList());
        List<User> users2 = users1.stream().filter(u -> u.getPassword().equals(password))
                .collect(Collectors.toList());

        if (users2.size() != 0){
            user = users2.get(0);
        }

        return user;
    }
}
