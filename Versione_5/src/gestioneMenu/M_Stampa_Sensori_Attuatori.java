package gestioneMenu;

import sistema_domotico.ControllerVisualizzazione;
import utility.Dati;

public class M_Stampa_Sensori_Attuatori implements MenuCommand{

	ControllerVisualizzazione controllerVisualizzazione = new ControllerVisualizzazione();
	
	@Override
	public void esegui(Dati dati) {
		
		controllerVisualizzazione.visualizzaListaUnitaRilevazione(dati.getImmobile());
		
	}


}
