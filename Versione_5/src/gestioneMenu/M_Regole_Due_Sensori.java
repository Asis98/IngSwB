package gestioneMenu;

import regole.Regola;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Regole_Due_Sensori implements MenuCommand{
	
	ControlInserimento controllerInserimento = new ControlInserimento();

	Regola regola = new Regola();
	
	@Override
	public void esegui(Dati dati) {
		
		regola=controllerInserimento.inserisciRegolaSensori(regola, dati.getImmobile());
	}
}
