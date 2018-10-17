package assignment2;

import java.util.Objects;

public class Identifier implements IdentifierInterface {
	private String identifierString;
	
	Identifier(char first){
		identifierString = "";
		init(first);
	}
	
	@Override
	public void init(char first) {
		if(Character.isLetter(first)) { //First char has to be a letter, so we check this input.
			identifierString += first; //if the char is a letter, we load it into the string
		}
	} 
	
	@Override
	public void add(char toAdd) {
		if(Character.isLetterOrDigit(toAdd)) { //If the char is a letter or digit, then PRE is met.
			identifierString += toAdd; //add the character to the string.
		}
	}

	@Override
	public String get() {
		return identifierString;
	}

	@Override
	public boolean equals(Identifier input) {
		String TempIdentifierString = input.get(); 
		if(TempIdentifierString.equals(identifierString)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return 1;
	}

	@Override
	public int size() {
		if (identifierString.length() < 1) {
			return 0;// Empty string, so return 0.
		}
		return identifierString.length();
	}

	@Override
	public char getAt(int index) {
		return identifierString.charAt(index);
	}

} 
