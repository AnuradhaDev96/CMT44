package com.calc.finalcalc;
//Developer R.M.R.U.Rivishanka
import com.calc.cs.ItemsDetected;

public class crCalculator {
	
	private static final String MethodBlockClosed = "Closed";
	private static final String MethodBlockOpened = "Opened";
	
	private String returnTypeArray[] = new String[] {"class", "void", "int", "long", "double", "float", "char", "String"};	
	private String str;
	private String methodNameDetected = null;
	
	public void calcCrOfCodeBlock(String codeStatement, int lineNo) {
		this.str = codeStatement;
		
		boolean methodBlockOpenCondition = (!(codeStatement.contains("class")) && ((codeStatement.contains("){")) || (codeStatement.contains(") {"))));
		boolean methodBlockCloseCondition = ((codeStatement.contains("}")) && (ItemsDetected.methodBlockStatus == MethodBlockOpened) && (ItemsDetected.crOpenBraCount == ItemsDetected.crCloseBraCount));
		boolean recursiveMethodCondition = ((codeStatement.contains(ItemsDetected.recursiveMethodStore + "(")) && (ItemsDetected.methodBlockStatus == MethodBlockOpened));
		
		if(ItemsDetected.methodBlockStatus == MethodBlockOpened) {
			if(this.str.contains("{")) {
				ItemsDetected.crOpenBraCount++;
			}
			if(this.str.contains("}")) {
				ItemsDetected.crCloseBraCount++;
			}		}
		if(!(codeStatement.contains("class")) && !(codeStatement.contains("if")) &&
				!(codeStatement.contains("else")) &&
				!(codeStatement.contains("while")) &&
				!(codeStatement.contains("switch")) && ((codeStatement.contains("){")) || (codeStatement.contains(") {")))) {
			ItemsDetected.methodBlockStatus = MethodBlockOpened;			
			ItemsDetected.methodDetectLine = lineNo;
			ItemsDetected.crOpenBraCount++;
			System.out.println(ItemsDetected.methodBlockStatus + " " + ItemsDetected.methodDetectLine);
			//Detect class names, methods, variables and arrays
			for  (int m = 0; m <returnTypeArray.length; m++) {
				this.detectMethodNameInStatement(returnTypeArray[m]);
			}		
			
		}else if((codeStatement.contains("}")) && (ItemsDetected.methodBlockStatus == MethodBlockOpened) && (ItemsDetected.crOpenBraCount == ItemsDetected.crCloseBraCount)) {
			ItemsDetected.methodBlockStatus = MethodBlockClosed;
			ItemsDetected.methodBlockEndLine = lineNo;
			System.out.println(ItemsDetected.methodBlockStatus + " " + ItemsDetected.methodBlockEndLine);
		}else if((codeStatement.contains(ItemsDetected.recursiveMethodStore + "(")) && (ItemsDetected.methodBlockStatus == MethodBlockOpened)) {
			ItemsDetected.IsRecursiveMethodDetected = true;
			ItemsDetected.IsCodeHasRecursiveMethod = true;
		}	
	}	
	
	public void detectMethodNameInStatement(String returnType) {		
		/* ERROR:
		 * THIS METHOD CANNOT REMOVE BRACKETS, COMMAS FROM NAMES*/
		int indexRecorder = 0;
		int chk = 0;		
		int SpaceAfterGeneric = 0;
		String DetectedName;
		
			if (this.str.contains(returnType)) {				
				indexRecorder = this.str.indexOf(returnType, chk);				
				if (indexRecorder != -1) {
					SpaceAfterGeneric = this.str.indexOf(" ", this.str.indexOf(returnType, chk));				
					DetectedName = (this.str.substring(SpaceAfterGeneric + 1, this.str.indexOf("(", SpaceAfterGeneric + 1)));
					ItemsDetected.recursiveMethodStore = DetectedName;
				}			
			}				
	}
}
