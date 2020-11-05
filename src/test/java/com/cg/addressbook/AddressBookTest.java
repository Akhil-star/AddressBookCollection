package com.cg.addressbook;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import static com.cg.addressbook.AddressBookSystem.IOService.DB_IO;


public class AddressBookTest {

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        List<Contact> addressBookContactList = addressBookSystem.readAddressBookData( DB_IO );
        System.out.println(addressBookContactList);
        Assert.assertEquals(7,addressBookContactList.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDataBase() {
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        List<Contact> employeePayrollData = addressBookSystem.readAddressBookData(DB_IO);
        addressBookSystem.updateContactPhoneNo("Akhil",503692);
        boolean result = addressBookSystem.checkAddressBookInsyncWithDB("Akhil");
        Assert.assertTrue( result );
    }

}
