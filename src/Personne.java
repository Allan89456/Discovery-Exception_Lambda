public class Personne implements Comparable<Personne>{
	private String nom;
	private int age;
		
	/**Constructs a Personne
	 * @param nom the name of the Personne
	 * @param age the age of the Personne
	 */
	public Personne(String nom, int age) {
		this.nom = nom;
		this.age = age;
	}
		
	/**get the age of the Personne
	 * @return the age of the Personne
	 */
	public int getAge() {
		return age;
	}
		
	/**get the name of the Personne
	 * @return the name of the Personne
	 */
	public String getNom() {
		return nom;
	}
		
	/**Compare two Personne
	 * @param p the second Personne to compare
	 * @return 0 if this == p; a negative number if this < p; a positive number if this > p
	 */
	@Override
	public int compareTo(Personne p) {
		if(p == null) return -1;
		if(p == this) return 0;
		return age - p.age;
	}
		
	/**Compare a Personne with an Object
	 * @param obj the Object to compare
	 * @return true if this == obj; otherwise false
	 */
	@Override
	public boolean equals(Object obj) {
		return equals((Personne) obj);
	}

	/**Compare two Personne
	 * @param p the second Personne to compare
	 * @return true if this == p; otherwise false
	 */
	public boolean equals(Personne p) {
		if(p == null) return false;
		if(p == this) return true;
		return nom.equals(p.nom) && age == p.age;
	}
		
	/**Returns a string representation of the Personne
	 * @return a string representation of the Personne
	 */
	@Override
	public String toString() {
		return nom + " " + age;
	}
}