package model;

import java.util.Date;

public class Withdraw {
    private String withdrawId;
    private String accountId;
    private int amount;
    private int totalAmount;
    private Date withdrawDate;

    public Withdraw(String withdrawId, String accountId, int amount, int totalAmount, Date withdrawDate) {
        this.withdrawId = withdrawId;
        this.accountId = accountId;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.withdrawDate = withdrawDate;
    }
    public String getWithdrawId() {
        return withdrawId;
    }
    public void setWithdrawId(String withdrawId) {
        this.withdrawId = withdrawId;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Date getWithdrawDate() {
        return withdrawDate;
    }
    public void setWithdrawDate(Date withdrawDate) {
        this.withdrawDate = withdrawDate;
    }
}
