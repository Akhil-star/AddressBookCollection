package com.addressbookcollection;

import java.util.Scanner;

public class AddressBook {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Address objmain = new Address();
		System.out.println("1.Add");
		System.out.println("Enter your choice: ");
		int n = sc.nextInt();
		if (n == 1) {
			Contact obj = new Contact();
			System.out.println("Add a contact: ");
			System.out.println("Enter the First Name: ");
			sc.nextLine();
			obj.setFirstName(sc.nextLine());
			System.out.println("Enter the Last Name: ");
			obj.setLastName(sc.nextLine());
			System.out.println("Enter Address");
			obj.setAddress(sc.nextLine());
			System.out.println("Enter city");
			obj.setCity(sc.nextLine());
			System.out.println("Enter state");
			obj.setState(sc.nextLine());
			System.out.println("Enter zip code");
			obj.setZip(Long.parseLong(sc.nextLine()));
			System.out.println("Enter the Phone No.: ");
			obj.setPhoneNumber(Long.parseLong(sc.nextLine()));
			System.out.println("Enter the Email: ");
			obj.setEmailId(sc.nextLine());
			objmain.addContact(obj);
		}
	}
}