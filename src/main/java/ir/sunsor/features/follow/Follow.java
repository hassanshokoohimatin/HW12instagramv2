package ir.sunsor.features.follow;

import ir.sunsor.entities.User;
import ir.sunsor.repositories.UserRepository;

public class Follow {

    static UserRepository userRepository = UserRepository.getInstance();

    public static void follow(User follower , User following){

        follower.getFollowings().add(following);
        userRepository.update(follower);

        following.getFollowers().add(follower);
        userRepository.update(following);
    }
}
