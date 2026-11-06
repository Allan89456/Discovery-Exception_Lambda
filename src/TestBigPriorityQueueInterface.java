import org.junit.Test;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.BeforeClass;

public class TestBigPriorityQueueInterface{
	private static Personne p1;
	private static Personne p2;
	private static Personne p3;
	
	@BeforeClass
	public static void init(){
		p1 = new Personne("Toto", 15);
		p2 = new Personne("Tata", 20);
		p3 = new Personne("Titi", 10);
	}
	
	@Test
	public void testOffer() {
		BigPriorityQueueInterface<Personne> queue1 = new ArrayBigPriorityQueue<>(2);
		BigPriorityQueueInterface<Personne> queue2 = new ArrayBigPriorityQueue<>(2, (p1, p2) -> p1.getNom().compareTo(p2.getNom()));

		assertThrows(NullPointerException.class, () -> {
			queue1.offer(null);
	    });
		assertThrows(NullPointerException.class, () -> {
			queue2.offer(null);
	    });
		
		Object x = "Toto";
		assertThrows(ClassCastException.class, () -> {
			queue1.offer((Personne) x);
	    });
		assertThrows(ClassCastException.class, () -> {
			queue2.offer((Personne) x);
	    });
		
		Assert.assertEquals("Queue 1 vide", null, queue1.peek());
		Assert.assertEquals("Queue 2 vide", null, queue2.peek());
		
		assertDoesNotThrow(() -> {
			queue1.offer(p1);
	    });
		assertDoesNotThrow(() -> {
			queue2.offer(p1);
	    });
		Assert.assertEquals("Queue 1 de taille 1", p1, queue1.peek());
		Assert.assertEquals("Queue 2 de taille 1", p1, queue2.peek());
		
		assertDoesNotThrow(() -> {
			queue1.offer(p2);
	    });
		assertDoesNotThrow(() -> {
			queue2.offer(p2);
	    });
		Assert.assertEquals("Queue 1 de taille 2, premier inchangé car age plus grand", true, queue1.peek().equals(p1));
		Assert.assertEquals("Queue 2 de taille 2, premier changé car nom plus petit", true, queue2.peek().equals(p2));
		
		assertDoesNotThrow(() -> {
			queue1.offer(p3);
	    });
		assertDoesNotThrow(() -> {
			queue2.offer(p3);
	    });
		Assert.assertEquals("Queue 1 de taille 3, premier changé car age plus petit", true, queue1.peek().equals(p3));
		Assert.assertEquals("Queue 2 de taille 3, premier inchangé car nom plus grand", true, queue2.peek().equals(p2));
		
		assertThrows(NullPointerException.class, () -> {
			queue1.offer(null);
	    });
		assertThrows(NullPointerException.class, () -> {
			queue2.offer(null);
	    });
		
		assertThrows(ClassCastException.class, () -> {
			queue1.offer((Personne) x);
	    });
		assertThrows(ClassCastException.class, () -> {
			queue2.offer((Personne) x);
	    });
	}
	
	@Test
	public void testPoll() {
		BigPriorityQueueInterface<Personne> queue1 = new ArrayBigPriorityQueue<>(2);
		BigPriorityQueueInterface<Personne> queue2 = new ArrayBigPriorityQueue<>(2, (p1, p2) -> p1.getNom().compareTo(p2.getNom()));

		Assert.assertEquals("Queue 1 vide", null, queue1.poll());
		Assert.assertEquals("Queue 2 vide", null, queue2.poll());
		
		queue1.offer(new Personne("Toto", 15));
		queue2.offer(new Personne("Toto", 15));
		Assert.assertEquals("Queue 1 de taille 1", p1, queue1.poll());
		Assert.assertEquals("Queue 2 de taille 1", p1, queue2.poll());
		
		queue1.offer(p1);
		queue2.offer(p1);
		queue1.offer(new Personne("Tata", 20));
		queue2.offer(new Personne("Tata", 20));
		Assert.assertEquals("Queue 1 de taille 2, premier inchangé car age plus grand", p1, queue1.poll());
		Assert.assertEquals("Queue 2 de taille 2, premier changé car nom plus petit", p2, queue2.poll());
		
		queue1.offer(p1);
		queue2.offer(p2);
		queue1.offer(new Personne("Titi", 10));
		queue2.offer(new Personne("Titi", 10));
		Assert.assertEquals("Queue 1 de taille 3, premier changé car age plus petit", true, queue1.poll().equals(p3));
		Assert.assertEquals("Queue 2 de taille 3, premier inchangé car nom plus grand", true, queue2.poll().equals(p2));
		
		Assert.assertEquals("Queue 1 de taille 2", true, queue1.poll().equals(p1));
		Assert.assertEquals("Queue 2 de taille 2", true, queue2.poll().equals(p1));
		
		Assert.assertEquals("Queue 1 de taille 1", true, queue1.poll().equals(p2));
		Assert.assertEquals("Queue 2 de taille 1", true, queue2.poll().equals(p3));
		
		Assert.assertEquals("Queue 1 vide", null, queue1.poll());
		Assert.assertEquals("Queue 2 vide", null, queue2.poll());
	}
	
	@Test
	public void testContains() {
		BigPriorityQueueInterface<Personne> queue1 = new ArrayBigPriorityQueue<>(2);
		BigPriorityQueueInterface<Personne> queue2 = new ArrayBigPriorityQueue<>(2, (p1, p2) -> p1.getNom().compareTo(p2.getNom()));

		Assert.assertEquals("Queue 1 vide", false, queue1.contains(null));
		Assert.assertEquals("Queue 1 vide", false, queue2.contains(null));
		Assert.assertEquals("Queue 1 vide", false, queue1.contains(p1));
		Assert.assertEquals("Queue 1 vide", false, queue2.contains(p1));
		
		queue1.offer(new Personne("Toto", 15));
		queue2.offer(new Personne("Toto", 15));
		Assert.assertEquals("Queue 1 de taille 1, élément présent", true, queue1.contains(p1));
		Assert.assertEquals("Queue 2 de taille 1, élément présent", true, queue2.contains(p1));
		Assert.assertEquals("Queue 1 de taille 1, élément absent", false, queue1.contains(p2));
		Assert.assertEquals("Queue 2 de taille 1, élément absent", false, queue2.contains(p2));
		
		queue1.offer(new Personne("Tata", 20));
		queue2.offer(new Personne("Tata", 20));
		Assert.assertEquals("Queue 1 de taille 2, élément présent en premier", true, queue1.contains(p2));
		Assert.assertEquals("Queue 2 de taille 2, élément présent en premier", true, queue2.contains(p1));
		Assert.assertEquals("Queue 1 de taille 2, élément présent en dernier", true, queue1.contains(p1));
		Assert.assertEquals("Queue 2 de taille 2, élément présent en dernier", true, queue2.contains(p2));

		Assert.assertEquals("Queue 1 de taille 2, élément absent", false, queue1.contains(p3));
		Assert.assertEquals("Queue 2 de taille 2, élément absent", false, queue2.contains(p3));
	}
	
	@Test
	public void testToString() {
		BigPriorityQueueInterface<Personne> queue1 = new ArrayBigPriorityQueue<>(2);
		BigPriorityQueueInterface<Personne> queue2 = new ArrayBigPriorityQueue<>(2, (p1, p2) -> p1.getNom().compareTo(p2.getNom()));

		Assert.assertEquals("Queue 1 vide", "Queue vide", queue1.toString());
		Assert.assertEquals("Queue 2 vide", "Queue vide", queue2.toString());
		
		queue1.offer(p1);
		queue2.offer(p1);
		Assert.assertEquals("Deque de taille 1", "Toto 15\n", queue1.toString());
		Assert.assertEquals("Deque de taille 1", "Toto 15\n", queue2.toString());
		
		queue1.offer(p2);
		queue2.offer(p2);
		Assert.assertEquals("Deque de taille 2", "Toto 15\nTata 20\n", queue1.toString());
		Assert.assertEquals("Deque de taille 2", "Tata 20\nToto 15\n", queue2.toString());
		
		queue1.offer(p3);
		queue2.offer(p3);
		Assert.assertEquals("Deque de taille 3", "Titi 10\nToto 15\nTata 20\n", queue1.toString());
		Assert.assertEquals("Deque de taille 3", "Tata 20\nToto 15\nTiti 10\n", queue2.toString());
	}
}