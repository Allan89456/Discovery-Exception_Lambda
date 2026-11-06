import org.junit.Test;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;

import exceptions.EmptyCollectionException;

public class TestDequeInterface{
	
	@Test
	public void testOfferFirst() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(NullPointerException.class, () -> {
			deque.offerFirst(null);
	    });
		Assert.assertEquals("Taille inchangée", 0, deque.size());
		

		assertDoesNotThrow(() -> {
			deque.offerFirst("Toto");
	    });
		Assert.assertEquals("Ajout début dans deque vide, élément présent", "Toto", deque.peekFirst());
		Assert.assertEquals("Nombre d'éléments augmentés", 1, deque.size());
		
		assertDoesNotThrow(() -> {
			deque.offerFirst("Titi");
	    });
		Assert.assertEquals("Ajout début dans deque, premier ajouté", "Titi", deque.peekFirst());
		Assert.assertEquals("Ajout début dans deque plein, dernier inchangé", "Toto", deque.peekLast());
		Assert.assertEquals("Taille max atteinte", 2, deque.size());
		
		assertDoesNotThrow(() -> {
			deque.offerFirst("Tata");
	    });
		Assert.assertEquals("Ajout début dans deque plein, premier ajouté", "Tata", deque.peekFirst());
		Assert.assertEquals("Ajout début dans deque plein, dernier inchangé", "Toto", deque.peekLast());
		Assert.assertEquals("Deque agrandi", 3, deque.size());
	}
	
	@Test
	public void testOfferLast() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(NullPointerException.class, () -> {
			deque.offerLast(null);
	    });
		Assert.assertEquals("Taille inchangée", 0, deque.size());
		
		assertDoesNotThrow(() -> {
			deque.offerLast("Toto");
	    });
		Assert.assertEquals("Ajout fin dans deque vide, élément présent", "Toto", deque.peekFirst());
		Assert.assertEquals("Nombre d'éléments augmentés", 1, deque.size());
		
		assertDoesNotThrow(() -> {
			deque.offerLast("Titi");
	    });
		Assert.assertEquals("Ajout fin dans deque, dernier ajouté", "Titi", deque.peekLast());
		Assert.assertEquals("Ajout fin dans deque, premier inchangé", "Toto", deque.peekFirst());
		Assert.assertEquals("Taille max atteinte", 2, deque.size());
		
		assertDoesNotThrow(() -> {
			deque.offerLast("Tata");
	    });
		Assert.assertEquals("Ajout fin dans deque plein, dernier ajouté", "Tata", deque.peekLast());
		Assert.assertEquals("Ajout fin dans deque plein, premier inchangé", "Toto", deque.peekFirst());
		Assert.assertEquals("Deque agrandi", 3, deque.size());
	}
	
	@Test
	public void testPeek() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(EmptyCollectionException.class, () -> {
			deque.peekFirst();
	    });
		assertThrows(EmptyCollectionException.class, () -> {
			deque.peekLast();
	    });
		
		deque.offerFirst("Toto");
		Assert.assertEquals("Ajout dans deque vide, premier ajouté", "Toto", deque.peekFirst());
		Assert.assertEquals("Ajout dans deque vide, dernier ajouté", "Toto", deque.peekLast());
		
		deque.offerFirst("Titi");
		Assert.assertEquals("Ajout début dans deque plein, premier ajouté", "Titi", deque.peekFirst());
		Assert.assertEquals("Ajout début dans deque plein, dernier inchangé", "Toto", deque.peekLast());
		
		deque.offerLast("Tata");
		Assert.assertEquals("Ajout fin dans deque plein, premier inchangé", "Titi", deque.peekFirst());
		Assert.assertEquals("Ajout fin dans deque plein, dernier ajouté", "Tata", deque.peekLast());
	}
	
	@Test
	public void testRemoveFirst() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(EmptyCollectionException.class, () -> {
			deque.removeFirst();
	    });

		deque.offerFirst("Toto");
		Assert.assertEquals("Enlève seul élément présent", "Toto", deque.removeFirst());
		Assert.assertEquals("Taille réduite", 0, deque.size());
		assertThrows(EmptyCollectionException.class, () -> {
			deque.removeFirst();
	    });
		
		deque.offerFirst("Toto");
		deque.offerFirst("Titi");
		Assert.assertEquals("Enlève premier élément de deque", "Titi", deque.removeFirst());
		Assert.assertEquals("Taille réduite", 1, deque.size());
		Assert.assertEquals("Enlève dernier élément présent", "Toto", deque.removeFirst());
		Assert.assertEquals("Taille réduite", 0, deque.size());
		assertThrows(EmptyCollectionException.class, () -> {
			deque.removeFirst();
	    });
	}
	
	@Test
	public void testRemoveLast() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(EmptyCollectionException.class, () -> {
			deque.removeLast();
	    });

		deque.offerFirst("Toto");
		Assert.assertEquals("Enlève seul élément présent", "Toto", deque.removeLast());
		Assert.assertEquals("Taille réduite", 0, deque.size());
		assertThrows(EmptyCollectionException.class, () -> {
			deque.removeLast();
	    });
		
		deque.offerFirst("Toto");
		deque.offerFirst("Titi");
		Assert.assertEquals("Enlève dernier élément de deque", "Toto", deque.removeLast());
		Assert.assertEquals("Taille réduite", 1, deque.size());
		Assert.assertEquals("Enlève dernier élément présent", "Titi", deque.removeLast());
		Assert.assertEquals("Taille réduite", 0, deque.size());
		assertThrows(EmptyCollectionException.class, () -> {
			deque.removeLast();
	    });
	}
	
	@Test
	public void testContains() {
		DequeInterface<Personne> deque = new LinkedDeque<>();
		
		assertThrows(NullPointerException.class, () -> {
			deque.contains(null);
	    });
		Personne p1 = new Personne("Toto", 15);
		Assert.assertEquals("Deque vide", false, deque.contains(p1));
		
		deque.offerFirst(new Personne("Toto", 15));
		Assert.assertEquals("Deque de taille 1, élément présent", true, deque.contains(p1));
		Personne p2 = new Personne("Titi", 20);
		Assert.assertEquals("Deque de taille 1, élément absent", false, deque.contains(p2));
		
		deque.offerLast(new Personne("Titi", 20));
		Assert.assertEquals("Deque de taille 2, élément présent en premier", true, deque.contains(p1));
		Assert.assertEquals("Deque de taille 2, élément présent en dernier", true, deque.contains(p2));
		Personne p3 = new Personne("Tata", 10);
		Assert.assertEquals("Deque de taille 2, élément absent", false, deque.contains(p3));
	}
	
	@Test
	public void testToString() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		Assert.assertEquals("Deque vide", "Deque vide", deque.toString());
		
		deque.offerFirst("Toto");
		Assert.assertEquals("Deque de taille 1", "Toto\n", deque.toString());
		
		deque.offerFirst("Titi");
		Assert.assertEquals("Deque de taille 2", "Titi\nToto\n", deque.toString());
		
		deque.offerLast("Tata");
		Assert.assertEquals("Deque de taille 3", "Titi\nToto\nTata\n", deque.toString());
		
		deque.removeFirst();
		Assert.assertEquals("Deque de taille 2", "Toto\nTata\n", deque.toString());
	}
	
	@Test
	public void testContainsAll() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(NullPointerException.class, () -> {
			deque.containsAll(null);
	    });
		
		Collection<String> c = new ArrayList<>();
		Assert.assertEquals("Deque vide, Collection vide", false, deque.containsAll(c));
		
		c.add("Toto");
		Assert.assertEquals("Deque vide", false, deque.containsAll(c));
		
		deque.offerFirst("Toto");
		Assert.assertEquals("Deque de taille 1, Collection de taille 1, élément présent", true, deque.containsAll(c));
		
		c.clear();
		c.add("Titi");
		Assert.assertEquals("Deque de taille 1, Collection de taille 1, élément absent", false, deque.containsAll(c));
		
		c.add("Toto");
		Assert.assertEquals("Deque de taille 1, Collection de taille 2", false, deque.containsAll(c));
		
		deque.offerFirst("Titi");
		Assert.assertEquals("Deque de taille 2, Collection de taille 2, éléments présents", true, deque.containsAll(c));
		
		c.remove("Titi");
		Assert.assertEquals("Deque de taille 2, Collection de taille 1, élément présent", true, deque.containsAll(c));
		
		c.add("Tata");
		Assert.assertEquals("Deque de taille 2, Collection de taille 2, élément absent", false, deque.containsAll(c));
		
		c.remove("Toto");
		Assert.assertEquals("Deque de taille 2, Collection de taille 1, élément absent", false, deque.containsAll(c));
		
		c.clear();
		Assert.assertEquals("Deque de taille 2, Collection vide", true, deque.containsAll(c));
		
		c.add(null);
		assertThrows(NullPointerException.class, () -> {
			deque.containsAll(c);
	    });
		
		assertThrows(NullPointerException.class, () -> {
			deque.containsAll(null);
	    });
	}
	
	@Test
	public void testOfferAll() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertThrows(NullPointerException.class, () -> {
			deque.offerAll(null);
	    });
		
		Collection<String> c = new ArrayList<String>();
		Assert.assertEquals("Deque vide, Collection vide", false, deque.offerAll(c));
		Assert.assertEquals("Taille inchangée", 0, deque.size());
		
		c.add("Toto");
		Assert.assertEquals("Deque vide, Collection de taille 1", true, deque.offerAll(c));
		Assert.assertEquals("Élément ajouté", 1, deque.size());
		Assert.assertEquals("Deque de taille 1, Collection de taille 1, élément présent", true, deque.containsAll(c));
		
		deque.removeFirst();
		c.add("Titi");
		Assert.assertEquals("Deque vide, Collection de taille 2", true, deque.offerAll(c));
		Assert.assertEquals("Éléments ajoutés", 2, deque.size());
		Assert.assertEquals("Deque de taille 2, Collection de taille 2, élément présent", true, deque.containsAll(c));
		
		c.clear();
		c.add(null);
		c.add(null);
		c.add(null);
		Assert.assertEquals("Deque de taille 2, Collection de taille 3 avec tous les éléments null", false, deque.offerAll(c));
		Assert.assertEquals("Taille inchangée", 2, deque.size());
		
		c.add("Tata");
		Assert.assertEquals("Deque vide, Collection de taille 4 avec certains éléments null", true, deque.offerAll(c));
		Assert.assertEquals("Élément ajouté", 3, deque.size());
		Assert.assertEquals("Deque de taille 3, élément présent", true, deque.contains("Tata"));
		
		c.clear();
		for(int i = 0; i < 4; i++) {
			c.add("Toto");
		}
		Assert.assertEquals("Deque de taille 3, Collection de taille 4", true, deque.offerAll(c));
		Assert.assertEquals("Éléments ajoutés", 7, deque.size());
		
		assertThrows(NullPointerException.class, () -> {
			deque.offerAll(null);
	    });
	}
	
	@Test
	public void testContainsIf() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		Assert.assertEquals("Deque vide, predicat null", false, deque.containsIf(null));
		Assert.assertEquals("Deque vide", false, deque.containsIf(x -> x.charAt(0) == 'T'));
		
		deque.offerFirst("Toto");
		Assert.assertEquals("Deque de taille 1, predicat true", true, deque.containsIf(x -> x.charAt(0) == 'T'));
		Assert.assertEquals("Deque de taille 1, predicat false", false, deque.containsIf(x -> x.charAt(1) == 'i'));
		
		deque.offerFirst("Titi");
		Assert.assertEquals("Deque de taille 2, predicat true sur premier élément", true, deque.containsIf(x -> x.charAt(1) == 'i'));
		Assert.assertEquals("Deque de taille 2, predicat true sur deuxième élément", true, deque.containsIf(x -> x.charAt(1) == 'o'));
		Assert.assertEquals("Deque de taille 2, predicat false", false, deque.containsIf(x -> x.charAt(1) == 'a'));
		
		assertThrows(NullPointerException.class, () -> {
			deque.containsIf(null);
	    });
	}
	
	@Test
	public void testForEach() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		assertDoesNotThrow(() -> {
			deque.forEach(null);
	    });
		System.out.println("\tTests de forEach()\n");
		
		System.out.print("Test 1 (deque vide) :\nRésultat attendu : \nRésultat reçu :    ");
		assertDoesNotThrow(() -> {
			deque.forEach(x -> System.out.println("Hello " + x + " - "));
	    });
		
		
		System.out.print("\n\nTest 2 (deque de taille 1) :\nRésultat attendu : Hello Toto - \nRésultat reçu :    ");
		deque.offerFirst("Toto");
		assertDoesNotThrow(() -> {
			deque.forEach(x -> System.out.print("Hello " + x + " - "));
	    });
		
		System.out.print("\n\nTest 3 (deque de taille 2) :\nRésultat attendu : Hello Titi - Hello Toto - \nRésultat reçu :    ");
		deque.offerFirst("Titi");
		assertDoesNotThrow(() -> {
			deque.forEach(x -> System.out.print("Hello " + x + " - "));
	    });
		
		System.out.print("\n\nTest 3 (deque de taille 3) :\nRésultat attendu : Hello Titi - Hello Toto - Hello Tata - \nRésultat reçu :    ");
		deque.offerLast("Tata");
		assertDoesNotThrow(() -> {
			deque.forEach(x -> System.out.print("Hello " + x + " - "));
	    });
		
		assertThrows(NullPointerException.class, () -> {
			deque.forEach(null);
	    });
	}
	
	@Test
	public void testToArray() {
		DequeInterface<String> deque = new LinkedDeque<>();
		
		Object [] array = deque.toArray();
		Assert.assertEquals("Deque vide", 0, array.length);
		
		deque.offerFirst("Toto");
		array = deque.toArray();
		Assert.assertEquals("Deque de taille 1", 1, array.length);
		Assert.assertEquals("Deque de taille 1, seule case", "Toto", array[0]);
		
		deque.offerFirst("Titi");
		array = deque.toArray();
		Assert.assertEquals("Deque de taille 2", 2, array.length);
		Assert.assertEquals("Deque de taille 2, première case", "Titi", array[0]);
		Assert.assertEquals("Deque de taille 2, dernière case", "Toto", array[1]);
		
		deque.offerLast("Tata");
		array = deque.toArray();
		Assert.assertEquals("Deque de taille 3", 3, array.length);
		Assert.assertEquals("Deque de taille 3, première case", "Titi", array[0]);
		Assert.assertEquals("Deque de taille 3, deuxième case", "Toto", array[1]);
		Assert.assertEquals("Deque de taille 3, dernière case", "Tata", array[2]);
	}
}