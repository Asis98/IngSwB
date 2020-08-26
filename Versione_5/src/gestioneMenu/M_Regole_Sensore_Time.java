package gestioneMenu;

import regole.Regola;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Regole_Sensore_Time implements MenuCommand{
	
	ControlInserimento controllerInserimento = new ControlInserimento();

	@Override
	public void esegui(Dati dati) {
		
		Regola regola = new Regola();
		
		regola = controllerInserimento.inserisciRegolaTime(regola, dati.getImmobile());
	}

}
