package fr.eni.papeterie.bo;

public class Personne {

	private String nom;
	private String prenom;
	private int numSecu;
	
	
	
	/**
	 * @param nom
	 * @param prenom
	 * @param numSecu
	 */
	public Personne(String nom, String prenom, int numSecu) {
		setNom(nom);
		setPrenom(prenom);
		setNumSecu(numSecu);
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getNumSecu() {
		return this.numSecu;
	}
	public void setNumSecu(int numSecu) {
		this.numSecu = numSecu;
	} 
}
