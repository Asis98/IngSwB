package gestioneMenu;

import liste.ListaImmobili;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Unita_Immobiliare implements MenuCommand {

	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		ListaImmobili listaUnitaImmobiliari = dati.getListaUnitaImmobiliari();
		dati.setListaUnitaImmobiliari(new ControlInserimento().inserisciUnitaImmobiliare(listaUnitaImmobiliari));
	}

}
