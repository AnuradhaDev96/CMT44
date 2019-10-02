package com.calc.cnc;

import com.calc.cs.ItemsDetected;

public class cncEvaluator {
	private static final String MethodBlockClosed = "Closed";
	private static final String MethodBlockOpened = "Opened";
	
	private String returnTypeArray[] = new String[] {"class", "void", "int", "long", "double", "float", "char", "String"};	
	
	
	private String str;
	private String methodNameDetected = null;
	
	public String calcCncOfCodeBlock(String codeStatement) {
		this.str = codeStatement;
		
		boolean methodBlockOpenCondition = (!(codeStatement.contains("class")) && ((codeStatement.contains("){")) || (codeStatement.contains(") {"))));
		boolean methodBlockCloseCondition = ((codeStatement.contains("}")) && (ItemsDetected.methodBlockStatus == MethodBlockOpened) && (ItemsDetected.crOpenBraCount == ItemsDetected.crCloseBraCount));
		boolean recursiveMethodCondition = ((codeStatement.contains(ItemsDetected.recursiveMethodStore + "(")) && (ItemsDetected.methodBlockStatus == MethodBlockOpened));
		String IsAssign = "AssignVal";
		
		if(ItemsDetected.methodBlockStatus == MethodBlockOpened) {
			if(this.str.contains("{")) {
				ItemsDetected.crOpenBraCount++;
			}
			if(this.str.contains("}")) {
				ItemsDetected.crCloseBraCount++;
			}
		}
		
		//conditions for opening a code block
		if(!(codeStatement.contains("class")) && 
			(codeStatement.contains("if")) ||
			(codeStatement.contains("else")) ||
			(codeStatement.contains("while")) ||
			(codeStatement.contains("switch")) || 
			((codeStatement.contains("){")) || (codeStatement.contains(") {")))) {
			if (BlockSets.firstNestStatus == "CLOSED" && 
				BlockSets.secondNestStatus == "CLOSED") {
				BlockSets.firstNestStatus = "OPENED";//OPENING THE FIRST BLOCK
				BlockSets.cncMark = 1;
				IsAssign = "DontAssignVal";
			}
			
			if (BlockSets.firstNestStatus == "OPENED" && 
					BlockSets.secondNestStatus == "CLOSED") {
					BlockSets.secondNestStatus = "OPENED";//OPENING THE SECOND BLOCK
					BlockSets.cncMark = 2;
					IsAssign = "DontAssignVal";
				}		
			
			
		}else if((codeStatement.contains("}")) && 
				(BlockSets.firstNestStatus == "OPENED" || BlockSets.secondNestStatus == "OPENED")) {
			//
			if (BlockSets.firstNestStatus == "OPENED" && 
					BlockSets.secondNestStatus == "CLOSED") {
					BlockSets.firstNestStatus = "CLOSED";//close THE first BLOCK
					BlockSets.cncMark = 0;
					IsAssign = "DontAssignVal";
			}else if(BlockSets.firstNestStatus == "OPENED" && 
					BlockSets.secondNestStatus == "OPENED") {
					BlockSets.secondNestStatus = "CLOSED";//close THE SECOND BLOCK
					BlockSets.cncMark = 1;
					IsAssign = "DontAssignVal";
			}
			
		}		
		return IsAssign;
		
	}
}
