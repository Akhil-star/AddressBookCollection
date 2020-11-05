package com.cg.addressbook;

import java.time.LocalDate;
import java.util.Objects;

public class Contact {
     String firstName;
     String lastName;
     long phoneNumber;
     String emailId;
     String address;
     String city;
     String state;
     long zip;
     String type;
     LocalDate date;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String address, String city, String state, long phoneNumber,
                   long zip, String emailId) {
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
    public Contact(String firstName, String lastName, String address, String city, String state, long zip, long phoneNumber, String emailId, String type) {
        this(firstName, lastName, address, city, state, phoneNumber, zip, emailId );
        this.type=type;
    }

    public Contact(String firstName, String lastName, String address, String city, String state, long zip, long phoneNumber, String emailId, String type,LocalDate date) {
        this(firstName, lastName, address, city, state, zip, phoneNumber, emailId, type );
        this.date = date;
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



    @Override
    public String toString() {
        String pattern = ("FirstName: " + firstName + " LastName: " + lastName + " City: " + city + " State: " + state
                + " Zip: " + zip + " MobileNumber:" + phoneNumber + " EmailId: " + emailId + "\n");
        return pattern;
    }

//    public boolean equals(Object obj) {
//        Contact c = (Contact) obj;
//        if (c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName))
//            return true;
//        return false;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return phoneNumber == contact.phoneNumber &&
                zip == contact.zip &&
                Objects.equals( firstName, contact.firstName ) &&
                Objects.equals( lastName, contact.lastName ) &&
                Objects.equals( emailId, contact.emailId ) &&
                Objects.equals( address, contact.address ) &&
                Objects.equals( city, contact.city ) &&
                Objects.equals( state, contact.state ) &&
                Objects.equals( type, contact.type );
    }

}
