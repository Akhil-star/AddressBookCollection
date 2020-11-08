package com.cg.addressbook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookSystem {

    public enum IOService{DB_IO,REST_IO}

    private List<Contact> addressBookContactlist;
    private AddressBookDBSystem addressBookDBSystem;

    public AddressBookSystem(){
        addressBookDBSystem = addressBookDBSystem.getInstance();

    }

    public AddressBookSystem(List<Contact> addressBookContactList){
        this();
        this.addressBookContactlist=new ArrayList<>(addressBookContactList);
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

    public void addContactsToAddressBook(List<Contact> addressBookContactList){
        addressBookContactList.forEach( contactData -> {
            System.out.println("employees being added : "+contactData.firstName);
            this.addContactToAddressBook(contactData.firstName, contactData.lastName, contactData.address, contactData.city,
                    contactData.state,contactData.zip,contactData.phoneNumber,contactData.emailId,contactData.type,contactData.date);
            System.out.println("Employees added: "+contactData.firstName);
        } );
        System.out.println(this.addressBookContactlist);
    }

    public void addContactsToAddressBookWithThreads(List<Contact> addressBookContactlist) {
        Map<Integer, Boolean> contactAdditionStatus = new HashMap<Integer, Boolean>();
        addressBookContactlist.forEach( contactData -> {
            Runnable task = () -> {
                contactAdditionStatus.put( contactData.hashCode(), false );
                System.out.println( "employees being added : " + Thread.currentThread().getName() );
                this.addContactToAddressBook(contactData.firstName, contactData.lastName, contactData.address, contactData.city,
                        contactData.state,contactData.zip,contactData.phoneNumber,contactData.emailId,contactData.type,contactData.date);
                contactAdditionStatus.put( contactData.hashCode(), true );
                System.out.println( "Employees added: " + Thread.currentThread().getName() );
            };
            Thread thread = new Thread( task, contactData.firstName );
            thread.start();
        });
        while (contactAdditionStatus.containsValue( false )){
            try {
                Thread.sleep( 10 );
            }catch (InterruptedException e){
            }
        }
        System.out.println(this.addressBookContactlist);
    }

    public long countEntries(IOService ioService){
        if(ioService.equals( IOService.DB_IO ))
            return addressBookContactlist.size();
        else
            return addressBookContactlist.size();
    }

    public void addContactToAddressBook(Contact contact, IOService ioService) {
        if(ioService.equals( IOService.REST_IO ))
            addressBookContactlist.add( contact );
    }
}
