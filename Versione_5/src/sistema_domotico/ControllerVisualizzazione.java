package sistema_domotico;

import ambiente.Artefatto;
import ambiente.Stanza;
import ambiente.UnitaImmobiliare;
import costanti.Costanti;
import costanti.Messaggi;
import liste.ListaCategorie;

public class ControllerVisualizzazione {
	
	private UIvisualizzazione view = new UIvisualizzazione();
	private ModelVisualizzazione model = new ModelVisualizzazione();
	

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE UNITA' IMMOBILIARI , STANZE E ARTEFATTI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	public void visualizzaUnitaImmobiliari(UnitaImmobiliare immobile)
	{
		view.stampaMessaggio(Messaggi.UNITA_IMMOBILIARE + immobile.getdestinazione());
		view.stampaMessaggio(Messaggi.LISTA_UNITA_DOMOTICHE);
		
		for(int i=0;i<immobile.size();i++)
		{
			if(model.visualizzazioneUnitaImmobiliari(immobile, i))
			{
				view.stampaMessaggio(immobile.getUnitaDomotica(i).getUnitName() + Costanti.TRATTINO + 
						(immobile.getUnitaDomotica(i) instanceof Stanza ? Costanti.TYPESTANZA : Costanti.TYPEARTEFATTO)+
						Messaggi.FA_PARTE_DI +((Artefatto)immobile.getUnitaDomotica(i)).getStanza());
			}
			else
			view.stampaMessaggio(immobile.getUnitaDomotica(i).getUnitName() + Costanti.TRATTINO + (immobile.getUnitaDomotica(i) instanceof Stanza ? Costanti.TYPESTANZA : Costanti.TYPEARTEFATTO));
		}
	}
	
	public void visualizzaCategorie(ListaCategorie listaCategorieAttuatori, ListaCategorie listaCategorieSensori)
	{
		view.stampaMessaggio(Costanti.STRINGA_VUOTA);
		
		//stampo attuatori
		view.stampaMessaggio(Messaggi.ELENCO_CATEGORIE_ATTUATORI);
		view.stampaMessaggio(listaCategorieAttuatori.stampaListaString());
		
		view.stampaMessaggio("");
		
		//stampo sensori
		view.stampaMessaggio(Messaggi.ELENCO_CATEGORIE_SENSORI);
		view.stampaMessaggio(listaCategorieSensori.stampaListaString());
		
	}

	public void visualizzaValoriSensori(UnitaImmobiliare immobile)
	{
		if(model.sizeValoriSensori(immobile))
		{
			view.stampaMessaggio(Costanti.ELENCO_SENSORI);
		    for(int i=0;i<immobile.sizeSensori();i++)
		    {
		    	view.stampaMessaggio(immobile.getSensore(i).getNomeUnita() + ": ");
		    	view.stampaMessaggio(immobile.getSensore(i).printListaMisurazioni());
		    }	
		}else view.stampaMessaggio(Messaggi.AVVISO_LISTA_SENSORI_VUOTA);
	}
	
	public void visualizzaListaUnitaRilevazione(UnitaImmobiliare immobile)
	{
		view.stampaMessaggio(Costanti.ELENCO_ATTUATORI);
		if(!model.isEmptyAttuatori(immobile))
			view.stampaMessaggio(immobile.stampaListaAttuatori());
		view.stampaMessaggio(Costanti.ELENCO_SENSORI);
		if(!model.isEmptySensori(immobile))
			view.stampaMessaggio(immobile.stampaListaSensori());
	}
	
	public void visualizzaAttuatoriModOp(UnitaImmobiliare immobile)
	{
		view.stampaMessaggio("Lista Attuatori");
		//raggruppo gli attuatori per stanza
		for(int i =0; i<immobile.size();i++)
		{
			view.stampaMessaggio(immobile.getUnitaDomotica(i).getUnitName().toUpperCase());
			
			for(int c=0; c<immobile.sizeAttuatori();c++)
			{
				if(immobile.getAttuatore(c).getUnitaDomotica().getUnitName().equalsIgnoreCase(immobile.getUnitaDomotica(i).getUnitName()))
				{
					view.stampaMessaggio("\t"+immobile.getAttuatore(c).getNomeUnita());
					view.stampaMessaggio("\t"+"\t"+Costanti.MODALITA_OPERATIVE);
					view.stampaMessaggio("\t"+"\t"+"\t"+immobile.getAttuatore(c).getModOperativa().printModNameParamValue());
				}
			}
			
			view.stampaMessaggio("\n");
		}
	}

}
