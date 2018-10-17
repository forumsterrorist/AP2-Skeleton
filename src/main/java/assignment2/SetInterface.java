package assignment2;

/** @elements
 *    objects of type E
 * @structure There is no structure
 * @domain 
 * @constructors *  
 *  Set();
 *  	precondition: -
 *  	postcondition: The new set created is empty
 *  Set(Set source);
 *   	precondition: -
 *  	postcondition: A new set has been created which contains a copy of the source set
 */

public interface SetInterface<E> {
	void init();
	 /*		Precondition: 	-
	  * 	Postcondition: 	The set is empty
	  */

	void add(E d);
	 /*		Precondition: -
	  * 	Postcondition: Data has been  
	  */
	 
	 void remove(E toRemove);
	 /*		Precondition:	The element is in the set
	  * 	Postcondition:	An element has been removed from the set
	  */
	 
	 E retrieve();
	 /*		Precondition:	the set is not empty
	  * 	Postcondition:	An element from the set is returned
	  */
	 
	 boolean isEmpty();
	 /*		Precondition:	-
	  * 	Postcondition:	Return True if the set is empty (elements = 0)
	  * 					Return False if the set is NOT empty (elements > 0)
	  */
		
	boolean isInList(E element);
	 /*		Precondition: -
	  * 	Postcondition: 	Return True if the element is in the set
	  * 				  	Return False if its not in the set
	  */
		 
	SetInterface<E> union(SetInterface<E> set1);
	 /*		Precondition:	-
	  * 	Postcondition:	Return the union between set1 and set2
	  */
	SetInterface<E> intersection(SetInterface<E> set1);
	 /*		Precondition:	-
	  * 	Postcondition:	Return the intersection between set1 and set2
	  */
	SetInterface<E> complement(SetInterface<E> set1);
	 /*		Precondition:	-
	  * 	Postcondition:	Return the complement between set1 and set2
	  */
	SetInterface<E> symmetricDifference(SetInterface<E> set1);
	 /*		Precondition:	-
	  * 	Postcondition:	Return the symmetric difference between set1 and set2
	  */
	String toString();
	/*		Precondition: 	-
	 * 		Postcondition: Returns a string representation of the set.
	 */
			
}