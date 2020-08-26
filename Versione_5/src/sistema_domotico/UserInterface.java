package sistema_domotico;

import costanti.TitoliMenu;
import costanti.VociMenu;
import inputUtente.DatiUtente;
import utility.MyMenu;

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
	public int inputLeggiInteroIntervallo(String messaggio, int num1, int num2)
	{
		return input.leggiIntero(messaggio, num1, num2);
	}
	
	public void AD_stampa_menu_unita_rilevazione()
	{
		MyMenu menuGestioneStatoUnitaRilevazione = new MyMenu(TitoliMenu.TITOLOSTATOUNITARIL, VociMenu.VOCISTATOUNITARILEVAZIONE);
		System.out.println(menuGestioneStatoUnitaRilevazione.stampaMenu());
	}
	
	public void AD_stampa_menu_regole()
	{
		MyMenu menuRegole= new MyMenu(TitoliMenu.TITOLOATTIVADISATTIVA, VociMenu.VOCISTATOREGOLE);
		System.out.println(menuRegole.stampaMenu());
	}
	
	
}


