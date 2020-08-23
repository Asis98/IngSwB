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
import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MV_Manutentore;
import gestioneMenu.MV_Regole;
import gestioneMenu.MV_StanzaArtefatto;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import liste.ListaCategorie;
import liste.ListaImmobili;
import liste.ListaUnitaDomotiche;
import liste.ListaUnitaRilevazione;
import operazioni.BooleanOperator;
import regole.Regola;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import rilevazione.UnitaRilevazione;
import utility.MyMenu;

public class ControlInserimento {
	
	private UserInterface view = new UserInterface();
	private Modelinserimento model = new Modelinserimento();
	//METODI DEL MENU' DEL MANUTENTORE
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO UNITA' IMMOBILIARI E UNITA' DOMOTICHE 
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	
	public void inserisciUnitaImmobiliare(ListaImmobili listaImmobili)
	{
		//qui non va il controllo se è unico o no perchè tanto richiamando questo metodo setto solo una variabile e non istanzio nessun nuovo oggetto
		
		String destinazione = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_IMMOBILIARE);
		if(model.inserimentoUnitaImmobiliare(destinazione, listaImmobili))
			view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT);
		else
			view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
		
	}
	
	//FARE INSERIMENTO UNITA' DOMOTICHE
	
	public void inserimentoUnitaDomotica(Immobile immobile)
	{
		MyMenu menuManutentore= new MyMenu(TitoliMenu.TITOLOUNITADOMOTICA,VociMenu.VOCIUNITADOMOTICA);
		menuManutentore.stampaMenu();
		int scelta;
		do {
			scelta = view.inputLeggiIntero(Messaggi.SCEGLI_VOCE);
			MenuCommand targetOperation = MV_StanzaArtefatto
			      .getOperation(scelta)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
			targetOperation.esegui();
		}while(scelta !=0);
	}
	
	public void inserisciStanza(Immobile immobile)
	{
		if(immobile.getUnitList().isEmpty())
			view.stampaMessaggio(Messaggi.IMMOBILE_NON_ESISTENTE);
		else
		{
			//inserimentoStanza
			ListaUnitaDomotiche unitList = immobile.getUnitList();
			String nomeStanza = view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_DOMOTICA);
			unitList = model.inserisciStanza(unitList, nomeStanza);
			if(!model.verificaPresenzaUnitaD(unitList, nomeStanza))
				view.stampaMessaggio(Messaggi.MESSAGGIO_SUCCESSO_INPUT );
			else
				view.stampaMessaggio(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
		}
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
		
	public void inserisciCategoria(ListaCategorie listaCategorie, Categoria categoria)
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
	}
	
	public CategoriaAttuatori helpInserisciModalitaOperative(CategoriaAttuatori categoria)
	{
		categoria.addModalitaOperativa(new ModalitaOperativa(Costanti.IDLE));
		view.stampaMessaggio(Messaggi.MESSAGGIO_INSERIMENTO__MODALITA_OPERATIVE);
		String risposta;
		do
		{
			ModalitaOperativa modOp = new ModalitaOperativa(view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_MODALITA_OPERATIVA), view.InputStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DESCRIZIONE_MODALITA_OPERATIVA));
			if(model.verificaModOp(modOp, categoria))
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
	public void inserisciUnitaRilevazione(UnitaRilevazione unita, ListaCategorie listaCategorie, Immobile immobile)
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
	
	
	public void inserimentoRegole(Immobile immobile)
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
				targetOperation.esegui();
			}while(scelta !=0);
			
			
			//DA FARE ANCORA
			if(!regola.isEmptyAntecedente())
			{
				HelpMethod.inserisciConseguente(immobile, regola);
				if(regola.valutaRegola())
					regola.setStato(StatoRegola.ABILITATA);
				else
					regola.setStato(StatoRegola.DISABILITATA);
				
				immobile.setRegola(regola);
			}

		}
	}
	
	public Regole inserisciRegolaSensori(Regola regola, Immobile immobile)
	{
		Sensore operando_a = new Sensore();
		operando_a = scegliSensore(immobile);
		String operatoreRelazionale = null;
		if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
		{
			operatoreRelazionale = Costanti.UGUALE;
		}
		else
			operatoreRelazionale = HelpMethod.scegliOperatore();
			
		Sensore operando_b= new Sensore();
		operando_b=HelpMethod.scegliSensore(immobile);
		
		if(operando_a.getInfoRilevabile(0).getValoreAttuale().getValore().getClass() == String.class && 
				operando_b.getInfoRilevabile(0).getValoreAttuale().getValore().getClass() != String.class)
		{
			System.out.println(Costanti.OPERANDI_INVALIDI);
		}
		else if(new AntecedenteSensore(operando_a,operatoreRelazionale,operando_b).confrontaElementiAntecedente())
		{
			System.out.println(Costanti.INSERIMENTO_NON_VALIDO);
		}
		else
			regola.setAntecedente(operando_a,operatoreRelazionale,operando_b);
		
		return regola;
	}
	
	public Sensore scegliSensore(Immobile immobile)
	{
		System.out.println(immobile.stampaListaSensoriAttivi());
		
		//setto il primo operando della regola
		int	numeroSensore = InputDati.leggiIntero(Costanti.MESSAGGIO_SCELTA_OPERANDO, Costanti.MIN, immobile.sizeSensori()-1);
		Sensore operando = new Sensore(immobile.getSensore(numeroSensore).getNomeUnita()); 
		operando.setCategoria(immobile.getSensore(numeroSensore).getCategoria());
		//stampo le infoRilevabili del sensore
		System.out.println(immobile.getSensore(numeroSensore).printListaMisurazioni());
		int	numeroInfoRil = InputDati.leggiIntero(Costanti.MESSAGGIO_INSERIMENTO_INFORIL_OPERANDO, Costanti.MIN, immobile.getSensore(numeroSensore).size()-1);
		//assegno infoRilevabile scelta
		operando.addMisurazione(immobile.getSensore(numeroSensore).getInfoRilevabile(numeroInfoRil));
		
		return operando;
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
			if(!listaCategorie.isEmpty())
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

	

}