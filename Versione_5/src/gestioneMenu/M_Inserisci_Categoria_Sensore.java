package gestioneMenu;

import categorie.CategoriaAttuatori;
import liste.ListaCategorie;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_Inserisci_Categoria_Sensore implements MenuCommand{

	@Override
	public void esegui(Dati dati) {
		ListaCategorie listaCategorieAttuatori = dati.getListaCategorieAttuatori();
		dati.setListaCategorieAttuatori(new ControlInserimento().inserisciCategoria(listaCategorieAttuatori, new CategoriaAttuatori()));
	}


}
