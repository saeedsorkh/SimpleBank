package saeed.sorkh.SimpleBank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saeed.sorkh.SimpleBank.data.money_transition.MoneyTransitionService;
import saeed.sorkh.SimpleBank.data.user.UserE;
import saeed.sorkh.SimpleBank.data.user.UserService;
import saeed.sorkh.SimpleBank.exception.InsufficientBalanceException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest
public class MoneyTransitionTests {

    @Autowired
    private MoneyTransitionService moneyTransitionService;

    @Autowired
    private UserService userService;

    @Test
    public void testMoneyTransition() {
        List<Long> amounts = List.of(2000L, -500L, 3000L, -100L, -250L);
        AtomicLong expectedAmount = new AtomicLong();

        UserE user = userService.createUser();

        amounts.forEach(amount -> {
            moneyTransitionService.transferMoney(user.getId(), amount);

            expectedAmount.addAndGet(amount);
            Assertions.assertEquals(expectedAmount.get(), userService.getUserBalance(user.getId()));
        });

        Assertions.assertEquals(expectedAmount.get(), userService.getUserBalance(user.getId()));
    }

    @Test
    public void testMoneyTransitionException() {
        long amount = 1000L;

        UserE user = userService.createUser();
        moneyTransitionService.transferMoney(user.getId(), amount);

        Assertions.assertEquals(amount, userService.getUserBalance(user.getId()));

        moneyTransitionService.transferMoney(user.getId(), -amount);

        Assertions.assertEquals(0, userService.getUserBalance(user.getId()));

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> moneyTransitionService.transferMoney(user.getId(), 0L)
        );

        Assertions.assertThrows(
                InsufficientBalanceException.class,
                () -> moneyTransitionService.transferMoney(user.getId(), -100L)
        );
    }
}
