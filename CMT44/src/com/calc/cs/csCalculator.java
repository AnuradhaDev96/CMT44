package com.calc.cs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.*;  

public class csCalculator {	
	
	private String str = "5 > 9 K >> 0 >> + long Time (String wine ); wine ";
	private int lengthofCodeStatement = str.length();	
	private List<String> identifiedTokens = new ArrayList<>();
	
	//char characterList[] = new char[] {'+', '-', '*', '/', '%', '!', '=', ',', '.', '>', '<'};
	//Array for single characters
	private String characterList[] = new String[] {" + ", " - ", " * ", " / ", " % ", " ! ", " = ", ",", ".", " > ", " < "};

	//array for key words
	private String keyWordArray[] = new String[] {"void ", "double ", "int ", "float ", "string ", "printf(", "println ", "cout <<", "cin >>", "if("};
	
	//array for dal characters
	private String dualdArray[] = new String[] {"==",  "!=", ">=", " <= ", "&& ", "||", ">>"};
	
	//searching for these keywords and detecting the next word as class,variable, method or array
	private String genericArray[] = new String[] {"class", "void", "int", "long", "double", "float", "char", "String"};	
	
	
	public void calcCsOfStatement(String codeStatement) {
		this.str = codeStatement;
//		Pattern p = Pattern.compile("");//. represents single character  
//		Matcher m = p.matcher(codeStatement);  
//		boolean b = m.matches();  
//		System.out.println(Pattern.matches("[amn]+", "aadd"));	
		
		//Detect single characters
		for  (int i = 0; i < this.characterList.length; i++) {
			this.detectSingleCharacters(characterList[i]);
		}
		
		//Detect dual characters
		for  (int k = 0; k < this.dualdArray.length; k++) {
			this.detectDualCharacters(dualdArray[k]);
		}
		
		//Detect keywords
		for  (int j = 0; j < this.keyWordArray.length; j++) {
			if (this.str.contains(keyWordArray[j])) {
				this.identifiedTokens.add(keyWordArray[j]);
			}
		}
		
		//Detect class names, methods, variables and arrays
		for  (int m = 0; m <genericArray.length; m++) {
			this.detectProgrammerDefinedWords(genericArray[m]);
		}
		
//		for(String obj:ItemsDetected.items)  {
//		    System.out.println(obj);  
//		 } 
		
		System.out.println(ItemsDetected.items);
		System.out.println(this.identifiedTokens);
		 
	}
	
	public void detectSingleCharacters(String singleCharacter) {
		int indexRecorder = -1;
		int chk = 0;
		
		do  {
			chk =indexRecorder + 1;
			indexRecorder = this.str.indexOf(singleCharacter, chk);
			
			if (indexRecorder != -1) {
				this.identifiedTokens.add(singleCharacter);
			}
			
		} while (indexRecorder != -1);
	}
	
	public void detectDualCharacters(String dualCharacter) {
		int indexRecorder = 0;
		int chk = 0;
		
		do  {
//			chk =indexRecorder + 2;
			indexRecorder = this.str.indexOf(dualCharacter, chk);
			
			if (indexRecorder != -1) {
				this.identifiedTokens.add(dualCharacter);
			}
			chk = indexRecorder + 2;
			
		} while (indexRecorder != -1);
		
	}
	
	public void detectProgrammerDefinedWords(String ProgrammerDefinedWord) {		
		/* ERROR:
		 * THIS METHOD STILL DOES NOT DETECT 
		 * SAME GENERIC WORD FOR SEVERAL TIMES*/
		int indexRecorder = 0;
		int chk = 0;
		
		
		
		int SpaceAfterGeneric = 0;
		String DetectedName;
		
			if (this.str.contains(ProgrammerDefinedWord)) {
				
				do  {
					indexRecorder = this.str.indexOf(ProgrammerDefinedWord, chk);
					
					if (indexRecorder != -1) {
						SpaceAfterGeneric = this.str.indexOf(" ", this.str.indexOf(ProgrammerDefinedWord, chk));				
						DetectedName = (this.str.substring(SpaceAfterGeneric + 1, this.str.indexOf(" ", SpaceAfterGeneric + 1)));
						ItemsDetected.items.add(DetectedName);
						this.identifiedTokens.add(DetectedName);
					}
					chk = indexRecorder + 2;
					
				} while (indexRecorder != -1);				
			}				
	}
	
//	public void detectItemsDetectedInLine(String PreviousItem) {		
//		/* ERROR:
//		 * THIS METHOD STILL DOES NOT DETECT 
//		 * SAME GENERIC WORD FOR SEVERAL TIMES*/
//		
////		int SpaceAfterGeneric = 0;
////		String DetectedName;
//		
//			if (this.str.contains(PreviousItem)) {
//				
//				SpaceAfterGeneric = this.str.indexOf(" ", this.str.indexOf(ProgrammerDefinedWord));				
//				DetectedName = (this.str.substring(SpaceAfterGeneric + 1, this.str.indexOf(" ", SpaceAfterGeneric + 1)));
//				ItemsDetected.items.add(DetectedName);
//				
//				do  {
//					chk =indexRecorder + 1;
//					indexRecorder = this.str.indexOf(singleCharacter, chk);
//					
//					if (indexRecorder != -1) {
//						this.identifiedTokens.add(singleCharacter);
//					}
//					
//				} while (indexRecorder != -1);
//			}
//				
//	}
	
	
}
