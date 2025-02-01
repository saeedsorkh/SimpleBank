package saeed.sorkh.SimpleBank.data.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.base-url}/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/balance")
    public ResponseEntity<Map<String, String>> getUserBalance(@RequestParam Long user_id) {
        Map<String, String> userBalanceMap = new HashMap<>();
        userBalanceMap.put("balance", userService.getUserBalance(user_id).toString());

        return ResponseEntity.ok(userBalanceMap);
    }

}
