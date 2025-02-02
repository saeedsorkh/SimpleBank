package saeed.sorkh.SimpleBank.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import saeed.sorkh.SimpleBank.data.money_transition.MoneyTransitionService;
import saeed.sorkh.SimpleBank.data.money_transition.util.TransitionAmountReport;

import java.time.LocalDateTime;

@Service
public class ScheduleService {

    private final MoneyTransitionService moneyTransitionService;

    public ScheduleService(MoneyTransitionService moneyTransitionService) {
        this.moneyTransitionService = moneyTransitionService;
    }

    /**
     * executes every day at 00:00 AM
     */
    @Scheduled(cron = "0 0 0 * * *")
    // @Scheduled(initialDelay = 1000, fixedDelay = 2000) // every second, just for test
    private void printMoneyTransitionLogs() {
        System.out.println(getMoneyTransitionLogs());
    }

    public String getMoneyTransitionLogs() {
        TransitionAmountReport transitionAmountReport = moneyTransitionService.calculateAllTransitionAmounts();

        StringBuilder moneyTransitionLogs = new StringBuilder();
        moneyTransitionLogs.append("Money transition logs in: '").append(LocalDateTime.now()).append("': \n");
        moneyTransitionLogs.append("Total transitions: ").append(transitionAmountReport.totalAmount()).append("\n");
        moneyTransitionLogs.append("Total deposit transitions: ").append(transitionAmountReport.totalDeposit()).append("\n");
        moneyTransitionLogs.append("Total withdraw transitions: ").append(transitionAmountReport.totalWithdraw()).append("\n");
        return moneyTransitionLogs.toString();
    }
}
