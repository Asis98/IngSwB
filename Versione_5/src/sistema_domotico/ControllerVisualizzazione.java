package sistema_domotico;

import ambiente.Artefatto;
import ambiente.Immobile;
import ambiente.Stanza;
import costanti.Costanti;
import costanti.Messaggi;
import liste.ListaCategorie;
import regole.Regola;
import rilevazione.Attuatore;
import rilevazione.Sensore;

public class ControllerVisualizzazione {
	
	private UserInterface view = new UserInterface();
	private ModelVisualizzazione model = new ModelVisualizzazione();
	

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE UNITA' IMMOBILIARI , STANZE E ARTEFATTI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	public void visualizzaUnitaImmobiliari(Immobile immobile)
	{
		view.stampaMessaggio(Messaggi.UNITA_IMMOBILIARE + immobile.getDestinazioneUnita());
		view.stampaMessaggio(Messaggi.LISTA_UNITA_DOMOTICHE);
		
		for(int i=0;i<immobile.getUnitList().size();i++)
		{
			if(model.visualizzazioneUnitaImmobiliari(immobile, i))
			{
				view.stampaMessaggio(immobile.getUnitList().getUnitaDomotica(i).getUnitName() + Costanti.TRATTINO + 
						(immobile.getUnitList().getUnitaDomotica(i) instanceof Stanza ? Costanti.TYPESTANZA : Costanti.TYPEARTEFATTO)+
						Messaggi.FA_PARTE_DI +((Artefatto)immobile.getUnitList().getUnitaDomotica(i)).getStanza());
			}
			else
			view.stampaMessaggio(immobile.getUnitList().getUnitaDomotica(i).getUnitName() + Costanti.TRATTINO + (immobile.getUnitList().getUnitaDomotica(i) instanceof Stanza ? Costanti.TYPESTANZA : Costanti.TYPEARTEFATTO));
		}
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE CATEGORIE SENSORI E ATTUATORI CON LE RISPETTIVE UNITA' DI RILEVAZIONE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
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

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE VALORI RILEVATI SENSORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public void visualizzaValoriSensori(Immobile immobile)
	{
		if(model.sizeValoriSensori(immobile))
		{
			view.stampaMessaggio(Costanti.ELENCO_SENSORI);
		    for(int i=0;i<immobile.getListaSensori().size();i++)
		    {
		    	view.stampaMessaggio(immobile.getListaSensori().getElemento(i).getNomeUnita() + ": ");
		    	view.stampaMessaggio(((Sensore)immobile.getListaSensori().getElemento(i)).printListaMisurazioni());
		    }	
		}else view.stampaMessaggio(Messaggi.AVVISO_LISTA_SENSORI_VUOTA);
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE LISTA SENSORI/ ATTUATORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public void visualizzaListaUnitaRilevazione(Immobile immobile)
	{
		view.stampaMessaggio(Costanti.ELENCO_ATTUATORI);
		if(!model.isEmptyAttuatori(immobile))
			view.stampaMessaggio(immobile.getListaAttuatori().stampaListaUnitaRilevazione());
		view.stampaMessaggio(Costanti.ELENCO_SENSORI);
		if(!model.isEmptySensori(immobile))
			view.stampaMessaggio(immobile.getListaSensori().stampaListaUnitaRilevazione());
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE ATTUATORI + MODALITA' OPERATIVA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public void visualizzaAttuatoriModOp(Immobile immobile)
	{
		view.stampaMessaggio("Lista Attuatori");
		//raggruppo gli attuatori per stanza
		for(int i =0; i<immobile.getUnitList().size();i++)
		{
			view.stampaMessaggio(immobile.getUnitList().getUnitaDomotica(i).getUnitName().toUpperCase());
			
			for(int c=0; c<immobile.getListaAttuatori().size();c++)
			{
				if(immobile.getListaAttuatori().getElemento(c).getUnitaDomotica().getUnitName().equalsIgnoreCase(immobile.getUnitList().getUnitaDomotica(i).getUnitName()))
				{
					view.stampaMessaggio("\t"+immobile.getListaAttuatori().getElemento(c).getNomeUnita());
					view.stampaMessaggio("\t"+"\t"+Costanti.MODALITA_OPERATIVE);
					view.stampaMessaggio("\t"+"\t"+"\t"+((Attuatore)immobile.getListaAttuatori().getElemento(c)).getModOperativa().printModNameParamValue());
				}
			}
			
			view.stampaMessaggio("\n");
		}
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE REGOLE ATTIVE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	public void visualizzaRegoleAttive(Immobile immobile)
	{	
		int i=0;
		for(Regola elemento: immobile.getListaRegole())
		{
			if(model.controllaRegolaAttiva(elemento))
			{
				view.stampaMessaggio(i+Costanti.TRATTINO + elemento.stampaRegola());
				view.stampaMessaggio(Costanti.RISULTATO + elemento.valutaRegola());
				i++;
			}
		}
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE REGOLE Disattive
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public void visualizzaRegoleDisattive(Immobile immobile)
	{
		int i=0;
		for(Regola elemento: immobile.getListaRegole())
		{
			if(model.controllaRegolaDisattiva(elemento))
			{
				view.stampaMessaggio(i+Costanti.TRATTINO+elemento.stampaRegola());
				view.stampaMessaggio(Costanti.RISULTATO+ elemento.valutaRegola());
				i++;
			}
		}
	}
	

}
