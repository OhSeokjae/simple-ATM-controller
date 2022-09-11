package controller;

import java.util.Date;
import java.util.List;

import model.Account;
import model.AuthToken;
import model.Card;
import model.Deposit;
import model.Withdraw;
import service.AccountService;
import service.CardService;

public class AtmController {
    
    //Step1. Insert Card
    public boolean isValidCard(String cardId, Date expirationDate) {
        Card targetCard = new Card(cardId, expirationDate);
        return new CardService().isValidCard(targetCard);
    }

    //Step2. Input Pin / Validate Pin
    public AuthToken validatePin(String cardId, int pin) throws Exception {
        return new CardService().validatePin(cardId, pin);
    }

    //Step3. Get Account List
    public List<Account> getAccountList(AuthToken authToken) throws Exception {
        return new AccountService().getAccountList(authToken);
    } 

    //Step4. Get Balance From Account
    public int getBalanceFromAccount(AuthToken authToken, Account account) {
        return new AccountService().getBalance(authToken, account);
    }

    //Step5. Get Deposit History From Account
    public List<Deposit> getDepositsFromAccount(AuthToken authToken, Account account) throws Exception {
        return new AccountService().getDeposits(authToken, account);
    }

    //Step6. Get Withdraw History From Account
    public List<Withdraw> getWithdrawsFromAccount(AuthToken authToken, Account account) throws Exception {
        return new AccountService().getWithdraws(authToken, account);
    }
}
