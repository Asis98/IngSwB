package gestioneMenu;

import sistema_domotico.ControllerVisualizzazione;
import utility.Dati;

public class M_Stampa_Unita_Immobiliari implements MenuCommand{

	ControllerVisualizzazione controllerVisualizzazione = new ControllerVisualizzazione();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		controllerVisualizzazione.visualizzaImmobili(dati);
	}


}
