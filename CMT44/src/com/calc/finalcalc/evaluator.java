package com.calc.finalcalc;
//Developer | R.M.N.S.Rathnayake
import java.util.List;

public class evaluator {
	public int calcTwOfStatement(int ctcMark, int cncMark, int ciMark) {// TODO: ci and cnc must be added as params
		return ctcMark + cncMark + ciMark;
	}
	
	public int calcCpsOfStatement(int ciMark, int twMark) {
		return ciMark * twMark;
	}
}
