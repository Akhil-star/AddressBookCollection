package com.cg.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.cg.addressbook.AddressBookSystem.IOService.DB_IO;

public class AddressBookTest {

    @Test
    public void givenAddressBookContactInDB_WhenRetrieved_ShouldMatchContactsCount() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        List<Contact> addressBookContactList = addressBookSystem.readAddressBookData( DB_IO );
        System.out.println(addressBookContactList);
        Assert.assertEquals(6,addressBookContactList.size());
    }

    @Test
    public void givenNewZipForParticularContact_WhenUpdated_ShouldSyncWithDataBase() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        List<Contact> employeePayrollData = addressBookSystem.readAddressBookData(DB_IO);
        addressBookSystem.updateContactPhoneNo("Akhil",503982);
        boolean result = addressBookSystem.checkAddressBookInsyncWithDB("Akhil");
        Assert.assertTrue( result );
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchAddressBookContactsCount() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        addressBookSystem.readAddressBookData(DB_IO);
        LocalDate startDate = LocalDate.of( 2018,01,01 );
        LocalDate endDate = LocalDate.now();
        List<Contact> addressBookContactData = addressBookSystem.readAddressBookContactDataForDateRange(DB_IO,startDate,endDate );
        Assert.assertEquals( 3,addressBookContactData.size() );
    }

    @Test
    public void givenAddressBookContacts_WhenCountByCityRetrieve_ShouldReturnProperValue() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        addressBookSystem.readAddressBookData(DB_IO);
        Map<String,Integer> countContactByCity = addressBookSystem.readContactCountByCity(DB_IO);
        Assert.assertTrue( countContactByCity.get("Mahabubnagar").equals( 2 )
                && countContactByCity.get( "Hosur" ).equals( 1 ) && countContactByCity.get( "Kurnool" ).equals( 1 ) && countContactByCity.get( "Kadapa" ).equals( 1 ));
    }

    @Test
    public void givenNewContact_WhenAdded_ShouldSyncWithDB() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        addressBookSystem.readAddressBookData(DB_IO);
        addressBookSystem.addContactToAddressBook("ab","Bunny","Balajinagar","vizag","AP",Long.parseLong( "92512"),Long.parseLong( "9869854127" ),"xyz@gmail.com","family",LocalDate.now());
        boolean result = addressBookSystem.checkAddressBookInsyncWithDB("ab");
        Assert.assertTrue( result );
    }
}
