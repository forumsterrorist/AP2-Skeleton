package assignment2;

public interface SetInterface {
	/*	Elements: Identifier objects
	 * 	Structure: Unstructured
	 * 	Domain: Identifier objects
	 * 
	 * 	Constructors:
	 * 
	 * 	SetInterface();
	 * 	PRE - 
	 * 	POST - A new empty Set is created.
	 * 
	 * 	SetInterface(IdentifierInterface first);
	 * 	PRE - 
	 * 	POST - A new Set is created with the given Identifier object as an element.
	 * 
	 * 	SetInterface(SetInterface set);
	 * 	PRE - The input Set contains at least one Identifier object.
	 * 	POST - A new Set is created, containing the contents of the input Set.
	 */	
	
	void init();
	/*	PRE - 
	 * 	POST - The Set is initialized to the empty set.
	 */
	
	void add(Identifier input);
	/*	PRE - The number of elements within the set is less than 20.
	 * 	POST - The input Identifier object is added to the Set. If the Identifier already exists, then it is not added.
	 */
	
	Identifier get();
	/*	PRE - The Set contains at least one object.
	 * 	POST - A random element is returned.
	 */
	
	void remove(Identifier input);
	/*	PRE - The given Identifier exists in the set.
	 * 	POST - Removes the given Identifier from the set.
	 */
	
	int size();
	/*	PRE - 
	 * 	POST - Returns the number of elements in the Set.
	 */
	
	boolean equals(Set input);
	/*	PRE - 
	 *	POST - Returns true if both Sets contain the same elements and false otherwise. 
	 */
	
	boolean contains(Identifier input);
	/*	PRE - The Set has been initialized.
	 *	POST - Returns true if the input is contained within the Set and false otherwise. 
	 */
	
	SetInterface union(Set input);
	/*	PRE - The sum of the sizes of each Set does not exceed 20.
	 *	POST - Returns a Set containing the union of the input Set and this Set. 
	 */
	
	SetInterface intersection(Set input);
	/*	PRE - Both the Set and the input Set have been initialized.
	 *	POST - Returns a Set containing the intersection of the input Set and this Set. 
	 */
	
	SetInterface difference(Set input);
	/*	PRE - Both the Set and the input Set have been initialized.
	 *	POST - Returns a Set containing the difference of the input Set and this Set. 
	 */
	
	SetInterface symmetricDifference(Set input);
	/*	PRE - Both the Set and the input Set have been initialized.
	 *	POST - Returns a Set containing the symmetric difference of the input Set and this Set. 
	 */
}
