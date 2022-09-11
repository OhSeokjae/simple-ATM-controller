package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Account;
import model.AuthToken;
import model.Card;
import model.Deposit;
import model.User;
import model.Withdraw;

public class BankRepositorySingleton {
    
    private static BankRepositorySingleton instance = null;
    
    private HashMap<String, Card> cards;
    private HashMap<AuthToken,  User> tokens;
    private HashMap<User, ArrayList<Account>> accounts;
    private HashMap<Account, ArrayList<Deposit>> deposits;
    private HashMap<Account, ArrayList<Withdraw>> withdraws;
    
    private BankRepositorySingleton() {
        this.tokens = new HashMap<>();
        this.cards = new HashMap<>();
        this.accounts = new HashMap<>();
        this.deposits = new HashMap<>();
        this.withdraws = new HashMap<>();
    }

    public static BankRepositorySingleton getInstance() {
        if(instance == null) {
            instance = new BankRepositorySingleton();
        }
        return instance;
    }

    public void putAuthToken(AuthToken authToken, User user) {
        tokens.put(authToken, user);
    }

    public User getAuthTokenUser(AuthToken authToken) {
        return tokens.get(authToken);
    }

    public boolean validAuthToken(AuthToken authToken) {
        return tokens.containsKey(authToken);
    }

    public boolean isValidCard(String cardId) {
        return cards.containsKey(cardId);
    }

    public HashMap<String, Card> getCards() {
        return cards;
    }

    public Card putCard(Card card) {
        cards.put(card.getCardId(), card);
        return cards.get(card.getCardId());
    }

    public Card getCard(String cardId) {
        return cards.get(cardId);
    }

    public List<Account> getAccounts(User user) {
        return accounts.get(user);
    }

    public void putAccount(User user, Account account) {
        ArrayList<Account> accountOfUser = new ArrayList<>();
        if(accounts.containsKey(user)) {
            accountOfUser = accounts.get(user);
        }
        accountOfUser.add(account);
        accounts.put(user, accountOfUser);
    }

    public List<Deposit> getDeposits(Account account) {
        return deposits.get(account);
    }

    public void putDeposit(Account account, Deposit deposit) {
        ArrayList<Deposit> depositArrayList = new ArrayList<>();
        if(deposits.containsKey(account)) {
            depositArrayList = deposits.get(account);
        }
        depositArrayList.add(deposit);
        deposits.put(account, depositArrayList);
    }


    public List<Withdraw> getWithdraw(Account account) {
        return withdraws.get(account);
    }

    public boolean isInvalidAuthToken(AuthToken authToken) {
        return tokens.containsKey(authToken);
    }

    public int getBalance(Account account) {
        int accountIndex = accounts.get(account.getUser()).indexOf(account);
        
        return accounts.get(account.getUser())
                       .get(accountIndex)
                       .getBalance();
    }

    public void setBalance(Account account, int totalAmount) {
        int accountIndex = accounts.get(account.getUser()).indexOf(account);
        accounts.get(account.getUser())
                .get(accountIndex)
                .setBalance(totalAmount);
    }

    public void putWithdraw(Account account, Withdraw withdraw) {
        ArrayList<Withdraw> withdrawArrayList = new ArrayList<>();
        if(withdraws.containsKey(account)) {
            withdrawArrayList = withdraws.get(account);
        }
        withdrawArrayList.add(withdraw);
        withdraws.put(account, withdrawArrayList);
    }
}
