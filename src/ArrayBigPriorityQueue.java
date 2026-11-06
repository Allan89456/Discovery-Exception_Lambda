import java.util.Comparator;

public class ArrayBigPriorityQueue<T extends Comparable<T>> implements BigPriorityQueueInterface<T>{
	private int nbElements; 	//queue.length >= nbElements >= 0
	private int first;			//queue.length >= first >= 0
	private int last;			//queue.length >= last >= 0
	private Object[] queue ;	//Pour tout i : first <= i <= last : nbElements <> 0 <=> tab[i] <> null
	private Comparator<T> compare;


	//Constructs an empty queue with an initial capacity of ten
	public ArrayBigPriorityQueue(){
		nbElements = 0;
		queue =  new Object[5];
		first = 2;
		last = 2;
		compare = T::compareTo;
	}
	
	/**Constructs an empty queue with an initial capacity of ten
	 * @param compare the comparator of the queue
	 */
	public ArrayBigPriorityQueue(Comparator<T> compare){
		nbElements = 0;
		queue = new Object[5];
		first = 2;
		last = 2;
		if(compare == null) {
			compare = T::compareTo;
		}else {
			this.compare = compare;
		}
	}


	/**Constructs an empty queue with the specified initial capacity 
	 * @param size the size of the queue
	 * @exception IllegalArgumentException if the size is null or negative
	 */
	public ArrayBigPriorityQueue(int size) throws IllegalArgumentException{
		if(size <= 0) throw new IllegalArgumentException();
		nbElements = 0;
		queue = new Object[size];
		first = size / 2;
		last = first;
		compare = T::compareTo;
	}
	
	
	/**Constructs an empty queue with the specified initial capacity 
	 * @param size the size of the queue
	 * @param compare the comparator of the queue
	 * @exception IllegalArgumentException if the size is null or negative
	 */
	public ArrayBigPriorityQueue(int size, Comparator<T> compare) throws IllegalArgumentException{
		if(size <= 0) throw new IllegalArgumentException();
		nbElements = 0;
		queue = new Object[size];
		first = size / 2;
		last = first;
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
		if(nbElements == 0 || compare.compare(e, (T) queue[first]) < 0) {	//O(1)
			offerFirst(e);													//O(queue.length + 11)
		}else {
			offerLast(e);													//O(queue.length + 11)
		}																	//O(queue.length + 11)
		return true;														//O(1)
	}																		//O(queue.length + 11)
	
	
	/**Inserts the specified element at the front of this deque 
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	private void offerFirst(T t) throws NullPointerException {
		if(t == null) throw new NullPointerException();	//O(1)
		if(nbElements != 0) {							//O(1)
			if(nbElements < queue.length){				//O(1)
				first--;								//O(1)
				if(first < 0) {							//O(1)
					first = queue.length - 1;			//O(1)
				}										//O(1)
			}else{
				expandDeque(6);							//O(queue.length + 11)
			}											//O(queue.length + 11)
		}												//O(queue.length + 11)
		queue[first] = t;								//O(1)
		nbElements++;									//O(1)
	}													//O(queue.length + 11)


	/**Copy deque into a new, bigger array
	 * @param locationFirstElement the first location of the new array where deque will be copied
	 */
	private void expandDeque(int locationFirstElement) {
		Object[] newArray = new Object[queue.length + 11];					//O(queue.length + 11)
		copyBetween(first, queue.length, newArray, locationFirstElement);	//O(queue.length)
		if(first != 0) {													//O(1)
			locationFirstElement += queue.length - first;					//O(1)
			copyBetween(0, last+1, newArray, locationFirstElement);			//O(queue.length)
		}																	//O(queue.length)
		first = 5;															//O(1)
		last = first + nbElements;											//O(1)
		queue = newArray;													//O(1)
	}																		//O(queue.length + 11)
	
	
	/**Copy deque between 'begin' and 'end' into an array
	 * @param begin the location of the table where the table will be copied first
	 * @param end the location of the table where the copy will be stopped
	 * @param array the table where deque will be copied
	 * @param locationFirstElement the first element of array where deque will be copied
	*/
	private void copyBetween(int begin, int end, Object[] array, int locationFirstElement) {
		for(int i = begin; i < end; i++) {			//O(queue.length)
			array[locationFirstElement] = queue[i];	//O(1)
			locationFirstElement++;					//O(1)
		}											//O(queue.length)
	}												//O(queue.length)


	/**Inserts the specified element at the end of this deque
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	private void offerLast(T t) throws NullPointerException {
		if(t == null) throw new NullPointerException();	//O(1)
		if(nbElements != 0) {							//O(1)
			if(nbElements < queue.length){				//O(1)
				last++;									//O(1)
				if(last == queue.length) {				//O(1)
					last = 0;							//O(1)
				}										//O(1)
			}else{
				expandDeque(5);							//O(queue.length + 11)
			}											//O(queue.length + 11)
		}												//O(queue.length + 11)
		queue[last] = t;								//O(1)
		nbElements++;									//O(1)
	}													//O(queue.length + 11)

	
	/**Retrieves and removes the head of this queue, 
	 * or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T poll() {
		if(nbElements == 0) return null;	//O(1)
		T elem = (T) queue[first];			//O(1)
		if(nbElements > 1)					//O(1)
			first++;						//O(1)
		nbElements--;						//O(1)
		if(first == queue.length)			//O(1)
			first = 0;						//O(1)
		return elem;						//O(1)
	}										//O(1)

	
	/**Returns true if this queue contains the specified element. 
	 * More formally, returns true if and only if this queue contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this queue is to be tested
	 * @return true if this queue contains the specified element, false otherwise
	 */
	@Override
	public boolean contains(Object o) {
		if(o == null || nbElements == 0) return false;				//O(1)
		boolean isContained = false;								//O(1)
		if(first <= last) {											//O(1)
			isContained = searchContains(first, last+1, o);			//O(queue.length)
		}else {
			isContained = searchContains(first, queue.length, o);	//O(queue.length)
			if(!isContained) {										//O(1)
				isContained = searchContains(0, last+1, o);			//O(queue.length)
			}														//O(queue.length)
		}															//O(queue.length)
		return isContained;											//O(1)
	}																//O(queue.length)
	
	
	/**Returns true if this deque contains the specified element between 'begin' and 'end'. 
	 * More formally, returns true if and only if this deque contains at least one element e between 'begin' and 'end' such that o.equals(e).
	 * @param begin the location of the table where the element will be searched first
	 * @param end the location of the table where the search for the element will be stopped
	 * @param o element whose presence in this deque is to be tested
	 * @return true if this deque contains the specified element, false otherwise
	 */
	private boolean searchContains(int begin, int end, Object o) {
		boolean isContained = false;						//O(1)
		for(int i = begin; i < end && !isContained; i++) {	//O(queue.length)
			isContained = o.equals(queue[i]);				//O(1)
		}													//O(queue.length)
		return isContained;									//O(1)
	}														//O(queue.length)

	
	/**Retrieves, but does not remove, 
	 * the head of this queue, or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		if(nbElements == 0) return null;	//O(1)
		return (T) queue[first];			//O(1)
	}
	
	
	/**Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	@Override
	public String toString(){
		if(nbElements == 0) return "Queue vide";					//O(1)
		String sentence = "";										//O(1)
		if(first <= last) {											//O(1)
			sentence = browseArrayToString(first, last+1);			//O(queue.length)
		} else {
			sentence = browseArrayToString(first, queue.length);	//O(queue.length)
			sentence += browseArrayToString(0, last+1);				//O(queue.length)
		}															//O(queue.length)
		return sentence;											//O(1)
	}																//O(queue.length)
	
	
	/**Returns a string representation of the object between 'begin' and 'end'.
	 * @param begin the location of the table where the element will be searched first
	 * @param end the location of the table where the search for the element will be stopped
	 * @return true if this deque contains the specified element, false otherwise
	 */
	private String browseArrayToString(int begin, int end) {
		String sentence = "";				//O(1)
		for(int i = begin; i < end; i++) {	//O(queue.length)
			sentence += queue[i] + "\n";	//O(1)
		}									//O(queue.length)
		return sentence;					//O(1)
	}										//O(queue.length)
}