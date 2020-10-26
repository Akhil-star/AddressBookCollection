package com.cg.addressbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import com.opencsv.*;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.jdi.Value;

public class AddressBook {
    static HashMap<String, Address> hashMap = new HashMap<String, Address>();

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        System.out.println( "Welcome to address book program" );
        int k = 0;
        while (k != 13) {
            System.out.println( "1.Create Address Book\n2.Add Details to Address Book\n3.View contact by city "
                    + "\n4.View By State\n5.Sort by first name\n6.Sort by city\n7.Sort by state\n8.Sort by zip"
                    + "\n9.Write to file\n10.Read file\n11.Write Csv\n12.Read Csv\n13.Exit" );
            k = sc.nextInt();
            if (k == 1) {
                System.out.println( "Enter the name of address book to be created" );
                String name = sc.next();
                Address obj = new Address();
                hashMap.put( name, obj );
            }
            if (k == 2) {
                System.out.println( "Enter the name of the address book to be accesed" );
                String name = sc.next();
                if (hashMap.containsKey( name )) {
                    int n = 0;
                    while (n != 5) {
                        System.out.println(
                                "Menu\n1.Add\n2.Edit Contact\n3.Delete Contact\n4.Display all contacts\n5.Exit" );
                        System.out.println( "Enter your choice: " );
                        n = sc.nextInt();
                        if (n == 1) {
                            Contact obj = new Contact();
                            System.out.println( "Add a contact: " );
                            System.out.println( "Enter the First Name: " );
                            sc.nextLine();
                            obj.setFirstName( sc.nextLine() );
                            System.out.println( "Enter the Last Name: " );
                            obj.setLastName( sc.nextLine() );
                            System.out.println( "Enter Address" );
                            obj.setAddress( sc.nextLine() );
                            System.out.println( "Enter city" );
                            obj.setCity( sc.nextLine() );
                            System.out.println( "Enter state" );
                            obj.setState( sc.nextLine() );
                            System.out.println( "Enter zip code" );
                            obj.setZip( Long.parseLong( sc.nextLine() ) );
                            System.out.println( "Enter the Phone No.: " );
                            obj.setPhoneNumber( Long.parseLong( sc.nextLine() ) );
                            System.out.println( "Enter the Email: " );
                            obj.setEmailId( sc.nextLine() );
                            hashMap.get( name ).addContact( obj );
                        }
                        if (n == 2) {
                            System.out.println( "Edit a contact: " );
                            System.out.println( "Enter the First Name: " );
                            sc.nextLine();
                            String firstName = sc.nextLine();
                            System.out.println( "Enter the Last Name: " );
                            String lastName = sc.nextLine();
                            boolean f2 = hashMap.get( name ).editContactGivenName( firstName, lastName );
                            if (f2)
                                System.out.println( "The contact is successfully Edited" );
                            else
                                System.out.println( "Contact is not found" );

                        }
                        if (n == 3) {
                            System.out.println( "Delete a contact :" );
                            System.out.println( "Enter the First Name: " );
                            sc.nextLine();
                            String firstName = sc.nextLine();
                            System.out.println( "Enter the Last Name: " );
                            String lastName = sc.nextLine();
                            boolean f1 = hashMap.get( name ).removeContact( firstName, lastName );

                            if (f1)
                                System.out.println( "The contact is successfully deleted" );
                            else
                                System.out.println( "Contact is not found" );
                        }

                        if (n == 4) {
                            System.out.println( "The contacts in the List are:" );
                            List<Contact> list = hashMap.get( name ).viewAllContacts();
                            for (Contact obj : list) {
                                System.out.println( "First Name : " + obj.getFirstName() );
                                System.out.println( "Last Name : " + obj.getLastName() );
                                System.out.println( "Address : " + obj.getAddress() );
                                System.out.println( "City : " + obj.getCity() );
                                System.out.println( "State : " + obj.getState() );
                                System.out.println( "Zip Code : " + obj.getZip() );
                                System.out.println( "Phone number : " + obj.getPhoneNumber() );
                                System.out.println( "Email :" + obj.getEmailId() );
                            }
                        }
                    }
                } else {
                    System.out.println( "No Address book found" );
                }
            }
            if (k == 3) {
                ArrayList<Contact> cityCon = new ArrayList<Contact>();
                System.out.println( "Enter the city" );
                sc.nextLine();
                String city = sc.nextLine();
                hashMap.values().stream().forEach( c -> cityCon.addAll( c.viewByCity( city ) ) );
                System.out.println( "View By City : " + cityCon );
                System.out.println( "count of people in the " + city + ": " + cityCon.size() );

            }
            if (k == 4) {
                ArrayList<Contact> stateCon = new ArrayList<Contact>();
                System.out.println( "Enter the state" );
                sc.nextLine();
                String state = sc.nextLine();
                hashMap.values().stream().forEach( c -> stateCon.addAll( c.viewByState( state ) ) );
                System.out.println( stateCon );
                System.out.println( "count of people in the " + state + ": " + stateCon.size() );

            }
            if (k == 5) {
                hashMap.values().stream().forEach( c -> System.out.println( c.sortByFirstname() ) );
            }
            if (k == 6) {
                hashMap.values().stream().forEach( c -> System.out.println( c.sortByCity() ) );
            }
            if (k == 7) {
                hashMap.values().stream().forEach( c -> System.out.println( c.sortByState() ) );
            }
            if (k == 8) {
                hashMap.values().stream().forEach( c -> System.out.println( c.sortByZip() ) );
            }
            if (k == 9) {
                writeFile();
            }
            if (k == 10) {
                readFile();
            }
            if (k == 11) {
                hashMap.values().stream().forEach( c -> {
                    try {
                        c.writeCSV();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (CsvDataTypeMismatchException e) {
                        e.printStackTrace();
                    } catch (CsvRequiredFieldEmptyException e) {
                        e.printStackTrace();
                    }
                } );
            }
            if(k == 12){
                hashMap.values().stream().forEach( c -> {
                    try {
                        c.readCSV();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (CsvValidationException e) {
                        e.printStackTrace();
                    }
                } );
            }
        }
        sc.close();

    }

    public static void writeFile() {
        BufferedWriter bf = null;
        try {
            String fileName = "addressbook.txt";
            File f = new File( fileName );
            boolean res = f.createNewFile();
            if (res == false) {
                System.out.println( "File already created" );
            }
            bf = new BufferedWriter( new FileWriter( fileName ) );
            for (Map.Entry<String, Address> entry : hashMap.entrySet()) {
                bf.write( "AddressBookName " + entry.getKey() + ":" + entry.getValue() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (Exception e) {

            }
        }
    }

    public static void readFile() {
        BufferedReader bf = null;
        int ch = 0;
        try {
            String fileName = "addressbook.txt";
            File f = new File( fileName );
            boolean res = f.createNewFile();
            if (res == false) {
                System.out.println( "Already Created" );
            }
            bf = new BufferedReader( new FileReader( fileName ) );
            while ((ch = bf.read()) != -1) {
                System.out.print( (char) ch );
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (Exception e) {

            }
        }
    }
}

