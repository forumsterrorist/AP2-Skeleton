package assignment2;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class Main {
	
	PrintStream out;
	//HashMap <Identifier, Set<BigInteger>> storage;

    private void start() {
        @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
        out = new PrintStream(System.out);
        
        while (true) {
        	try {
				statement(input.nextLine().replaceAll("\\s+", ""));
			} catch (APException e) {
				out.println(e.getMessage());;
			}
        }
    }
    
    private void statement(String line) throws APException {
		Scanner in = new Scanner(line);
		in.useDelimiter("");
		
		if (nextCharIs(in, '/')) {
			in.close();
			return;
		} else if (nextCharIs(in, '?')) {
			in.skip(Pattern.quote("?"));
			printStatement(in);
		} else if (nextCharIsLetter(in)) {
			assignment(in);
		} else {
			in.close();
			throw new APException("error no statement");
		}
		
		if (in.hasNext()) {
			in.close();
			throw new APException("error no end of line");
		}
	}

	private void assignment(Scanner in) throws APException {
		Identifier temp = new Identifier(nextChar(in));
		
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
				temp.add(nextChar(in));
		}
		
		if (!nextCharIs(in, '=')) {
			throw new APException("error missing equals");
		} else {
			in.skip(Pattern.quote("="));
			try {
				expression(in);
			} catch (APException e) {
				out.println(e.getMessage());
			}
		}
	}

	private void expression(Scanner in) throws APException {
		term(in);
		
		while (in.hasNext()) {
			if (nextCharIs(in,'+')) {
				in.skip(Pattern.quote("+"));
				term(in);
			} else if (nextCharIs(in, '|')) {
				in.skip(Pattern.quote("|"));
				term(in);
			} else if (nextCharIs(in, '-')) {
				in.skip(Pattern.quote("-"));
				term(in);
			} else if (nextCharIs(in, ')')) {
				return;
			} else {
				throw new APException("error missing additive operator");
			}
		}
	}

	private void term(Scanner in) throws APException {
		factor(in);
		
		while (in.hasNext()) {
			if (nextCharIs(in, '*')) {
				in.skip(Pattern.quote("*"));
				factor(in);
			} else {
				return;
			}
		}
	}

	private void factor(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			getIdentifier(in);
			return;
		} else if (nextCharIs(in, '{')) {
			in.skip(Pattern.quote("{"));
			readSet(in);
			return;
		} else if (nextCharIs(in, '(')) {
			in.skip(Pattern.quote("("));
			expression(in);
			if (nextCharIs(in, ')')) {
				in.skip(Pattern.quote(")"));
				return;
			} else {
				throw new APException("error missing parenthesis");
			}
		} else {
			throw new APException("error invalid factor");
		}
	}

	private void readSet(Scanner in) throws APException {
		BigInteger number;
		StringBuilder temp = new StringBuilder("");
		
		while (nextCharIsDigit(in) || nextCharIs(in, ',')) {
			if (nextCharIsDigit(in)) {
				while (nextCharIsDigit(in)) {
					temp.append("" + nextChar(in));
				}
				number = new BigInteger(temp.toString());
				out.println(number.toString());
				temp = new StringBuilder("");
			} else if (nextCharIs(in, ',')) {
				in.skip(Pattern.quote(","));
				continue;
			} else {
				throw new APException("error invalid set");
			}
		}
		
		if (nextCharIs(in, '}')) {
			in.skip(Pattern.quote("}"));
			return;
		} else {
			throw new APException("error missing closing bracket");
		}
	}

	private void getIdentifier(Scanner in) throws APException {
		Identifier temp;
		
		if (!nextCharIsLetter(in)) {
			throw new APException("error invalid identifier");
		} else {
			temp = new Identifier(nextChar(in));
		}
		
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			temp.add(nextChar(in));
		}
		
		return;
	}

	private void printStatement(Scanner in) throws APException {
		if (!in.hasNext()) {
			throw new APException("error missing input");
		} else {
			expression(in);
		}
	}



	char nextChar (Scanner in) {
    	return in.next().charAt(0); 
    }
    
    boolean nextCharIs(Scanner in, char c) {
    	return in.hasNext(Pattern.quote(c+"")); 
    }
    
    boolean nextCharIsDigit (Scanner in) {
    	return in.hasNext("[0-9]"); 
    }
    
    boolean nextCharIsLetter (Scanner in) {
    	return in.hasNext("[a-zA-Z]"); 
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
