package fr.eni.papeterie.bo;

public class Ramette extends Article {

	private int grammage;

	
	
	public Ramette(){
		
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
	public Ramette(Integer idArticle, String marque, String ref, String designation, float pu, int qte, int grammage) {
		setIdArticle(idArticle);
		setMarque(marque);
		setReference(ref);
		setDesignation(designation);
		setPrixUnitaire(pu);
		setQteStock(qte);
		setGrammage(grammage);
	}
	
	public Ramette(String marque, String ref, String designation, float pu, int qte, int grammage) {
		this(null, marque, ref, designation, pu, qte, grammage);
	}

	
	public int getGrammage() {
		return grammage;
	}

	public void setGrammage(int grammage) {
		this.grammage = grammage;
	} 
	
	
	@Override
	public String toString() {
		return String.format("%s Ramette [grammage=%d]", super.toString(), this.getGrammage());
	}
	
}
