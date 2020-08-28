package gestioneMenu;

import sistema_domotico.ControllerVisualizzazione;
import utility.Dati;

public class M_Visualizza_Unita_Immobiliari implements MenuCommand{

	ControllerVisualizzazione controllerVisualizzazione = new ControllerVisualizzazione();
	
	@Override
	public void esegui(Dati dati) {
		
		System.out.println(dati.getImmobile().getDestinazioneUnita());
		controllerVisualizzazione.visualizzaUnitaImmobiliari(dati.getImmobile());
		System.out.println("ciaooooooooo");
		
	}


}
