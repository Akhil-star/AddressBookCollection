package com.addressbookcollection;

import java.util.List;
import java.util.Scanner;

public class AddressBook {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Address objmain = new Address();
		int n = 0;
		while (n != 3) {
			System.out.println("1.Add\n2.Edit\n3.View");
			System.out.println("Enter your choice: ");
			n = sc.nextInt();
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
			if (n == 2) {
				System.out.println("Edit a contact: ");
				System.out.println("Enter the First Name: ");
				sc.nextLine();
				String firstName = sc.nextLine();
				System.out.println("Enter the Last Name: ");
				String lastName = sc.nextLine();
				boolean f2 = objmain.editContactGivenName(firstName, lastName);
				if (f2)
					System.out.println("The contact is successfully Edited");
				else
					System.out.println("Contact is not found");

			}
			if (n == 3) {
				System.out.println("The contacts in the List are:");
				List<Contact> list = objmain.viewAllContacts();
				for (Contact obj : list) {
					System.out.println("First Name : " + obj.getFirstName());
					System.out.println("Last Name : " + obj.getLastName());
					System.out.println("Address : " + obj.getAddress());
					System.out.println("City : " + obj.getCity());
					System.out.println("State : " + obj.getState());
					System.out.println("Zip Code : " + obj.getZip());
					System.out.println("Phone number : " + obj.getPhoneNumber());
					System.out.println("Email :" + obj.getEmailId());
				}
			}
		}
	}
}