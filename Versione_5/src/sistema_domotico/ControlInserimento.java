package sistema_domotico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ambiente.Immobile;
import ambiente.Stanza;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.InfoRilevabile;
import categorie.ModalitaOperativa;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;
import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MV_Manutentore;
import gestioneMenu.MV_Regole;
import gestioneMenu.MV_StanzaArtefatto;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import inputUtente.InputDati;
import liste.ListaCategorie;
import liste.ListaImmobili;
import liste.ListaRegole;
import liste.ListaUnitaDomotiche;
import liste.ListaUnitaRilevazione;
import operazioni.BooleanOperator;
import regole.AntecedenteSensore;
import regole.Regola;
import regole.StatoRegola;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import rilevazione.UnitaRilevazione;
import time.Orologio;
import time.Time;
import utility.Dati;
import utility.MyMenu;

public class ControlInserimento {
	
	private UserInterface view = new UserInterface();
	private Modelinserimento model = new Modelinserimento();
	//METODI DEL MENU' DEL MANUTENTORE
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO UNITA' IMMOBILIARI E UNITA' DOMOTICHE 
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	
	public ListaImmobili inserisciUnitaImmobiliare(ListaImmobili listaImmobili)
	{
		//qui non va il controllo se è unico o no perchè tanto richiamando questo metodo setto solo una variabile e non istanzio nessun nuovo oggetto
		
		String destinazione = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_IMMOBILIARE);
		if(model.inserimentoUnitaImmobiliare(destinazione, listaImmobili))
			view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT);
		else
			view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
		
		return listaImmobili;
	}
	
	//FARE INSERIMENTO UNITA' DOMOTICHE
	
	public Immobile inserimentoUnitaDomotica(Immobile immobile, Dati dati)
	{
		MyMenu menuManutentore= new MyMenu(TitoliMenu.TITOLOUNITADOMOTICA,VociMenu.VOCIUNITADOMOTICA);
		view.stampaMessaggio(menuManutentore.stampaMenu());
		int scelta;
		do {
			scelta = view.inputLeggiIntero(Messaggi.SCEGLI_VOCE);
			MenuCommand targetOperation = MV_StanzaArtefatto
			      .getOperation(scelta)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
			targetOperation.esegui(dati);
		}while(scelta !=0);
		
		return immobile;
	}
	
	public void inserisciStanza(Immobile immobile)
	{
		ListaUnitaDomotiche unitList = immobile.getUnitList();
		if(immobile.getUnitList().isEmpty())
			view.stampaMessaggio(Messaggi.IMMOBILE_NON_ESISTENTE);
		else
		{
			//inserimentoStanza
			String nomeStanza = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_DOMOTICA);
			unitList = model.inserisciStanza(unitList, nomeStanza);
			if(!model.verificaPresenzaUnitaD(unitList, nomeStanza))
				view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT );
			else
				view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
		}
		immobile.setUnitList(unitList);
	}
	
	public void inserisciArtefatto(Immobile immobile)
	{
		if(immobile.getUnitList().isEmpty())
			view.stampaMessaggio(Messaggi.IMMOBILE_NON_ESISTENTE);
		else
		{
			//inserimentoStanza
			ListaUnitaDomotiche unitList = immobile.getUnitList();
			String nomeArtefatto = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_DOMOTICA);
			//unitList = model.inserisciStanza(unitList, nomeStanza);
			
			
			if(!model.verificaPresenzaUnitaD(unitList, nomeArtefatto))
			{
				String insStanza;
				String nomeStanza = Costanti.STRINGA_VUOTA;
				do
				{
					insStanza=view.InputStringaNonVuota(Messaggi.MESSAGGIO_ASSOC_STANZA_ARTEFATTO);
					if(!insStanza.equalsIgnoreCase(Costanti.SI) && !insStanza.equalsIgnoreCase(Costanti.NO))
						view.stampaMessaggio(Messaggi.STRINGA_NON_VALIDA);
				}while(!insStanza.equalsIgnoreCase(Costanti.SI) && !insStanza.equalsIgnoreCase(Costanti.NO));
				
				
				if(insStanza.equalsIgnoreCase(Costanti.SI))
				{
					boolean presente = false;
					do {
							for(int i = 0; i<unitList.size();i++)
							{
								
								if(unitList.getUnitaDomotica(i) instanceof Stanza)
									view.stampaMessaggio(Messaggi.NOME_STANZA + unitList.getUnitaDomotica(i).getUnitName());
							}
							nomeStanza=view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_STANZA);
							presente=model.verificaPresenzaUnitaD(unitList, nomeStanza);
							if (!presente)	
								view.stampaMessaggio(Messaggi.STANZA_NON_ESISTENTE);
					}while(!presente);
					
				}
				
				unitList = model.inserimentoArtefatto(unitList,nomeArtefatto, nomeStanza);
				immobile.setUnitList(unitList);
				view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT );
			}
			else
				view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
		}
	}
	
/*-------------------------------------------------------------------------------------------------------------------------------------------------
 * METODI INSERIMENTO CATEGORIE ATTUATORI E SENSORI 
 * ------------------------------------------------------------------------------------------------------------------------------------------------
 */
		
	public ListaCategorie inserisciCategoria(ListaCategorie listaCategorie, Categoria categoria)
	{	
		String nomeCategoria = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_CATEGORIA);
		
		if(model.inserimentoCategoria(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_CATEGORIA), 
				view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DESCRIZIONE_CATEGORIA), listaCategorie, categoria))
		{
			
			if(categoria instanceof CategoriaAttuatori)
			{
					//metodo che prima chiede l'inserimento delle infoRilevabili e poi aggiunge l'oggetto categoria all'arrayList
					listaCategorie.addCategoria(helpInserisciModalitaOperative((CategoriaAttuatori) categoria));
					
			}
			//INSERIMENTO CATEGORIA SENSORI
			else if (categoria instanceof CategoriaSensori)
			{ 
				String scelta = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
				if(scelta.equalsIgnoreCase(Costanti.SI))
				{
					categoria=inserisciDominio((CategoriaSensori) categoria);
				}
					//metodo che prima chiede l'inserimento delle infoRilevabili e poi aggiunge l'oggetto categoria all'arrayList
					listaCategorie.addCategoria(inserisciInfoRilevabili((CategoriaSensori)categoria));
			}
			
			view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT);
		}
			
		else
			view.stampaMessaggio(Costanti.CATEGORIA + Costanti.SPAZIO + (categoria instanceof CategoriaAttuatori ? Costanti.ATTUATORE : Costanti.SENSORE) + Messaggi.GIA_PRESENTE);
		
		return listaCategorie;
	}
	
	public CategoriaAttuatori helpInserisciModalitaOperative(CategoriaAttuatori categoria)
	{
		categoria.addModalitaOperativa(new ModalitaOperativa(Costanti.IDLE));
		view.stampaMessaggio(Messaggi.MESSAGGIO_INSERIMENTO__MODALITA_OPERATIVE);
		String risposta;
		do
		{
			ModalitaOperativa modOp = new ModalitaOperativa(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_MODALITA_OPERATIVA), view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DESCRIZIONE_MODALITA_OPERATIVA));
			if(!model.verificaModOp(modOp, categoria))
			{
				String scelta= view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_PARAMETRO);
				if(scelta.equalsIgnoreCase(Costanti.SI))
				{
					do {
							String nomeParametro = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_PARAMETRO);
							if(!model.verificaParametro(modOp, nomeParametro))
							{
								
								modOp.addParametro(model.ParametroCreator(nomeParametro));
								view.stampaMessaggio(modOp.stampaListaParametri());
							}
							else
								view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
						
					}while(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRO_PARAMETRO).equalsIgnoreCase(Costanti.SI));
					
				}
				categoria.addModalitaOperativa(modOp);
			}
			else
				view.stampaMessaggio(Messaggi.MODALITA_OPERATIVA_GIA_PRESENTE);
			
			do
			{
				risposta=view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRE_MOD_OPERATIVE);
				if(!risposta.equalsIgnoreCase(Costanti.SI) && !risposta.equalsIgnoreCase(Costanti.NO))
					view.stampaMessaggio(Messaggi.STRINGA_NON_VALIDA);
			}while(!risposta.equalsIgnoreCase(Costanti.SI) && !risposta.equalsIgnoreCase(Costanti.NO));
		
		}while(risposta.equalsIgnoreCase(Costanti.SI));
		
		return categoria;
	}
	
	public CategoriaSensori inserisciDominio(CategoriaSensori categoria)
	{
		do
		{
			String elemento = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_VALORE_DOMINIO);
			if(model.verificaDominio(categoria, elemento))
				view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
			else
			{
				view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT);
				categoria.addDominio(elemento.trim());
			
			}
			
		}while(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRO_DOMINIO).equalsIgnoreCase(Costanti.SI));
		return categoria;
	}
	
	public CategoriaSensori inserisciInfoRilevabili(CategoriaSensori categoria)
	{
		view.stampaMessaggio(Messaggi.MESSAGGIO_INSERIMENTO_INFO_RILEVABILI);
		String risposta;
		do
		{
			//Chiedere se vuole inseire una infoRilevabile numerica o non
			InfoRilevabile infoRil = new InfoRilevabile(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_INFO_RILEVABILE));
			//anche le info rilevabili devono essere univoche per cui ho aggiunto questo controllo
			if(!model.verificaInfoRilevabile(categoria, infoRil))
			{
				String sceltaTipo;
				do
				{	
					sceltaTipo = (view.InputStringaNonVuota(Messaggi.MESSAGGIO_TIPO_INFO_RILEVABILE)).trim();
					model.setStatoInfoRilevabile(infoRil,sceltaTipo);
				}while(!sceltaTipo.equalsIgnoreCase(Costanti.NUMERICA) && !sceltaTipo.equalsIgnoreCase(Costanti.NONNUMERICA));
				
				if(infoRil.isNonnumerica() && categoria.dominioIsEmpty())
					view.stampaMessaggio(Messaggi.INSERIMENTO_NON_VALIDO);
				else
					categoria.addInfoRilevabili(infoRil);
				
			}
			else
				view.stampaMessaggio(Messaggi.INFO_RILEVABILE_GIA_ESISTENTE);
			
			risposta=view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRE_INFO_RILEVABILI);
			
		}while(risposta.equalsIgnoreCase(Costanti.SI));
		
		return categoria;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO UNITA' RILEVAZIONE (SENSORI / ATTUATORI)
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	public ListaUnitaRilevazione inserisciUnitaRilevazione(UnitaRilevazione unita, ListaCategorie listaCategorie, Immobile immobile)
	{	
		/*
		 * se la lista delle categorie e la lista delle unità domotiche sono vuote non ha senso inserire un attuatore
		 * perchè un attuatore va associato ad una categoria e ad una stanza o artefatto
		 */
		ListaUnitaRilevazione listaUnitaRilevazione = new ListaUnitaRilevazione();
		if(unita instanceof Attuatore)
			listaUnitaRilevazione = immobile.getListaAttuatori();
		else if(unita instanceof Sensore)
			listaUnitaRilevazione = immobile.getListaSensori();
		
		if(listaCategorie.size()!=0 && !immobile.getUnitList().isEmpty())
		{
			ListaUnitaDomotiche unitList = immobile.getUnitList();
			//setto l'unità domotica
			view.stampaMessaggio(Messaggi.ELENCO_UNITA_DOMOTICHE);
			view.stampaMessaggio(unitList.stampaLista());
			
			int numeroUnitaDomotica = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_UNI_DOMOTICA, Costanti.MIN, ((unitList.size())-1));
			
			int numeroCategoria;
			
			view.stampaMessaggio(Messaggi.ELENCO_CATEGORIE);
			int numeroCategorieDisponibili = 0;
			String [] stampaCategorie = listaCategorie.stampaLista();
			for(int i=0;i<stampaCategorie.length;i++)
			{	
				if(!listaUnitaRilevazione.isEmpty())
				{
					boolean esiste= false;
					for(int c=0; c<listaUnitaRilevazione.size() && !esiste;c++)
					{
						if(stampaCategorie[i].equalsIgnoreCase(listaUnitaRilevazione.nomeCategoria(c)) && listaUnitaRilevazione.nomeUnitaDomotica(c).equalsIgnoreCase(unitList.getUnitaDomotica(numeroUnitaDomotica).getUnitName()))
									esiste=true;
					}
					if(!esiste) 
					{
						view.stampaMessaggio(i+ Costanti.TRATTINO +stampaCategorie[i]);
						numeroCategorieDisponibili++;
					}
				}
				else {
					view.stampaMessaggio(i+ Costanti.TRATTINO +stampaCategorie[i]);
					numeroCategorieDisponibili++;
				}
			}
					
			if(numeroCategorieDisponibili>0)
			{
				numeroCategoria = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_CATEGORIA, Costanti.MIN, listaCategorie.size());	
				//inserimento del nome dell'unità di rilevazione, fare try catch per il nome
				String nomeUnita = null;
				boolean eccezione = false;
				do {
					
					try {
						
						unita = model.settingUnitaRilevazione(unitList.getUnitaDomotica(numeroUnitaDomotica),
								listaCategorie.getElemento(numeroCategoria), 
								view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_RILEVAZIONE), unita, listaUnitaRilevazione.size());
						eccezione = false;
					}
					catch (Exception e)
					{
						view.stampaMessaggio(Messaggi.NOME_NON_VALIDO_INSERIRE_UN_ALTRO_NOME);
						eccezione=true;
					}
				}while(eccezione);
			
				if(unita instanceof Attuatore)
				{
					view.stampaMessaggio(Messaggi.NOME_ATTUATORE + unita.getNomeUnita());
					listaUnitaRilevazione.add(scegliModalitaOperativa((Attuatore)unita, (CategoriaAttuatori)listaCategorie.getElemento(numeroCategoria)));
					immobile.setListaAttuatori(listaUnitaRilevazione);
					
				}
				else if (unita instanceof Sensore)
				{
					view.stampaMessaggio(Messaggi.NOME_SENSORE + unita.getNomeUnita());
					//richiamare metodo scegli infoRilevabili
					unita = scegliInfoRilevabile(((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)), (Sensore) unita);
					listaUnitaRilevazione.add(unita);
					immobile.setListaSensori(listaUnitaRilevazione);
				}
			}
			else
				view.stampaMessaggio(Messaggi.CATEGORIE_GIA_TUTTE_ASSEGNATE+ unitList.getNomeUnitaDomotica(numeroUnitaDomotica));
		}
		else
			view.stampaMessaggio(Messaggi.INSERIMENTO_NON_VALIDO + 
			(immobile.getUnitList().isEmpty()? (Messaggi.LISTA_UNITA_DOMOTICHE + Messaggi.AVVISO_LISTA_UN_DOMOTICHE_VUOTA) : (Messaggi.LISTA_CATEGORIE + Messaggi.AVVISO_LISTA_CATEGORIE_VUOTA)));
		
		return listaUnitaRilevazione;
	}
	
	public Attuatore scegliModalitaOperativa(Attuatore attuatore, CategoriaAttuatori categoria)
	{
		//setto la modalità operativa
		view.stampaMessaggio(Costanti.LISTA_MOD_OPERATIVE);
		view.stampaMessaggio(categoria.stampaModalitaOperative());
		int numeroModalitaOperativa = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_MOD_OPERATIVA,Costanti.MIN, categoria.size()-1);
		if(!categoria.getModalitaOperative(numeroModalitaOperativa).parametriIsEmpty())
		{
			for(int i=0;i<categoria.getModalitaOperative(numeroModalitaOperativa).size();i++)
			{
				view.stampaMessaggio(Costanti.PARAMETRO+ categoria.getNomeParametroModOp(numeroModalitaOperativa, i));
				Double valore = view.inputDouble(Messaggi.MESSAGGIO_INSERIMENTO_VALORE_PARAMETRO);
				categoria = model.setValoreParametro(categoria, numeroModalitaOperativa, i, valore);
				
			}
		}
		
		attuatore = model.setModalitaOperativa(categoria, numeroModalitaOperativa, attuatore);
		return attuatore;
	}
	
	public Sensore scegliInfoRilevabile(CategoriaSensori categoria, Sensore unita)
	{
		
		String risposta;
		do 
		{
				System.out.println(categoria.stampaInfoRilevabili());
		
		
				int numeroInfoRilevabile = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_INFO_RILEVABILI, Costanti.MIN, 
															categoria.size());
				
				//verificare che le infoRilevabile che non sia già stata scelta
				
				if(!model.presenzaInfoRil(unita, categoria.getInfoRilevabile(numeroInfoRilevabile)))
				{
					//assegnazione casuale di un valore
					InfoRilevabile info =new InfoRilevabile(categoria.getInfoRilevabile(numeroInfoRilevabile).getTipoInfoRilevabile());
					
					if(!categoria.getInfoRilevabile(numeroInfoRilevabile).isNonnumerica())
					{
						//metodo che assegna un valore numerico casuale
						unita = model.valoreNumericoCasuale(unita, info);
						
					}
					else if (categoria.getInfoRilevabile(numeroInfoRilevabile).isNonnumerica())
					{
						//metodo che assegna un valore non numerico in automatico
						//infoRile non numerica, stampo valori del dominio						
						unita= model.valoreNonNumericoCasuale(unita, categoria, info);
					}
					
				}
				else
				{
					view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
				}

				risposta = view.InputStringaNonVuota(Messaggi.ASSEGNARE_UN_ALTRA_INFORMAZIONE_RILEVABILE);
				
		}while(risposta.equalsIgnoreCase(Costanti.SI));
		
		return unita;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * ASSEGNAMENTO MODALITA' OPERATIVA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public void modificaModalitaOperativa(Immobile immobile)
	{
		ListaUnitaRilevazione listaAttuatori = immobile.getListaAttuatori();
		if(listaAttuatori.isEmpty())
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA);
		else
		{
			view.stampaMessaggio(listaAttuatori.stampaListaUnitaAttive());
			int numeroAttuatore = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_ATTUATORE, Costanti.MIN, listaAttuatori.size()-1);
			
			ArrayList<ModalitaOperativa> modOp = ((CategoriaAttuatori)listaAttuatori.getElemento(numeroAttuatore).getCategoria()).getListaModalitaOperative();
			
			if(modOp != null)
			{
				for(int i =0; i<modOp.size();i++)
					view.stampaMessaggio(i+Costanti.TRATTINO+modOp.get(i).getNomeModOperativa());
				int numeroModOp = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_MOD_OPERATIVA, Costanti.MIN, modOp.size()-1);
				
				if(modOp.get(numeroModOp).parametriIsEmpty())
					((Attuatore)listaAttuatori.getElemento(numeroAttuatore)).setModOperativa(modOp.get(numeroModOp));
				else
				{
					for(int c=0; c<modOp.get(numeroModOp).size();c++)
					{
						Double valore= view.inputDouble((Messaggi.MESSAGGIO_INSERIMENTO_VALORE_PARAMETRO + Costanti.SPAZIO + modOp.get(numeroModOp).getNomeParametro(c)));
						modOp.get(numeroModOp).setParametro(modOp.get(numeroModOp).getNomeParametro(c), valore);
					}
				}
			}
			immobile.setListaAttuatori(listaAttuatori);	
		}
			
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO INSERIMENTO REGOLA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	
	public void inserimentoRegole(Immobile immobile, Dati dati)
	{
		if(immobile.getListaSensori().isEmpty() || immobile.getListaAttuatori().isEmpty())
		{
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA + Messaggi.LISTA_SENS_ATT_VUOTE);
		}
		else
		{	
			int scelta ;
			MyMenu menuRegole= new MyMenu(TitoliMenu.TITOLOREGOLE, VociMenu.VOCIREGOLE);
			Regola regola = new Regola();
			menuRegole.stampaMenu();
			do {
				scelta = view.inputLeggiIntero(Messaggi.SCEGLI_VOCE);
				MenuCommand targetOperation = MV_Regole
				      .getOperation(scelta)
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
				targetOperation.esegui(dati);
			}while(scelta !=0);
			
			
			//DA FARE ANCORA
			if(!regola.isEmptyAntecedente())
			{
				regola = inserisciConseguente(immobile, regola);
				regola = model.valutaRegola(regola);
				
				ListaRegole listaR= immobile.getListaRegole();
				listaR.setRegola(regola);
				immobile.setListaRegole(listaR);
				
			}

		}
	}
	
	public Regola inserisciConseguente(Immobile immobile, Regola regola)
	{
		do {	
				//scelgo l'attuatore
				ListaUnitaRilevazione listaAttuatori = immobile.getListaAttuatori();
				System.out.println(listaAttuatori.stampaListaUnitaAttive());
				int	numeroAttuatore = view.inputInteriConMinimo(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, listaAttuatori.size()-1);
				
				
				
				//stampo l'attuale mod operativa dell'attuatore scelto
				view.stampaMessaggio(Messaggi.MODALITA_OPERATIVA_ATTUALE + ((Attuatore) listaAttuatori.getElemento(numeroAttuatore)).getModOperativa().getNomeModOperativa());
				if(!((Attuatore) listaAttuatori.getElemento(numeroAttuatore)).getModOperativa().parametriIsEmpty())
					view.stampaMessaggio(((Attuatore) listaAttuatori.getElemento(numeroAttuatore)).getModOperativa().printModNameParamValue());
				
				//scelgo nuova modalità operativa e se è parametrica imposto i parametri
				view.stampaMessaggio(((Attuatore) listaAttuatori.getElemento(numeroAttuatore)).getCategoria().stampaModalitaOperative());
				int numeroModalitaOperativa = view.inputInteriConMinimo(Messaggi.SCEGLI_UNA_NUOVA_MODALITA_OPERATIVA, Costanti.MIN, ((Attuatore) listaAttuatori.getElemento(numeroAttuatore)).getCategoria().size()-1);
				
				ModalitaOperativa modOp= ((CategoriaAttuatori) listaAttuatori.getElemento(numeroAttuatore).getCategoria()).getModalitaOperative(numeroModalitaOperativa);
				if(!modOp.parametriIsEmpty())
				{
					for(int i =0;i<modOp.size(); i++)
					{
						view.stampaMessaggio(modOp.getParametro(i).getNomeParametro());
						Double valore = view.inputDouble(Messaggi.INSERIRE_IL_NUOVO_VALORE_DEL_PARAMETRO);
						modOp = model.setValoreParametro(modOp, numeroModalitaOperativa, valore);
					}
				}
				
				Attuatore attuatore = model.setAttuatore((Attuatore) listaAttuatori.getElemento(numeroAttuatore), modOp);
				
				//setto il conseguente
				if(!regola.cercaAttuatore(attuatore))
				{	
					//se l'utente vuole assegno a start un'orario
					if(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ORARIO_INIZIO).equalsIgnoreCase(Costanti.SI))
						regola.setConseguente(attuatore, modOp, scegliOra());
					else
						regola.setConseguente(attuatore, modOp); 
				}
				else
					System.out.println(Messaggi.GIA_PRESENTE);
				
		}while(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRO_CONSEGUENTE).equalsIgnoreCase(Costanti.SI));

		return regola;		
	}
	
	public Regola inserisciRegolaSensori(Regola regola, Immobile immobile)
	{
		Sensore operando_a = new Sensore();
		operando_a = scegliSensore(immobile);
		String operatoreRelazionale = null;
		
		if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
		{
			operatoreRelazionale = Costanti.UGUALE;
		}
		else
			operatoreRelazionale = scegliOperatore();
			
		Sensore operando_b= new Sensore();
		operando_b=scegliSensore(immobile);
		
		if(operando_a.getInfoRilevabile(0).getValoreAttuale().getValore().getClass() == String.class && 
				operando_b.getInfoRilevabile(0).getValoreAttuale().getValore().getClass() != String.class)
		{
			view.stampaMessaggio(Costanti.OPERANDI_INVALIDI);
		}
		else if(new AntecedenteSensore(operando_a,operatoreRelazionale,operando_b).confrontaElementiAntecedente())
		{
			view.stampaMessaggio(Messaggi.INSERIMENTO_NON_VALIDO);
		}
		else
			regola.setAntecedente(operando_a,operatoreRelazionale,operando_b);
		
		return regola;
	}
	
	public Regola inserisciRegolaCostante(Regola regola, Immobile immobile)
	{
		//caso in cui operandoB = Costante
		Sensore operando_a = new Sensore();
		operando_a = scegliSensore(immobile);
		
		if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
			regola.setAntecedente(operando_a, Costanti.UGUALE, scegliCostanteStringa(operando_a));
		else
			regola.setAntecedente(operando_a, scegliOperatore(),scegliCostante());
		
		return regola;
	}
	
	public Regola inserisciRegolaTime(Regola regola, Immobile immobile)
	{
		view.stampaMessaggio(Messaggi.VALORE_DEL_PRIMO_ORARIO + new Orologio().toString());
		regola.setAntecedente(scegliOperatore(), scegliOra());
		return regola;
	}
	
	public Sensore scegliSensore(Immobile immobile)
	{
		ListaUnitaRilevazione listaSensori = immobile.getListaSensori();
		view.stampaMessaggio(listaSensori.stampaListaUnitaAttive());
		
		//setto il primo operando della regola
		int	numeroSensore = view.inputInteriConMinimo(Messaggi.MESSAGGIO_SCELTA_OPERANDO, Costanti.MIN, listaSensori.size()-1);
		
		//stampo le infoRilevabili del sensore
		view.stampaMessaggio(((Sensore) listaSensori.getElemento(numeroSensore)).printListaMisurazioni());
		int	numeroInfoRil = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_INFORIL_OPERANDO, Costanti.MIN, ((Sensore) listaSensori.getElemento(numeroSensore)).size()-1);
		//assegno infoRilevabile scelta
		
		return model.setOperandoSensore(listaSensori, numeroSensore, numeroInfoRil);
	}
	
	public ValoreNonNumerico scegliCostanteStringa(Sensore operando_a)
	{	
		for(int i=0;i<operando_a.getCategoria().sizeDominio();i++)
			view.stampaMessaggio(i+operando_a.getCategoria().getDominio(i));
		int sceltaDominio= view.inputInteriConMinimo(Messaggi.INSERIRE_IL_NUMERO_DEL_DOMINIO, Costanti.MIN, operando_a.getCategoria().sizeDominio());
		return new ValoreNonNumerico(operando_a.getCategoria().getDominio(sceltaDominio));
	}
	
	public ValoreNumerico scegliCostante()
	{
		return new ValoreNumerico(view.inputDouble(Messaggi.MESSAGGIO_INSERIMENTO_COSTANTE));
	}
	
	public Time scegliOra()
	{
		return new Time(view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_ORA, Costanti.MIN, 23),
				view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_MINUTI, Costanti.MIN, 59));
	}
	public String scegliOperatore()
	{
		//assegnare ad una stringa la chiave della mappa
		view.stampaMessaggio(BooleanOperator.stampaOperatore());
		return view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_OPERATORE);
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO CARICAMENTO LISTA IMMOBILI DA FILE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public ListaImmobili caricaImmobiliDaFile(ListaImmobili lista, String path, boolean catSensori, boolean catAttuatori)
	{
		if(!catSensori && !catAttuatori)
		{
			lista=model.caricaImmobilidaFile(path, lista);
		}
		else
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		return lista;
		
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO CARICAMENTO LISTA CATEGORIE DA FILE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	

	public <T> ListaCategorie inserisciCategorieDaFile(ListaCategorie listaCategorie, Class<T> type,String path)
	{
		try {
			
			ListaCategorie categorieCaricate = GestioneFile.caricaCategoriaSensoriDaFile(path,type);
			if(!categorieCaricate.isEmpty())
			{
				for(int c=0;c<categorieCaricate.size();c++)
					{
						if(!listaCategorie.categoriaGiaPresente(categorieCaricate.getElemento(c).getNomeCategoria()))
						{
							//inserisco la categorie + infoRilevabili
							if(type == CategoriaSensori.class)
							{
								CategoriaSensori categoria = new CategoriaSensori();
								categoria = (CategoriaSensori) categorieCaricate.getElemento(c);
								view.stampaMessaggio(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(c).getNomeCategoria());
								//inserisco dominio categoria
								String scelta = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
								if(scelta.equalsIgnoreCase(Costanti.SI))
									categoria=inserisciDominio((CategoriaSensori) categoria);
								//inserisco infoRilevabili
								listaCategorie.addCategoria(inserisciInfoRilevabili(categoria));
							}
							else if(type == CategoriaAttuatori.class)
							{
								CategoriaAttuatori categoria = new CategoriaAttuatori();
								categoria = (CategoriaAttuatori) categorieCaricate.getElemento(c);
								view.stampaMessaggio(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(c).getNomeCategoria());
								listaCategorie.addCategoria(helpInserisciModalitaOperative(categoria));
							}
						}
					}
			
				
			}
			else
			{
				for(int i=0;i<categorieCaricate.size();i++)
				{
					if(type == CategoriaSensori.class)
					{
						CategoriaSensori categoria = new CategoriaSensori();
						categoria = (CategoriaSensori) categorieCaricate.getElemento(i);
						view.stampaMessaggio(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(i).getNomeCategoria());
						//inserisco dominio categoria
						String scelta = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
						if(scelta.equalsIgnoreCase(Costanti.SI))
							categoria=inserisciDominio((CategoriaSensori) categoria);
						//inserisco infoRilevabili
						listaCategorie.addCategoria(inserisciInfoRilevabili(categoria));
					}
					else if(type == CategoriaAttuatori.class)
					{
						CategoriaAttuatori categoria = new CategoriaAttuatori();
						categoria = (CategoriaAttuatori) categorieCaricate.getElemento(i);
						System.out.println(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(i).getNomeCategoria());
						listaCategorie.addCategoria(helpInserisciModalitaOperative(categoria));
					}
						
				}
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCategorie;
		
	}
	
	public void scegliImmobile(Dati dati)
	{
		if(dati.getListaUnitaImmobiliari().isEmpty())
		{
			
			view.stampaMessaggio(Messaggi.LISTA_IMMOBILI_VUOTA);
			dati.setListaUnitaImmobiliari(inserisciUnitaImmobiliare(dati.getListaUnitaImmobiliari()));
			Immobile immobile = dati.getListaUnitaImmobiliari().getImmobile(Costanti.MIN);
		}
		else {
			view.stampaMessaggio(dati.getListaUnitaImmobiliari().stampaListaUnitaImmobiliari());
			int numeroUnitaImmobiliare = view.inputInteriConMinimo(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_IMMOBILE, 
					Costanti.MIN, ((dati.getListaUnitaImmobiliari().size())-1));
			Immobile immobile = dati.getListaUnitaImmobiliari().getImmobile(numeroUnitaImmobiliare);
			dati.setImmobile(immobile);
		}
	
	}
	

	

}