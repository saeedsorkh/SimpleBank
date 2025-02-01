package saeed.sorkh.SimpleBank.data.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserE getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user with id '" + id + "' not found :)"));
    }

    public Long getUserBalance(Long userId) {
        UserE user = getById(userId);

        return user.getWallet().getBalance();
    }
}
