package com.addressbookcollection;

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
		return arrayList.stream().anyMatch(duplicate -> duplicate.equals(obj));
	}

	public void addContact(Contact contactObj) {
		if(!checkDuplicate(contactObj))
		arrayList.add(contactObj);
		else
			System.out.println("Duplicate found");
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
		sc.close();
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
	
	public List<Contact> viewByCity(String city) {
		 return arrayList.stream().filter(list -> list.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
	}
	
	public List<Contact> viewByState(String state){
		 return arrayList.stream().filter(list -> list.getState().equalsIgnoreCase(state)).collect(Collectors.toList());

	}
	
	public List<Contact> sortByFirstname(){
   		return arrayList.stream().sorted(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName)).collect(Collectors.toList());

	}
}
