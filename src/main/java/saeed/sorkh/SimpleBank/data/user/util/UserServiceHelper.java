package saeed.sorkh.SimpleBank.data.user.util;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import saeed.sorkh.SimpleBank.data.user.UserService;

@Service
public class UserServiceHelper {

    private final UserService userService;

    public UserServiceHelper(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (userService.count() == 0)
            userService.createUser();
    }
}
