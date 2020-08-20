package gestioneMenu;

import utenti.Manutentore;

public class MenuImmobileAdmin implements MenuCommand {

	private Manutentore manutentore = new Manutentore();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
		manutentore.gestioneImmobileView();
	}
}
