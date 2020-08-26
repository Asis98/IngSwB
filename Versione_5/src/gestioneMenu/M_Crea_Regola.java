package gestioneMenu;

import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Crea_Regola implements MenuCommand{

	ControlInserimento controllerInserimento= new ControlInserimento();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		controllerInserimento.inserimentoRegole(dati.getImmobile(), dati);
	}


}
