package gestioneMenu;

import sistema_domotico.ControllerVisualizzazione;
import utility.Dati;

public class M_Visualizza_Categorie implements MenuCommand{

	ControllerVisualizzazione controllerVisualizzazione = new ControllerVisualizzazione();
	
	@Override
	public void esegui(Dati dati) {
		
		controllerVisualizzazione.visualizzaCategorie(dati.getListaCategorieAttuatori(),dati.getListaCategorieSensori());
		
	}

}
