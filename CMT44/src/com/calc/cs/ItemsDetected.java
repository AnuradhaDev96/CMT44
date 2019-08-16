package com.calc.cs;

import java.util.ArrayList;
import java.util.List;

public class ItemsDetected {
	public static List<String> items = new ArrayList<>();
	
	public static void addNewItem(String newItem) {
		items.add(newItem);
	}
	
	public static List<String> accessItems() {
		return items;		
	}
}
