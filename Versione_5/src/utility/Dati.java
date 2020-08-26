package utility;

import ambiente.Immobile;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import liste.ListaCategorie;
import liste.ListaImmobili;

public class Dati {
	
	ListaCategorie listaCategorieAttuatori;
	ListaImmobili listaUnitaImmobiliari;
	Immobile immobile;
	ListaCategorie listaCategorieSensori;
	Categoria categoriaSensori;
	Categoria categoriaAttuatori;
	
	public Dati(Immobile immobile)
	{
		listaCategorieAttuatori = new ListaCategorie();
		listaUnitaImmobiliari = new ListaImmobili();
		listaCategorieSensori = new ListaCategorie();
		categoriaSensori = new CategoriaSensori();
		categoriaAttuatori = new CategoriaAttuatori();
		this.immobile = immobile;
	}

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
