package sistema_domotico;

import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import gestioneMenu.MV_User;
import utility.MyMenu;

public class Main2 {


	public static void main(String[] args) {
			
		MyMenu menu= new MyMenu(TitoliMenu.TITOLO, VociMenu.VOCIUTENTE);
		menu.stampaMenu();
		
		MenuCommand targetOperation = MV_User
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui();
	}
	
	

}
