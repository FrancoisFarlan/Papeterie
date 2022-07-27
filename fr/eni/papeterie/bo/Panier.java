package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private float montant;
	private List<Ligne> lignesPanier; 
	/**
	 * 
	 */
	public Panier() {
		lignesPanier = new ArrayList<>();
	}
	public List<Ligne> getLignesPanier() {
		return lignesPanier;
	}
	
	public Ligne getLigne(int index) {
		return lignesPanier.get(index);
	}
	
	public void addLigne(Article article, int qte) {
		Ligne nouvelleLigne = new Ligne(article, qte);
		this.lignesPanier.add(nouvelleLigne);
	}
	
	public void updateLigne(int index, int newQte) {
		this.getLigne(index).setQte(newQte);
	}
	
	public void removeLigne(int index) {
		lignesPanier.remove(index);
	}
	
	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("Panier : \n \n");
		for(Ligne l : lignesPanier) {
			if(l != null) {
				bld.append("ligne " + lignesPanier.indexOf(l) + ":\t");
				bld.append(l.toString()); 
				bld.append("\n");
			}
		}
		bld.append("\nValeur du panier : ");
		bld.append(this.getMontant());
		return bld.toString(); 
	}
}
