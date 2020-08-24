package utility;

import ambiente.Immobile;
import liste.ListaCategorie;
import liste.ListaImmobili;

public class Dati {
	
	ListaCategorie listaCategorieAttuatori;
	ListaImmobili listaUnitaImmobiliari;
	Immobile immobile;
	ListaCategorie listaCategorieSensori;
	
	public void setListaCategorieSensori(ListaCategorie listaCategorieSensori) {
		this.listaCategorieSensori = listaCategorieSensori;
	}
	public void setListaCategorieAttuatori(ListaCategorie listaCategorieAttuatori) {
		this.listaCategorieAttuatori = listaCategorieAttuatori;
	}
	public void setListaUnitaImmobiliari(ListaImmobili listaUnitaImmobiliari) {
		this.listaUnitaImmobiliari = listaUnitaImmobiliari;
	}
	public void setImmobile(Immobile immobile) {
		this.immobile = immobile;
	}

	public ListaCategorie getListaCategorieSensori() {
		return listaCategorieSensori;
	}
	public ListaCategorie getListaCategorieAttuatori() {
		return listaCategorieAttuatori;
	}
	public ListaImmobili getListaUnitaImmobiliari() {
		return listaUnitaImmobiliari;
	}
	public Immobile getImmobile() {
		return immobile;
	}
	
	

}
