package gestioneMenu;


import sistema_domotico.ControlInserimento;
import utenti.Manutentore;
import utility.Dati;

public class MenuImmobileAdmin implements MenuCommand {

	private Manutentore manutentore = new Manutentore();
	ControlInserimento	controlInserimento = new ControlInserimento();
	
	@Override
	public void esegui(Dati dati) {
		
		controlInserimento.scegliImmobile(dati);	
		manutentore.gestioneImmobileView(dati);
			
	}
}
