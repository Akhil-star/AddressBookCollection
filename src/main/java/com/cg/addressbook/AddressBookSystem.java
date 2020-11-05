package com.cg.addressbook;

import java.util.List;

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
}
