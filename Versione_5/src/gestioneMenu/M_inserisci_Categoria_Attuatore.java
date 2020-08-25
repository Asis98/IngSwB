package gestioneMenu;

import categorie.CategoriaAttuatori;
import liste.ListaCategorie;
import sistema_domotico.ControlInserimento;
import utility.Dati;

public class M_inserisci_Categoria_Attuatore implements MenuCommand{

	@Override
	public void esegui(Dati dati) {
		ListaCategorie listaCategorieAttuatori = dati.getListaCategorieAttuatori();
		dati.setListaCategorieAttuatori(new ControlInserimento().inserisciCategoria(listaCategorieAttuatori, new CategoriaAttuatori()));
	}


}
