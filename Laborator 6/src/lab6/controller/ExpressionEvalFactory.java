/*
 * Copyright (c) 2014 Meding Software Technik - All Rights Reserved.
 */
package lab6.controller;

import static java.lang.Math.pow;
import org.apache.commons.jexl2.JexlArithmetic;
import org.apache.commons.jexl2.JexlEngine;

/**
 * Expression evaluator helper.
 * @author uwe
 */
public class ExpressionEvalFactory {

	private final static JexlEngine engine;

	static {
		engine = new JexlEngine(null, new ExtendedJexlEngine(false), null, null);
	}

	/**
	 * Get an instance of the expression evaluator.
	 * @return the evaluator
	 */
	public static JexlEngine getInstance() {
		return engine;
	}

	/**
	 * Jexl engine extension: Changed ^ operator to calculate the power, instead bitwise xor.
	 * @author uwe
	 */
	private static class ExtendedJexlEngine extends JexlArithmetic {

		public ExtendedJexlEngine(boolean lenient) {
			super(lenient);
		}

		/**
		 * Overridden to calculate pow(left, right).
		 * @param left  value
		 * @param right exponent
		 * @return the power
		 */
		@Override
		public Object bitwiseXor(Object left, Object right) {
			double l = toDouble(left);
			double r = toDouble(right);
			return Math.pow(l, r);
		}
	}

        
}