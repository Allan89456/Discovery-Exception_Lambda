import java.util.Comparator;

import exceptions.EmptyCollectionException;

public class ArrayBigPriorityQueueNaive<T extends Comparable<T>> implements BigPriorityQueueInterface<T>{
	private Object[] queue; //Pour tout i : 0 <= i < deque.length : tab[i] <> null
	private Comparator<T> compare;
	
	
	//Constructs an empty queue with an initial capacity of zero
	public ArrayBigPriorityQueueNaive(){
		queue = new Object[0];
		compare = T::compareTo;
	}
	
	
	/**Constructs an empty queue with an initial capacity of zero
	 * @param compare the comparator of the queue
	 */
	public ArrayBigPriorityQueueNaive(Comparator<T> compare){
		queue = new Object[0];
		if(compare == null) {
			compare = T::compareTo;
		}else {
			this.compare = compare;
		}
	}
	
	
	/**Inserts the specified element into this priority queue.
	 * @param e the element to add
	 * @return true if the element was added to this queue, else false 
	 * @throws ClassCastException if the specified element cannot be compared with elements currently 
	 * in this priority queue according to the priority queue's ordering
	 * @throws NullPointerException if the specified element is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean offer(T e) {
		if(queue.length == 0 || compare.compare(e, (T) queue[0]) < 0) {	//O(1)
			offerFirst(e);												//O(queue.length + 1)
		}else {
			offerLast(e);												//O(queue.length + 1)
		}																//O(queue.length + 1)
		return true;													//O(1)
	}																	//O(queue.length + 1)
	
	
	/**Inserts the specified element at the front of this deque 
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	private void offerFirst(T t) throws NullPointerException {
		if(t == null) throw new NullPointerException();	//O(1)
		expandQueue(1);									//O(queue.length + 1)
		queue[0] = t;									//O(1)
	}													//O(queue.length + 1)
	
	/**Copy queue into a new, bigger array
	 * @param locationFirstElement the first location of the new array where deque will be copied
	 */
	private void expandQueue(int locationFirstElement) {
		Object[] newArray = new Object[queue.length + 1];	//O(queue.length + 1)
		for(int i = 0; i < queue.length; i++) {				//O(queue.length)
			newArray[i+locationFirstElement] = queue[i];	//O(1)
		}													//O(queue.length)
		queue = newArray;									//O(1)
	}														//O(queue.length + 1)
	
	
	/**Inserts the specified element at the end of this deque
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	private void offerLast(T t) throws NullPointerException {
		if(t == null) throw new NullPointerException();	//O(1)
		expandQueue(0);									//O(queue.length + 1)
		queue[queue.length-1] = t;						//O(1)
	}													//O(queue.length + 1)

	
	/**Retrieves and removes the head of this queue, 
	 * or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T poll() {
		if(queue.length == 0) return null;	//O(1)
		Object elem = queue[0];				//O(1)
		shrinkQueue();						//O(queue.length - 1)
		return (T) elem;					//O(1)
	}										//O(queue.length - 1)
	
	
	/**Shrink queue of one size
	 * @exception EmptyCollectionException if the queue is empty
	 */
	private void shrinkQueue() throws EmptyCollectionException {
		if(queue.length == 0) throw new EmptyCollectionException();	//O(1)
		Object[] newArray = new Object[queue.length - 1];			//O(queue.length - 1)
		for(int i = 1; i < queue.length; i++) {						//O(queue.length - 1)
			newArray[i-1] = queue[i];								//O(1)
		}															//O(queue.length - 1)
		queue = newArray;											//O(1)
	}																//O(queue.length - 1)

	
	/**Returns true if this queue contains the specified element. 
	 * More formally, returns true if and only if this queue contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this queue is to be tested
	 * @return true if this queue contains the specified element, false otherwise
	 */
	@Override
	public boolean contains(Object o) {
		if(o == null) return false;								//O(1)
		boolean isContained = false;							//O(1)
		for(int i = 0; i < queue.length && !isContained; i++) {	//O(queue.length)
			isContained = o.equals(queue[i]);					//O(1)
		}														//O(queue.length)
		return isContained;										//O(1)
	}															//O(queue.length)

	
	/**Retrieves, but does not remove, 
	 * the head of this queue, or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		if(queue.length == 0) return null;	//O(1)
		return (T) queue[0];				//O(1)
	}										//O(1)
	
	
	/**Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	@Override
	public String toString(){
		if(queue.length == 0) return "Queue vide";	//O(1)
		String sentence = "";						//O(1)
		for(int i = 0; i < queue.length; i++) {		//O(queue.length)
			sentence += queue[i] + "\n";			//O(1)
		}											//O(queue.length)
		return sentence;							//O(1)
	}												//O(queue.length)
}