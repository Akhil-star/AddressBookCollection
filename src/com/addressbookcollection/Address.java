package com.addressbookcollection;

import java.util.List;
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
			System.out.println(arrayList);
	}
}
