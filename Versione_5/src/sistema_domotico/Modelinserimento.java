package sistema_domotico;

import ambiente.Immobile;
import ambiente.UnitaDomotica;
import ambiente.UnitaImmobiliare;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.InfoRilevabile;
import categorie.ModalitaOperativa;
import categorie.Parametro;
import costanti.Costanti;
import liste.ListaCategorie;
import liste.ListaImmobili;
import rilevazione.UnitaRilevazione;

public class Modelinserimento {

	public Modelinserimento()
	{

	}
	
	/**
	 * Inserisci unita immobiliare.
	 */
	public boolean inserimentoUnitaImmobiliare(String destinazione, ListaImmobili listaImmobili)
	{
		if(!listaImmobili.unitaImmobiliareGiaPresente(destinazione))
		{
			UnitaImmobiliare unitaImmobiliare = new UnitaImmobiliare(destinazione);
			//immobile.setdestinazione(destinazione);
			listaImmobili.addImmobile(new Immobile(unitaImmobiliare));
			return true;
		}
		else
			return false;
	}
	
	public boolean inserimentoCategoria(String nome, String descrizione, ListaCategorie listaCategorie, Categoria categoria)
	{
		if(!listaCategorie.categoriaGiaPresente(nome) && descrizione.length()<Costanti.MAXCHAR)
		{
			categoria.setNomeCategoria(nome);
			categoria.setDescrizioneCategoria(descrizione);
			return true;
		}
		return false;
	}
	
	public boolean verificaModOp(ModalitaOperativa modOp, CategoriaAttuatori categoria)
	{
		return categoria.modalitaGiaPresente(modOp.getNomeModOperativa());
	}
	
	public boolean verificaParametro(ModalitaOperativa modOp, String nome)
	{
		if(modOp.parametroGiaPresente(nome))
		{
			return true;
		}
		return false;
	}
	
	public Parametro ParametroCreator(String nomeParametro)
	{
		return new Parametro(nomeParametro);
	}
	
	public boolean verificaDominio(CategoriaSensori categoria, String elemento)
	{
		return categoria.dominioGiaPresente(elemento.trim());
	}
	
	public boolean verificaInfoRilevabile(CategoriaSensori categoria, InfoRilevabile infoRil)
	{
		return categoria.infoGiaPresente(infoRil.getTipoInfoRilevabile());
	}
	
	public InfoRilevabile setStatoInfoRilevabile(InfoRilevabile infoRil, String tipo)
	{
		if(tipo.equalsIgnoreCase(Costanti.NUMERICA))
			infoRil.setNonnumerica(false);
		else if (tipo.equalsIgnoreCase(Costanti.NONNUMERICA))
			infoRil.setNonnumerica(true);
		return infoRil;
	}
	
	public UnitaRilevazione settingUnitaRilevazione(UnitaDomotica unitaDomotica, Categoria categoria, String nomeUnita, UnitaRilevazione unita, int numero)
	{
		unita.setUnitaDomotica(unitaDomotica);
		unita.setCategoria(categoria);
		unita.setNomeUnita(nomeUnita
				+ numero+Costanti.TRATTINO_BASSO+categoria.getNomeCategoria());
		return unita;
	}
	
}
