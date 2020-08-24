package gestioneMenu;

import utenti.Manutentore;
import utility.Dati;

public class MenuManutentore implements MenuCommand{

	private Manutentore manutentore = new Manutentore();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		manutentore.menuPersonalizzato();
	}
	
	
	
	
	

}
