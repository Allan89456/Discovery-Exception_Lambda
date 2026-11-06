import java.util.Comparator;


public class LinkedBigPriorityQueue<T extends Comparable<T>> implements BigPriorityQueueInterface<T>{
	private class Node{
		private Node next;
		private T value;
		
		private Node(T value, Node next) {
			this.value = value;
			this.next = next;
		}
		
		private Node() {
			value = null;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int nbElements; //nbElements >= 0
	private Comparator<T> compare;
	
	//Constructs an empty queue
	public LinkedBigPriorityQueue() {
		nbElements = 0;
		head = new Node();
		tail = head;
		compare = T::compareTo;
	}
	
	/**Constructs an empty queue
	 * @param compare the comparator of the queue
	 */
	public LinkedBigPriorityQueue(Comparator<T> compare) {
		nbElements = 0;
		head = new Node();
		tail = head;
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
	@Override
	public boolean offer(T e) {
		if(nbElements == 0 && e != null) {					//O(1)
			head.value = e;									//O(1)
		}else {
			if(compare.compare(e, (T) head.value) < 0) {	//O(1)
				head = new Node(e, head);					//O(1)
			}else {
				tail.next = new Node(e, null);				//O(1)
				tail = tail.next;							//O(1)
			}												//O(1)
		}													//O(1)
		nbElements++;										//O(1)
		return true;										//O(1)
	}														//O(1)

	
	/**Retrieves and removes the head of this queue, 
	 * or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@Override
	public T poll() {
		if(nbElements == 0) return null;	//O(1)
		T elem = head.value;				//O(1)
		if(nbElements > 1) {				//O(1)
			head = head.next;				//O(1)
		}									//O(1)
		nbElements--;						//O(1)
		return elem;						//O(1)
	}										//O(1)

	
	/**Returns true if this queue contains the specified element. 
	 * More formally, returns true if and only if this queue contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this queue is to be tested
	 * @return true if this queue contains the specified element, false otherwise
	 */
	@Override
	public boolean contains(Object o) {
		if(o == null || nbElements == 0) return false;								//O(1)
		boolean isContained = o.equals(head.value);									//O(1)
		for(Node tmp = head.next; tmp != null && !isContained; tmp = tmp.next) {	//O(nbElements)
			isContained = o.equals(tmp.value);										//O(1)
		}																			//O(nbElements)
		return isContained;															//O(1)
	}																				//O(nbElements)

	
	/**Retrieves, but does not remove, 
	 * the head of this queue, or returns null if this queue is empty.
	 * @return the head of this queue, or null if this queue is empty
	 */
	@Override
	public T peek() {
		if(nbElements == 0) return null;
		return head.value;
	}
	
	
	/**Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	@Override
	public String toString(){
		if(nbElements == 0) return "Queue vide";			//O(1)
		String sentence = "";								//O(1)
		for(Node tmp = head; tmp != null; tmp = tmp.next) {	//O(nbElements)
			sentence += tmp.value + "\n";					//O(1)
		}													//O(nbElements)
		return sentence;									//O(1)
	}														//O(nbElements)
}