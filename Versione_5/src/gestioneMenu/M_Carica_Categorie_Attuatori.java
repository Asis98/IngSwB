package gestioneMenu;

import categorie.CategoriaAttuatori;
import costanti.NomiFile;
import liste.ListaCategorie;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Carica_Categorie_Attuatori implements MenuCommand{

	@Override
	public void esegui(Dati dati) {
		// TODO Auto-generated method stub
		ListaCategorie listaCategorieAttuatori = dati.getListaCategorieAttuatori();
		dati.setListaCategorieAttuatori(new ControlInserimento().inserisciCategorieDaFile(listaCategorieAttuatori, 
				CategoriaAttuatori.class,NomiFile.CATEGORIE_ATTUATORI_CSV));
		
	}

}
