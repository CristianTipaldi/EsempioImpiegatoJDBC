package it.ats.esempioimpiegatojdbc.modello;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Impiegato {

	private long id;
	private int matricola;
	private String nome;
	private String cognome;
	private char sesso;
	private Date dataNascita;
	private double salario;

	private String indirizzoResidenza;

	// per l'inserimento
	public Impiegato(int matricola, String nome, String cognome, char sesso, Date dataNascita, double salario, String indirizzoResidenza) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
		this.salario = salario;
		this.indirizzoResidenza = indirizzoResidenza;
	}

	// per il recupero
	public Impiegato(long id, int matricola, String nome, String cognome, char sesso, Date dataNascita,
					 double salario, String indirizzoResidenza) {
		super();
		this.id = id;
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.dataNascita = dataNascita;
		this.salario = salario;
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public long getId() {
		return id;
	}

	public int getMatricola() {
		return matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public char getSesso() {
		return sesso;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	private String formattaDataNascita() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataNascita);
	}

	@Override
	public String toString() {
		return "Impiegato [id=" + id + ", matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome
				+ ", sesso=" + sesso + ", dataNascita=" + formattaDataNascita() + ", salario=" + salario + ", indirizzo residenza:" +indirizzoResidenza +"]";
	}

}
