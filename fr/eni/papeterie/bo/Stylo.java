package fr.eni.papeterie.bo;

public class Stylo extends Article{

private String couleur;

	
	
	public Stylo(){
		
	}

	/**
	 * @param idArticle
	 * @param marque
	 * @param ref
	 * @param designation
	 * @param pu
	 * @param qte
	 * @param grammage
	 */
	public Stylo(Integer idArticle, String marque, String ref, String designation, float pu, int qte, String couleur) {
		setIdArticle(idArticle);
		setMarque(marque);
		setReference(ref);
		setDesignation(designation);
		setPrixUnitaire(pu);
		setQteStock(qte);
		setCouleur(couleur);
	}
	
	public Stylo(String marque, String ref, String designation, float pu, int qte, String couleur) {
		this(null, marque, ref, designation, pu, qte, couleur);
	}

	
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	} 
	
	
	@Override
	public String toString() {
		return String.format("%s Stylo [couleur=%s]", super.toString(), this.getCouleur());
	}
	
}
