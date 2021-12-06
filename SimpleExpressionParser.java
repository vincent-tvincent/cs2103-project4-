/**
 * Starter code to implement an ExpressionParser. Your parser methods should use the following grammar:
 * S := S+M | M
 * M := M*P | P
 * P := (S) | L
 * L := [a-z] | [0-9]+
 */
public class SimpleExpressionParser implements ExpressionParser {
	/*
	 * Attempts to create an expression tree -- flattened as much as possible -- from the specified String.
         * Throws a ExpressionParseException if the specified string cannot be parsed.
	 * @param str the string to parse into an expression tree
	 * @return the Expression object representing the parsed expression tree
	 */
	public Expression parse (String str) throws ExpressionParseException {
		// Remove spaces -- this simplifies the parsing logic
		str = str.replaceAll(" ", "");
		Expression expression = parseExpression(str);
		if (expression == null) {
			// If we couldn't parse the string, then raise an error
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		// Flatten the expression before returning
		expression.flatten();
		return expression;
	}
	
	protected Expression parseExpression (String str) {
		System.out.println("S");
		return parseS(str);
	}

	private Expression parseS(String str) {
		char[] chars = str.toCharArray();
		// find + outside of ()
		int plus = -1;
		int pDepth = 0;
		for (int i = 0; i < chars.length - 1; i++) {
			char c = chars[i];
			if(c == '(') {
				pDepth++;
			} else if(c == ')') {
				pDepth--;
				if(pDepth < 0) {
					return null;
				}
			} else if(c == '+') {
				if(pDepth == 0) {
					plus = i;
					break;
				}
			}
		}

		if(plus == -1) {
			// M
			System.out.println("M");
			return parseM(str);
		} else {
			// S + M
			System.out.println("S + M");
			Operator op = new Operator("+");

			String s1 = str.substring(0, plus);
			System.out.println("S1:" + s1);
			String s2 = str.substring(plus + 1);
			System.out.println("S2:" + s2);
			Expression first = parseS(s1);
			Expression second = parseM(s2);

			if(first == null || second == null) {
				return null;
			}

			first.setParent(op);
			second.setParent(op);

			op.addSubexpression(first);
			op.addSubexpression(second);

			return op;
		}
	}

	private Expression parseM(String str) {

		char[] chars = str.toCharArray();
		// find * outside of ();
		int mult = -1;
		int pDepth = 0;
		for (int i = 0; i < chars.length - 1; i++) {
			char c = chars[i];
			if(c == '(') {
				pDepth++;
			} else if(c == ')') {
				pDepth--;
				if(pDepth < 0) {
					return null;
				}
			} else if(c == '*') {
				if(pDepth == 0) {
					mult = i;
					break;
				}
			}
		}

		if(mult == -1) {
			// P
			System.out.println("P");
			return parseP(str);
		} else {
			// M * P
			System.out.println("M * P");
			Operator op = new Operator("*");

			String s1 = str.substring(0, mult);
			System.out.println("S1:" + s1);
			String s2 = str.substring(mult + 1);
			System.out.println("S2:" + s2);
			Expression first = parseS(s1);
			Expression second = parseM(s2);
			if(first == null || second == null) {
				return null;
			}

			first.setParent(op);
			second.setParent(op);

			op.addSubexpression(first);
			op.addSubexpression(second);

			return op;
		}
	}

	private Expression parseP(String str) {

		char[] chars = str.toCharArray();
		// find (
		int start = -1;
		int end = -1;
		int count = 0;
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(c == '(') {
				if(start != -1) {
					count++;
				} else {
					start = i;
					System.out.println("start: " + i);
				}
			} else if (c == ')') {
				if(start != -1) {
					if(count > 1) {
						count--;
					} else {
						end = i;
						System.out.println("end: " + i);
						break;
					}
				} else {
					return null;
				}
			}
		}

		if(start != -1 && end == -1) {
			return null;
		}

		if(end != -1) {
			// (S)
			System.out.println("(S)");
			Operator op = new Operator("()");
			String s = str.substring(start + 1, end);
			System.out.println(s);
			Expression e = parseS(str.substring(start + 1, end));
			if(e == null) {
				return null;
			}
			e.setParent(op);
			op.addSubexpression(e);
			return op;
		} else {
			for(int ascii : chars) {
				if (ascii >= 40 && ascii <= 43) {
					// S
					System.out.println("S");
					return parseS(str);
				}
			}

			// L
			System.out.println("L");
			return parseL(str);
		}
	}

	public Expression parseL(String str) {
		// L
		char[] chars = str.toCharArray();
		for(int ascii : chars) {
			// if not [a-z] || [A-Z] || [0-9]
			if(!(((ascii >= 97 && ascii <= 122) || (ascii >= 65 && ascii <= 90)) || (ascii >= 48 && ascii <= 57))){
				return null;
			}
		}
		return new Literal(str);
	}
}
