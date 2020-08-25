package gestioneMenu;


import sistema_domotico.ControlInserimento;
import utenti.Fruitore;
import utility.Dati;

public class MenuImmobileFruitore implements MenuCommand {

	private Fruitore fruitore= new Fruitore();
	ControlInserimento	controlInserimento = new ControlInserimento();
	
	@Override
	public void esegui(Dati dati) {
		
		//ListaImmobili listaUnitaImmobiliari;
		//Immobile immobile;
		// TODO Auto-generated method stub
		
				controlInserimento.scegliImmobile(dati);
				fruitore.gestioneImmobileView();
				
		
	}
}
