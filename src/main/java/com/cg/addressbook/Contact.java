package com.cg.addressbook;

public class Contact {
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String emailId;
    private String address;
    private String city;
    private String state;
    private long zip;

    public Contact() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public Contact(String firstName, String lastName, String address, String city, String state, long zip,
                   long phoneNumber, String emailId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        String pattern = ("FirstName: " + firstName + " LastName: " + lastName + " City: " + city + " State: " + state
                + " Zip: " + zip + " MobileNumber:" + phoneNumber + " EmailId: " + emailId + "\n");
        return pattern;
    }

    public boolean equals(Object obj) {
        Contact c = (Contact) obj;
        if (c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName))
            return true;
        return false;
    }
}
