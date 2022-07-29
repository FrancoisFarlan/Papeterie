package fr.eni.papeterie.dal;

import java.util.List;



public interface DAO<T> {

	public T selectById(int idArticle) throws DALException;
	public List<T> selectAll() throws DALException;
	public void update(T a)  throws DALException;
	public void insert(T a) throws DALException;
	public void delete(int index) throws DALException;
	public List<T> selectByMarque(String marque) throws DALException;
	public List<T> selectByMotCle(String motCle) throws DALException;
	public boolean isInStock(T a) throws DALException; 
	public void updateQteStock(T a) throws DALException;

}
