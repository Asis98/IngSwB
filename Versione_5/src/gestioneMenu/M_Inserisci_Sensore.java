package gestioneMenu;

import ambiente.Immobile;
import rilevazione.Attuatore;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Sensore implements MenuCommand{

	ControlInserimento controllerInserimento = new ControlInserimento();
	
	@Override
	public void esegui(Dati dati) {
		
		Immobile immobile = dati.getImmobile();
		immobile.setListaSensori(controllerInserimento.inserisciUnitaRilevazione(new Attuatore(), dati.getListaCategorieSensori(),immobile));
	}


}
