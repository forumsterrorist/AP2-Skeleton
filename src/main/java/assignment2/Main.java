package assignment2;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class Main {
	
	PrintStream out;
	HashMap <String, SetInterface<BigInteger>> storage;

    private void start() {
        @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
        out = new PrintStream(System.out);
        storage = new HashMap<>();
        
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
		HashMap <String, SetInterface<BigInteger>> tempStorage = new HashMap<>();
		
		if (nextCharIs(in, '/')) {
			in.close();
			return;
		} else if (nextCharIs(in, '?')) {
			in.skip(Pattern.quote("?"));
			printStatement(in);
		} else if (nextCharIsLetter(in)) {
			tempStorage = assignment(in);
		} else {
			in.close();
			throw new APException("error no statement");
		}
		
		if (in.hasNext()) {
			in.close();
			throw new APException("error no end of line");
		} else {
			storage.putAll(tempStorage);
		}
	}

	private HashMap<String, SetInterface<BigInteger>> assignment(Scanner in) throws APException {
		Identifier identifier = new Identifier(nextChar(in));
		SetInterface<BigInteger> tempset = new Set<>();
		HashMap<String, SetInterface<BigInteger>> temp = new HashMap<>();
		
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
				identifier.add(nextChar(in));
		}
		
		if (!nextCharIs(in, '=')) {
			throw new APException("error missing equals");
		} else {
			in.skip(Pattern.quote("="));
			tempset = expression(in);
			temp.put(identifier.get(), tempset);
		}
		
		return temp;
	}

	private SetInterface<BigInteger> expression(Scanner in) throws APException {
		SetInterface<BigInteger> resultSet = new Set<>();
		resultSet = term(in);
		
		while (in.hasNext()) {
			if (nextCharIs(in,'+')) {
				in.skip(Pattern.quote("+"));
				resultSet = resultSet.union(term(in));
			} else if (nextCharIs(in, '|')) {
				in.skip(Pattern.quote("|"));
				resultSet = resultSet.symmetricDifference(term(in));
			} else if (nextCharIs(in, '-')) {
				in.skip(Pattern.quote("-"));
				resultSet = resultSet.complement(term(in));
			} else if (nextCharIs(in, ')')) {
				return resultSet;
			} else {
				throw new APException("error missing additive operator");
			}
		}
		
		return resultSet;
	}

	private SetInterface<BigInteger> term(Scanner in) throws APException {
		SetInterface<BigInteger> set;
		set = factor(in);
		
		while (in.hasNext()) {
			if (nextCharIs(in, '*')) {
				in.skip(Pattern.quote("*"));
				set = set.intersection(factor(in));
			} else {
				return set;
			}
		}
		
		return set;
	}

	private SetInterface<BigInteger> factor(Scanner in) throws APException {
		SetInterface<BigInteger> set;
		
		if (nextCharIsLetter(in)) {
			set = getIdentifier(in);
			return set;
		} else if (nextCharIs(in, '{')) {
			in.skip(Pattern.quote("{"));
			set = readSet(in);
			return set;
		} else if (nextCharIs(in, '(')) {
			in.skip(Pattern.quote("("));
			set = expression(in);
			if (nextCharIs(in, ')')) {
				in.skip(Pattern.quote(")"));
				return set;
			} else {
				throw new APException("error missing parenthesis");
			}
		} else {
			throw new APException("error invalid factor");
		}
	}

	private Set<BigInteger> readSet(Scanner in) throws APException {
		Set<BigInteger> output = new Set<>();
		BigInteger number;
		StringBuilder temp = new StringBuilder("");
		
		while (nextCharIsDigit(in) || nextCharIs(in, ',')) {
			if (nextCharIsDigit(in)) {
				while (nextCharIsDigit(in)) {
					temp.append("" + nextChar(in));
				}
				number = new BigInteger(temp.toString());
				output.add(number);
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
			return output;
		} else {
			throw new APException("error missing closing bracket");
		}
	}

	private SetInterface<BigInteger> getIdentifier(Scanner in) throws APException {
		Identifier temp;
		
		if (!nextCharIsLetter(in)) {
			throw new APException("error invalid identifier");
		} else {
			temp = new Identifier(nextChar(in));
		}
		
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			temp.add(nextChar(in));
		}
		
		if (storage.containsKey(temp.get())) {
			return storage.get(temp.get());
		} else {
			throw new APException("error undefined variable");
		}
	}

	private void printStatement(Scanner in) throws APException {
		String output;
		SetInterface<BigInteger> result;
		
		if (!in.hasNext()) {
			throw new APException("error missing input");
		} else {
			result = expression(in);
			output = result.toString();
			out.println(output);
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
