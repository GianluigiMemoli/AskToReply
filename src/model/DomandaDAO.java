package model;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class DomandaDAO {

	public static Logger log = Logger.getAnonymousLogger();
	
	public static DomandaBean addDomanda(DomandaBean domanda) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("CreateDomanda", 5);
			
			procedure.setNString(1, domanda.getTitolo());
			procedure.setNString(2, domanda.getCorpo());
			procedure.setNString(3, domanda.getAutore().getId());
			procedure.setDate(4, new java.sql.Date(domanda.getDataPubblicazione().getTime()));
			
			procedure.registerOutParameter(5, Types.VARCHAR);
			
			procedure.executeUpdate();
			
			domanda.setId(procedure.getNString(5));
			
			return domanda;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void addCategoriaDomanda(DomandaBean domanda, CategoriaBean categoria) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			// E se inserisce una categoria già presente?
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("AddCategoriaDomanda", 2);
			
			procedure.setNString(1, domanda.getId());
			procedure.setNString(2, categoria.getId());
			
			procedure.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateCategorieDomanda(DomandaBean domanda) {
		
		System.out.println("Dimensioni categorie:" + domanda.getCategorie().size());
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			//
			
			String query_delete = "DELETE FROM categoriedomande WHERE idDomanda = ?;";
			
			PreparedStatement stmt_delete = manager.createPreparedStatement(query_delete);
			
			stmt_delete.setString(1, domanda.getId());
			
			stmt_delete.executeUpdate();
			
			// 
			
			String query_insert = "";
			
			for(int i = 0; i < domanda.getCategorie().size(); i++) {
				query_insert += "INSERT INTO categoriedomande(idDomanda, idCategoria) VALUES (?, ?);";
			}
			
			PreparedStatement stmt_insert = manager.createPreparedStatement(query_insert);
			
			int p = 1;
			
			for(int i = 0; i < domanda.getCategorie().size(); i++) {
				stmt_insert.setString(p, domanda.getId());
				stmt_insert.setString(p + 1, domanda.getCategorie().get(i).getId());
				p += 2;
			}
			
			stmt_insert.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<DomandaBean> getDomandeByUtente(String idUtente) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("GetDomandeByUser", 1);
			
			procedure.setNString(1, idUtente);
			
			ResultSet rs = procedure.executeQuery();
			
			ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
			
			while(rs.next()) {
				
				DomandaBean domanda = new DomandaBean();
				
				domanda.setArchiviata(rs.getBoolean("isArchiviata"));
				
				UtenteBean autore = new UtenteBean();
				autore.setId(rs.getNString("idAutore"));
				
				domanda.setAutore(autore);
				domanda.setCorpo(rs.getNString("corpo"));
				domanda.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				domanda.setId(rs.getNString("id"));
				domanda.setTitolo(rs.getString("titolo"));
				
				domande.add(domanda);
				
			}
			
			return domande;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
public static ArrayList<DomandaBean> getDomandeRisposte(String idUtente) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("GetDomandeRisposte", 1);
			
			procedure.setNString(1, idUtente);
			
			ResultSet rs = procedure.executeQuery();
			
			ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
			
			while(rs.next()) {
				
				DomandaBean domanda = new DomandaBean();
				
				domanda.setArchiviata(rs.getBoolean("isArchiviata"));
				
				UtenteBean autore = new UtenteBean();
				autore.setId(rs.getNString("idAutore"));
				
				domanda.setAutore(autore);
				domanda.setCorpo(rs.getNString("corpo"));
				domanda.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				domanda.setId(rs.getNString("id"));
				domanda.setTitolo(rs.getString("titolo"));
				
				domande.add(domanda);
				
			}
			
			return domande;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static ArrayList<DomandaBean> getDomandeByUtente(String idUtente, int start, int end) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement procedure = manager.prepareStoredProcedureCall("GetDomandeByAutoreLimit", 3);
			
			procedure.setNString(1, idUtente);
			procedure.setInt(2, start);
			procedure.setInt(3, end);
			
			ResultSet rs = procedure.executeQuery();
			
			ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
			
			while(rs.next()) {
				
				DomandaBean domanda = new DomandaBean();
				
				domanda.setArchiviata(rs.getBoolean("isArchiviata"));
				
				UtenteBean autore = new UtenteBean();
				autore.setId(rs.getNString("idAutore"));
				
				domanda.setAutore(autore);
				domanda.setCorpo(rs.getNString("corpo"));
				domanda.setDataPubblicazione(rs.getDate("dataPubblicazione"));
				domanda.setId(rs.getNString("id"));
				domanda.setTitolo(rs.getString("titolo"));
				
				domande.add(domanda);
				
			}
			
			return domande;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static DomandaBean getDomandaById(String id) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetDomandaById", 1);
			
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				DomandaBean domanda = new DomandaBean();
				
				domanda.setId(rs.getString("id"));
		        domanda.setTitolo(rs.getNString("titolo"));
		        domanda.setCorpo(rs.getString("corpo"));
		        
		        UtenteBean autore = new UtenteBean();
		        
		        autore.setId(rs.getNString("idAutore"));
		        
		        domanda.setAutore(autore);
		        domanda.setDataPubblicazione(rs.getDate("DataPubblicazione"));
		        domanda.setArchiviata(rs.getBoolean("isArchiviata"));
		        
		        log.info(domanda.toString());
		        
		        return domanda;
		        
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
		
	}
	
	public static ArrayList<DomandaBean> getDomandeCercate(String testo, Boolean isArchiviata, String[] categorie) {
		
		String query_select = "SELECT u.id, u.username, d.dataPubblicazione, d.titolo, d.corpo, d.id, d.isArchiviata";
		String query_from = "FROM domande d, categoriedomande cd, utenti u";
		String query_where = "WHERE cd.idDomanda = d.id AND d.idAutore = u.id";
		String query_group_by = "";
		String query_having = "";
		String query_order_by = "ORDER BY d.dataPubblicazione ASC";
		
		if(testo != null) {
			query_select += ", MATCH(d.titolo, d.corpo) AGAINST (?) AS rilevanza";
			query_order_by = "ORDER BY rilevanza DESC";
		}
		
		if(isArchiviata != null) {
			query_where += " " + "AND d.isArchiviata = ?";
		}
		
		if(categorie != null) {
			if(categorie.length > 0) {
				query_where += " " + "AND cd.idCategoria IN (SELECT id FROM categorie WHERE id IN (";
				
				query_where += "?";
				
				for (int i = 1; i < categorie.length; i++) {
					query_where += ", ?"; 
				}
				
				query_where += "))";
				
				query_having += "HAVING COUNT(cd.idCategoria) >= " + categorie.length;
			}
		}
		
		query_group_by += "GROUP BY cd.idDomanda";
		
		// query_order_by += " " + "DESC"; // TODO Se è solo la data di pubblicazione deve essere ASC
		
		String query = query_select + "\n" + query_from + "\n" + query_where + "\n" + query_group_by + "\n" + query_having + "\n" + query_order_by;
		
		log.info(query);
		
		DBManager manager = DBManager.getInstance();
		
		int p = 1;
		
		try {
			
			PreparedStatement stmt = manager.createPreparedStatement(query);
			
			if(testo != null) {
				stmt.setString(p, testo);
				p++;
			}
			
			if(isArchiviata != null) {
				stmt.setBoolean(p, isArchiviata);
				p++;
			}
			
			if(categorie != null) {
				for (int i = 0; i < categorie.length; i++) {
					stmt.setString(p, categorie[i]);
					p++;
				}
			}
					
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
			
			while(rs.next()) {
				
				// TODO Modificare il bean Domanda sostituendo String idAutore con Partecipante Autore
				// perché in questo modo si può restituire il nome utente di un autore all'interno della domanda.
				
				String idAutore = rs.getNString("u.id");
				String username = rs.getString("u.username");
				Date dataPubblicazione = rs.getDate("d.dataPubblicazione");
				String titolo = rs.getString("d.titolo");
				String corpo = rs.getString("d.corpo");
				String id = rs.getString("d.id");
				boolean archiviata = rs.getBoolean("d.isArchiviata");
				
				DomandaBean domanda = new DomandaBean();
				
				domanda.setId(id);
				domanda.setArchiviata(archiviata);
				domanda.setCorpo(corpo);
				domanda.setDataPubblicazione(dataPubblicazione);
				domanda.setTitolo(titolo);
				
				UtenteBean utente = new UtenteBean();
				
				utente.setId(idAutore);
				utente.setUsername(username);
				
				domanda.setAutore(utente);
						
				domande.add(domanda);
				
				// TODO Aggiungere le categorie alle domanda
	
			}
			
			return domande;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}

	public static void removeDomanda(String idDomanda) {
		
		DBManager manager = DBManager.getInstance();
		
		try {
			
			CallableStatement stmt = manager.prepareStoredProcedureCall("RemoveDomanda", 1);
			
			stmt.setString(1, idDomanda);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static ArrayList<DomandaBean> getDomandeRecenti(int start, int end){		
		String query = "SELECT * FROM Domande ORDER BY dataPubblicazione  LIMIT ?, ?" ;		
		DBManager dbManager = DBManager.getInstance();
		ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
		try {
			PreparedStatement stmt = dbManager.createPreparedStatement(query);
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			ResultSet records = stmt.executeQuery();			
			
			while(records.next()) {
				String id = records.getNString("id");
				String titolo = records.getNString("titolo");
				String corpo = records.getNString("corpo");
				String idAutore = records.getNString("idAutore");
				Date dataPubblicazione = records.getDate("dataPubblicazione");
				boolean isArchiviata = records.getBoolean("isArchiviata");
				DomandaBean domandaInstance = new DomandaBean();
				domandaInstance.setId(id);
				domandaInstance.setTitolo(titolo);
				domandaInstance.setCorpo(corpo);
				domandaInstance.setArchiviata(isArchiviata);
				domandaInstance.setDataPubblicazione(dataPubblicazione);
				UtenteBean autore = new UtenteBean();
				autore.setId(idAutore);
				domandaInstance.setAutore(autore);
				domande.add(domandaInstance);		
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return domande;
	}
	
	public static int getNumeroDomandePertinenti(ArrayList<CategoriaBean> categorie) {
		int amount = 0;
		String paramPlaceholder = "?,";
		String catParams = paramPlaceholder.repeat(categorie.size());
		catParams = catParams.substring(0, catParams.length() - 1);
		String query =
				"SELECT Count(DISTINCT dom.id) as Amount FROM Domande AS dom JOIN categoriedomande as catDom ON dom.id = catDom.idDomanda "
				+ "WHERE catDom.idCategoria IN (" + catParams +")" ;
		log.info(query);
		log.info(catParams);
		DBManager dbManager = DBManager.getInstance();
		ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
		try {
			PreparedStatement stmt = dbManager.createPreparedStatement(query);			
			for (int currentParam = 1; currentParam <= categorie.size(); currentParam++) {
				stmt.setNString(currentParam, categorie.get(currentParam-1).getId());
			}			
			ResultSet record = stmt.executeQuery();			
			log.info(stmt.toString());
			while(record.next()) {
					amount = record.getInt("Amount");
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return amount;
	}
	
	public static ArrayList<DomandaBean> getDomandePertinenti(ArrayList<CategoriaBean> categorie, int start, int end){		
		String paramPlaceholder = "?,";
		String catParams = paramPlaceholder.repeat(categorie.size());
		catParams = catParams.substring(0, catParams.length() - 1);
		String query =
				"SELECT * FROM Domande AS dom JOIN categoriedomande as catDom ON dom.id = catDom.idDomanda "
				+ "WHERE catDom.idCategoria IN (" + catParams +") GROUP BY dom.id ORDER BY dom.dataPubblicazione DESC limit ?, ?" ;										
		log.info(query);
		log.info(catParams);
		DBManager dbManager = DBManager.getInstance();
		ArrayList<DomandaBean> domande = new ArrayList<DomandaBean>();
		try {
			PreparedStatement stmt = dbManager.createPreparedStatement(query);
			int currentParam = 1;
			for (; currentParam <= categorie.size(); currentParam++) {
				stmt.setNString(currentParam, categorie.get(currentParam-1).getId());
			}
			stmt.setInt(currentParam, start);
			stmt.setInt(++currentParam, end);
			ResultSet records = stmt.executeQuery();			
			log.info(stmt.toString());
			while(records.next()) {
				log.info(records.toString());
				String id = records.getNString("id");
				String titolo = records.getNString("titolo");
				String corpo = records.getNString("corpo");
				String idAutore = records.getNString("idAutore");
				Date dataPubblicazione = records.getDate("dataPubblicazione");
				boolean isArchiviata = records.getBoolean("isArchiviata");
				DomandaBean domandaInstance = new DomandaBean();
				domandaInstance.setId(id);
				domandaInstance.setTitolo(titolo);
				domandaInstance.setCorpo(corpo);
				domandaInstance.setArchiviata(isArchiviata);
				domandaInstance.setDataPubblicazione(dataPubblicazione);
				UtenteBean autore = new UtenteBean();
				autore.setId(idAutore);
				domandaInstance.setAutore(autore);
				domande.add(domandaInstance);		
		}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return domande;
	}
	
	public static int getNumeroDomandeByAutore(String idAutore) {
		
		DBManager manager = DBManager.getInstance();
		
		try {			
			CallableStatement stmt = manager.prepareStoredProcedureCall("GetNumeroDomandeByAutore", 1);			
			stmt.setString(1, idAutore);			
			
			ResultSet rs = stmt.executeQuery();			
			if(rs.next())
				return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
}
