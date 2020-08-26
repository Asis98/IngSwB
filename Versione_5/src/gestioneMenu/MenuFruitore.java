package gestioneMenu;

import utenti.Fruitore;
import utility.Dati;


public class MenuFruitore implements MenuCommand {

	private Fruitore fruitore = new Fruitore();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		fruitore.menuPersonalizzato(dati);
	}

}
