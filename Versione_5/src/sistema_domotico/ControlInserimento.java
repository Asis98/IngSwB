package sistema_domotico;

import ambiente.Immobile;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.InfoRilevabile;
import categorie.ModalitaOperativa;
import costanti.Costanti;
import costanti.Messaggi;
import liste.ListaCategorie;
import liste.ListaImmobili;
import liste.ListaUnitaDomotiche;
import liste.ListaUnitaRilevazione;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import rilevazione.UnitaRilevazione;

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
		ListaUnitaRilevazione listaUnitaRilevazione;
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
					
					//ARRIVATA QUI
					listaUnita.add(HelpMethod.scegliModalitaOperativa((Attuatore)unita, (CategoriaAttuatori)listaCategorie.getElemento(numeroCategoria)));
					
				}
				else if (unita instanceof Sensore)
				{
					System.out.println(Costanti.NOME_SENSORE + unita.getNomeUnita());
					
					String risposta;
					do 
					{
							System.out.println(((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).stampaInfoRilevabili());
					
					
							int numeroInfoRilevabile = InputDati.leggiIntero(Costanti.MESSAGGIO_INSERIMENTO_INFO_RILEVABILI, Costanti.MIN, 
																		((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).size());
							
							//verificare che le infoRilevabile che non sia già stata scelta
							
							if(!((Sensore)unita).nomeInfoRilPresente(((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).getInfoRilevabile(numeroInfoRilevabile).getTipoInfoRilevabile()))
							{
								//assegnazione casuale di un valore
								InfoRilevabile info = new InfoRilevabile(((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).getInfoRilevabile(numeroInfoRilevabile).getTipoInfoRilevabile());
								
								if(!((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).getInfoRilevabile(numeroInfoRilevabile).isNonnumerica())
								{
									//metodo che assegna un valore numerico casuale
									
									info.setValore(new ValoreNumerico());
									((Sensore)unita).addMisurazione(info); 	
								}
								else if (((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).getInfoRilevabile(numeroInfoRilevabile).isNonnumerica())
								{
									//metodo che assegna un valore non numerico in automatico
									//infoRile non numerica, stampo valori del dominio
									
									int numeroDominio = NumeriCasuali.estraiIntero(0, ((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).sizeDominio()-1);
									ValoreNonNumerico valore = new ValoreNonNumerico();
									
									valore.setValore(((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).getDominio(numeroDominio));
									info.setValore(valore);
									
									((Sensore) unita).addMisurazione(info);
									
								}
								
							}
							else
							{
								System.out.println(Costanti.MESSAGGIO_ELEMENTO_ESISTENTE);
							}
							
							
							risposta = InputDati.leggiStringa(Costanti.ASSEGNARE_UN_ALTRA_INFORMAZIONE_RILEVABILE);
							
					}while(risposta.equalsIgnoreCase(Costanti.SI));
					
					
					listaUnita.add(unita);
				}
			}
			else
				System.out.println(Costanti.CATEGORIE_GIA_TUTTE_ASSEGNATE+ immobile.getUnitaDomotica(numeroUnitaDomotica).getUnitName());
				
		}
		else
			System.out.println(Costanti.INSERIMENTO_NON_VALIDO + 
			(immobile.isEmpty()? (Costanti.LISTA_UNITA_DOMOTICHE + Costanti.AVVISO_LISTA_UN_DOMOTICHE_VUOTA) : (Costanti.LISTA_CATEGORIE + Costanti.AVVISO_LISTA_CATEGORIE_VUOTA)));
		
		return listaUnita;
	}
	
}