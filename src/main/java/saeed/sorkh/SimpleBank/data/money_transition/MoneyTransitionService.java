package saeed.sorkh.SimpleBank.data.money_transition;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import saeed.sorkh.SimpleBank.data.money_transition.util.TransitionAmountReport;
import saeed.sorkh.SimpleBank.data.user.UserService;
import saeed.sorkh.SimpleBank.data.wallet.WalletE;
import saeed.sorkh.SimpleBank.data.wallet.WalletService;
import saeed.sorkh.SimpleBank.exception.InsufficientBalanceException;

import java.util.Optional;
import java.util.Random;

@Service
public class MoneyTransitionService {

    private static final byte REFERENCE_ID_LENGTH = 11;

    private final MoneyTransitionRepository moneyTransitionRepository;

    private final UserService userService;
    private final WalletService walletService;

    public MoneyTransitionService(MoneyTransitionRepository moneyTransitionRepository, UserService userService,
                                  WalletService walletService) {
        this.moneyTransitionRepository = moneyTransitionRepository;
        this.userService = userService;
        this.walletService = walletService;
    }

    public Optional<MoneyTransitionE> getByReferenceId(String referenceId) {
        return moneyTransitionRepository.findByReferenceId(referenceId);
    }

    @Transactional
    public MoneyTransitionE transferMoney(Long userId, Long amount) {
        if (amount == 0) throw new IllegalArgumentException("Amount cannot be zero!");
        else if (amount < 0) {
            Long userBalance = userService.getUserBalance(userId);
            if (userBalance < Math.abs(amount)) throw new InsufficientBalanceException("Insufficient balance!");
        }

        WalletE wallet = walletService.updateWalletBalance(userId, amount);

        return createMoneyTransition(wallet, amount);
    }

    @Transactional
    public MoneyTransitionE createMoneyTransition(WalletE wallet, Long amount) {
        MoneyTransitionE moneyTransition = new MoneyTransitionE();

        moneyTransition.setWalletE(wallet);
        moneyTransition.setAmount(amount);
        moneyTransition.setBalance(wallet.getBalance());
        moneyTransition.setReferenceId(generateReferenceId());
        return moneyTransitionRepository.save(moneyTransition);
    }

    public String generateReferenceId() {
        Random random = new Random();
        StringBuilder referralCode = new StringBuilder();

        do {
            byte rnd = (byte) (referralCode.isEmpty() ? random.nextInt(9) + 1 :
                    random.nextInt(10));
            referralCode.append(rnd);
        } while (referralCode.length() < REFERENCE_ID_LENGTH);

        if (getByReferenceId(referralCode.toString()).isPresent())
            return generateReferenceId();

        return referralCode.toString();
    }

    public TransitionAmountReport calculateAllTransitionAmounts() {
        Object[] amounts = (Object[]) moneyTransitionRepository.calculateAllTransitionAmounts();
        return new TransitionAmountReport(
                ((Number) amounts[0]).longValue(),
                ((Number) amounts[1]).longValue(),
                ((Number) amounts[2]).longValue()
        );
    }
}
