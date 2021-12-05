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
		return parseS(str);
	}

	private Expression parseS(String str) {

		char[] chars = str.toCharArray();
		// find +
		int plus = -1;
		for (int i = 0; i < chars.length - 1; i++) {
			char c = chars[i];
			if(c == '+') {
				plus = i;
				break;
			}
		}

		if(plus == -1) {
			// M
			return parseM(str);
		} else {
			// S + M
			Operator op = new Operator('+');

			// TODO: check substring
			Expression first = parseS(str.substring(plus));
			Expression second = parseM(str.substring(0, plus));

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
		// find *
		int mult = -1;
		for (int i = 0; i < chars.length - 1; i++) {
			char c = chars[i];
			if(c == '*') {
				mult = i;
				break;
			}
		}

		if(mult == -1) {
			// P
			return parseP(str);
		} else {
			// M * P
			Operator op = new Operator('*');

			// TODO: check substring
			Expression first = parseS(str.substring(mult));
			Expression second = parseM(str.substring(0, mult));

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
		for (int i = 0; i < chars.length - 1; i++) {
			char c = chars[i];
			if(c == '(') {
				if(start != -1) {
					count++;
				} else {
					start = i;
				}
			} else if (c == ')') {
				if(start != -1) {
					if(count > 0) {
						count--;
					} else {
						end = i;
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

		if(end == -1) {
			// (S)
			return parseS(str);
		} else {
			// L
			// TODO: check ascii
			for(int ascii : chars) {
				// if not [a-z] || [A-Z] || [0-9]
				if(!(((ascii >= 97 && ascii <= 122) || (ascii >= 65 && ascii <= 90)) || (ascii >= 48 && ascii <= 57))){
					return null;
				}
			}
			return new Literal(str);
		}
	}
}
