package com.calc.cs;
//Developer | A.S.H.Siribaddana |
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
	
	//cr store point
	public static String methodBlockStatus = "Closed";
	public static String recursiveMethodStore;
	public static int methodDetectLine;
	public static int methodBlockEndLine;
	public static boolean IsRecursiveMethodDetected = false;
	public static int crOpenBraCount = 0;
	public static int crCloseBraCount = 0;
	
	public static boolean IsCodeHasRecursiveMethod = false;
}
