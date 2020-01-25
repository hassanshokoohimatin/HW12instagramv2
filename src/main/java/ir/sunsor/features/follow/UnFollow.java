package ir.sunsor.features.follow;

import ir.sunsor.entities.User;
import ir.sunsor.repositories.UserRepository;

public class UnFollow {

    static UserRepository userRepository = UserRepository.getInstance();

    public static void unFollow(User follower , User following){
        userRepository.findById(follower.getId()).getFollowings().remove(following);
        userRepository.update(follower);

        userRepository.findById(following.getId()).getFollowers().remove(follower);
        userRepository.update(following);
    }
}
