package com.cg.addressbook;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AddressBookSystem {

    public enum IOService{DB_IO}

    private List<Contact> addressBookContactlist;
    private AddressBookDBSystem addressBookDBSystem;

    public AddressBookSystem(){
        addressBookDBSystem = addressBookDBSystem.getInstance();

    }

    public AddressBookSystem(List<Contact> addressBookContactList){
        this();
        this.addressBookContactlist=addressBookContactList;
    }

    public List<Contact> readAddressBookData(IOService ioService){
        if(ioService.equals( IOService.DB_IO ))
            this.addressBookContactlist =  addressBookDBSystem.readData();
        return this.addressBookContactlist;
    }

    public void updateContactPhoneNo(String firstname, long zip) {
        int result = addressBookDBSystem.updateAddressBookContactData( firstname,zip);
        if(result == 0) return;
        Contact addressBookContact = this.getAddressBookContact(firstname);
        if(addressBookContact!=null)
            addressBookContact.zip = zip;
    }

    private Contact getAddressBookContact(String firstname) {
        Contact contact;
        contact = this.addressBookContactlist.stream()
                .filter( contactDataItem -> contactDataItem.firstName.equals( firstname ))
                .findFirst().orElse( null );
        return contact;
    }

    public boolean checkAddressBookInsyncWithDB(String firstname) {
        List<Contact> addressBookContactData = addressBookDBSystem.getAddressBookContactData(firstname);
        return addressBookContactData.get(0).equals(getAddressBookContact(firstname));
    }

    public List<Contact> readAddressBookContactDataForDateRange(IOService ioService,LocalDate startDate,LocalDate endDate) {
        if(ioService.equals( IOService.DB_IO ))
            return addressBookDBSystem.getAddressBookContactDataForDateRange(startDate,endDate);
        return null;
    }

    public Map<String,Integer> readContactCountByCity(IOService ioService){
        if(ioService.equals( IOService.DB_IO ))
            return addressBookDBSystem.getContactCountByCity();
        return null;
    }

    public void addContactToAddressBook(String firstname,String lastname,String address, String city, String state,
                                        long zip,long phoneNumber,String emailId,String type,LocalDate date) {
        addressBookContactlist.add(addressBookDBSystem.addContactToAddressBook(firstname,lastname,address,city,state,zip,phoneNumber,emailId,type,date));
    }
}
