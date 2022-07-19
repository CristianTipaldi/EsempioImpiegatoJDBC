package it.ats.esempioimpiegatojdbc.modello.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ats.esempioimpiegatojdbc.modello.Impiegato;
import it.ats.esempioimpiegatojdbc.modello.dao.eccezioni.DAOException;

public class DAOImpiegato {

	private static DAOImpiegato instance;

	private static final String INSERT = "INSERT INTO impiegato(matricola,nome,cognome,sesso,data_nascita,salario,indirizzo_residenza) VALUES (?,?,?,?,?,?,?)";
	private static final String FIND_ALL = "SELECT * FROM impiegato";
	private static final String FIND_BY_ID = "SELECT * FROM impiegato WHERE id = ?";
	private static final String FIND_BY_MATRICOLA = "SELECT * FROM impiegato WHERE matricola = ?";
	private static final String UPDATE_SALARIO = "UPDATE impiegato SET salario = ? WHERE id = ?";
	private static final String UPDATE_RESIDENZA = "UPDATE impiegato SET indirizzo_residenza = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM impiegato WHERE id = ?";

	private DAOImpiegato() {
		super();
	}

	public void inserisci(Connection connection, Impiegato impiegato) throws DAOException {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setInt(1, impiegato.getMatricola());
			preparedStatement.setString(2, impiegato.getNome());
			preparedStatement.setString(3, impiegato.getCognome());
			preparedStatement.setString(4, String.valueOf(impiegato.getSesso()));
			preparedStatement.setDate(5, new Date(impiegato.getDataNascita().getTime()));
			preparedStatement.setDouble(6, impiegato.getSalario());
			preparedStatement.setString(7, impiegato.getIndirizzoResidenza());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DAOException("Impossibile inserire l'impiegato, errore DB");
		}
	}

	public List<Impiegato> cercaTutti(Connection connection) throws DAOException {
		List<Impiegato> listaImpiegati = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(FIND_ALL);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				int matricola = resultSet.getInt("matricola");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				char sesso = resultSet.getString("sesso").charAt(0);
				java.util.Date dataNascita = new java.util.Date(resultSet.getDate("data_nascita").getTime());
				double salario = resultSet.getDouble("salario");
				String indirizzoResidenza = resultSet.getString("indirizzo_residenza");
				Impiegato impiegato = new Impiegato(id, matricola, nome, cognome, sesso, dataNascita, salario, indirizzoResidenza);
				listaImpiegati.add(impiegato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossibile cercare gli impiegati, errore DB");
		}
		return listaImpiegati;
	}

	public Impiegato cercaPerId(Connection connection, long id) throws DAOException {
		Impiegato impiegato = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(FIND_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int matricola = resultSet.getInt("matricola");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				char sesso = resultSet.getString("sesso").charAt(0);
				java.util.Date dataNascita = new java.util.Date(resultSet.getDate("data_nascita").getTime());
				double salario = resultSet.getDouble("salario");
				String indirizzoResidenza = resultSet.getString("indirizzo_residenza");
				impiegato = new Impiegato(id, matricola, nome, cognome, sesso, dataNascita, salario, indirizzoResidenza);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DAOException("Impossibile cercare l'impiegato per id, errore DB");
		}
		return impiegato;
	}

	public Impiegato cercaPerMatricola(Connection connection, int matricola) throws DAOException {
		Impiegato impiegato = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(FIND_BY_MATRICOLA);
			preparedStatement.setLong(1, matricola);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				char sesso = resultSet.getString("sesso").charAt(0);
				java.util.Date dataNascita = new java.util.Date(resultSet.getDate("data_nascita").getTime());
				double salario = resultSet.getDouble("salario");
				String indirizzoResidenza = resultSet.getString("indirizzo_residenza");
				impiegato = new Impiegato(id, matricola, nome, cognome, sesso, dataNascita, salario, indirizzoResidenza);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DAOException("Impossibile cercare l'impiegato per matricola, errore DB");
		}
		return impiegato;
	}

	public void modificaSalario(Connection connection, Impiegato impiegato) throws DAOException {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_SALARIO);
			preparedStatement.setDouble(1, impiegato.getSalario());
			preparedStatement.setLong(2, impiegato.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DAOException("Impossibile modificare il salario dell'impiegato, errore DB");
		}
	}

	public void modificaResidenza(Connection connection, Impiegato impiegato) throws DAOException {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(UPDATE_RESIDENZA);
			preparedStatement.setString(1, impiegato.getIndirizzoResidenza());
			preparedStatement.setLong(2, impiegato.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Impossibile modificare la residenza dell'impiegato, errore DB");
		}
	}


	public void elimina(Connection connection, Impiegato impiegato) throws DAOException {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setLong(1, impiegato.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new DAOException("Impossibile eliminare l'impiegato, errore DB");
		}
	}

	public static DAOImpiegato getInstance() {
		if (instance == null) {
			instance = new DAOImpiegato();
		}
		return instance;
	}

}