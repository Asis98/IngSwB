package sistema_domotico;

import ambiente.Immobile;
import gestioneMenu.MenuHome;
import utility.Dati;


public class Main2 {

	private static Immobile immobile;
	

	public static void main(String[] args) {
		
		Dati dati = new Dati(immobile);
		
		MenuHome menuHome = new MenuHome();
		menuHome.esegui(dati);
		
	}
	
	

}
