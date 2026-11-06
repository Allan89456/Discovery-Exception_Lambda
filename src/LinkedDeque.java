import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

import exceptions.EmptyCollectionException;

public class LinkedDeque<T> implements DequeInterface<T>{
	private class Node{
		private Node next;
		private Node previous;
		private T value;
		
		private Node(Node previous, T value, Node next) {
			this.value = value;
			this.next = next;
			this.previous = previous;
			nbOp += 3;
		}
		
		private Node() {
			value = null;
			next = null;
			previous = null;
			nbOp += 3;
		}
	}
	
	private Node head;
	private Node tail;
	private int nbElements; //Invariant : nbElements >= 0
	private int nbOp;
	
	//Constructs an empty deque
	public LinkedDeque() {
		nbElements = 0;
		head = new Node();
		tail = head;
		nbOp = 0;
	}
	//Invariant -> 0 is assigned to nbElements -> nbElements >= 0
	
	
	/**get nbOp
	 * @return nbOp
	 */
	public int getNbOp() {
		return nbOp;
	}
	//Invariant -> nbElements is unchanged -> nbElements >= 0
	
	
	/**Inserts the specified element at the front of this deque 
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public void offerFirst(T t) throws NullPointerException {
		nbOp++;
		if(t == null) throw new NullPointerException();	//O(1)
		nbOp++;
		if(nbElements == 0) {							//O(1)
			head.value = t;								//O(1)
			nbOp++;
		}else {
			head.previous = new Node(null, t, head);	//O(1)
			head = head.previous;						//O(1)
			nbOp += 2;
		}												//O(1)
		nbElements++;									//O(1)
		nbOp++;
	}													//O(1)
	//Invariant -> if t = null, nbElements is unchanged; otherwise can only have 1 added -> nbElements >= 0

	
	/**Inserts the specified element at the end of this deque
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	@Override
	public void offerLast(T t) throws NullPointerException {
		nbOp = 1;
		if(t == null) throw new NullPointerException();	//O(1)
		nbOp++;
		if(nbElements == 0) {							//O(1)
			tail.value = t;								//O(1)
			nbOp++;
		}else {
			tail.next = new Node(tail, t, null);		//O(1)
			tail = tail.next;							//O(1)
			nbOp += 2;
		}												//O(1)
		nbElements++;									//O(1)
		nbOp++;
	}													//O(1)
	//Invariant -> if t = null, nbElements is unchanged; otherwise can only have 1 added -> nbElements >= 0
	

	
	/**Retrieves and removes the first element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T removeFirst() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		T elem = head.value;										//O(1)
		nbOp += 2;
		if(nbElements > 1) {										//O(1)
			head = head.next;										//O(1)
			head.previous = null;									//O(1)
			nbOp += 2;
		}															//O(1)
		nbElements--;												//O(1)
		nbOp += 2;
		return elem;												//O(1)
	}																//O(1)
	//Invariant -> if nbElement = 0, nbElement is unchanged; otherwise, nbElement can have only 1 removed -> nbElements >= 0

	
	/**Retrieves and removes the last element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T removeLast() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		T elem = tail.value;										//O(1)
		nbOp += 2;
		if(nbElements > 1) {										//O(1)
			tail = tail.previous;									//O(1)
			tail.next = null;										//O(1)
			nbOp += 2;
		}															//O(1)
		nbElements--;												//O(1)
		nbOp += 2;
		return elem;												//O(1)
	}																//O(1)
	//Invariant -> if nbElement = 0, nbElement is unchanged; otherwise, nbElement can have only 1 removed -> nbElements >= 0

	
	/**Retrieves the first element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T peekFirst() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		nbOp++;
		return head.value;											//O(1)
	}																//O(1)
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
	/**Retrieves the last element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	@Override
	public T peekLast() throws EmptyCollectionException {
		nbOp = 1;
		if(nbElements == 0) throw new EmptyCollectionException();	//O(1)
		nbOp++;
		return tail.value;											//O(1)
	}																//O(1)
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
	/**Returns the number of elements in this deque.
	 * @return the number of elements in this deque.
	 */
	@Override
	public int size() {
		nbOp = 1;
		return nbElements;	//O(1)
	}
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
	/**Returns true if this deque contains the specified element. 
	 * More formally, returns true if and only if this deque contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this deque is to be tested
	 * @return true if this deque contains the specified element, false otherwise
	 */
	@Override
	public boolean contains(Object o) throws NullPointerException {
		nbOp++;
		if(o == null) throw new NullPointerException();								//O(1)
		nbOp++;
		if(nbElements == 0) return false;											//O(1)
		boolean isContained = o.equals(head.value);									//O(1)
		nbOp += 3;
		for(Node tmp = head.next; tmp != null && !isContained; tmp = tmp.next) {	//O(nbElements)
			isContained = o.equals(tmp.value);										//O(1)
			nbOp += 3;
		}																			//O(nbElements)
		nbOp++;
		return isContained;															//O(1)
	}																				//O(nbElements)
	//Invariant -> nbElements is unchanged -> nbElements >= 0
	
	
	/**Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		nbOp = 1;
		if(nbElements == 0) return "Deque vide";			//O(1)
		String sentence = "";								//O(1)
		nbOp += 3;
		for(Node tmp = head; tmp != null; tmp = tmp.next) {	//O(nbElements)
			sentence += tmp.value + "\n";					//O(1)
			nbOp += 3;
		}													//O(nbElements)
		nbOp++;
		return sentence;									//O(1)
	}														//O(nbElements)
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
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
		for(int i = 0; i < array.length && isContained; i++) {	//(array.length)
			isContained = contains(array[i]);					//O(nbElements)
			nbOp += 3;
		}														//O(array.length * nbElements)
		nbOp++;
		return isContained;										//O(1)
	}															//O(array.length * nbElements)
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
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
				offerFirst(array[i]);			//O(1)
				isChanged = true;				//O(1)
				nbOp++;
			}catch (NullPointerException e) {}
		}										//O(array.length)
		nbOp++;
		return isChanged;						//O(1)
	}											//O(array.length)
	//Invariant -> if c = null or c have 0 elements, nbElements is unchanged; otherwise offerFirst() invariant is used -> nbElements >= 0

	
	/**Checks if some elements of this deque satisfy the filter
     * @param filter a predicate which returns true for elements to be contained
     * @return true if at least one element of the deque satifies the filter, false otherwise
     * @throws NullPointerException if the specified filter is to be used and is null
     */
	@Override
	public boolean containsIf(Predicate<? super T> filter) {
		if(nbElements == 0) return false;									//O(1)
		nbOp = 1;
		boolean isContained = false;										//O(1)
		nbOp += 3;
		for(Node tmp = head; tmp != null && !isContained; tmp = tmp.next) {	//O(nbElements)
			isContained = filter.test(tmp.value);							//O(1)
			nbOp += 3;
		}																	//O(nbElements)
		nbOp++;
		return isContained;													//O(1)
	}																		//O(nbElements)
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
	/**Performs an action for each element of this deque.
	 * @param  action an action to perform on the elements
	 * @throws NullPointerException if the specified action is to be performed and is null
	 */
	@Override
	public void forEach(Consumer<? super T> action) {
		nbOp = 1;
		if(nbElements != 0) {									//O(1)
			nbOp += 2;
			for(Node tmp = head; tmp != null; tmp = tmp.next) {	//O(nbElements)
				action.accept(tmp.value);						//O(1)
				nbOp += 3;
			}													//O(nbElements)
		}														//O(nbElements)
	}															//O(nbElements)
	//Invariant -> nbElements is unchanged -> nbElements >= 0

	
	/**Returns an array containing all of the elements in this deque.
	 * @return an array, whose runtime component type is Object, containing all of the elements in this deque in the same order. 
	 * The first element of the deque must be the first element of the array.
	*/
	@Override
	public Object[] toArray() {
		Object[] array = new Object[nbElements];	//O(nbElements)
		nbOp = array.length;
		Node tmp = head;							//O(1)
		nbOp += 3;
		for(int i = 0; i < nbElements; i++) {		//O(nbElements)
			array[i] = tmp.value;					//O(1)
			tmp = tmp.next;							//O(1)
			nbOp += 4;
		}											//O(nbElements)
		nbOp++;
		return array;								//O(1)
	}												//O(nbElements)
	//Invariant -> nbElements is unchanged -> nbElements >= 0
}