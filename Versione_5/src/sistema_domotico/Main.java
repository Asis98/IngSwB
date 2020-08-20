package sistema_domotico;
import java.util.*;

import ambiente.*;
import categorie.*;
import costanti.Case;
import costanti.Costanti;
import costanti.Messaggi;
import costanti.NomiFile;
import costanti.TitoliMenu;
import costanti.VociMenu;
import inputUtente.DatiUtente;
import inputUtente.InputDati;
import liste.ListaCategorie;
import liste.ListaImmobili;
import rilevazione.*;
import time.Orologio;
import utenti.Fruitore;
import utenti.Manutentore;
import utenti.Utente;
import utility.MyMenu;


public class Main {
	
	//Dichiarazioni variabili delle classi sistema domotico
	private static Utente userManutentore= new Manutentore();
	private static Utente userFruitore= new Fruitore();
			
	private static ListaCategorie listaCategorieAttuatori;
	private static ListaCategorie listaCategorieSensori;
	private static UnitaImmobiliare immobile;
	private static ListaImmobili listaUnitaImmobiliari ;
	private static DatiUtente input;
	
	public static void main(String[] args) {
				
		//Dichiarazione variabili main
		String sceltaMenuDomotico = null;
		input = new DatiUtente(); 
		
		//Caricamenti da File
		listaCategorieAttuatori = GestioneFile.istanziaDaFile(NomiFile.NOME_FILE_LISTA_CATEGORIE_ATTUATORI, ListaCategorie.class);
		listaCategorieSensori = GestioneFile.istanziaDaFile(NomiFile.NOME_FILE_LISTA_CATEGORIE_SENSORI, ListaCategorie.class);
		//Da cambiare con Immobile
		listaUnitaImmobiliari = GestioneFile.istanziaDaFile(NomiFile.NOME_FILE_UNITA_IMMOBILIARE, ListaImmobili.class);
		
		
		System.out.println("\n" + "Orario: " + new Orologio().stampaOra());
		
		do {
			scelta_menu_utente();
			
			//menu manutentore
			if(userManutentore.getNomeUtente().equalsIgnoreCase(Costanti.UTENTE1))
			{	
				System.out.println("\n" + "Orario: " + new Orologio().stampaOra());
				scelta_menu_principale_manutentore();
				
			}
			
			//menu fruitore
			else if(userFruitore.getNomeUtente().equalsIgnoreCase(Costanti.UTENTE2))
			{
				scelta_menu_principale_fruitore();
				
			}
			sceltaMenuDomotico = input.leggiStringaNonVuota(Messaggi.SCELTAUSCITA);
			
		} while(!sceltaMenuDomotico.equalsIgnoreCase(Costanti.USCITA));
		
		//salvataggio su file
		GestioneFile.salvaFile(NomiFile.PATH,Arrays.asList(
													listaCategorieAttuatori,
													listaCategorieSensori,
													listaUnitaImmobiliari));
	}//chiusura main
	
	//metodi di gestione dei menù
	
	public static void scelta_menu_utente()
	{
		MyMenu menu= new MyMenu(TitoliMenu.TITOLO, VociMenu.VOCIUTENTE);
		int scelta;
		do 
		{
			scelta=menu.scegli();
			switch(scelta)
			{
				case Case.ESCI: break;
				case Case.MANUTENTORE:
						userManutentore.setNomeUtente(Costanti.UTENTE1);
						userFruitore.setNomeUtente(Costanti.NULL); //se non si istanzia sarà impostato come nome null
						scelta = 0; //per uscire dal menù
					break;
				case Case.FRUITORE: 
						userFruitore.setNomeUtente(Costanti.UTENTE2);
						userManutentore.setNomeUtente(Costanti.NULL); //se non si istanzia sarà impostato come nome null
						scelta=0; //per uscire dal menù
					break;
			}
		} while (scelta != Case.ESCI);
	}
	
	public static void gestioneImmobile_menu_manutentore()
	{
		int scelta;
		MyMenu menuImmobile = new MyMenu(TitoliMenu.TITOLOMENUIMMOBILE, VociMenu.VOCIIMMOBILE);
		do {
			System.out.println("\n" + "Orario: " + new Orologio().stampaOra());
			scelta = menuImmobile.scegli();
			switch(scelta)
			{
				case Case.ESCI: break;
				
				case Case.INSERISCI_UNITA_DOMOTICA:
					immobile=ControlInserimento.inserisciUnitaDomotica(immobile);
					break;
				case Case.INSERISCI_ATTUATORE: 
					immobile.setListaAttuatori(ControlInserimento.inserisciUnitaRilevazione(new Attuatore(),immobile.getListaAttuatori(),listaCategorieAttuatori,immobile));
					break;
				case Case.INSERISCI_SENSORE: 
					immobile.setListaSensori(ControlInserimento.inserisciUnitaRilevazione(new Sensore(),immobile.getListaSensori(),listaCategorieSensori,immobile));
					break;
				case Case.VISUALIZZA_UNITA_IMMOBILIARI: 
					Visualizzazione.visualizzaUnitaImmobiliari(immobile);
					break;	
				case Case.VISUALIZZA_CATEGORIE:
					Visualizzazione.visualizzaCategorie(listaCategorieAttuatori,listaCategorieSensori);
					break;
				case Case.STAMPA_SENSORI_ATTUATORI:
					Visualizzazione.visualizzaListaUnitaRilevazione(immobile);
					break;
				case Case.CARICA_REGOLE:
					immobile = GestioneFile.caricaRegoleDaFile(immobile, NomiFile.REGOLE_XML);
					break;
			}
	
		}while(scelta != Case.ESCI);
	}
	
	public static void scelta_menu_principale_manutentore()
	{
		int scelta;
		MyMenu menuManutentore=new MyMenu(TitoliMenu.TITOLOMANUTENTORE,VociMenu.VOCIMANUTENTORE);
		
		do
		{
			scelta = menuManutentore.scegli();
			switch(scelta)
			{
				case Case.ESCI: break;
				case Case.CARICA_CATEGORIE_SENSORI:
					listaCategorieSensori = ControlInserimento.inserisciCategorieDaFile(listaCategorieSensori, CategoriaSensori.class,NomiFile.CATEGORIE_SENSORI_CSV);
					break;
				case Case.CARICA_CATEGORIE_ATTUATORI:
					listaCategorieAttuatori = ControlInserimento.inserisciCategorieDaFile(listaCategorieAttuatori, CategoriaAttuatori.class,NomiFile.CATEGORIE_ATTUATORI_CSV);
					break;
				case Case.CARICA_UNITA_IMMOBILIARI:
					listaUnitaImmobiliari = ControlInserimento.caricaImmobiliDaFile(listaUnitaImmobiliari, NomiFile.IMMOBILI_CSV, listaCategorieSensori.isEmpty(), listaCategorieAttuatori.isEmpty());
					break;
				case Case.INSERISCI_UNITA_IMMOBILIARE: 
					listaUnitaImmobiliari= ControlInserimento.inserisciUnitaImmobiliare(listaUnitaImmobiliari);
					break;
				case Case.GESTISCI_IMMOBILE_COME_MANUTENTORE:
					if(listaUnitaImmobiliari.isEmpty())
					{
						System.out.println(Messaggi.LISTA_IMMOBILI_VUOTA);
					}
					else {
							System.out.println(listaUnitaImmobiliari.stampaListaUnitaImmobiliari());
							int numeroUnitaImmobiliare = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_IMMOBILE, Costanti.MIN, ((listaUnitaImmobiliari.size())-1));
							immobile = listaUnitaImmobiliari.getUnitaImmobiliare(numeroUnitaImmobiliare);
							
							gestioneImmobile_menu_manutentore();
					}
					break;
				case Case.STAMPA_UNITA_IMMOBILIARI:
					if(listaUnitaImmobiliari.isEmpty())
					{
						System.out.println(Messaggi.LISTA_IMMOBILI_VUOTA);
					}
					else {
					System.out.println(listaUnitaImmobiliari.stampaListaUnitaImmobiliari());
					}
					break;
				case Case.INSERISCI_CATEGORIA_ATTUATORE: 
					listaCategorieAttuatori= ControlInserimento.inserisciCategoria(new CategoriaAttuatori(),listaCategorieAttuatori);
					break;
				case Case.INSERISCI_CATEGORIA_SENSORE:
					listaCategorieSensori= ControlInserimento.inserisciCategoria(new CategoriaSensori(),listaCategorieSensori);
					break;
				case Case.STAMPA_CATEGORIE:
					Visualizzazione.visualizzaCategorie(listaCategorieAttuatori,listaCategorieSensori);
					break;
				
			}
			
		}while(scelta != Case.ESCI);
	}
	
	public static void gestioneImmobile_menu_fruitore()
	{
		int scelta;
		MyMenu sottoMenuFruitore=new MyMenu(TitoliMenu.TITOLOMENUIMMOBILE,VociMenu.VOCISOTTOMENUFRUITORE);
		do {
			scelta = sottoMenuFruitore.scegli();
			switch(scelta)
			{
				case Case.ESCI: break;
				
				case Case.VISUALIZZA_DESCRIZIONE_UNITA_IMMOBILIARE:
					Visualizzazione.visualizzaUnitaImmobiliari(immobile);
					break;
				case Case.VISUALIZZA_VALORI_SENSORI:
					Visualizzazione.visualizzaValoriSensori(immobile);
					break;
				case Case.ASSEGNA_MODALITA_OPERATIVA:
					immobile=ControlInserimento.modificaModalitaOperativa(immobile);
					break;
				case Case.VISUALIZZA_ATTUATORI_MODALITA:
					Visualizzazione.visualizzaAttuatoriModOp(immobile);
					break;
				case Case.CREA_REGOLA:
					immobile = ControlInserimento.inserisciRegola(immobile);
					break;
				case Case.VISUALIZZA_REGOLE_ATTIVE:
					Visualizzazione.visualizzaRegoleAttive(immobile);
					break;
				case Case.ATTIVA_DISATTIVA_REGOLA:
					immobile = AbleDisable.gestisciStatoRegole(immobile);
					break;
				case Case.ATTIVA_DISATTIVA_UNITA_RILEVAZIONE:
					immobile = AbleDisable.gestisciStatoUnitaRilevazione(immobile);
					break;
			}
		
		}while(scelta != Case.ESCI);
	}
	
	public static void scelta_menu_principale_fruitore()
	{
		int scelta;
		MyMenu menuFruitore = new MyMenu(TitoliMenu.TITOLOFRUITORE,VociMenu.VOCIMENUFRUITORE);
		do {
			System.out.println("\n" + "Orario: " + new Orologio().stampaOra());
			scelta = menuFruitore.scegli();
			switch(scelta)
			{
				case Case.ESCI: break;
				case Case.VISUALIZZA_LISTA_UNITA_IMMOBILIARI:
					System.out.println(listaUnitaImmobiliari.stampaListaUnitaImmobiliari());
					break;
				case Case.GESTISCI_IMMOBILE_COME_FRUITORE:
					if(listaUnitaImmobiliari.isEmpty())
					{
						System.out.println(Messaggi.LISTA_IMMOBILI_VUOTA);
					}
					else {
							System.out.println(listaUnitaImmobiliari.stampaListaUnitaImmobiliari());
							int numeroUnitaImmobiliare = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_IMMOBILE, Costanti.MIN, ((listaUnitaImmobiliari.size())-1));
							immobile = listaUnitaImmobiliari.getUnitaImmobiliare(numeroUnitaImmobiliare);
							
							gestioneImmobile_menu_fruitore();
					}
					break;
				case Case.VISUALIZZA_CATEGORIE_SENSORI_ATTUATORI:
					Visualizzazione.visualizzaCategorie(listaCategorieAttuatori,listaCategorieSensori);
					break;
			}
		}while(scelta!= Case.ESCI);
	
	}

}//chiusura classe
