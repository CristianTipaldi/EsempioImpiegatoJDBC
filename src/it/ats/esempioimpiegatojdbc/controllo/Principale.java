package it.ats.esempioimpiegatojdbc.controllo;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import it.ats.esempioimpiegatojdbc.modello.Impiegato;
import it.ats.esempioimpiegatojdbc.modello.dao.DAOImpiegato;
import it.ats.esempioimpiegatojdbc.modello.dao.DataSource;
import it.ats.esempioimpiegatojdbc.modello.dao.eccezioni.DAOException;
import it.ats.esempioimpiegatojdbc.utilita.Console;

public class Principale {

	private DataSource dataSource;
	private DAOImpiegato daoImpiegato;

	public static void main(String[] args) {
		new Principale().esegui();
	}

	private void esegui() {
		Connection connection = null;
		while (true) {
			switch (schermoMenu()) {
			case 0: {
				System.out.println("Arrivederci!");
				System.exit(0);
			}
			case 1: {
				connection = dataSource.getConnection();
				System.out.println("Matricola? ");
				int matricola = Console.leggiInt();
				try {
					if (daoImpiegato.cercaPerMatricola(connection, matricola) != null) {
						System.out.println("Impossibile procedere, ? gi? presente un impiegato con questa matricola!");
					} else {
						System.out.println("Nome? ");
						String nome = Console.leggiString();
						System.out.println("Cognome? ");
						String cognome = Console.leggiString();
						char sesso;
						do {
							System.out.println("Sesso? (M/F)");
							sesso = Console.leggiChar();
						} while (sesso != 'M' && sesso != 'F');
						System.out.println("--Data Nascita--");
						System.out.println("Giorno? ");
						int giorno = Console.leggiInt();
						System.out.println("Mese? ");
						int mese = Console.leggiInt();
						System.out.println("Anno? ");
						int anno = Console.leggiInt();
						Date dataNascita = new Date(anno - 1900, mese - 1, giorno + 1);
						System.out.println("Salario? ");
						double salario = Console.leggiDouble();
						System.out.println("Indirizzo di residenza? ");
						String indirizzoResidenza = Console.leggiString();
						Impiegato impiegato = new Impiegato(matricola, nome, cognome, sesso, dataNascita, salario, indirizzoResidenza);
						daoImpiegato.inserisci(connection, impiegato);
					}
				} catch (DAOException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					dataSource.close(connection);
				}
				break;
			}
			case 2: {
				connection = dataSource.getConnection();
				List<Impiegato> listaImpiegati;
				try {
					listaImpiegati = daoImpiegato.cercaTutti(connection);
					if (!listaImpiegati.isEmpty()) {
						for (Impiegato impiegato : listaImpiegati) {
							System.out.println(impiegato);
						}
					} else {
						System.out.println("Nessun impiegato presente!");
					}
				} catch (DAOException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					dataSource.close(connection);
				}
				break;
			}
			case 3: {
				connection = dataSource.getConnection();
				System.out.println("Id? ");
				long id = Console.leggiInt();
				Impiegato impiegato;
				try {
					impiegato = daoImpiegato.cercaPerId(connection, id);
					if (impiegato != null) {
						System.out.println("Impiegato trovato: " + impiegato);
					} else {
						System.out.println("Nessun impiegato con questo id!");
					}
				} catch (DAOException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					dataSource.close(connection);
				}
				break;
			}
			case 4: {
				connection = dataSource.getConnection();
				try {
					System.out.println("Id? ");
					long id = Console.leggiInt();
					Impiegato impiegato = daoImpiegato.cercaPerId(connection, id);
					if (impiegato != null) {
						System.out.println("Nuovo salario? ");
						double salario = Console.leggiDouble();
						impiegato.setSalario(salario);
						daoImpiegato.modificaSalario(connection, impiegato);
					} else {
						System.out.println("Nessun impiegato con questo id!");
					}
				} catch (DAOException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					dataSource.close(connection);
				}
				break;
			}
			case 5: {
				connection = dataSource.getConnection();
				try {
					System.out.println("Id? ");
					long id = Console.leggiInt();
					Impiegato impiegato = daoImpiegato.cercaPerId(connection, id);
					if (impiegato != null) {
						daoImpiegato.elimina(connection, impiegato);
						System.out.println("Impiegato eliminato!");
					} else {
						System.out.println("Nessun impiegato con questo id!");
					}
				} catch (DAOException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					dataSource.close(connection);
				}
				break;
			}
			case 6: {
				connection = dataSource.getConnection();
				try {
					System.out.println("Id? ");
					long id = Console.leggiInt();
					Impiegato impiegato = daoImpiegato.cercaPerId(connection, id);
					if (impiegato != null) {
						System.out.println("Nuovo indirizzo? ");
						String indirizzoResidenza = Console.leggiString();
						impiegato.setIndirizzoResidenza(indirizzoResidenza);
						daoImpiegato.modificaResidenza(connection, impiegato);
					} else {
						System.out.println("Nessun impiegato con questo id!");
					}
				} catch (DAOException e) {
					// e.printStackTrace();
					System.out.println(e.getMessage());
				} finally {
					dataSource.close(connection);
				}
				break;
			}
			}
		}
	}

	private int schermoMenu() {
		int scelta;
		do {
			System.out.println("Digita 0 per uscire");
			System.out.println("Digita 1 per inserire un impiegato");
			System.out.println("Digita 2 per cercare tutti gli impiegati");
			System.out.println("Digita 3 per cercare un impiegato per id");
			System.out.println("Digita 4 per modificare il salario di un impiegato");
			System.out.println("Digita 5 per eliminare un impiegato");
			System.out.println("Digita 6 per modificare l'indirizzo di un impiegato");
			scelta = Console.leggiInt();
		} while (scelta < 0 || scelta > 6);
		return scelta;
	}

	public Principale() {
		super();
		this.daoImpiegato = DAOImpiegato.getInstance();
		this.dataSource = DataSource.getInstance();
	}

}
