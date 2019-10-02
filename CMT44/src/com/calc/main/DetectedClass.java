package com.calc.main;

import java.util.ArrayList;

public class DetectedClass {
	private String className;
	private ArrayList<String> ancestorClasses = new ArrayList<String>();
	private int classStartLine;
	private int classEndLine;
	
	
	public DetectedClass() {
		
	}

	//getter and setter for class name
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	//getter and setter for ancestor classes
	public ArrayList<String> getAncestorClasses() {
		return ancestorClasses;
	}

	public void setAncestorClasses(ArrayList<String> ancestorClasses) {
		this.ancestorClasses = ancestorClasses;
	}

	//getter and setter for class start line
	public int getClassStartLine() {
		return classStartLine;
	}

	public void setClassStartLine(int classStartLine) {
		this.classStartLine = classStartLine;
	}

	//get
	public int getClassEndLine() {
		return classEndLine;
	}

	public void setClassEndLine(int classEndLine) {
		this.classEndLine = classEndLine;
	}
	
	
}
