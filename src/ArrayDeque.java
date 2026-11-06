import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

import exceptions.EmptyCollectionException;

public class ArrayDeque<T> implements DequeInterface<T>{
	private int nbElements; //Invariant : deque.length >= nbElements >= 0
	private int first;		//Invariant : deque.length >= first >= 0
	private int last;		//Invariant : deque.length >= last >= 0
	private T[] deque ;		//Invariant : Pour tout i : first <= i <= last : nbElements <> 0 <=> tab[i] <> null
	private int nbOp;


	//Constructs an empty deque with an initial capacity of ten
	@SuppressWarnings("unchecked")
	public ArrayDeque(){
		nbElements = 0;
		deque = (T[]) new Object[5];
		first = 2;
		last = 2;
		nbOp = 0;
	}
	/**Invariant -> 0 is assigned to nbElements -> deque.length >= nbElements >= 0
	 * 				2 is assigned to first -> deque.length >= first >= 0
	 * 				2 is assigned to last -> deque.length >= last >= 0
	 */


	/**Constructs an empty deque with the specified initial capacity 
	 * @param size the size of the deque
	 * @exception IllegalArgumentException if the size is null or negative
	 */
	@SuppressWarnings("unchecked")
	public ArrayDeque(int size) throws IllegalArgumentException{
		if(size <= 0) throw new IllegalArgumentException();
		nbElements = 0;
		deque = (T[]) new Object[size];
		first = size / 2;
		last = first;
		nbOp = 0;
	}
	/**Invariant -> 0 is assigned to nbElements -> deque.length >= nbElements >= 0
	 * 				deque.length / 2 is assigned to first -> deque.length >= first >= 0
	 * 				deque.length / 2 is assigned to last -> deque.length >= last >= 0
	 */
	
	
	/**get nbOp
	 * @return nbOp
	 */
	public int getNbOp() {
		return nbOp;
	}
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Inserts the specified element at the front of this deque 
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public void offerFirst(T t) throws NullPointerException {
		nbOp = 1;
		if(t == null) throw new NullPointerException();	//O(1)
		nbOp++;
		if(nbElements != 0) {							//O(1)
			nbOp++;
			if(nbElements < deque.length){				//O(1)
				first--;
				nbOp += 2;
				if(first < 0) {							//O(1)
					first = deque.length - 1;			//O(1)
					nbOp++;
				}										//O(1)
			}else{
				expandDeque(6);							//O(deque.length + 11)
				nbOp++;
			}											//O(deque.length + 11)
		}												//O(deque.length + 11)
		deque[first] = t;								//O(1)
		nbElements++;									//O(1)
		nbOp += 2;
	}													//O(deque.length + 11)
	/**Invariant -> if t = null nbElements is unchanged; otherwise can only have 1 added. If nbElement = deque.length, deque is enlarged -> deque.length >= nbElements >= 0
	 * 				if t = null or nbElements = 0, first is unchanged; otherwise if first = 0, deque.length is assigned to first; otherwise have only 1 removed, or expandDeque() invariant is used -> deque.length >= first >= 0
	 * 				if t = null or nbElements = 0 or nbElements < deque.length, last is unchanged; otherwise expandDeque() invariant is used -> deque.length >= last >= 0
	 */


	/**Copy deque into a new, bigger array
	 * @param locationFirstElement the first location of the new array where deque will be copied
	 */
	@SuppressWarnings("unchecked")
	private void expandDeque(int locationFirstElement) {
		T[] newArray = (T[]) new Object[deque.length + 11];					//O(deque.length + 11)
		nbOp += deque.length + 11;
		copyBetween(first, deque.length, newArray, locationFirstElement);	//O(deque.length)
		nbOp += 2;
		if(first != 0) {													//O(1)
			locationFirstElement += deque.length - first;					//O(1)
			copyBetween(0, last+1, newArray, locationFirstElement);			//O(deque.length)
			nbOp += 2;
		}																	//O(deque.length)
		first = 5;															//O(1)
		last = first + nbElements;											//O(1)
		deque = newArray;													//O(1)
		nbOp += 3;
	}																		//O(deque.length + 11)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				5 is assigned to first -> deque.length >= first >= 0
	 * 				5 + nbElements is assigned to last -> deque.length >= last >= 0
	 */
	
	
	/**Copy deque between 'begin' and 'end' into an array
	 * @param begin the location of the table where the table will be copied first
	 * @param end the location of the table where the copy will be stopped
	 * @param array the table where deque will be copied
	 * @param locationFirstElement the first element of array where deque will be copied
	*/
	private void copyBetween(int begin, int end, Object[] array, int locationFirstElement) {
		nbOp += 2;
		for(int i = begin; i < end; i++) {			//O(deque.length)
			array[locationFirstElement] = deque[i];	//O(1)
			locationFirstElement++;					//O(1)
			nbOp += 4;
		}											//O(deque.length)
	}
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Inserts the specified element at the end of this deque
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public void offerLast(T t) throws NullPointerException {
		nbOp = 1;
		if(t == null) throw new NullPointerException();	//O(1)
		nbOp++;
		if(nbElements != 0) {							//O(1)
			nbOp++;
			if(nbElements < deque.length){				//O(1)
				last++;									//O(1)
				nbOp += 2;
				if(last == deque.length) {				//O(1)
					last = 0;							//O(1)
					nbOp++;
				}										//O(1)
			}else{
				expandDeque(5);							//O(deque.length + 11)
				nbOp++;
			}											//O(deque.length + 11)
		}												//O(deque.length + 11)
		deque[last] = t;								//O(1)
		nbElements++;									//O(1)
		nbOp += 2;
	}													//O(deque.length + 11)
	/**Invariant -> if t = null nbElements is unchanged; otherwise can only have 1 added. If nbElement = deque.length, deque is enlarged -> deque.length >= nbElements >= 0
	 * 				if t = null or nbElements = 0 or nbElements < deque.length, first is unchanged; otherwise expandDeque() invariant is used -> deque.length >= first >= 0
	 * 				if t = null or nbElements = 0, last is unchanged; otherwise if last = deque.length - 1, 0 is assigned to last; otherwise have only 1 added, or expandDeque() invariant is used -> deque.length >= last >= 0
	 */


	/**Retrieves and removes the first element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T removeFirst() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		T elem = deque[first];										//O(1)
		nbOp += 2;
		if(nbElements > 1) {										//O(1)
			first++;												//O(1)
			nbOp++;
		}															//O(1)
		nbElements--;												//O(1)
		nbOp += 2;
		if(first == deque.length) {									//O(1)
			first = 0;												//O(1)
			nbOp++;
		}															//O(1)
		nbOp++;
		return elem;												//O(1)
	}																//O(1)
	/**Invariant -> if nbElements = 0, nbElements is unchanged; otherwise can only have 1 removed -> deque.length >= nbElements >= 0
	 * 				if nbElements = 0, first is unchanged, or can only have 1 added. If first = deque.length, 0 is assigned to first -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Retrieves and removes the last element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T removeLast() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		T elem = deque[last];										//O(1)
		nbOp += 2;
		if(nbElements > 1) {										//O(1)
			last--;													//O(1)
			nbOp++;
		}															//O(1)
		nbElements--;												//O(1)
		nbOp += 2;
		if(last < 0) {												//O(1)
			last = deque.length - 1;								//O(1)
			nbOp++;
		}															//O(1)
		nbOp++;
		return elem;												//O(1)
	}																//O(1)
	/**Invariant -> if nbElements = 0, if nbElements = 0, nbElements is unchanged; otherwise can only have 1 removed -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				if nbElements = 0, if nbElements = 0, last is unchanged; otherwise have only 1 removed. If last = 0, deque.length - 1 is assigned to last -> deque.length >= last >= 0
	 */


	/**Retrieves the first element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T peekFirst() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		nbOp++;
		return deque[first];										//O(1)
	}																//O(1)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Retrieves the last element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T peekLast() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		nbOp++;
		return deque[last];											//O(1)
	}																//O(1)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Returns the number of elements in this deque.
	 * @return the number of elements in this deque.
	 */
	@Override
	public int size() {
		nbOp = 1;
		return nbElements;	//O(1)
	}						//O(1)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Returns true if this deque contains the specified element. 
	 * More formally, returns true if and only if this deque contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this deque is to be tested
	 * @return true if this deque contains the specified element, false otherwise
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public boolean contains(Object o) throws NullPointerException {
		nbOp++;
		if(o == null) throw new NullPointerException();				//O(1)
		nbOp++;
		if(nbElements == 0) return false;							//O(1)
		boolean isContained = false;								//O(1)
		nbOp += 2;
		if(first <= last) {											//O(1)
			isContained = searchContains(first, last+1, o);			//O(deque.length)
			nbOp++;
		}else {
			isContained = searchContains(first, deque.length, o);	//O(deque.length)
			nbOp += 2;
			if(!isContained) {
				isContained = searchContains(0, last+1, o);			//O(deque.length)
				nbOp++;
			}														//O(deque.length)
		}															//O(deque.length)
		nbOp++;
		return isContained;											//O(1)
	}																//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */
	
	
	/**Returns true if this deque contains the specified element between 'begin' and 'end'. 
	 * More formally, returns true if and only if this deque contains at least one element e between 'begin' and 'end' such that o.equals(e).
	 * @param begin the location of the table where the element will be searched first
	 * @param end the location of the table where the search for the element will be stopped
	 * @param o element whose presence in this deque is to be tested
	 * @return true if this deque contains the specified element, false otherwise
	 */
	private boolean searchContains(int begin, int end, Object o) {
		boolean isContained = false;						//O(1)
		nbOp += 3;
		for(int i = begin; i < end && !isContained; i++) {	//O(deque.length)
			isContained = o.equals(deque[i]);				//O(1)
			nbOp += 3;
		}													//O(deque.length)
		nbOp++;
		return isContained;									//O(1)
	}														//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		nbOp = 1;
		if(nbElements == 0) return "Deque vide";					//O(1)
		String sentence = "";										//O(1)
		nbOp += 2;
		if(first <= last) {											//O(1)
			sentence = browseArrayToString(first, last+1);			//O(deque.length)
			nbOp++;
		} else {
			sentence = browseArrayToString(first, deque.length);	//O(deque.length)
			sentence += browseArrayToString(0, last+1);				//O(deque.length)
			nbOp += 2;
		}															//O(deque.length)
		nbOp++;
		return sentence;											//O(1)
	}																//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */
	
	
	/**Returns a string representation of the object between 'begin' and 'end'.
	 * @param begin the location of the table where the element will be searched first
	 * @param end the location of the table where the search for the element will be stopped
	 * @return true if this deque contains the specified element, false otherwise
	 */
	private String browseArrayToString(int begin, int end) {
		String sentence = "";				//O(1)
		nbOp += 3;
		for(int i = begin; i < end; i++) {	//O(deque.length)
			sentence += deque[i] + "\n";	//O(1)
			nbOp += 3;
		}									//O(deque.length)
		nbOp++;
		return sentence;					//O(1)
	}										//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Returns true if this deque contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this deque
     * @return true if this deque contains all the elements of the specified collection, false otherwise
     * @throws NullPointerException if the specified collection is null
     */
	@Override
	public boolean containsAll(Collection<?> c) {
		Object[] array = c.toArray();							//O(array.length)
		nbOp = array.length + 1;
		if(nbElements == 0) return false;						//O(1)
		boolean isContained = true;								//O(1)
		nbOp += 3;
		for(int i = 0; i < array.length && isContained; i++) {	//O(array.length)
			isContained = contains(array[i]);					//O(deque.length)
			nbOp += 3;
		}														//O(array.length * deque.length)
		nbOp++;
		return isContained;										//O(1)
	}															//O(array.length * deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */

	
	/**Inserts all of the elements one by one in the specified collection at the front of
     * this deque.
     * Null elements of the specified collection are ignored.
     * @param c collection containing elements to be added to this deque
     * @return true if this collection has changed after the call, false otherwise
     * @throws NullPointerException if the specified collection is null
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean offerAll(Collection<? extends T> c) {
		T[] array = (T[]) c.toArray();			//O(array.length)
		boolean isChanged = false;				//O(1)
		nbOp = array.length + 3;
		for(int i = 0; i < array.length; i++) {	//O(array.length)
			try {
				nbOp++;
				offerFirst(array[i]);			//O(deque.length + 11)
				isChanged = true;				//O(1)
				nbOp++;
			}catch (NullPointerException e) {}
			nbOp += 2;
		}										//O(array.length * (deque.length + 11))
		nbOp++;
		return isChanged;						//O(1)
	}											//O(array.length * (deque.length + 11))
	/**Invariant -> if c = null or c have 0 elements, nbElements is unchanged; otherwise offerFirst() invariant is used -> deque.length >= nbElements >= 0
	 * 				if c = null or c have 0 elements, first is unchanged; otherwise offerFirst() invariant is used -> deque.length >= first >= 0
	 * 				if c = null or c have 0 elements, last is unchanged; otherwise offerFirst() invariant is used -> deque.length >= last >= 0
	 */


	/**Checks if some elements of this deque satisfy the filter
     * @param filter a predicate which returns true for elements to be contained
     * @return true if at least one element of the deque satifies the filter, false otherwise
     * @throws NullPointerException if the specified filter is to be used and is null
     */
	@Override
	public boolean containsIf(Predicate<? super T> filter) {
		boolean isContained = false;										//O(1)
		nbOp = 2;
		if(first <= last) {													//O(1)
			isContained = searchContainsIf(first, last+1, filter);			//O(deque.length)
			nbOp++;
		}else {
			isContained = searchContainsIf(first, deque.length, filter);	//O(deque.length)
			nbOp += 2;
			if(!isContained) {												//O(1)
				isContained = searchContainsIf(0, last+1, filter);			//O(deque.length)
				nbOp++;
			}																//O(deque.length)
		}																	//O(deque.length)
		nbOp++;
		return isContained;													//O(1)
	}																		//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */
	
	
	/**Checks if some elements of this deque satisfy the filter between 'begin' and 'end'. 
	 * @param begin the location of the table where the element will be searched first
	 * @param end the location of the table where the search for the element will be stopped
	 * @param filter a predicate which returns true for elements to be contained
	 * @return true if at least one element of the deque satifies the filter, false otherwise
	 * @throws NullPointerException if the specified filter is to be used and is null
	 */
	private boolean searchContainsIf(int begin, int end, Predicate<? super T> filter) {
		if(nbElements == 0) return false;					//O(1)
		boolean isContained = false;						//O(1)
		nbOp += 2;
		for(int i = begin; i < end && !isContained; i++) {	//O(deque.length)
			isContained = filter.test(deque[i]);			//O(1)
			nbOp += 3;
		}													//O(deque.length)
		nbOp++;
		return isContained;									//O(1)
	}														//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Performs an action for each element of this deque.
	 * @param  action an action to perform on the elements
	 * @throws NullPointerException if the specified action is to be performed and is null
	 */
	@Override
	public void forEach(Consumer<? super T> action) {
		nbOp = 1;
		if(nbElements != 0) {									//O(1)
			nbOp++;
			if(first <= last) {									//O(1)
				forEachBetween(first, last+1, action);			//O(deque.length)
				nbOp++;
			}else {
				forEachBetween(first, deque.length, action);	//O(deque.length)
				forEachBetween(0, last+1, action);				//O(deque.length)
				nbOp += 2;
			}													//O(deque.length)
		}														//O(deque.length)
	}															//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */
	
	/**Performs an action for each element of this deque between 'begin' and 'end'
	 * @param begin the location of the table where the element will be searched first
	 * @param end the location of the table where the search for the element will be stopped
	 * @param action an action to perform on the elements
	 * @throws NullPointerException if the specified action is to be performed and is null
	 */
	private void forEachBetween(int begin, int end, Consumer<? super T> action) {
		nbOp += 2;
		for(int i = begin; i < end; i++) {	//O(deque.length)
			action.accept(deque[i]);		//O(1)
			nbOp += 3;
		}									//O(deque.length)
	}										//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */


	/**Returns an array containing all of the elements in this deque.
	 * @return an array, whose runtime component type is Object, containing all of the elements in this deque in the same order. 
	 * The first element of the deque must be the first element of the array.
	*/
	@Override
	public Object[] toArray() {
		Object[] array = new Object[nbElements];						//O(nbElements)
		nbOp = nbElements + 1;
		if(nbElements != 0) {											//O(1)
			nbOp++;
			if(first <= last) {											//O(1)
				copyBetween(first, last+1, array, 0);					//O(deque.length)
				nbOp++;
			}else {
				copyBetween(first, deque.length, array, 0);				//O(deque.length)
				copyBetween(0, last+1, array, deque.length - first);	//O(deque.length)
				nbOp += 2;
			}
		}
		nbOp++;
		return array;													//O(1)
	}																	//O(deque.length)
	/**Invariant -> nbElements is unchanged -> deque.length >= nbElements >= 0
	 * 				first is unchanged -> deque.length >= first >= 0
	 * 				last is unchanged -> deque.length >= last >= 0
	 */
}