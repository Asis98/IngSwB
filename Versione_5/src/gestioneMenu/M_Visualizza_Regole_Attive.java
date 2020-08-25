package gestioneMenu;

import sistema_domotico.ControllerVisualizzazione;
import utility.Dati;

public class M_Visualizza_Regole_Attive implements MenuCommand{

	ControllerVisualizzazione controllerV= new ControllerVisualizzazione();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		controllerV.visualizzaRegoleAttive(dati.getImmobile());
	}


}
