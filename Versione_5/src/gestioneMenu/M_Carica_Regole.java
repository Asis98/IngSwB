package gestioneMenu;

import ambiente.Immobile;
import costanti.NomiFile;
import sistema_domotico.GestioneFile;
import utility.Dati;

public class M_Carica_Regole implements MenuCommand{

	@Override
	public void esegui(Dati dati) {
		
		Immobile immobile = dati.getImmobile();
		immobile = GestioneFile.caricaRegoleDaFile(immobile, NomiFile.REGOLE_XML);
		
	}

}
