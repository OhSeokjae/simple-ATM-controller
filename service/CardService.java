package service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.AuthToken;
import model.Card;
import model.User;
import repository.BankRepositorySingleton;
import util.RandomIdGenerator;

public class CardService {
    BankRepositorySingleton bankInstance = BankRepositorySingleton.getInstance();
    private final int CARD_EXPIRATION_DAYS = 365 * 5;

    public boolean isValidCard(Card card) {
        return bankInstance.isValidCard(card.getCardId());
    }

    public Card createCard(String cardName, int pin, User user) {

        String cardId = RandomIdGenerator.createId();
        
        while(bankInstance.isValidCard(cardId)) {
            System.out.println("[CardService:createCard] Invalid Card ID, Generate New Card ID ");
            cardId = RandomIdGenerator.createId();
        }
        
        long cardExpirationPeriod = TimeUnit.MILLISECONDS.convert(CARD_EXPIRATION_DAYS, TimeUnit.DAYS);
        Date cardExpirationDate = new Date(System.currentTimeMillis() + cardExpirationPeriod);

        Card card = new Card(cardId, cardName, cardExpirationDate, pin, user);
    
        return bankInstance.putCard(card);
    }

    public AuthToken validatePin(String cardId, int pin) throws Exception  {

        if(pin != bankInstance.getCard(cardId).getPin()) {
            throw new Exception("[CardService : validatePin] wrong PIN number");
        }

        return new AuthTokenService().generateAuthToken(bankInstance.getCard(cardId).getUser());        
    }
}
