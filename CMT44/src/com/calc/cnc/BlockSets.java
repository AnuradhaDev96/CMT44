package com.calc.cnc;

import java.util.ArrayList;
import java.util.List;

public class BlockSets {
	//create variable. we can direct use that in another class
	public static List<String> openCurlyBraArray= new ArrayList<>();
	public static List<String> closeCurlyBraArray= new ArrayList<>();
	
	public static boolean isBlockOpen = false;
	public static boolean isBlockClose = false;	
	
	public static boolean isIfFound = false;
	public static boolean isForFound = false;
	public static boolean isWhileFound = false;
	public static boolean isDoFound = false;
	
	public static int blockBeginLine = 0;
	public static int blockEndLine = 0;
	public static int cncMark = 0;
	
	public static String firstNestStatus = "CLOSED";
	public static String secondNestStatus = "CLOSED";
	
}
