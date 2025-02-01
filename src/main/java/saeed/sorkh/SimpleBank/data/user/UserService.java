package saeed.sorkh.SimpleBank.data.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getUserBalance(Long userId) {
        UserE user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user with id '" + userId + "' not found :)"));

        return user.getWallet().getBalance();
    }
}
