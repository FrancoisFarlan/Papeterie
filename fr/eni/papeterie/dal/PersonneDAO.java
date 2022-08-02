package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Personne;

public interface PersonneDAO extends DAO<Personne> {

	public Personne selectByNumSS(int numSecu) throws DALException;
}
