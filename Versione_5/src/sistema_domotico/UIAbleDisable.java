package sistema_domotico;

import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import utility.MyMenu;

public class UIAbleDisable 
{
	public void AD_stampa_menu_unita_rilevazione()
	{
		MyMenu menuGestioneStatoUnitaRilevazione = new MyMenu(TitoliMenu.TITOLOSTATOUNITARIL, VociMenu.VOCISTATOUNITARILEVAZIONE);
		menuGestioneStatoUnitaRilevazione.stampaMenu();
	}
	
	public void AD_stampa_menu_regole()
	{
		MyMenu menuRegole= new MyMenu(TitoliMenu.TITOLOATTIVADISATTIVA, VociMenu.VOCISTATOREGOLE);
		menuRegole.stampaMenu();
	}
	
	public void AD_messaggio_lista_vuota()
	{
		System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
	}
}
