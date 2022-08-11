package com.simona.uber1;

public class Client {

    private String clientID;
    private String clientFirstName;
    private String clientLastName;
    private String clientPhone;
    private String clientEmail;
    private String password;
    private int rating;

    public Client() {
    }

    public Client(String clientID, String clientFirstName, String clientLastName,
                  String clientPhone, String clientEmail, String password, int rating) {
        this.clientID = clientID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.password = password;
        this.rating = rating;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getPassword() {
        return password;
    }

    public int getRating() {
        return rating;
    }
}
