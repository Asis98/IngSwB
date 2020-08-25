package gestioneMenu;

import ambiente.Immobile;
import rilevazione.Attuatore;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Attuatore implements MenuCommand{

	ControlInserimento controllerInserimento = new ControlInserimento();
	
	@Override
	public void esegui(Dati dati) {
		
		Immobile immobile = dati.getImmobile();
		immobile.setListaAttuatori(controllerInserimento.inserisciUnitaRilevazione(new Attuatore(), dati.getListaCategorieAttuatori(),immobile));
		
	}


}
