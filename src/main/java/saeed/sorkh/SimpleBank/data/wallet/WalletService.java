package saeed.sorkh.SimpleBank.data.wallet;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public WalletE getByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet with userId '" + userId + "' not found :)"));
    }

    @Transactional
    public WalletE updateWalletBalance(Long userId, Long amount) {
        WalletE wallet = getByUserId(userId);

        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);
    }

    @Transactional
    public WalletE createWallet(){
        WalletE wallet = new WalletE();

        return walletRepository.save(wallet);
    }
}
