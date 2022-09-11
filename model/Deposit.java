package model;

import java.util.Date;

public class Deposit {
    private String depositId;
    private String accountId;
    private int amount;
    private int totalAmount;
    private Date depositDate;

    public Deposit(String depositId, String accountId, int amount, int totalAmount, Date depositDate) {
        this.depositId = depositId;
        this.accountId = accountId;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.depositDate = depositDate;
    }
    public String getDepositId() {
        return depositId;
    }
    public void setDepositId(String depositId) {
        this.depositId = depositId;
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
    public Date getDepositDate() {
        return depositDate;
    }
    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
