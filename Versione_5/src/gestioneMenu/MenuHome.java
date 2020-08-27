package gestioneMenu;

import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import inputUtente.DatiUtente;
import utility.Dati;
import utility.MyMenu;

public class MenuHome implements MenuCommand{

	public void esegui(Dati dati) {
		
		MyMenu menu = new MyMenu(TitoliMenu.TITOLO, VociMenu.VOCIUTENTE);
		System.out.println(menu.stampaMenu());
		
		MenuCommand targetOperation = MV_User
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui(dati);
		
	}
}
