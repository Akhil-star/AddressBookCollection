package com.cg.addressbook;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Address {
    private List<Contact> arrayList = new CopyOnWriteArrayList<Contact>();

    public void setArrayList(List<Contact> obj) {
        arrayList = obj;
    }

    public List<Contact> getArrayList() {
        return arrayList;
    }

    public boolean checkDuplicate(Contact obj) {
        return arrayList.stream().anyMatch( duplicate -> duplicate.equals( obj ) );
    }

    public void addContact(Contact contactObj) {
        if (!checkDuplicate( contactObj )) {
            arrayList.add( contactObj );
        } else
            System.out.println( "Duplicate found" );
    }

    public List<Contact> viewAllContacts() {
        return arrayList;
    }

    public boolean editContactGivenName(String firstName, String lastName) {
        boolean f = false;
        Scanner sc = new Scanner( System.in );
        for (Contact obj2 : arrayList) {
            if (obj2.getFirstName().equalsIgnoreCase( firstName ) && obj2.getLastName().equalsIgnoreCase( lastName )) {
                f = true;
                System.out.println( "Enter Address" );
                obj2.setAddress( sc.nextLine() );
                System.out.println( "Enter city" );
                obj2.setCity( sc.nextLine() );
                System.out.println( "Enter state" );
                obj2.setState( sc.nextLine() );
                System.out.println( "Enter zip code" );
                obj2.setZip( Long.parseLong( sc.nextLine() ) );
                System.out.println( "Enter the Phone No.: " );
                obj2.setPhoneNumber( Long.parseLong( sc.nextLine() ) );
                System.out.println( "Enter the Email: " );
                obj2.setEmailId( sc.nextLine() );
                break;
            }
        }
        sc.close();
        return f;
    }

    public boolean removeContact(String firstName, String lastName) {
        boolean f = false;
        for (Contact obj : arrayList) {
            if (obj.getFirstName().equalsIgnoreCase( firstName ) && obj.getLastName().equalsIgnoreCase( lastName )) {
                f = true;
                arrayList.remove( obj );
                break;
            }
        }
        return f;
    }

    public List<Contact> viewByCity(String city) {
        return arrayList.stream().filter( list -> list.getCity().equalsIgnoreCase( city ) ).collect( Collectors.toList() );
    }

    public List<Contact> viewByState(String state) {
        return arrayList.stream().filter( list -> list.getState().equalsIgnoreCase( state ) ).collect( Collectors.toList() );

    }

    public List<Contact> sortByFirstname() {
        return arrayList.stream()
                .sorted( Comparator.comparing( Contact::getFirstName ).thenComparing( Contact::getLastName ) )
                .collect( Collectors.toList() );

    }

    public List<Contact> sortByCity() {
        return arrayList.stream().sorted( Comparator.comparing( Contact::getCity ) ).collect( Collectors.toList() );

    }

    public List<Contact> sortByState() {
        return arrayList.stream().sorted( Comparator.comparing( Contact::getState ) ).collect( Collectors.toList() );

    }

    public List<Contact> sortByZip() {
        return arrayList.stream().sorted( Comparator.comparingLong( Contact::getZip ) ).collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return "AddressDetails " + arrayList;
    }


    public void writeCSV() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        String CsvWriteFile = "addressBookCsv.txt";
        Writer writer = Files.newBufferedWriter( Paths.get( CsvWriteFile ) );
        StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder( writer ).withQuotechar( CSVWriter.NO_QUOTE_CHARACTER ).build();
        beanToCsv.write( arrayList );
        writer.close();

    }

    public void readCSV() throws IOException, CsvValidationException {
        String CsvReadFile = "addressBookCsv.txt";
        Reader reader = Files.newBufferedReader(Paths.get(CsvReadFile));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;
        nextRecord = csvReader.readNext();
        while((nextRecord = csvReader.readNext())!=null) {
            System.out.println("First name : "+nextRecord[3]+"\nLast name : "+nextRecord[4]+"\nAddress : "+nextRecord[0]+"\nCity : "+nextRecord[1]+
                    "\nState : "+nextRecord[6]+"\nZip : "+nextRecord[7]+"\nPhone no : "+nextRecord[5]+"\nEmailId : "+nextRecord[2]);
        }
    }

    public void writeJsonFile() throws IOException{
        String JsonWriteFile = "addressBookJson.txt";
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        FileWriter fileWriter = new FileWriter( JsonWriteFile );
        BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
        bufferedWriter.write( json );
        bufferedWriter.close();
    }

    public void readJsonFile() throws IOException{
        String JsonReadFile = "addressBookJson.txt";
        Gson gson = new Gson();
        FileReader fileReader = new FileReader( JsonReadFile);
        BufferedReader bufferedReader = new BufferedReader( fileReader );
        int ch =0;
        while ((ch = bufferedReader.read()) != -1) {
            System.out.print( (char) ch );
        }
        bufferedReader.close();
    }
}

