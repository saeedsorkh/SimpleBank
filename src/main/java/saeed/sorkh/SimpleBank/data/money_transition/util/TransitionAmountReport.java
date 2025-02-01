package saeed.sorkh.SimpleBank.data.money_transition.util;

import lombok.Getter;

@Getter
public class TransitionAmountReport {

    private long totalAmount, totalDeposit, totalWithdraw;

    public TransitionAmountReport(long totalAmount, long totalDeposit, long totalWithdraw) {
        this.totalAmount = totalAmount;
        this.totalDeposit = totalDeposit;
        this.totalWithdraw = totalWithdraw;
    }
}
