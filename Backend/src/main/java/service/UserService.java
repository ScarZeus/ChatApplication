package service;

import repository.UserRepo.UserRepository;
import lombok.RequiredArgsConstructor;
import model.Status;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public User getUser(String username) {
        return null;
    }
    public void saveUser(User user) {
     user.setStatus(Status.ONLINE);
     userRepository.save(user);
    }
    public void disconnect(User user) {
        User storedUser = userRepository.findById(user.getUsername()).orElse(null);
    if (storedUser != null) {
        user.setStatus(Status.OFFLINE);
        userRepository.save(user);
    }

    }

   public List<User> getConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
