package gestioneMenu;


import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Assegna_Modalita_Operativa implements MenuCommand{

	ControlInserimento controllerInserimento= new ControlInserimento();
	
	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		
		controllerInserimento.modificaModalitaOperativa(dati.getImmobile());

	}


}
