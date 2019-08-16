package com.calc.main;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.calc.cs.ItemsDetected;
import com.calc.cs.csCalculator;
public class codeApp {
	
	private static String codeStatement;
	private static String filePath;
//	private 

	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sc = new Scanner(System.in);
		// TODO Auto-generated method stub
//		System.out.println("Enter the Code");
//		codeStatement = sc.nextLine();
		
		JTextField inputFilePath;
		JButton b; 
		 JFrame f = new JFrame("Code App");
		    f.getContentPane().setLayout(new FlowLayout());
		    inputFilePath = new JTextField("Text field 1");
		    b =new JButton("Submit");    
			b.setBounds(100,100,140, 40);
		    f.getContentPane().add(inputFilePath);
		    f.add(b);
		    f.pack();
		    f.setVisible(true);
		  
		    b.addActionListener(new ActionListener() {
		    
		   	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					filePath = inputFilePath.getText();				
				}          
		      });
		
		    //"F:\\sliit\\java projects\\singleLine"
		    
		@SuppressWarnings("resource")
		Scanner s = new Scanner(new File(filePath)).useDelimiter(System.lineSeparator());
		ArrayList<String> list = new ArrayList<String>	();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		
		csCalculator sizeObject = new csCalculator();
		
		
//		for(String obj:list)  {
//			sizeObject.calcCsOfStatement(obj); 
//		 } 
//		System.out.println(list);
		
				
	}

}
