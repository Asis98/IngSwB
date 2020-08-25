package gestioneMenu;

import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Artefatto implements MenuCommand{
	
	ControlInserimento controllerInserimento = new ControlInserimento();

	@Override
	public void esegui(Dati dati) {
		
		controllerInserimento.inserisciArtefatto(dati.getImmobile());
	}


}
