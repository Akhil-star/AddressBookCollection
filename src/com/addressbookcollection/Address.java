package com.addressbookcollection;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Address {
	private List<Contact> arrayList = new CopyOnWriteArrayList<Contact>();

	public void setArrayList(List<Contact> obj) {
		arrayList = obj;
	}

	public List<Contact> getArrayList() {
		return arrayList;
	}

	public boolean checkDuplicate(Contact obj) {
		return arrayList.stream().anyMatch(duplicate -> duplicate.equals(obj));
	}

	public void addContact(Contact contactObj) {
			arrayList.add(contactObj);
	}
	public List<Contact> viewAllContacts() {
		return arrayList;
	}

	public boolean editContactGivenName(String firstName, String lastName) {
		boolean f = false;
		Scanner sc = new Scanner(System.in);
		for (Contact obj2 : arrayList) {
			if (obj2.getFirstName().equalsIgnoreCase(firstName) && obj2.getLastName().equalsIgnoreCase(lastName)) {
				f = true;
				System.out.println("Enter Address");
				obj2.setAddress(sc.nextLine());
				System.out.println("Enter city");
				obj2.setCity(sc.nextLine());
				System.out.println("Enter state");
				obj2.setState(sc.nextLine());
				System.out.println("Enter zip code");
				obj2.setZip(Long.parseLong(sc.nextLine()));
				System.out.println("Enter the Phone No.: ");
				obj2.setPhoneNumber(Long.parseLong(sc.nextLine()));
				System.out.println("Enter the Email: ");
				obj2.setEmailId(sc.nextLine());
				break;
			}
		}
		return f;
	}
	public boolean removeContact(String firstName, String lastName) {
		boolean f = false;
		for (Contact obj : arrayList) {
			if (obj.getFirstName().equalsIgnoreCase(firstName) && obj.getLastName().equalsIgnoreCase(lastName)) {
				f = true;
				arrayList.remove(obj);
				break;
			}
		}
		return f;
	}
}
