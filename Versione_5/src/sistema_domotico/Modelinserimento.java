package sistema_domotico;

import ambiente.UnitaImmobiliare;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import costanti.Costanti;
import liste.ListaCategorie;
import liste.ListaImmobili;

public class Modelinserimento {

	public Modelinserimento()
	{

	}
	
	/**
	 * Inserisci unita immobiliare.
	 * 
	 * @param: listaImmobili
	 * @pre: listaImmobili!=null
	 * @post: if(!listaImmobili.unitaImmobiliareGiaPresente(destinazione)) listaImmobili.size() = listaImmobili.size()@pre +1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaImmobili.size()-1)) ==> element(k)@pre == element(k)) 
	 * @return listaImmobili
	 */
	public boolean inserimentoUnitaImmobiliare(String destinazione, ListaImmobili listaImmobili)
	{
		if(!listaImmobili.unitaImmobiliareGiaPresente(destinazione))
		{
			UnitaImmobiliare immobile = new UnitaImmobiliare(destinazione);
			//immobile.setdestinazione(destinazione);
			listaImmobili.addImmobile(immobile);
			return true;
		}
		else
			return false;
	}
	
	public boolean inserimentoCategoria(String nome, String descrizione, Categoria categoria, ListaCategorie listaCategorie)
	{
		if(!listaCategorie.categoriaGiaPresente(nome) && descrizione.length()<Costanti.MAXCHAR)
		{
			categoria.setNomeCategoria(nome);
			categoria.setDescrizioneCategoria(descrizione);
			
			//INSERIMENTO CATEGORIA ATTUATORI
			if(categoria instanceof CategoriaAttuatori)
			{
					//metodo che prima chiede l'inserimento delle infoRilevabili e poi aggiunge l'oggetto categoria all'arrayLis
					//DA CAMBIARE
					listaCategorie.addCategoria(HelpMethod.inserisciModalitaOperative((CategoriaAttuatori)categoria));		
			}
			//INSERIMENTO CATEGORIA SENSORI
			else if (categoria instanceof CategoriaSensori)
			{ 
				//DA CAMBIARE
				String scelta = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
				if(scelta.equalsIgnoreCase(Costanti.SI))
				{
					categoria=HelpMethod.inserisciDominio((CategoriaSensori) categoria);
				}
					//metodo che prima chiede l'inserimento delle infoRilevabili e poi aggiunge l'oggetto categoria all'arrayList
					listaCategorie.addCategoria(HelpMethod.inserisciInfoRilevabili((CategoriaSensori)categoria));
			}
		}
		return false;
	}
	
	
	
	
}
