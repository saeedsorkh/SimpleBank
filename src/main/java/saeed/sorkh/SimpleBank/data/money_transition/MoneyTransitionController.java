package saeed.sorkh.SimpleBank.data.money_transition;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.base-url}/money-transitions")
public class MoneyTransitionController {

    private final MoneyTransitionService moneyTransitionService;

    public MoneyTransitionController(MoneyTransitionService moneyTransitionService) {
        this.moneyTransitionService = moneyTransitionService;
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> transferMoney(@RequestParam Long user_id, @RequestParam Long amount) {
        Map<String, String> userBalanceMap = new HashMap<>();
        userBalanceMap.put("reference_id", moneyTransitionService.transferMoney(user_id, amount).getReferenceId());

        return ResponseEntity.ok(userBalanceMap);
    }

}
