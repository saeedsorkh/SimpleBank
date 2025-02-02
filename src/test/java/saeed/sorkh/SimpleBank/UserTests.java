package saeed.sorkh.SimpleBank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saeed.sorkh.SimpleBank.data.user.UserE;
import saeed.sorkh.SimpleBank.data.user.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    public void testUserCreation() {
        UserE user = userService.createUser();

        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getWallet());
        assertNotNull(user.getWallet().getId());
    }

    @Test
    public void testUserBalance() {
        UserE user = userService.createUser();

        assertEquals(0, userService.getUserBalance(user.getId()));
    }
}
