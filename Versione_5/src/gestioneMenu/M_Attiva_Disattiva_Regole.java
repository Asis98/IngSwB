package gestioneMenu;

import ambiente.Immobile;
import sistema_domotico.ControllerAbleDisable;
import utility.Dati;

public class M_Attiva_Disattiva_Regole implements MenuCommand{

	ControllerAbleDisable controllerAD= new ControllerAbleDisable();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		Immobile immobile= dati.getImmobile();
		dati.setImmobile(controllerAD.gestisciStatoRegole(immobile, dati));
	}

}
