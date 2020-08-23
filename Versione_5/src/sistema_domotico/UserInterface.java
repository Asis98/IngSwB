package sistema_domotico;

import costanti.Messaggi;
import inputUtente.DatiUtente;

public class UserInterface {
	
	private DatiUtente input;
	
	public UserInterface()
	{
		input = new DatiUtente();
	}
	
	public String InputStringaNonVuota(String messaggio)
	{
		return input.leggiStringaNonVuota(messaggio);
	}
	
	public void stampaMessaggio(String messaggio)
	{
		System.out.println(messaggio);
	}
	
	public int inputInteriConMinimo(String messaggio, int minimo, int massimo)
	{
		return input.leggiIntero(messaggio, minimo, massimo);
	}
	
	public double inputDouble(String messaggio)
	{
		return input.leggiDouble(messaggio);
	}
	
	public int inputLeggiIntero(String messaggio)
	{
		return input.leggiIntero(messaggio);
	}
}


