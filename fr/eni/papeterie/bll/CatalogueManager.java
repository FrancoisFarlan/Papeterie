package fr.eni.papeterie.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {

	private DAO<Article> daoArticle;
	private static CatalogueManager instance = null; 

	public CatalogueManager() throws BLLException {
		this.daoArticle = DAOFactory.getArticleDAO();
	}
	
	public static CatalogueManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new CatalogueManager();
		}
		return instance;
	}
	
	public void addArticle(Article a) throws BLLException {
		
		
		
		try {
			this.validerArticle(a);
			this.daoArticle.insert(a);
		} catch (DALException ex) {
			throw new BLLException(ex);
		}
		
	}
	
	public void updateArticle(Article a) throws BLLException {
		
		
		
		
		try {
			this.validerArticle(a);
			this.daoArticle.update(a);
		} catch (DALException ex) {
			throw new BLLException(ex);
		}
		
	}

	public void removeArticle(Article a) throws BLLException {
		
		try {
			this.daoArticle.delete(a.getIdArticle());
		} catch (DALException ex) {
			throw new BLLException(ex);
		}
	
	}
	
	public List<Article> getCatalogue() throws BLLException {
		List<Article> liste = new ArrayList<>();
		
		try {
			liste = this.daoArticle.selectAll();
		} catch (DALException ex) {
			throw new BLLException("Erreur lors de la récupération du catalogue", ex);
		}
		return liste;
	}
	
	public void validerArticle(Article a) throws BLLException {
		
		if(a == null) {
			throw new BLLException("Article null");
		}
		
		//Cas ramette
		if(a instanceof Ramette) {
			//Vérifier les attributs de ramette
			Ramette ra = (Ramette) a;
			if(ra.getDesignation() == null || ra.getDesignation().isBlank()
					|| ra.getMarque() == null || ra.getMarque().isBlank()
					|| ra.getReference()==null || ra.getReference().isBlank()
					|| ra.getGrammage() == 0
					|| ra.getPrixUnitaire() == 0
					|| ra.getQteStock() == 0) {
				throw new BLLException("Chaque attribut doit être correctement renseigné "
						+ "(Marque, reference, et designation doivent être indiqués. "
						+ "Le prix unitaire, le grammage et la quantité en stock doivent être différents de 0");
			}
			
			//Vérifier que le grammage d'une ramette est positif
			if(((Ramette) a).getGrammage() < 0) {
				throw new BLLException("Le grammage d'une Ramette doit être positif");
			}
		}
		
		//Vérifier que la quantité en stock est positive
		if(a.getQteStock() < 0) {
			throw new BLLException("La quantité en stock doit être positive"); 
		}
		
		//Vérifier les attributs de stylo
		if(a instanceof Stylo) {
			Stylo st = (Stylo) a;
			if(st.getDesignation()==null || st.getDesignation().isBlank()
					|| st.getMarque()==null || st.getMarque().isBlank()
					|| st.getReference()==null || st.getReference().isBlank()
					|| st.getCouleur()==null || st.getCouleur().isBlank()
					|| st.getPrixUnitaire() == 0
					|| st.getQteStock() == 0) {
				throw new BLLException("Chaque attribut doit être correctement renseigné "
						+ "(Marque, reference, designation et couleur doivent être indiqués. "
						+ "Le prix unitaire et la quantité en stock doivent être différents de 0");
			}
		}
		
	}
}
