package model;

import java.util.Date;

public class Card {
    
    private String cardId;
    private String cardName;
    private Date expirationDate;
    private int pin;
    private User user;

    public Card(String cardId, Date expirationDate) {
        this.cardId = cardId;
        this.cardName = "";
        this.expirationDate = expirationDate;
        this.pin = 0;
        this.user = null;
    }

    public Card(String cardId, String cardName, Date expirationDate, int pin, User user) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.expirationDate = expirationDate;
        this.pin = pin;
        this.user = user;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
   
}
