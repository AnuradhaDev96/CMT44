package com.calc.ctc;
//Developer De Silva P.H.N.N
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.*;

import com.calc.cs.SwitchCounter;  

public class ctcCalculator {	
	
	private String str = "5 > 9 K >> 0 >> + long Time (String wine ); wine ";
	private int lengthofCodeStatement = str.length();	
	private List<String> identifiedTokens = new ArrayList<>();
	
	private String singlecharacterList[] = new String [] {" & ", " | "};
	//Array for single characters
	private String characterList[] = new String[] {" && "," || "};

	private static final String closeCurlyBra = "}";
	
	
public int calcCtcOfStatement(String codeStatement, int index) {
		
	if (!(identifiedTokens.isEmpty())) {
		identifiedTokens.clear();
	}
		
	//detect if statement
	String findIf=("if");
	String findFor=("for");
	String findWhile=("while");
	String findCatch=("catch");
	String findSwitch=("switch");
	String findCase=("case");
	String findDo=("do");
	int ctcMark = 0;
	int i=0;
	int k=0;
	
	//find if and operators		
    if (codeStatement.contains(findIf)) {
        
	Pattern pattern = Pattern.compile(findIf);
    Matcher  matcher = pattern.matcher(codeStatement);
	while (matcher.find()) {
		ctcMark++;
    }        
	//find operators
	this.str = codeStatement;

	int count=0;
	//Detect dual characters
	for  (int j = 0; j < this.characterList.length; j++) {
		this.detectDualCharacters(characterList[j]);

	}    		
	//Detect single characters
	for  (int j = 0; j < this.singlecharacterList.length; j++) {
		this.detectSingleCharacters(singlecharacterList[j]);

	}    		
	int retval = identifiedTokens.size();
	int allcount=retval+ctcMark;
	ctcMark = allcount;
    }
    
    
    //find for and operators
    else if(codeStatement.contains(findFor)) {
    	Pattern pattern = Pattern.compile(findFor);
	    Matcher  matcher2 = pattern.matcher(codeStatement);
	    while (matcher2.find()) {
	    	ctcMark = ctcMark+2;
	    }
        
        //find operators
    		this.str = codeStatement;

    		
    		//Detect dual characters
    		for  (int j = 0; j < this.characterList.length; j++) {
    			this.detectDualCharacters(characterList[j]);

    		}
    		//Detect single characters
    		for  (int j = 0; j < this.singlecharacterList.length; j++) {
    			this.detectSingleCharacters(singlecharacterList[j]);

    		}
    		
    		int retval = identifiedTokens.size()*2;
    		
    		int allcount=retval+ctcMark;
    		ctcMark = allcount;	    
    }
    else if(codeStatement.contains(findWhile)) {
    	
    	Pattern pattern = Pattern.compile(findWhile);
	    Matcher  matcher3 = pattern.matcher(codeStatement);
	    while (matcher3.find()) {
	    	ctcMark=ctcMark+2;
	        }

    		
    		//Detect dual characters
    		for  (int j = 0; j < this.characterList.length; j++) {
    			this.detectDualCharacters(characterList[j]);

    		}
    		
    		//Detect single characters
    		for  (int j = 0; j < this.singlecharacterList.length; j++) {
    			this.detectSingleCharacters(singlecharacterList[j]);

    		}
    		int retval = identifiedTokens.size()*2;
    		
    		int allcount=retval+ctcMark;
    		ctcMark = allcount;	    
    }
    
    //find catch and operators    
    else if(codeStatement.contains(findCatch)) {
    	
    	Pattern pattern = Pattern.compile(findCatch);
	    Matcher  matcher4 = pattern.matcher(codeStatement);
	    while (matcher4.find()) {
	    	ctcMark++;
	        }
	    System.out.println("Eureka we've found catch!!");
        System.out.println(ctcMark); 
        //find operators
    		this.str = codeStatement;    		
    		//Detect dual characters
    		for  (int j = 0; j < this.characterList.length; j++) {
    			this.detectDualCharacters(characterList[j]);

    		}
    		//Detect single characters
    		for  (int j = 0; j < this.singlecharacterList.length; j++) {
    			this.detectSingleCharacters(singlecharacterList[j]);

    		}
    		int retval = identifiedTokens.size();
    		
    		int allcount=retval+ctcMark;
    		ctcMark = allcount;;
    		
    		System.out.println(this.identifiedTokens);
    		System.out.println(findCatch);
    		
    		
	    
    }// find switch and record that switch is found
    else if (codeStatement.contains(findSwitch)) {
    	Pattern pattern = Pattern.compile(findSwitch);
	    Matcher  matcher5 = pattern.matcher(codeStatement);
	    while (matcher5.find()) {
//    	    	ctcMark=ctcMark+2;
	    	SwitchCounter.isSwitchFound = true;
	    	SwitchCounter.switchDetectedLine = index;
	    }
    }//find for case statements only if switch is found
    
    else if ((codeStatement.contains(findCase) && (SwitchCounter.isSwitchFound == true)) || (codeStatement.contains(this.closeCurlyBra) && SwitchCounter.isSwitchFound == true)) {
    	if (codeStatement.contains(findCase)) {
    		Pattern pattern = Pattern.compile(findCase);
    	    Matcher  matcher6 = pattern.matcher(codeStatement);
    	    while (matcher6.find()) {
    	    	SwitchCounter.caseCount = SwitchCounter.caseCount + 1;
    	    }
    	}else if (codeStatement.contains(this.closeCurlyBra) && SwitchCounter.isSwitchFound == true) { 
    		ctcMark = SwitchCounter.caseCount;
    		//
    		SwitchCounter.lastLineOfSwitch = index;
    		//
    		SwitchCounter.isSwitchFound = false;
    		SwitchCounter.updMarkOfSwitchLine = true;
//        		System.out.println(ctcMark);
    	}
    	
    }
    
    // find do
    else if (codeStatement.contains(findDo)) {
    	Pattern pattern = Pattern.compile(findDo);
	    Matcher  matcher7 = pattern.matcher(codeStatement);
	    while (matcher7.find()) {
	    	ctcMark=ctcMark+2;
	    }
    }
    else {
//        	System.out.println(ctcMark); 
    }
    return ctcMark;   
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
	public void detectSingleCharacters(String singleCharacter) {
		int indexRecorder = -1;
		int chk = 0;
		
		do  {			
			indexRecorder = this.str.indexOf(singleCharacter, chk);
			
			if (indexRecorder != -1) {
				this.identifiedTokens.add(singleCharacter);
			}
			chk =indexRecorder + 1;
		} while (indexRecorder != -1);
	}	
}

