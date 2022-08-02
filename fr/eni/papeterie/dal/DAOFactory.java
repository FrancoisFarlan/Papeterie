package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.papeterie.dal.jdbc.PersonnesDAOJdbcImpl;

public class DAOFactory {

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static PersonneDAO getPersonneDAO() {
		return new PersonnesDAOJdbcImpl();
	}
	
	
}
