package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {
	public Article selectById(int idArticle) throws DALException;
	public List<Article> selectAll() throws DALException;
	public void update(Article a)  throws DALException;
	public void insert(Article a) throws DALException;
	public void delete(int index) throws DALException;
	public List<Article> selectByMarque(String marque) throws DALException;
	
}
