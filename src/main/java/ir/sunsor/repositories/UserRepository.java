package ir.sunsor.repositories;

import ir.sunsor.config.CrudRepository;
import ir.sunsor.entities.User;

public class UserRepository extends CrudRepository<User, Long> {

    private static UserRepository userRepository;

    private UserRepository(){}

    public static UserRepository getInstance(){
        if (userRepository==null)
            userRepository = new UserRepository();
        return userRepository;
    }


    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
