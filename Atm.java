import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.*;
import model.*;
import model.AuthToken;
import repository.*;
import service.*;
import util.RandomIdGenerator;

public class Atm {

    public static void main(String[] args) throws Exception {
        final int PIN1 = 1234;
        final int PIN2 = 1234;

        System.out.println("===============  Simple ATM Controller Test ");
        
        /*  00. Create Bank Instance */
        System.out.println("===============  0. Init ATM System");
        // Create Bank
        System.out.println("===============  0-1. Create Bank Instance");
        BankRepositorySingleton instance = BankRepositorySingleton.getInstance();
        
        // Create Bank Users
        System.out.println("===============  0-2. Create User");
        String userId1 = RandomIdGenerator.createId();
        String userId2 = RandomIdGenerator.createId();

        User user1 = new User(userId1, "Seokjae Oh");
        User user2 = new User(userId2, "Gildong Hong");

        // Create Account
        System.out.println("===============  0-3. Create Account");
        String accountId1 = RandomIdGenerator.createId();
        String accountId2 = RandomIdGenerator.createId();
        String accountId3 = RandomIdGenerator.createId();
        String accountId4 = RandomIdGenerator.createId();
        Account account1 = new Account(accountId1, "ACCOUNT NAME 01", user1, 0);
        Account account2 = new Account(accountId2, "ACCOUNT NAME 02", user1, 0);
        Account account3 = new Account(accountId3, "ACCOUNT NAME 03", user1, 0);
        Account account4 = new Account(accountId4, "ACCOUNT NAME 04", user2, 0);

        AccountService accountService = new AccountService();
        accountService.createAccount(account1);
        accountService.createAccount(account2);
        accountService.createAccount(account3);
        accountService.createAccount(account4);

        // // Create initial deposit
        // String depositId1 = RandomIdGenerator.createId();
        // String depositId2 = RandomIdGenerator.createId();
        // String depositId3 = RandomIdGenerator.createId();
        // String depositId4 = RandomIdGenerator.createId();
        // Deposit deposit1 = new Deposit(depositId1, accountId1, 100, new Date());
        // Deposit deposit2 = new Deposit(depositId2, accountId2, 500, new Date());
        // Deposit deposit3 = new Deposit(depositId3, accountId3, 1000, new Date());
        // Deposit deposit4 = new Deposit(depositId4, accountId4, 9999, new Date());

        accountService.credit(account1, 1);
        accountService.credit(account1, 50);
        accountService.credit(account1, 800);
        accountService.credit(account2, 50);
        accountService.credit(account2, 150);
        accountService.credit(account2, 500);
        accountService.credit(account3, 1000);
        accountService.credit(account4, 99999);

        System.out.println("===============  0-4. create credit/debit history");

        /*  01. Insert Card Test */
        System.out.println("===============  1. Insert Card Test");
        //Create Cards
        
        System.out.println("===============  1-1. Create Cards");
        CardService cardService = new CardService();
        Card card1 = cardService.createCard("Card001 : Seokjae Oh's private card", PIN1, user1);
        Card card2 = cardService.createCard("Card002 : ATM test card", PIN2, user2);

        System.out.println("card1 - getCardId() : " + card1.getCardId() + ", getCardName() : " + card1.getCardName() + ", getExpirationDate() : " + card1.getExpirationDate());
        System.out.println("card2 - getCardId() : " + card2.getCardId() + ", getCardName() : " + card2.getCardName() + ", getExpirationDate() : " + card2.getExpirationDate());

        // //Get Cards from DB
        // HashMap<String, Card> cards = instance.getCards();
        System.out.println("===============  1-2. List of Create Card in repository");
        HashMap<String, Card> cards = instance.getCards();

        for (Map.Entry<String, Card> card : cards.entrySet()) {
            System.out.println(card.getKey() + " = " + card.getValue().getCardName() + " : " + card.getValue().getExpirationDate());
        }
   
        System.out.println("===============  1-3. Validate Card (case1: Correct Card)");
        boolean resultOfCardValidation = new AtmController().isValidCard(card1.getCardId(), card1.getExpirationDate());
        if(resultOfCardValidation) {
            System.out.println("case1: Correct Card : Test Success");
        } else {
            System.out.println("case1: Correct Card : Test Fail");
        }
    
        System.out.println("===============  1-4. Validate Card (case2: Incorrect Card)");

        resultOfCardValidation = new AtmController().isValidCard("AnnoymousID", new Date());
        if(!resultOfCardValidation) {
            System.out.println("case2: Inorrect Card : Test Success");
        } else {
            System.out.println("case2: Incorrect Card : Test Fail");
        }

        System.out.println("===============  2. Input PIN");
        AuthToken authToken = new AtmController().validatePin(card1.getCardId(), PIN1);
        System.out.println("===============  2-1. Display Account List");
        System.out.println("====  authToken : " + authToken);
        List<Account> accounts = new AccountService().getAccountList(authToken);
        System.out.println("====  Card1's Users accounts : " + accounts);
        
        for(Account a : accounts) {
            System.out.println(a.getAccountId() + " : " + a.getAccountName() + " : " + a.getBalance() + " : " + a.getUser().getUserName());
        }

        System.out.println("===============  3. Select Accounts");
        System.out.println("===============  3-1. Show Balance From Accounts");
        int balance1 = new AtmController().getBalanceFromAccount(authToken, accounts.get(0));
        System.out.println("====  balance1 : " + balance1);

        int balance2 = new AtmController().getBalanceFromAccount(authToken, accounts.get(1));
        System.out.println("====  balance2 : " + balance2);
        
        System.out.println("===============  3-2. Show Deposit History From Accounts");
        System.out.println("====  3-2. Account1 Deposit History");
        List<Deposit> deposits = new AtmController().getDepositsFromAccount(authToken, accounts.get(0));
        if(deposits != null){
            for(Deposit deposit : deposits) {
                System.out.println("====  deposit : " + deposit.getAccountId() + " : " + deposit.getDepositId() + " : " + deposit.getAmount() + " : " + deposit.getTotalAmount() + " : " + deposit.getDepositDate());
            }    
        } else {
            System.out.println("====  deposit : empty deposit history"); 
        }
        System.out.println("====  3-2. Account2 Deposit History");
        deposits = new AtmController().getDepositsFromAccount(authToken, accounts.get(1));
        if(deposits != null){
            for(Deposit deposit : deposits) {
                System.out.println("====  deposit : " + deposit.getAccountId() + " : " + deposit.getDepositId() + " : " + deposit.getAmount() + " : " + deposit.getTotalAmount() + " : " + deposit.getDepositDate());
            }    
        } else {
            System.out.println("====  deposit : empty deposit history"); 
        }

        accountService.debit(account1, 50);
        accountService.debit(account1, 45);
        accountService.debit(account1, 200);

        accountService.debit(account2, 2000);

        System.out.println("===============  3-3. Show Withdraw History From Accounts");
        System.out.println("====  3-3. Account1 Withdraw History");
        List<Withdraw> withdraws = new AtmController().getWithdrawsFromAccount(authToken, accounts.get(0));
        if(withdraws != null){
            for(Withdraw withdraw : withdraws) {
                System.out.println("====  withdraw : " + withdraw.getAccountId() + " : " + withdraw.getWithdrawId() + " : " + withdraw.getAmount() + " : " + withdraw.getWithdrawDate());
            }    
        } else {
            System.out.println("====  withdraw : empty withdraw history"); 
        }
        System.out.println("====  3-3. Account2 Withdraw History");
        withdraws = new AtmController().getWithdrawsFromAccount(authToken, accounts.get(1));
        if(withdraws != null){
            for(Withdraw withdraw : withdraws) {
                System.out.println("====  withdraw : " + withdraw.getAccountId() + " : " + withdraw.getWithdrawId() + " : " + withdraw.getAmount() + " : " + withdraw.getWithdrawDate());
            }    
        } else {
            System.out.println("====  withdraw : empty withdraw history"); 
        }
    }
}
