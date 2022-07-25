package fr.eni.papeterie.bo;

public class Ligne {

	private Article article;
	protected int qte;
	/**
	 * @param article
	 * @param qte
	 */
	public Ligne(Article article, int qte) {
		this.article = article;
		this.qte = qte;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	
	public float getPrix() {
		return this.article.getPrixUnitaire(); 
	}
	
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("Ligne [");
		bld.append(" qte=");
		bld.append(getQte());
		bld.append(", prix=");
		bld.append(getPrix());
		bld.append(", ");
		if (article != null) {
			bld.append("article=");
			bld.append(getArticle().toString());
		}
		bld.append("]");
		return bld.toString();
	}
	
}
