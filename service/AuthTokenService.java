package service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.AuthToken;
import model.User;
import repository.BankRepositorySingleton;
import util.RandomIdGenerator;

public class AuthTokenService {
    BankRepositorySingleton bankInstance = BankRepositorySingleton.getInstance();
        
    private final int TOKEN_EXPIRATION_PERIOD_MINUTES = 5;
    
    public AuthToken generateAuthToken(User user) {

        String authTokenValue = RandomIdGenerator.createId();

        long expirationPeriod = TimeUnit.MILLISECONDS.convert(TOKEN_EXPIRATION_PERIOD_MINUTES, TimeUnit.MINUTES);
        Date expirationDate =  new Date(System.currentTimeMillis() + expirationPeriod);
    
        AuthToken authToken = new AuthToken(authTokenValue, expirationDate);
       
        while(bankInstance.isInvalidAuthToken(authToken)) {
            authToken = new AuthToken(authTokenValue, expirationDate);
        }

        bankInstance.putAuthToken(authToken, user);
        return authToken;
    }

    public boolean validAuthToken(AuthToken authToken) throws Exception {
        if(!bankInstance.validAuthToken(authToken)) {
            throw new Exception("[AuthTokenService:validateAuthToken] : Invalid auth token" + authToken);
        }
        return true;
    }

    public User getAuthTokenUser(AuthToken authToken) {
        return bankInstance.getAuthTokenUser(authToken);
    }
}
