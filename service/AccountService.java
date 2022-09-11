package service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import model.Account;
import model.AuthToken;
import model.Deposit;
import model.User;
import model.Withdraw;
import repository.BankRepositorySingleton;
import util.RandomIdGenerator;

public class AccountService {
    BankRepositorySingleton bankInstance = BankRepositorySingleton.getInstance();

    public List<Account> getAccountList(AuthToken authToken) throws Exception {
        AuthTokenService authTokenService = new AuthTokenService();
        if(!authTokenService.validAuthToken(authToken)) {
            throw new Exception("[AccountService: getAccountList] Invalid auth token " + authToken);
        }
        User user = new AuthTokenService().getAuthTokenUser(authToken);
        
        return bankInstance.getAccounts(user);
    }

    public void createAccount(Account account) {
        BankRepositorySingleton bankInstance;
        bankInstance = BankRepositorySingleton.getInstance();

        bankInstance.putAccount(account.getUser(), account);
    }
    
    public int credit(Account account, int amount) throws Exception {
        String depositId = RandomIdGenerator.createId();
        final ReentrantLock locker = new ReentrantLock();

        try {
            locker.lock();
            int totalAmount = bankInstance.getBalance(account) + amount;
            bankInstance.setBalance(account, totalAmount);
            Deposit deposit = new Deposit(depositId, account.getAccountId(), amount, totalAmount, new Date());
            bankInstance.putDeposit(account, deposit);
        } catch (Exception e) {
            throw new Exception("[AccountService:credit] Bank Instance is unavailable");
        } finally {
            locker.unlock();
        }
        
        return bankInstance.getBalance(account);
    }

    public int debit(Account account, int amount) throws Exception {
        String withdrawId = RandomIdGenerator.createId();
        final ReentrantLock locker = new ReentrantLock();

        try {
            locker.lock();
            int totalAmount = bankInstance.getBalance(account) - amount;
            if(totalAmount < 0){
                System.out.println("[AccountService:debit] The withdrawal amount has exceeded the balance.");
            } else {
                bankInstance.setBalance(account, totalAmount);
                Withdraw withdraw = new Withdraw(withdrawId, account.getAccountId(), amount, totalAmount, new Date());
                bankInstance.putWithdraw(account, withdraw);
            }
        } catch (Exception e) {
            throw new Exception("[AccountService:debit] Bank Instance is unavailable");
        } finally {
            locker.unlock();
        }
        
        return bankInstance.getBalance(account);
    }

    public List<Deposit> getDeposits(AuthToken authToken, Account account) throws Exception {

        try {
            validateAuthTokenAndAccount(authToken, account);
        } catch (Exception e) {
            System.out.println("[AccountService:getDeposits] Exception :" + e);
        }

        return bankInstance.getDeposits(account);
    }

    public List<Withdraw> getWithdraws(AuthToken authToken, Account account) {
        try {
            validateAuthTokenAndAccount(authToken, account);
        } catch (Exception e) {
            System.out.println("[AccountService:getWithdraws] Exception :" + e);
        }

        return bankInstance.getWithdraw(account);
    }

    private void validateAuthTokenAndAccount(AuthToken authToken, Account account) throws Exception {
        AuthTokenService authTokenService = new AuthTokenService();
        if(!authTokenService.validAuthToken(authToken)) {
            throw new Exception("[AccountService:validateAuthTokenAndAccount] Invalid auth token" + authToken);
        }

        User user = authTokenService.getAuthTokenUser(authToken);
        if(!sameAccountUserAndAuthTokenUser(account, user)) {
            throw new Exception("[AccountService:validateAuthTokenAndAccount] authToken's user info is not matched with account's user : " + user);
        }
    }

    private boolean sameAccountUserAndAuthTokenUser(Account account, User user) {
        return user == account.getUser();
    }

	public int getBalance(AuthToken authToken, Account account) {
        try {
            validateAuthTokenAndAccount(authToken, account);
        } catch (Exception e) {
            System.out.println("[AccountService:getBalance] Exception :" + e);
        }

        return bankInstance.getBalance(account);
	}
}
