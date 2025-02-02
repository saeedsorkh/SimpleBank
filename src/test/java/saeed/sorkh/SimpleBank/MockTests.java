package saeed.sorkh.SimpleBank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saeed.sorkh.SimpleBank.data.money_transition.MoneyTransitionRepository;
import saeed.sorkh.SimpleBank.data.money_transition.MoneyTransitionService;
import saeed.sorkh.SimpleBank.data.money_transition.util.TransitionAmountReport;

import java.util.function.Supplier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockTests {

    @Mock
    private MoneyTransitionRepository moneyTransitionRepository;

    @InjectMocks
    private MoneyTransitionService moneyTransitionService;

    @Test
    public void a() {
        long total = 1000L, totalDeposit = 800L, totalWithdraw = 200L;

        Supplier<Object[]> supplier = () -> {
            Object[] object = new Object[3];

            object[0] = total;
            object[1] = totalDeposit;
            object[2] = totalWithdraw;
            return object;
        };

        when(moneyTransitionRepository.calculateAllTransitionAmounts()).thenReturn(supplier.get());

        TransitionAmountReport transitionAmountReport = moneyTransitionService.calculateAllTransitionAmounts();

        Assertions.assertEquals(total, transitionAmountReport.totalAmount());
        Assertions.assertEquals(totalDeposit, transitionAmountReport.totalDeposit());
        Assertions.assertEquals(totalWithdraw, transitionAmountReport.totalWithdraw());
    }

}
