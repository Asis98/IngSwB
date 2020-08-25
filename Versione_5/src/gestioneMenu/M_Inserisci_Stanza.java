package gestioneMenu;

import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Stanza implements MenuCommand{
	
	ControlInserimento controllerInserimento = new ControlInserimento();

	@Override
	public void esegui(Dati dati) {
		
		controllerInserimento.inserisciStanza(dati.getImmobile());
		
	}


}
