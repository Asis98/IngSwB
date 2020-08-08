package sistema_domotico;

import ambiente.Artefatto;
import ambiente.Stanza;
import ambiente.UnitaImmobiliare;
import categorie.ListaCategorie;
import costanti.Costanti;
import costanti.Messaggi;
import regole.Regola;
import regole.StatoRegola;

public class Visualizzazione {

	

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE UNITA' IMMOBILIARI , STANZE E ARTEFATTI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	

	/**
	 * Visualizza unita immobiliari.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: - 
	 */
	public static void visualizzaUnitaImmobiliari(UnitaImmobiliare immobile)
	{
		System.out.println(Messaggi.UNITA_IMMOBILIARE+ immobile.getdestinazione());
		
		System.out.println(Messaggi.LISTA_UNITA_DOMOTICHE);
		for(int i=0;i<immobile.size();i++)
		{
			if(immobile.getUnitaDomotica(i) instanceof Artefatto && ((Artefatto)immobile.getUnitaDomotica(i)).getStanza() != null )
			{
				System.out.println(immobile.getUnitaDomotica(i).getUnitName() + Costanti.TRATTINO + 
						(immobile.getUnitaDomotica(i) instanceof Stanza ? Costanti.TYPESTANZA : Costanti.TYPEARTEFATTO)+
						Messaggi.FA_PARTE_DI +((Artefatto)immobile.getUnitaDomotica(i)).getStanza());
			}
			else
			System.out.println(immobile.getUnitaDomotica(i).getUnitName() + Costanti.TRATTINO + (immobile.getUnitaDomotica(i) instanceof Stanza ? Costanti.TYPESTANZA : Costanti.TYPEARTEFATTO));
		}
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE CATEGORIE SENSORI E ATTUATORI CON LE RISPETTIVE UNITA' DI RILEVAZIONE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	
	/**
	 * Visualizza categorie.
	 *
	 * @param listaCategorieAttuatori 
	 * @param listaCategorieSensori 
	 * @pre: listaCategorieAttuatori!=null && listaCategorieSensori!=null
	 * @post: -
	 */
	public static void visualizzaCategorie(ListaCategorie listaCategorieAttuatori, ListaCategorie listaCategorieSensori)
	{
		System.out.println(Costanti.STRINGA_VUOTA);
		
		//stampo attuatori
		System.out.println(Messaggi.ELENCO_CATEGORIE_ATTUATORI);
		
		System.out.println(listaCategorieAttuatori.stampaListaString());
		
		
		System.out.println("");
		
		//stampo sensori
		System.out.println(Messaggi.ELENCO_CATEGORIE_SENSORI);
		System.out.println(listaCategorieSensori.stampaListaString());
		
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE VALORI RILEVATI SENSORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	
	/**
	 * Visualizza valori sensori.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: -
	 */
	public static void visualizzaValoriSensori(UnitaImmobiliare immobile)
	{
		if(immobile.sizeSensori()!=0)
		{
		    System.out.println(Costanti.ELENCO_SENSORI);
		    for(int i=0;i<immobile.sizeSensori();i++)
		    {
		    	System.out.println(immobile.getSensore(i).getNomeUnita() + ": ");
		    	System.out.println(immobile.getSensore(i).printListaMisurazioni());
		    }	
		}else System.out.println(Messaggi.AVVISO_LISTA_SENSORI_VUOTA);
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE LISTA SENSORI/ ATTUATORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	

	/**
	 * Visualizza lista unita rilevazione.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: -
	 */
	public static void visualizzaListaUnitaRilevazione(UnitaImmobiliare immobile)
	{
		System.out.println(Costanti.ELENCO_ATTUATORI);
		if(!immobile.isEmptyAttuatori())
			System.out.println(immobile.stampaListaAttuatori());
		System.out.println(Costanti.ELENCO_SENSORI);
		if(!immobile.isEmptySensori())
			System.out.println(immobile.stampaListaSensori());
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE ATTUATORI + MODALITA' OPERATIVA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Visualizza attuatori mod op.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: -
	 */
	public static void visualizzaAttuatoriModOp(UnitaImmobiliare immobile)
	{
		System.out.println("Lista Attuatori");
		//raggruppo gli attuatori per stanza
		for(int i =0; i<immobile.size();i++)
		{
			System.out.println(immobile.getUnitaDomotica(i).getUnitName().toUpperCase());
			
			for(int c=0; c<immobile.sizeAttuatori();c++)
			{
				if(immobile.getAttuatore(c).getUnitaDomotica().getUnitName().equalsIgnoreCase(immobile.getUnitaDomotica(i).getUnitName()))
				{
					System.out.println("\t"+immobile.getAttuatore(c).getNomeUnita());
					System.out.println("\t"+"\t"+Costanti.MODALITA_OPERATIVE);
					System.out.println("\t"+"\t"+"\t"+immobile.getAttuatore(c).getModOperativa().printModNameParamValue());
				}
			}
			
			System.out.println("\n");
		}
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE REGOLE Attive
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Visualizza regole.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: -
	 */
	public static void visualizzaRegoleAttive(UnitaImmobiliare immobile)
	{	
		int i=0;
		for(Regola elemento: immobile.getListaRegole())
		{
			if(elemento.getStato() == StatoRegola.ABILITATA)
			{
				System.out.println(i+Costanti.TRATTINO+elemento.stampaRegola());
				System.out.println(Costanti.RISULTATO+ elemento.valutaRegola());
				i++;
			}
		}
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO VISUALIZZAZIONE REGOLE Disattive
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Visualizza regole.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: -
	 */
	public static void visualizzaRegoleDisattive(UnitaImmobiliare immobile)
	{
		int i=0;
		for(Regola elemento: immobile.getListaRegole())
		{
			if(elemento.getStato() == StatoRegola.DISABILITATA)
			{
				System.out.println(i+Costanti.TRATTINO+elemento.stampaRegola());
				System.out.println(Costanti.RISULTATO+ elemento.valutaRegola());
				i++;
			}
		}
	}
	
}
