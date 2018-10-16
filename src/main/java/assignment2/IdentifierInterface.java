package assignment2;

public interface IdentifierInterface {
	/*	Elements: Characters
	 * 	Structure: Linear
	 * 	Domain: All alphanumeric sequences where the initial character is a letter
	 * 	
	 * 	Constructors:
	 * 
	 * 	Identifier(char first);
	 * 	PRE - 
	 * 	POST - A new Identifier object is created, containing the given character. Identifier starts with 
	 *         letter, and may only have letters/numbers.   

	 * 	POST - A new Identifier object is created, containing the character literal 'a'.
	 */
	
	void init(char first);
	
	/*	PRE -  The character given lies within the domain.
	 * 	POST - The Identifier object is initialized, containing the given character.
	 */
	
	void add(char toAdd);
	
	/*	PRE - The character given lies within the domain.
	 *  POST - the given character is appended.
	 */
	
	String get();
	/*	PRE - 
	 * 	POST - A string is returned, containing the characters representing the value of the Identifier.
	 */
	
	char getAt(int index);
	/*	PRE - The index exists within the Identifier object.
	 * 	POST - The character at the given index is returned.
	 */
	
	boolean equals(Identifier input);
	/* PRE - 
	 * POST - Returns true if both Identifiers contain the same sequence of characters, and false otherwise.
	 */
	
	int size();
	/* PRE - 
	 * POST - Returns the number of characters in the Identifier; if the Identifier does not contain any, returns 0.
	 */
}
