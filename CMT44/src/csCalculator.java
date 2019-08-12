import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;  

public class csCalculator {
//	private String str
		
	
	private String str = "+ p+p - void if(";
	private int lengthofCodeStatement = str.length();	
	private int tokenArray[] = new int[lengthofCodeStatement];// array for single char operators
	private	int subArrayCount = 0; //counter of tokenArray
	
	private String tokenStringArray[] = new String[50]; // array for keywords
	private int tokenStringArrayCount = 0;// counter of tokenStringArray 
	
	private List<String> identifiedTokens = new ArrayList<>();
	
	public void calcCsOfStatement(String codeStatement) {
		
//		Pattern p = Pattern.compile("");//. represents single character  
//		Matcher m = p.matcher(codeStatement);  
//		boolean b = m.matches();  
//		System.out.println(Pattern.matches("[amn]+", "aadd"));
//		String str = "+++";//		
		
//		char characterList[] = new char[] {'+', '-', '*', '/', '%', '!', '=', ',', '.', '>', '<'};
		String characterList[] = new String[] {"+", "-", "*", "/", "%", "!", "=", ",", ".", ">", "<"};

		String keyWordArray[] = new String[] {"void ", "double ", "int ", "float ", "string ", "printf(", "println ", "cout <<", "cin >>", "if("}; 
		String dualdArray[] = new String[] {"++ ", "-- ", "==", "!= ", ">= ", "<=", "&& ", "||"};
		
		for  (int i = 0; i <characterList.length; i++) {
			this.detectSingleCharacters(characterList[i]);
		}
		
		for  (int k = 0; k <dualdArray.length; k++) {
			this.detectDualCharacters(dualdArray[k]);
		}
		
		for  (int j = 0; j <keyWordArray.length; j++) {
			if (this.str.contains(keyWordArray[j])) {
//				this.tokenStringArray[this.tokenStringArrayCount] = keyWordArray[j];
//				this.tokenStringArrayCount++;
				this.identifiedTokens.add(keyWordArray[j]);
			}
		}
		
//		System.out.println(this.tokenStringArrayCount);
//		System.out.println("===========");
//		for (int count = 0; count < this.tokenStringArray.length; count = count + 1) {
//			System.out.println(this.tokenStringArray[count]);
//		}
//		System.out.println(this.subArrayCount);
//		System.out.println("===========");
//		for (int count = 0; count < this.tokenArray.length; count = count + 1) {
//			System.out.println(this.tokenArray[count]);
//		}
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
		int indexRecorder = -1;
		int chk = 0;
		
		do  {
			chk =indexRecorder + 1;
			indexRecorder = this.str.indexOf(dualCharacter, chk);
			
			if (indexRecorder != -1) {
				this.identifiedTokens.add(dualCharacter);
			}
			
		} while (indexRecorder != -1);
	}
}
