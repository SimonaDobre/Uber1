package com.simona.uber1;

public class Driver {

    private String driverID;
    private String driverFirstName;
    private String driverLastName;
    private String driverPhone;
    private String driverEmail;
    private String password;
    private int rating;
    private boolean available;

    public Driver(String driverID, String driverFirstName,
                  String driverLastName, String driverPhone,
                  String driverEmail,
                  String password, int rating, boolean available) {
        this.driverID = driverID;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        this.driverPhone = driverPhone;
        this.driverEmail = driverEmail;
        this.password = password;
        this.rating = rating;
        this.available = available;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getPassword() {
        return password;
    }

    public int getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void changeAvailability(){
        available = !available;
    }
}
