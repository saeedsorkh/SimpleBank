package saeed.sorkh.SimpleBank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saeed.sorkh.SimpleBank.data.user.UserE;
import saeed.sorkh.SimpleBank.data.user.UserService;
import saeed.sorkh.SimpleBank.data.wallet.WalletService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WalletTests {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Test
    public void testUpdateWalletBalance(){
        long amount = 5000L;

        UserE user = userService.createUser();

        walletService.updateWalletBalance(user.getId(), amount);

        assertEquals(amount, userService.getUserBalance(user.getId()));
    }

}
