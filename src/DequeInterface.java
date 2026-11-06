import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

import exceptions.*;

/**
 * A linear collection that supports element insertion and removal at both ends. 
 * The name deque is short for "double ended queue". 
 * Version inspired from oracle documentation 
 */

public interface DequeInterface<E> {
	/**
	 * Inserts the specified element at the front of this deque 
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	void offerFirst(E e) throws NullPointerException;
	
	/**
	 * Inserts the specified element at the end of this deque
	 * @param e the element to insert
	 * @exception NullPointerException if the specified element is null
	 */
	void offerLast(E e) throws NullPointerException;
	
	/**
	 * Retrieves and removes the first element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	E removeFirst() throws EmptyCollectionException;
	
	/**
	 * Retrieves and removes the last element of this deque.
	 * @return the element removed
	 * @exception EmptyCollectionException if the deque is empty
	 */
	E removeLast() throws EmptyCollectionException;
	
	/**
	 * Retrieves the first element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	E peekFirst() throws EmptyCollectionException;
	
	/**
	 * Retrieves the last element of this deque.
	 * @return the element retrieved
	 * @exception EmptyCollectionException if the deque is empty
	 */
	E peekLast() throws EmptyCollectionException;
	
	/**
	 * Returns the number of elements in this deque.
	 * @return the number of elements in this deque.
	 */
	int size();
	
	/**
	 * Returns true if this deque contains the specified element. 
	 * More formally, returns true if and only if this deque contains at least one element e such that o.equals(e).
	 * @param o element whose presence in this deque is to be tested
	 * @return true if this deque contains the specified element, false otherwise
	 */
	boolean contains(Object o) throws NullPointerException;
	
	/**
	 * Returns a string representation of the object.
	 * @return a string representation of the object
	 */
	String toString();
	
	/**
     * Returns true if this deque contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this deque
     * @return true if this deque contains all the elements of the specified collection, false otherwise
     * @throws NullPointerException if the specified collection is null
     */
    boolean containsAll(Collection<?> c) ;

    /**
     * Inserts all of the elements one by one in the specified collection at the front of
     * this deque.
     * Null elements of the specified collection are ignored.
     * @param c collection containing elements to be added to this deque
     * @return true if this collection has changed after the call, false otherwise
     * @throws NullPointerException if the specified collection is null
     */
    boolean offerAll(Collection<? extends E> c) ;

    /**
     * checks if some elements of this deque satisfy the filter
     * @param filter a predicate which returns true for elements to be contained
     * @return true if at least one element of the deque satifies the filter, false otherwise
     * @throws NullPointerException if the specified filter is to be used and is null
     */
    boolean containsIf(Predicate<? super E> filter);
	
	/**
	 * Performs an action for each element of this deque.
	 * @param  action an action to perform on the elements
	 * @throws NullPointerException if the specified action is to be performed and is null
	 */
	void forEach(Consumer<? super E> action);
	
	/**
	 * Returns an array containing all of the elements in this deque.
	 * @return an array, whose runtime component type is Object, containing all of the elements in this deque in the same order. 
	 * The first element of the deque must be the first element of the array.
	*/
	Object[] toArray();
}
