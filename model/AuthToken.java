package model;

import java.util.Date;

public class AuthToken {
    private String authTokenValue;
    private Date expirationDate;
    
    public AuthToken(String authTokenValue, Date expirationDate) {
        this.authTokenValue = authTokenValue;
        this.expirationDate = expirationDate;
    }
    public Date getExpiredDate() {
        return expirationDate;
    }
    public void setExpiredDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getAuthTokenValue() {
        return authTokenValue;
    }

    public void setAuthTokenValue(String authTokenValue) {
        this.authTokenValue = authTokenValue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
}
