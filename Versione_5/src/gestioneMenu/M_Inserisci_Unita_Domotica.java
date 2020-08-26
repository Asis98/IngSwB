package gestioneMenu;

import ambiente.Immobile;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Unita_Domotica implements MenuCommand{

	ControlInserimento controllerInserimento = new ControlInserimento();
	@Override
	public void esegui(Dati dati) {
		
		Immobile immobile = dati.getImmobile();
		dati.setImmobile(controllerInserimento.inserimentoUnitaDomotica(immobile, dati));
		
	}

}
