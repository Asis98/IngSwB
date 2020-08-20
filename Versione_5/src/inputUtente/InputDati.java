package inputUtente;

import java.util.Scanner;

public interface InputDati {

	  public Scanner creaScanner ();
	  public String leggiStringa (String messaggio);
	  public String leggiStringaNonVuota(String messaggio);
	  public int leggiIntero (String messaggio);
	  public int leggiInteroConMinimo(String messaggio, int minimo);
	  public int leggiIntero(String messaggio, int minimo, int massimo);
	  public double leggiDouble (String messaggio);
	  public double leggiDoubleConMinimo (String messaggio, double minimo);
	  
	  
}
