package it.ats.esempioimpiegatojdbc.utilita;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * Classe contenente metodi per leggere dalla console i valori 
 * inseriti dall'utente
 * 
 * @author Nome Programmatore
 *
 */
public class Console {
	
	/**
	 * Metodo che cattura una stinga inserita sulla console
	 * 
	 * @return La stringa inserita nella console
	 */
	public static String leggiString() {
		
		String linea = " ";
		try 
		{
			InputStreamReader stream = new InputStreamReader(System.in);
			BufferedReader input= new BufferedReader(stream);

			linea = input.readLine();
		}

		catch (Exception e)
		{
			System.out.println("errore");
			System.exit(0) ;
		}
		return linea;

	}

	/**
	 * Metodo che cattura un intero inserito sulla console
	 * 
	 * @return L'intero inserito nella console
	 */
	public static int leggiInt() {
		
		String linea = " ";
		int numero;
		try {
			InputStreamReader stream = new InputStreamReader(System.in);
			BufferedReader input= new BufferedReader(stream);
			linea = input.readLine();
		} catch (Exception e) {
			System.out.println("errore");
			System.exit(0) ;
		}
		numero = Integer.parseInt (linea);
		return numero;
	}
	
	/**
	 * Metodo che cattura un carattere inserito sulla console
	 * 
	 * @return Il carattere inserito nella console
	 */

	public static char leggiChar() {
		
		String linea = " ";
		
		try {
			InputStreamReader stream = new InputStreamReader(System.in);
			BufferedReader input= new BufferedReader(stream);
			linea = input.readLine();

			if(linea.length() !=1)			
				throw new Exception();
		} catch (Exception e) {
			System.out.println("errore");
			System.exit(0) ;
		}
		return linea.charAt(0);	
	}
	
	/**
	 * Metodo che cattura un double inserito sulla console
	 * 
	 * @return Il numero double a virgola mobile inserito nella console
	 */

	public static double leggiDouble() {
		String linea = " ";
		double numero;
		try {
			InputStreamReader stream = new InputStreamReader(System.in);
			BufferedReader input= new BufferedReader(stream);

			linea = input.readLine();
		} catch (Exception e) {
			System.out.println("errore");
			System.exit(0);
		}
		numero = Double.parseDouble (linea);
		return numero;
	}

	/**
	 * Metodo che cattura un float inserito sulla console
	 * 
	 * @return Il numero float a virgola mobile inserito nella console
	 */
	public static float leggiFloat() {
		String linea = " ";
		float numero;
		
		try {
			InputStreamReader stream = new InputStreamReader(System.in);
			BufferedReader input= new BufferedReader(stream);

			linea = input.readLine();

		} catch (Exception e) {
			System.out.println("errore");
			System.exit(0);
		}
		
		numero = Float.parseFloat (linea);
		
		return numero;
	}
}
