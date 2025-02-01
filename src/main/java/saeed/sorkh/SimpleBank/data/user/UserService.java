package saeed.sorkh.SimpleBank.data.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import saeed.sorkh.SimpleBank.data.wallet.WalletService;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final WalletService walletService;

    public UserService(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    public UserE getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id '" + id + "' not found :)"));
    }

    public Long getUserBalance(Long userId) {
        UserE user = getById(userId);

        return user.getWallet().getBalance();
    }

    @Transactional
    public UserE createUser() {
        UserE user = new UserE();
        user.setWallet(walletService.createWallet());
        return userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

}
