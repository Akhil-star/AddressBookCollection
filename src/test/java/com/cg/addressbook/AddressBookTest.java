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
        Assert.assertEquals(7,addressBookContactList.size());
    }

}
