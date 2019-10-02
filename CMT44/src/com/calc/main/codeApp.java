package com.calc.main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.calc.cnc.BlockSets;
import com.calc.cnc.cncCalculator;
import com.calc.cnc.cncEvaluator;
import com.calc.cs.ItemsDetected;
import com.calc.cs.SwitchCounter;
import com.calc.cs.csCalculator;
import com.calc.ctc.ctcCalculator;
import com.calc.finalcalc.crCalculator;
import com.calc.finalcalc.evaluator;

//Developers | A.S.H.Sirribaddana | De Silva P.H.N.N
public class codeApp {
	
	private static String codeStatement;
	private static String filePath = "";
	private JTable table_uploaded;
	
	public static void main(String[] args) throws FileNotFoundException {		
		JTextField inputFilePath;
		JButton b; 
		JTable table_uploaded = new JTable();
		JFrame f = new JFrame("Code App");
	    f.getContentPane().setLayout(new FlowLayout());	   
	    inputFilePath = new JTextField(50);
	    inputFilePath.setBounds(100,100,140, 40);
	    b =new JButton("Submit");
	    f.getContentPane().add(inputFilePath,BorderLayout.NORTH);
	    f.add(b,BorderLayout.NORTH);

        // Frame Size 
        f.setSize(1000, 800); 
        // Frame Visible = true
        f.setVisible(true);	    
	    b.addActionListener(new ActionListener() {
		    
		   	
				@SuppressWarnings("resource")
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
			        
					filePath = inputFilePath.getText();		
					Scanner s = null;
					try {
						s = new Scanner(new File(filePath)).useDelimiter(System.lineSeparator());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					List<String> list = new ArrayList<String>();
					while (s.hasNext()){
					    list.add(s.next());
					} 
					s.close();					
					/*
					 * ctc part start*/
					
					ctcCalculator controlStructObject = new ctcCalculator();
					int singleCtcMark = 0;
					ArrayList<Integer> ctcMarkList = new ArrayList<Integer>();
					for(String obj:list) {
						singleCtcMark = controlStructObject.calcCtcOfStatement(obj, list.indexOf(obj));
						ctcMarkList.add(singleCtcMark);
						if (SwitchCounter.updMarkOfSwitchLine == true) {
							ctcMarkList.set(SwitchCounter.switchDetectedLine, singleCtcMark);
							SwitchCounter.switchDetectedLine = 0;
							SwitchCounter.updMarkOfSwitchLine = false;
						}
						
					}
					
					
					
					System.out.println("\n\n");
					System.out.println("CTC COUNT FOR EACH STATEMENT");
					int con = 0;
					for (Integer obj:ctcMarkList) {
						
						System.out.println("Line No " + (con + 1) + " " + obj);
						con++;
					}
					/* 
					 * ctc part end*/
					
					/*
					 * cs part start*/
					
					csCalculator sizeObject = new csCalculator();
					
					List<Integer> csMarkList = new ArrayList<Integer>();
					ArrayList<String> singleLineIdentiedTokens = new ArrayList<>();
					Collection<ArrayList<String>> allLineIdentiedTokens = new ArrayList<>();
					int returnCsMark = 0;
					
					for(String obj:list)  {
						singleLineIdentiedTokens = (ArrayList<String>) sizeObject.calcCsOfStatement(obj);						
						csMarkList.add(singleLineIdentiedTokens.size());
						allLineIdentiedTokens.add(new ArrayList<>(singleLineIdentiedTokens));
						singleLineIdentiedTokens.clear();						
					 }
					
					/* 
					 * cs part end*/


					/*
					 * cnc part start*/
					cncEvaluator nestEvaluator = new cncEvaluator();
					String returnStatus;
					ArrayList<Integer> cncMarkList = new ArrayList<Integer>();
					for(String obj:list)  {
						returnStatus = nestEvaluator.calcCncOfCodeBlock(obj);	
						if(returnStatus == "AssignVal") {
							cncMarkList.add(BlockSets.cncMark);
						}else if(returnStatus == "DontAssignVal") {
							cncMarkList.add(0);
						}
					 } 
					System.out.println(cncMarkList);
					/*
					 * cnc part end*/
					
					/*
					 * ci part start*/
					DetectedClass cls1 = new DetectedClass();
					DetectedClass cls2 = new DetectedClass();
					DetectedClass cls3 = new DetectedClass();
					DetectedClass cls4 = new DetectedClass();
					DetectedClass cls5 = new DetectedClass();
					DetectedClass cls6 = new DetectedClass();
					DetectedClass cls7 = new DetectedClass();
					DetectedClass returnClassReference;
					ArrayList<DetectedClass> detectedClassArray = new ArrayList<DetectedClass>();
					detectedClassArray.add(cls1);
					detectedClassArray.add(cls2);
					detectedClassArray.add(cls3);
					detectedClassArray.add(cls4);
					detectedClassArray.add(cls5);
					detectedClassArray.add(cls6);
					detectedClassArray.add(cls7);
					int AccessClassIndex = 0;
//					for(String codeLine: list) {
//						AccessClassIndex = ClassTracker.detectedClassCount;
//						if((codeStatement.contains(" class ")) || ((codeStatement.contains("{")) || ((codeStatement.contains("}")) ))) {
//							returnClassReference = detectClassesForCi(codeLine, list.indexOf(codeLine), detectedClassArray.get(AccessClassIndex));
//							detectedClassArray.set(AccessClassIndex, returnClassReference);
//							System.out.println("*****" + returnClassReference.getClassName());
//						}
//						
//					}
					
					
					/*
					 * ci part end*/
					
					//calculating TW and Cps of a program statement - start
					int lineCount = list.size();						
					evaluator evaluator = new evaluator();
					ArrayList<Integer> TWMarkList = new ArrayList<Integer>();
					ArrayList<Integer> CpsMarkList = new ArrayList<Integer>();
					int twMark = 0;
					int cpsMark = 0;					
					int Cp = 0;
					
					for(int rowCount = 0; rowCount < lineCount; rowCount++) {
						twMark = evaluator.calcTwOfStatement(ctcMarkList.get(rowCount), cncMarkList.get(rowCount), 0);
						TWMarkList.add(twMark);	
						cpsMark = evaluator.calcCpsOfStatement(csMarkList.get(rowCount), twMark);
						CpsMarkList.add(cpsMark);
//						cpsSubTotal = cpsSubTotal + cpsMark;
						
					}
					//calculating TW and Cps of a program statement - end			
					
					/*
					 * cr part start
					 */
					ArrayList<Integer> crMarkList = new ArrayList<Integer>();
					ArrayList<Integer> modifiedCpsList = new ArrayList<Integer>();
//					Collections.copy(modifiedCpsList, CpsMarkList); //this arraylist is to remove the cps values which has a cr value
					modifiedCpsList.addAll(CpsMarkList);
					crCalculator investigateCr = new crCalculator();
					for(String obj:list) {
						investigateCr.calcCrOfCodeBlock(obj, list.indexOf(obj));
						crMarkList.add(0);
						if((ItemsDetected.methodBlockStatus == "Closed") && (ItemsDetected.IsRecursiveMethodDetected == true) ) {
							int start = ItemsDetected.methodDetectLine;
							int end   = ItemsDetected.methodBlockEndLine + 1;
							System.out.println(start);
							System.out.println(end);
							int assignVal = 0;
							for(int count = start; count<end; count++) {
								assignVal = CpsMarkList.get(count);
								crMarkList.set(count, (assignVal * 2) );
								modifiedCpsList.set(count, 0);
							}
							ItemsDetected.IsRecursiveMethodDetected = false;
						}												
					}
//					
					
					/*
					 * cr part end
					 */
					
					//calculate cp value when there is a recursive method or not - start
					int CpTotal = 0;
					int crSubTotal = 0;
					int modiefiedCpsSubTotal = 0;
					//calculate Cp when code does not has recursive
					if(ItemsDetected.IsCodeHasRecursiveMethod == false) {
						for(int cpCounter = 0; cpCounter < CpsMarkList.size(); cpCounter++) {
							CpTotal = CpTotal + CpsMarkList.get(cpCounter);
						}
					}else if(ItemsDetected.IsCodeHasRecursiveMethod == true) {
						//cr sub total
						for(int x = 0; x < crMarkList.size(); x++) {
							crSubTotal = crSubTotal + crMarkList.get(x);
						}
						//modified cps sub total
						for(int y = 0; y < modifiedCpsList.size(); y++) {
							modiefiedCpsSubTotal = modiefiedCpsSubTotal + modifiedCpsList.get(y);
						}
						
						CpTotal = modiefiedCpsSubTotal + crSubTotal;
					}
					//calculate cp value when there is a recursive method or not - end
					
					
					Object[][] data = new Object[lineCount][10]; 
				    String[] columnNames = { "Line no", "Program statements", "Tokens identified under size factor", "Cs", "Ctc", "Cnc", "TW", "Cps", "Cr"}; 
				    for(int rowCount = 0; rowCount < lineCount; rowCount++) {
				    	
					    	data[rowCount][0] = (rowCount + 1);
					    	data[rowCount][1] = new String((list.get(rowCount)));
					    	data[rowCount][2] = ((ArrayList<ArrayList<String>>) allLineIdentiedTokens).get(rowCount);
					    	data[rowCount][3] = (csMarkList.get(rowCount));
					    	data[rowCount][4] = (ctcMarkList.get(rowCount));
					    	data[rowCount][5] = (cncMarkList.get(rowCount));
					    	data[rowCount][6] = (TWMarkList.get(rowCount));
					    	data[rowCount][7] = (CpsMarkList.get(rowCount));
					    	data[rowCount][8] = (crMarkList.get(rowCount));
					    
				    }
					JTable resultTable;
			        resultTable = new JTable(data, columnNames);  
			  
			        // adding it to JScrollPane 
			        JScrollPane sp = new JScrollPane(resultTable); 
			        sp.setSize(700, 750);
			        f.add(sp, BorderLayout.CENTER); 
					sp.setVisible(true);
					JLabel withoutReMethod;
					if(ItemsDetected.IsCodeHasRecursiveMethod == false) {
						withoutReMethod = new JLabel("Cp Value (without recursive method): " + CpTotal);
						f.add(withoutReMethod);
					}else if(ItemsDetected.IsCodeHasRecursiveMethod == true) {
						withoutReMethod = new JLabel("Cp Value (with recursive method): " + CpTotal);
						f.add(withoutReMethod);
					}
					
					f.repaint();
				    f.revalidate();
				}          
		      });  
	 }
	 
	 public static DetectedClass detectClassesForCi(String codeStatement, int lineNo, DetectedClass classReference) {
			
			if(ClassTracker.classBlockStatus == "OPENED") {
				if(codeStatement.contains("{")) {
					ClassTracker.classOpenBraCount++;
				}
				if(codeStatement.contains("}")) {
					ClassTracker.classCloseBraCount++;
				}			}
			if((codeStatement.contains("class")) && ((codeStatement.contains("{")) )) {
				ClassTracker.classBlockStatus = "OPENED";
				ClassTracker.classOpenBraCount++;
				//Detect class names, methods, variables and arrays
				int indexRecorder = 0;
				int chk = 0;				
				int SpaceAfterGeneric = 0;
				String ClassName;
				
				if (codeStatement.contains("class")) {
					
					indexRecorder =codeStatement.indexOf("class", chk);
					
					if (indexRecorder != -1) {
						SpaceAfterGeneric = codeStatement.indexOf(" ", codeStatement.indexOf("class", chk));				
						ClassName = (codeStatement.substring(SpaceAfterGeneric + 1, codeStatement.indexOf(" ", SpaceAfterGeneric + 1)));
						classReference.setClassName(ClassName);
						classReference.setClassStartLine(lineNo);
					}					
				}				
			}else if((codeStatement.contains("}")) && (ClassTracker.classBlockStatus == "OPENED") && (ClassTracker.classOpenBraCount == ClassTracker.classCloseBraCount)) {
				ClassTracker.classBlockStatus = "CLOSED";
				classReference.setClassEndLine(lineNo);
			}			
			return classReference;	
		}	

} 


