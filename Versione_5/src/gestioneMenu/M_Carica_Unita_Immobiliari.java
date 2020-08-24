package gestioneMenu;

import costanti.NomiFile;
import liste.ListaImmobili;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Carica_Unita_Immobiliari implements MenuCommand{

	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		ListaImmobili listaUnitaImmobiliari = dati.getListaUnitaImmobiliari();
		dati.setListaUnitaImmobiliari(new ControlInserimento().caricaImmobiliDaFile(listaUnitaImmobiliari, 
				NomiFile.IMMOBILI_CSV, dati.getListaCategorieSensori().isEmpty(), dati.getListaCategorieAttuatori().isEmpty()));
	}


}
