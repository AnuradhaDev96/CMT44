package com.calc.cs;
//Developer | A.S.H.Sirirbaddana |
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.*;  

public class csCalculator {	
	
	private String str;
	private List<String> identifiedTokens = new ArrayList<>();
	
	//Array for single characters
	private String characterList[] = new String[] {" + ", " - ", " * ", " / ", " % ", " ! ", " = ", ",", ".", " > ", " < "};

	//array for key words
	private String keyWordArray[] = new String[] {"void ", "double ", "int ", "float ", "string ", "printf(", "println ", "cout <<", "cin >>", "if(", "endl", "\n", "etc."};
	
	//array for dal characters
	private String dualdArray[] = new String[] {"==",  "!=", ">=", " <= ", "&& ", "||", ">>"};
	
	//searching for these keywords and detecting the next word as class,variable, method or array
	private String genericArray[] = new String[] {"class", "void", "int", "long", "double", "float", "char", "String"};	
	
	
	public List<String> calcCsOfStatement(String codeStatement) {
		this.str = codeStatement;	
		
		if (!(this.identifiedTokens.isEmpty())) {
			this.identifiedTokens.clear();
		}
		
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
//		for  (int m = 0; m <genericArray.length; m++) {
//			this.detectProgrammerDefinedWords(genericArray[m]);
//		}
		
		//Detect previously detected names, methods, variables and arrays
//		for(String obj:ItemsDetected.items)  {
//			detectItemsDetectedInLine(obj);  
//		} 		
		return this.identifiedTokens;		 
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
			indexRecorder = this.str.indexOf(dualCharacter, chk);			
			if (indexRecorder != -1) {
				this.identifiedTokens.add(dualCharacter);
			}
			chk = indexRecorder + 2;
			
		} while (indexRecorder != -1);
		
	}
	
	public void detectProgrammerDefinedWords(String ProgrammerDefinedWord) {		
		/* ERROR:
		 * THIS METHOD CANNOT REMOVE BRACKETS, COMMAS FROM NAMES*/
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
						chk = indexRecorder + 1;
					}					
				} while (indexRecorder != -1);				
			}				
	}
	
	public void detectItemsDetectedInLine(String PreviousItem) {		
		/* ERROR:
		 * THIS METHOD CANNOT REMOVE BRACKETS, COMMAS FROM NAMES*/
		
		int indexRecorder = 0;
		int chk = 0;
		
			if (this.str.contains(PreviousItem)) {				
				do  {
					indexRecorder = this.str.indexOf(PreviousItem, chk);
					
					if (indexRecorder != -1) {
						this.identifiedTokens.add(PreviousItem);
					}
					chk = indexRecorder + 1;
					
				} while (indexRecorder != -1);
			}				
	}	
	
}
