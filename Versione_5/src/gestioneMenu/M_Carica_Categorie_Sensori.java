package gestioneMenu;

import categorie.CategoriaSensori;
import costanti.NomiFile;
import liste.ListaCategorie;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Carica_Categorie_Sensori implements MenuCommand{

	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		ListaCategorie listaCategorieSensori = dati.getListaCategorieSensori();
		dati.setListaCategorieSensori(new ControlInserimento().inserisciCategorieDaFile(listaCategorieSensori, 
				CategoriaSensori.class, NomiFile.CATEGORIE_SENSORI_CSV));
	}


}
