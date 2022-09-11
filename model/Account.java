package model;

public class Account {
    private String accountId;
    private String accountName;
    private User user;
    private int balance;

    public Account(String accountId, String accountName, User user, int balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.user = user;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}