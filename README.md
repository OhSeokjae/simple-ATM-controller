# simple-ATM-controller


## Requirements
At least the following flow should be implemented:

### Insert Card => PIN number => Select Account => See Balance/Deposit/Withdraw

For simplification, there are only 1 dollar bills in this world, no cents. **Thus account balance can be represented in integer.**

Your code doesn't need to integrate with a real bank system, **but keep in mind that we may want to integrate it with a real bank system in the future.**
It doesn't have to integrate with a real cash bin in the ATM, **but keep in mind that we'd want to integrate with that in the future.**
And even if we integrate it with them, **we'd like to test our code.**

Implementing bank integration and ATM hardware like cash bin and card reader is not a scope of this task, **but testing the controller part (not including bank system, cash bin etc) is within the scope.**

A bank API wouldn't give the ATM the PIN number, **but it can tell you if the PIN number is correct or not.**

Based on your work, another engineer should be able to implement the user interface. **You don't need to implement any REST API, RPC, network communication etc, but just functions/classes/methods, etc.**

You can simplify some complex real world problems if you think it's not worth illustrating in the project.

## Context Diagram
![simple_atm-context diagram drawio](https://user-images.githubusercontent.com/34128826/189163600-d451fb59-e6b8-4a67-8564-6c73b8210962.png)

## Usercase Diagram

User
- view home ui
- insert card
- enter pin (card's pin number)
- select account (card could be linked with multiple accounts)
- see current balance of accout and history of account(deposit / withdraw)

## Code Structure
/model
    Account.java
        accountId : String
        accountName : String
        balance : int
    Card.java
        cardNumber : int
        cardName : String
        expiredDate : int
        userId : String
        authToken : String
    Deposit.java
        depositId : String
        accountId : String
        depositTime : Date
    Withdraw.java
        withdrawId : String
        accountId : String
        withdrawTime : Date
    User.java
        userId : String
        userName : String
/controller
    AccountController.java
        List<Account> getAccounts(Card card);
        int getAccountBalance(int AccountId);
        List<Deposit> getDeposits(Card card);
        List<Withdraw> getWithdraws(Card card);
    CardController.java
        Card validateCardwithPin(int cardNumber, int pin)
/database
    BankDatabase.java
        getAccountBalanceFromDB(int accountId);

Atm.java

    
     - view account balance
     
     - deposit funds

## Class Diagram



##  


## Issues of this problem
1. In korea, card could be linked with only 1 account.
In korea banking system and ATM user experience, user couldn't meet step3(select account).

2. Missed account PIN number step.
In this problem, there are 4 steps.
insert card -> pin -> select account -> see history
but, generally, in order to see account history, users have to input pin after select account. in the problem the PIN is related with Card. so, I designed pin as card's attribute.



## 
