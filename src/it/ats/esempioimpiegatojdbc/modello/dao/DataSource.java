package it.ats.esempioimpiegatojdbc.modello.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//Qui i metodi non rilanciano eccezioni ma andrebbe fatto!
public class DataSource {

	private static Properties dbProperties;
	private static String url;
	private static Driver dbDriver;
	private static DataSource instance;

	private DataSource() {
		super();
		this.load();
	}

	private void load() {
		try {
			dbProperties = new Properties();
			InputStream inputStream = DataSource.class.getClassLoader().getResourceAsStream("resources/jdbc.properties");
			dbProperties.load(inputStream);
			dbDriver = (Driver) Class.forName(dbProperties.getProperty("DriverClassName")).newInstance();
			url = dbProperties.getProperty("url");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = dbDriver.connect(url, dbProperties);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return connection;
	}

	public void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
