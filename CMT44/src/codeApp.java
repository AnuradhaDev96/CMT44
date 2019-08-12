import java.util.Scanner;
public class codeApp {
	
	private static String codeStatement;
//	private 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
//		System.out.println("Enter the Code");
//		codeStatement = sc.nextLine();
		
		csCalculator sizeObject = new csCalculator();
		sizeObject.calcCsOfStatement(codeStatement);
		
	}

}
