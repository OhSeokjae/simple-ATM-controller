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

## Build and Run
```
git clone https://github.com/OhSeokjae/simple-ATM-controller.git
javac Atm.java
java Atm
```

## Context Diagram
![simple_atm-context diagram drawio](https://user-images.githubusercontent.com/34128826/189163600-d451fb59-e6b8-4a67-8564-6c73b8210962.png)

## Usercase Diagram
![image](https://user-images.githubusercontent.com/34128826/189533610-e07f9430-b508-45c0-b2d8-346e45399e88.png)

## Architecture of simple ATM(Module View)
![image](https://user-images.githubusercontent.com/34128826/189534296-dc320876-5425-4b84-921b-2a9859d75034.png)

## Code Structure
### Atm.java
Atm application, tested with this application
### /controller
 : application call this controller method as interface
### /model
 : models of ATM
### /service
 : services of ATM
### /database
 : In this toy project, there isn't real bank system(real database). this repository should be replaced after implementation with a real banking system and database.
 the repository should be managed with a single instance and should be supported multi-thread. Thus, it should be designed and developed with a singleton pattern and mutex.

## Issues and remain tasks of this problem
1. In Korea, the card could be linked with only 1 account.
In Korea banking system and ATM user experience, users couldn't meet step3(select account).

2. Missed account PIN number step.
In this problem, there are 4 steps.
insert card -> pin -> select account -> see history
but, generally, in order to see account history, users have to input a pin after selecting an account. in the problem, the PIN is related to the Card. so, I designed the pin as the card's attribute.

3. AuthToken and Security
To avoid security issues, I have designed with simple authToken. The token should be issued from only permitted devices. So, ATM hardware machines must have secure storage to store certification files.

4. credit and debit
Cash bind and card reader tasks aren't in this project scope. but, in order to serve deposit and withdraw history, I have implemented credit and debit methods in AccountService. The credit/debit controllers weren't implemented.

5. Interface with Banksystem.
The Bank system will provide an API that enables secure communication. So, singleton and mutex should be managed in backend. In my project, BankRepository and Services code should be migrated to backend.
