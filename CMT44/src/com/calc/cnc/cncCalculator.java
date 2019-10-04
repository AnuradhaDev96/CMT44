package com.calc.cnc;

public class cncCalculator {
	private static final String openCurlyBra = "{"; 
	private static final String closeCurlyBra = "}"; 
	private static final String keywordIf = "if";
	private static final String keywordFor = "for";
	private static final String keywordWhile = "while";
	private static final String keywordDo = "do";
	
	public boolean calcCncOfStatement(String codeStatement, int index) {
		/*
		 THIS METHOD CANNOT REDUCE THE INNER SINGLE BLOCK LEVEL COUNT 
		 */
		int blockStartIndex = 0;
		int blockEndIndex = 0;
		int chk = 0;
//		int cncMark = 0;
//		System.out.println(index);
		
		if (codeStatement.contains(keywordIf)) {
			BlockSets.isIfFound = true;
		}else if (codeStatement.contains(keywordFor)) {
			BlockSets.isForFound = true;
		}else if (codeStatement.contains(keywordWhile)) {
			BlockSets.isWhileFound = true;
		}
		else if (codeStatement.contains(keywordDo)) {
			BlockSets.isDoFound = true;
		}
		
		if ((BlockSets.isIfFound == true) || (BlockSets.isForFound == true) || (BlockSets.isWhileFound == true) || (BlockSets.isDoFound == true) ) {
			
			blockStartIndex = codeStatement.indexOf(openCurlyBra, chk);
			
			if (blockStartIndex != -1) {
				BlockSets.openCurlyBraArray.add(openCurlyBra);
				
				if (BlockSets.isBlockOpen == false) {
					BlockSets.blockBeginLine = index;
					BlockSets.isBlockOpen = true;
					//new
					BlockSets.isBlockClose = false;
				}
				
			}
			
			blockEndIndex = codeStatement.indexOf(closeCurlyBra, chk);
			if (blockEndIndex != -1) {
				
				BlockSets.closeCurlyBraArray.add(closeCurlyBra);
				
				if (BlockSets.openCurlyBraArray.size() > BlockSets.closeCurlyBraArray.size()) {
					BlockSets.isBlockClose = false;
				}else if ((BlockSets.openCurlyBraArray.size() == BlockSets.closeCurlyBraArray.size()) 
						 && (!(BlockSets.openCurlyBraArray.isEmpty()) && !(BlockSets.closeCurlyBraArray.isEmpty()))) {
					
					BlockSets.isBlockOpen = false;
					BlockSets.isBlockClose = true;
					
					BlockSets.cncMark = BlockSets.openCurlyBraArray.size();
					BlockSets.blockEndLine = index;
					
//					System.out.println("cnc mark:" + cncMark);
//					System.out.println("this block begins at:" + BlockSets.blockBeginLine);
//					System.out.println("this block ends at:" + index);
					
					this.resetMainBlock();
				}
			}
		}
//		System.out.println("cnc mark:" + BlockSets.cncMark);
		return BlockSets.isBlockClose;
	}
	
	public void resetMainBlock() {
		BlockSets.openCurlyBraArray.clear();
		BlockSets.closeCurlyBraArray.clear();
		BlockSets.isIfFound = false;
		BlockSets.isForFound = false;
		BlockSets.isWhileFound = false;
		BlockSets.isDoFound = false;

	}
	
}
