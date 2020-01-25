package ir.sunsor.features.search;

import ir.sunsor.entities.User;
import ir.sunsor.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Search {

    static UserRepository userRepository = UserRepository.getInstance();

    public User search(String username){

        return
                userRepository.findAll().stream().filter(u->u.getUsername().equals(username))
                        .collect(Collectors.toList()).get(0);
    }

    public List<User> search(String firstName , String lastName){

        return
                userRepository.findAll().stream().filter(u->u.getFirstName().equals(firstName))
                        .filter(u->u.getLastName().equals(lastName)).collect(Collectors.toList());
    }
}
