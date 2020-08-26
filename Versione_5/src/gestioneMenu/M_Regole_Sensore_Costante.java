package gestioneMenu;

import costanti.Messaggi;
import regole.Regola;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Regole_Sensore_Costante implements MenuCommand{
	
	ControlInserimento controllerInserimento = new ControlInserimento();

	@Override
	public void esegui(Dati dati) {
		
		Regola regola = new Regola();
		
		if(dati.getImmobile().getListaSensori().size() == 1)
			System.out.println(Messaggi.OPERANDI_DELLA_LISTA_NON_SUFFICIENTI);
		else
		{
			regola=controllerInserimento.inserisciRegolaCostante(regola, dati.getImmobile());
		}
	}
}
