
package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;

public class ArticleDAOJdbcImpl implements DAO<Article> {

	public Article selectById(int idArticle) throws DALException {

		Connection con = null;
		String sql = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles "
				+ "WHERE idArticle = ?";
		PreparedStatement stmt = null;
		Article a = null;
		ResultSet rs = null;

		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idArticle);

			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").trim().equalsIgnoreCase("Stylo")) {
					a = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
				}

				if (rs.getString("type").trim().equalsIgnoreCase("Ramette")) {
					a = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
			}

		} catch (SQLException ex) {
			throw new DALException("erreur dans le selectById", ex); 
		} finally {
			JdbcTools.close(rs);
			JdbcTools.close(stmt);
			JdbcTools.close(con);

		}
		return a;
	}

	public List<Article> selectAll() throws DALException {
		List<Article> articles = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = JdbcTools.getConnection();
			stmt = con.createStatement();
			rs = stmt
					.executeQuery("SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, "
							+ "couleur, type FROM Articles");
			Article a = null;
			String type;
			while (rs.next()) {
				type = rs.getString("type").trim();
				if (type.equalsIgnoreCase("Stylo")) {
					a = new Stylo(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"), rs.getString("designation"), 
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getString("couleur"));
					articles.add(a);
				} else if (type.equalsIgnoreCase("Ramette")) {
					a = new Ramette(rs.getInt("idArticle"), rs.getString("reference"), rs.getString("marque"), rs.getString("designation"), 
							rs.getFloat("prixUnitaire"), rs.getInt("qteStock"), rs.getInt("grammage"));
					articles.add(a);
				}
			}

		} catch (SQLException e) {
			throw new DALException("erreur selectAll", e);
		} finally {
			JdbcTools.close(rs);
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}
		return articles;

	}

	public void update(Article a) throws DALException{

		Connection con = null;
		String sql = "UPDATE Articles " + "SET reference = ?, " + "marque = ?, " + "designation = ?, "
				+ "prixUnitaire = ?, " + "qteStock = ?, " + "grammage = ?, " + "couleur = ?, " + "type = ? "
				+ "WHERE idArticle = ?";

		PreparedStatement stmt = null;

		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);

			stmt.setString(1, a.getReference());
			stmt.setString(2, a.getMarque());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());

			if (a instanceof Stylo) {
				Stylo p = (Stylo) a;
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, p.getCouleur());

			} else if (a instanceof Ramette) {
				Ramette r = (Ramette) a;
				stmt.setInt(6, r.getGrammage());
				stmt.setNull(7, Types.NVARCHAR);
			}

			stmt.setString(8, a.getClass().getSimpleName());
			stmt.setInt(9, a.getIdArticle());

			stmt.executeUpdate();

		} catch (SQLException ex) {
			throw new DALException("erreur update impossible", ex);
		} finally {
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}
	}

	public void insert(Article a) throws DALException {

		Connection con = null;
		String sql = "INSERT INTO Articles(reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, a.getReference());
			stmt.setString(2, a.getMarque());
			stmt.setString(3, a.getDesignation());
			stmt.setFloat(4, a.getPrixUnitaire());
			stmt.setInt(5, a.getQteStock());

			if (a instanceof Stylo) {
				Stylo p = (Stylo) a;
				stmt.setNull(6, Types.INTEGER);
				stmt.setString(7, p.getCouleur());

			} else if (a instanceof Ramette) {
				Ramette r = (Ramette) a;
				stmt.setInt(6, r.getGrammage());
				stmt.setNull(7, Types.NVARCHAR);
			}

			stmt.setString(8, a.getClass().getSimpleName());

			int nbLignes = stmt.executeUpdate();
			if (nbLignes == 1) {
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					a.setIdArticle(rs.getInt(1));
				}
			}

		} catch (SQLException e) {
			throw new DALException("insertion impossibl", e);
		} finally {
			JdbcTools.close(rs);
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}

	}

	public void delete(int index) throws DALException{

		Connection con = null;
		String sql = "DELETE FROM Articles WHERE idArticle = ?";
		PreparedStatement stmt = null;
				
		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, index);
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			throw new DALException("delete impossible", ex);
		}  finally {
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}
		
	}

	@Override
	public List<Article> selectByMarque(String marque) throws DALException {
		
		Connection con = null;
		String sql = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles "
				+ "WHERE marque = ?";
		PreparedStatement stmt = null;
		List<Article> liste = new ArrayList<>();	
		ResultSet rs = null;

		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, marque);
			rs = stmt.executeQuery();
			
			Article a = null;
			
			while(rs.next()) {
				if(rs.getString("type").trim().equalsIgnoreCase("Stylo")) {
					a = new Stylo(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getString("couleur"));
					liste.add(a);
				} else if(rs.getString("type").trim().equalsIgnoreCase("Ramette")) {
					a = new Ramette(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
							rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
							rs.getInt("grammage"));
					liste.add(a);
				}
			}
			
			
			
		} catch (SQLException ex) {
			throw new DALException("erreur selectByMarque", ex);
		} finally {
			JdbcTools.close(rs);
			JdbcTools.close(stmt);
			JdbcTools.close(con);

		}
		return liste;
		
	}
	
	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type " + 
				" FROM articles WHERE marque like ? or designation like ?";
		List<Article> liste = new ArrayList<Article>();
		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, motCle);
			rs = stmt.executeQuery();
			Article art = null;

			while (rs.next()) {
				if (rs.getString("type").trim().equalsIgnoreCase("Stylo")){

					art = new Stylo(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getString("couleur"));
				}
				if (rs.getString("type").trim().equalsIgnoreCase("Ramette")){
					art = new Ramette(rs.getInt("idArticle"),
							rs.getString("marque"),
							rs.getString("reference").trim(),
							rs.getString("designation"),
							rs.getFloat("prixUnitaire"),
							rs.getInt("qteStock"),
							rs.getInt("grammage"));
				}
				liste.add(art);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMotCle failed - " , e);
		} finally {
			JdbcTools.close(rs);
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}
		return liste;
	}

	@Override
	public boolean isInStock(Article a) throws DALException {
		boolean isInStock = false;
		Connection con = null;
		String sql = "SELECT 1 FROM Articles "
				+ "WHERE reference = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, a.getReference());
			rs = stmt.executeQuery();
			
			isInStock = rs.next();
			
		} catch (SQLException ex) {
			throw new DALException("erreur dans la fonction isInStock", ex);
		} finally {
			JdbcTools.close(rs);
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}
		
		
		return isInStock;
	}

	@Override
	public void updateQteStock(Article a) throws DALException {
		
		Connection con = null;
		
		String sql = "UPDATE Articles "
				+ "SET qteStock = qteStock + ? "
				+ "WHERE reference = ?";
		PreparedStatement stmt = null;
		
		
		try {
			con = JdbcTools.getConnection();
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, a.getQteStock());
			stmt.setString(2, a.getReference());
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			throw new DALException("erreur dans la fonction updateQteStock", ex);
		} finally {
			JdbcTools.close(stmt);
			JdbcTools.close(con);
		}
		
		
	}

}