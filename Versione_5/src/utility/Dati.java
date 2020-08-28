package utility;

import java.util.Arrays;

import ambiente.Immobile;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import costanti.NomiFile;
import liste.ListaCategorie;
import liste.ListaImmobili;
import sistema_domotico.GestioneFile;

public class Dati {
	
	ListaCategorie listaCategorieAttuatori;
	ListaImmobili listaUnitaImmobiliari;
	Immobile immobile;
	ListaCategorie listaCategorieSensori;
	Categoria categoriaSensori;
	Categoria categoriaAttuatori;
	
	public Dati(Immobile immobile)
	{
		listaCategorieAttuatori = GestioneFile.istanziaDaFile(NomiFile.NOME_FILE_LISTA_CATEGORIE_ATTUATORI, ListaCategorie.class);
		listaUnitaImmobiliari = GestioneFile.istanziaDaFile(NomiFile.NOME_FILE_UNITA_IMMOBILIARE, ListaImmobili.class);
		listaCategorieSensori = GestioneFile.istanziaDaFile(NomiFile.NOME_FILE_LISTA_CATEGORIE_SENSORI, ListaCategorie.class);
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
	
	public void salva()
	{
		GestioneFile.salvaFile(NomiFile.PATH,Arrays.asList(
				listaCategorieAttuatori,
				listaCategorieSensori,
				listaUnitaImmobiliari));
	}
	
	

}
