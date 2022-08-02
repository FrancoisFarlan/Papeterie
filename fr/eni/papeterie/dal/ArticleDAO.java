package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO extends DAO<Article> {

	public List<Article> selectByMarque(String marque) throws DALException;
	public List<Article> selectByMotCle(String motCle) throws DALException;
	public boolean isInStock(Article a) throws DALException; 
	public void updateQteStock(Article a) throws DALException;
}
