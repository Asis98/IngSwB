package sistema_domotico;

import ambiente.Immobile;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import gestioneMenu.MV_User;
import utility.Dati;
import utility.MyMenu;

public class Main2 {

	private static Immobile immobile;

	public static void main(String[] args) {
		
		Dati dati = new Dati(immobile);
		
		MyMenu menu = new MyMenu(TitoliMenu.TITOLO, VociMenu.VOCIUTENTE);
		System.out.println(menu.stampaMenu());
		
		MenuCommand targetOperation = MV_User
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui(dati);
		
	}
	
	

}
