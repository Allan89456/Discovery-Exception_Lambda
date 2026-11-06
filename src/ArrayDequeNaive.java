import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

import exceptions.EmptyCollectionException;

public class ArrayDequeNaive<T> implements DequeInterface<T>{
	private T[] deque;	//Invariant : Pour tout i : 0 <= i < deque.length : tab[i] <> null
	private int nbOp;
	
	//Constructs an empty deque with an initial capacity of zero
	@SuppressWarnings("unchecked")
	public ArrayDequeNaive(){
		deque = (T[]) new Object[0];
		nbOp = 0;
	}
	//Invariant -> deque is initialized with a size of 0 -> Pour tout i : 0 <= i < deque.length : tab[i] <> null
	
	
	/**get nbOp
	 * @return nbOp
	 */
	public int getNbOp() {
		return nbOp;
	}
	//Invariant -> deque is unchanged -> deque.length >= nbElements >= 0
	
	
	/**Inserts the specified element at the front of this deque 
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public void offerFirst(T t) throws NullPointerException {
		nbOp = 1;
		if(t == null) throw new NullPointerException(); //O(1)
		expandDeque(1);									//O(deque.length + 1)
		nbOp++;
		deque[0] = t;									//O(1)
		nbOp ++;
	}													//O(deque.length + 1)
	//Invariant -> if t = null, deque is unchanged; otherwise deque is enlarged and filled -> Pour tout i : 0 <= i < deque.length : tab[i] <> null
	
	
	/**Copy deque into a new, bigger array
	 * @param locationFirstElement the first location of the new array where deque will be copied
	 */
	@SuppressWarnings("unchecked")
	private void expandDeque(int locationFirstElement) {
		T[] newArray = (T[]) new Object[deque.length + 1];	//O(deque.length + 1)
		nbOp += deque.length + 3;
		for(int i = 0; i < deque.length; i++) {				//O(deque.length)
			newArray[i+locationFirstElement] = deque[i];	//O(1)
			nbOp += 3;
		}													//O(deque.length)
		deque = newArray;									//O(1)
		nbOp ++;
	}														//O(deque.length + 1)
	//Invariant -> deque is unchanged -> deque.length >= nbElements >= 0
	
	
	/**Inserts the specified element at the end of this deque
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public void offerLast(T t) throws NullPointerException {
		nbOp = 1;
		if(t == null) throw new NullPointerException();	//O(1)
		expandDeque(0);									//O(deque.length + 1)
		nbOp++;
		deque[deque.length-1] = t;						//O(1)
		nbOp ++;
	}													//O(deque.length + 1)
	//Invariant -> if t = null, deque is unchanged; otherwise deque is enlarged and filled. If nbElement = deque.length, deque is enlarged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Retrieves and removes the first element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T removeFirst() throws EmptyCollectionException {
		nbOp = 1;
		if(deque.length == 0) throw new EmptyCollectionException();	//O(1)
		T elem = deque[0];											//O(1)
		nbOp ++;
		shrinkDeque(1, deque.length);								//O(deque.length - 1)
		nbOp += 2;
		return elem;												//O(1)
	}																//O(deque.length - 1)
	//Invariant -> if deque.length = 0, deque is unchanged; otherwise, deque have 1 case removed -> Pour tout i : 0 <= i < deque.length : tab[i] <> null
	
	
	/**Shrink deque of one size
	 * @param begin the location of the table where the table will be copied first
	 * @param end the location of the table where the copy will be stopped
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@SuppressWarnings("unchecked")
	private void shrinkDeque(int begin, int end) throws EmptyCollectionException {
		nbOp += 1;
		if(deque.length == 0) throw new EmptyCollectionException();	//O(1)
		T[] newArray = (T[]) new Object[deque.length - 1];			//O(deque.length - 1)
		nbOp += deque.length + 1;
		for(int i = begin; i < end; i++) {							//O(deque.length - 1)
			newArray[i-begin] = deque[i];							//O(1)
			nbOp += 3;
		}
		deque = newArray;											//O(1)
		nbOp ++;
	}																//O(deque.length - 1)
	//Invariant -> deque have 1 case removed -> Pour tout i : 0 <= i < deque.length : tab[i] <> null
	
	
	/**Retrieves and removes the last element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T removeLast() throws EmptyCollectionException {
		nbOp = 1;
		if(deque.length == 0) throw new EmptyCollectionException();	//O(1)
		T elem = deque[deque.length-1];								//O(1)
		nbOp++;
		shrinkDeque(0, deque.length-1);								//O(deque.length - 1)
		nbOp += 2;
		return elem;												//O(1)
	}																//O(deque.length - 1)
	//Invariant -> if deque.length = 0, deque is unchanged; otherwise, deque have 1 case removed -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Retrieves the first element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T peekFirst() throws EmptyCollectionException {
		nbOp = 1;
		if(deque.length == 0) throw new EmptyCollectionException();	//O(1)
		nbOp++;
		return deque[0];											//O(1)
	}																//O(1)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Retrieves the last element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T peekLast() throws EmptyCollectionException {
		nbOp = 1;
		if(deque.length == 0) throw new EmptyCollectionException();	//O(1)
		nbOp++;
		return deque[deque.length-1];								//O(1)
	}																//O(1)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Returns the number of elements in this deque.
	 * @return the number of elements in this deque.
	 */
	@Override
	public int size() {
		nbOp = 1;
		return deque.length;	//O(1)
	}							//O(1)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Returns true if this deque contains the specified element. 
	 * More formally, returns true if and only if this deque contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this deque is to be tested
	 * @return true if this deque contains the specified element, false otherwise
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public boolean contains(Object o) throws NullPointerException {
		nbOp++;
		if(o == null) throw new NullPointerException();			//O(1)
		boolean isContained = false;							//O(1)
		nbOp += 3;
		for(int i = 0; i < deque.length && !isContained; i++) {	//O(deque.length)
			isContained = deque[i].equals(o);					//O(1)
			nbOp += 3;
		}														//O(deque.length)
		nbOp++;
		return isContained;										//O(1)
	}															//O(deque.length)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null
	
	
	/**Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		nbOp = 1;
		if(deque.length == 0) return "Deque vide";	//O(1)
		String sentence = "";						//O(1)
		nbOp += 3;
		for(int i = 0; i < deque.length; i++) {		//O(deque.length)
			sentence += deque[i] + "\n";			//O(1)
			nbOp += 3;
		}											//O(deque.length)
		nbOp++;
		return sentence;							//O(1)
	}												//O(deque.length)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Returns true if this deque contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this deque
     * @return true if this deque contains all the elements of the specified collection, false otherwise
     * @throws NullPointerException if the specified collection is null
     */
	@Override
	public boolean containsAll(Collection<?> c) {
		Object[] array = c.toArray();							//O(array.length)
		nbOp = array.length + 1;
		if(deque.length == 0) return false;						//O(1)
		boolean isContained = true;								//O(1)
		nbOp += 3;
		for(int i = 0; i < array.length && isContained; i++) {	//O(array.length)
			isContained = contains(array[i]);					//O(deque.length)
			nbOp += 3;
		}														//O(array.length * deque.length)
		nbOp++;
		return isContained;										//O(1)
	}															//O(array.length * deque.length)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
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
		nbOp = array.length;
		boolean isChanged = false;				//O(1)
		nbOp += 3;
		for(int i = 0; i < array.length; i++) {	//O(array.length)
			try {
				nbOp++;
				offerFirst(array[i]);			//O(deque.length + 1)
				isChanged = true;				//O(1)
				nbOp++;
			}catch (NullPointerException e) {}
			nbOp += 2;
		}										//O(array.length * (deque.length + 1))
		nbOp++;
		return isChanged;						//O(1)
	}											//O(array.length * (deque.length + 1))
	//Invariant -> if c = null or c have 0 elements, deque is unchanged; otherwise offerFirst() invariant is used -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Checks if some elements of this deque satisfy the filter
     * @param filter a predicate which returns true for elements to be contained
     * @return true if at least one element of the deque satifies the filter, false otherwise
     * @throws NullPointerException if the specified filter is to be used and is null
     */
	@Override
	public boolean containsIf(Predicate<? super T> filter) {
		boolean isContained = false;							//O(1)
		nbOp = 3;
		for(int i = 0; i < deque.length && !isContained; i++) {	//O(deque.length)
			isContained = filter.test(deque[i]);				//O(1)
			nbOp += 3;
		}														//O(deque.length)
		nbOp++;
		return isContained;										//O(1)
	}															//O(deque.length)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Performs an action for each element of this deque.
	 * @param  action an action to perform on the elements
	 * @throws NullPointerException if the specified action is to be performed and is null
	 */
	@Override
	public void forEach(Consumer<? super T> action) {
		nbOp = 2;
		for(int i = 0; i < deque.length; i++) {	//O(deque.length)
			action.accept(deque[i]);			//O(1)
			nbOp += 3;
		}										//O(deque.length)
	}											//O(deque.length)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null

	
	/**Returns an array containing all of the elements in this deque.
	 * @return an array, whose runtime component type is Object, containing all of the elements in this deque in the same order. 
	 * The first element of the deque must be the first element of the array.
	*/
	@Override
	public Object[] toArray() {
		Object[] array = new Object[deque.length];	//O(deque.length)
		nbOp = deque.length + 2;
		for(int i = 0; i < deque.length; i++) {		//O(deque.length)
			array[i] = deque[i];					//O(1)
			nbOp += 3;
		}											//O(deque.length)
		nbOp++;
		return array;								//O(1)
	}												//O(deque.length)
	//Invariant -> deque is unchanged -> Pour tout i : 0 <= i < deque.length : tab[i] <> null
}