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
}


