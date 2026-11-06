import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Predicate;

import exceptions.*;

public class Question4{
	/**reverses a deque
	 * [1,2,3] becomes [3,2,1]
	 *
	 * @param <T> the type of the objects in the deque
	 * @param q the deque to reverse
	 */
	public static <T> void renverser(DequeInterface<T> q) {
		try {
			T tmp = q.removeFirst();
			renverser(q);
			q.offerLast(tmp);
		}catch(EmptyCollectionException e) {}
	}
	
	@Test
	public void testRenverser() {
		DequeInterface<String> deque = new ArrayDeque<>();
		
		assertDoesNotThrow(() -> {
			renverser(deque);
	    });
		Object[] array = deque.toArray();
		Assert.assertEquals("Deque vide", 0, array.length);
		
		deque.offerFirst("Toto");
		assertDoesNotThrow(() -> {
			renverser(deque);
	    });
		array = deque.toArray();
		Assert.assertEquals("Deque de taille 1", 1, array.length);
		Assert.assertEquals("Deque de taille 1, première case", "Toto", array[0]);
		
		deque.offerFirst("Titi");
		assertDoesNotThrow(() -> {
			renverser(deque);
	    });
		array = deque.toArray();
		Assert.assertEquals("Deque de taille 2", 2, array.length);
		Assert.assertEquals("Deque de taille 2, première case", "Toto", array[0]);
		Assert.assertEquals("Deque de taille 2, dernière case", "Titi", array[1]);
		
		deque.offerLast("Tata");
		assertDoesNotThrow(() -> {
			renverser(deque);
	    });
		array = deque.toArray();
		Assert.assertEquals("Deque de taille 3", 3, array.length);
		Assert.assertEquals("Deque de taille 3, première case", "Tata", array[0]);
		Assert.assertEquals("Deque de taille 3, deuxième case", "Titi", array[1]);
		Assert.assertEquals("Deque de taille 3, dernière case", "Toto", array[2]);
	}
	
	
	/**Checks whether there is at least one element in the deque that matches the given predicate.
	 *
	 * @param <T> the type of the elements in the deque
	 * @param q the deque to test
	 * @param predicate the predicate to apply to the elements
	 * @return true if any element in the deque matches the predicate, false otherwise
	 */
	public static <T> boolean anyMatch(DequeInterface<T> q, Predicate< ? super T> predicate) {
		try {
			T elem = q.removeFirst();
			boolean isContained = predicate.test(elem);
			if(!isContained) {
				isContained = anyMatch(q, predicate);
			}
			q.offerFirst(elem);
			return isContained;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Test
	public void testAnyMatch() {
		DequeInterface<String> deque = new ArrayDeque<>();
	
		Assert.assertEquals("Deque vide, predicat null", false, anyMatch(deque, null));
		Assert.assertEquals("Deque vide", false, anyMatch(deque, x -> x.charAt(0) == 'T'));
		
		deque.offerFirst("Toto");
		Assert.assertEquals("Deque de taille 1, predicat true", true, anyMatch(deque, x -> x.charAt(0) == 'T'));
		Assert.assertEquals("Deque de taille 1, predicat false", false, anyMatch(deque, x -> x.charAt(1) == 'i'));
		
		deque.offerFirst("Titi");
		Assert.assertEquals("Deque de taille 2, predicat true sur premier élément", true, anyMatch(deque, x -> x.charAt(1) == 'i'));
		Assert.assertEquals("Deque de taille 2, predicat true sur deuxième élément", true, anyMatch(deque, x -> x.charAt(1) == 'o'));
		Assert.assertEquals("Deque de taille 2, predicat false", false, anyMatch(deque, x -> x.charAt(1) == 'a'));
		
		Assert.assertEquals("Deque de taille 2, predicat null", false, anyMatch(deque, null));
	}
	
	
	/**Finds the minimum element of a deque according to the natural ordering.
	 *
	 * @param <T> the type of the elements in the deque
	 * @param q the deque from which to find the minimum
	 * @return the minimum element according to the natural ordering
	 * @throws NoSuchElementException if the deque is empty
	 */
	public static <T extends Comparable<T>> T min (DequeInterface<T> q) throws NoSuchElementException{
		try {
			T elem = q.removeLast();
			try {
				T minElem = min(q);
				if(elem.compareTo(minElem) < 0) {
					minElem = elem;
				}
				q.offerLast(elem);
				return minElem;
			}catch(NoSuchElementException e) {
				q.offerLast(elem);
				return elem;
			}
		}catch(EmptyCollectionException e) {
			throw new NoSuchElementException();
		}
	}
	
	@Test
	public void testMin() {
		DequeInterface<String> deque = new ArrayDeque<>();
	
		assertThrows(NoSuchElementException.class, () -> {
			min(deque);
	    });
		
		deque.offerFirst("Titi");
		Assert.assertEquals("Deque de taille 1", "Titi", min(deque));
		
		deque.offerFirst("Toto");
		Assert.assertEquals("Deque de taille 2, min dernier", "Titi", min(deque));
		
		deque.offerFirst("Tete");
		Assert.assertEquals("Deque de taille 3, min premier", "Tete", min(deque));
		
		deque.offerFirst("Tata");
		Assert.assertEquals("Deque de taille 4, min dernier", "Tata", min(deque));
	}
}