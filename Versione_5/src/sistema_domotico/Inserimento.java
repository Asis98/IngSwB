package sistema_domotico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ambiente.*;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.InfoRilevabile;
import categorie.ListaCategorie;
import categorie.ModalitaOperativa;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;
import costanti.Case;
import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.NumeriCasuali;
import regole.Regola;
import regole.StatoRegola;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import rilevazione.UnitaRilevazione;

public class Inserimento {
	
	

	//METODI DEL MENU' DEL MANUTENTORE
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO UNITA' IMMOBILIARI E UNITA' DOMOTICHE 
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
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
	public static ListaUnitaImmobiliari inserisciUnitaImmobiliare(ListaUnitaImmobiliari listaImmobili)
	{
		//qui non va il controllo se è unico o no perchè tanto richiamando questo metodo setto solo una variabile e non istanzio nessun nuovo oggetto
		
		String destinazione = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_IMMOBILIARE);
		if(!listaImmobili.unitaImmobiliareGiaPresente(destinazione))
		{
			UnitaImmobiliare immobile = new UnitaImmobiliare(destinazione);
			//immobile.setdestinazione(destinazione);
			listaImmobili.addUnitaImmobiliare(immobile);
		}
		else
			System.out.println(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
		
		return listaImmobili;
		
	}
	
	/**
	 * Inserisci unita domotica.
	 * 
	 * @param: immobile
	 * @post: unitList.size() = unitList.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (unitList.size()-1)) ==> element(k)@pre == element(k)) 
	 * @return immobile
	 */
	public static UnitaImmobiliare inserisciUnitaDomotica(UnitaImmobiliare immobile)
	{
		if(!immobile.getdestinazione().equalsIgnoreCase(Costanti.STRINGA_VUOTA))
		{
			MyMenu menuUnitaDomotica=new MyMenu(TitoliMenu.TITOLOUNITADOMOTICA,VociMenu.VOCIUNITADOMOTICA);
			int scelta;
			do 
			{
				scelta=menuUnitaDomotica.scegli();
				switch(scelta)
				{
					case Case.ESCI: break;
					case Case.INSERISCI_STANZA:	
						
						Stanza stanza = new Stanza(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_DOMOTICA));
						boolean esiste = immobile.verificaPresenzaUnitaDomotica(stanza.getUnitName(), Stanza.class);
						
						if(!esiste) {
							immobile.addUnitaDomotica(stanza);
							System.out.println(Messaggi.MESSAGGIO_SUCCESSO_INPUT );
						}
						else
							System.out.println(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
						break;
						
					case Case.INSERISCI_ARTEFATTO: 
						
						Artefatto artefatto = new Artefatto(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_DOMOTICA));
						
						boolean giaPresente= immobile.verificaPresenzaUnitaDomotica(artefatto.getUnitName(), Artefatto.class);
						
						if(!giaPresente) 
						{
							String insStanza;
							do
							{
								insStanza=InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_ASSOC_STANZA_ARTEFATTO);
								if(!insStanza.equalsIgnoreCase(Costanti.SI) && !insStanza.equalsIgnoreCase(Costanti.NO))
									System.out.println(Messaggi.STRINGA_NON_VALIDA);
							}while(!insStanza.equalsIgnoreCase(Costanti.SI) && !insStanza.equalsIgnoreCase(Costanti.NO));
							
							if(insStanza.equalsIgnoreCase(Costanti.SI))
							{
								boolean presente = false;
								do {
										for(int i = 0; i<immobile.size();i++)
										{
											
											if(immobile.getUnitaDomotica(i) instanceof Stanza)
												System.out.println(Messaggi.NOME_STANZA + immobile.getUnitaDomotica(i).getUnitName());
										}
										String nomeStanza=InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_STANZA);
										presente=immobile.verificaPresenzaUnitaDomotica(nomeStanza,Stanza.class);
										if (presente)	
											artefatto.setStanza(nomeStanza);
										else
										{
											System.out.println(Messaggi.STANZA_NON_ESISTENTE);
										}
								}while(!presente);
								
							}
							immobile.addUnitaDomotica(artefatto);
							System.out.println(Messaggi.MESSAGGIO_SUCCESSO_INPUT);
						}
						else
							System.out.println(Messaggi.ARTEFATTO_GIA_ESISTENTE);
						break;
				}
			} while (scelta != Case.ESCI);
			
		}	
		
		else
		{
			System.out.println(Messaggi.IMMOBILE_NON_ESISTENTE);
		}
		
		return immobile;
		
	}
		
/*-------------------------------------------------------------------------------------------------------------------------------------------------
 * METODI INSERIMENTO CATEGORIE ATTUATORI E SENSORI 
 * ------------------------------------------------------------------------------------------------------------------------------------------------
 */
		
	/**
	* Inserisci categoria.
	*
	* @param categoria the categoria
	* @param listaCategorie the lista categorie
	* @param tipoUnitaRilevazione the tipo unita rilevazione
	* @return the lista categorie
	* @pre: categorie != null, listaCategorie ! = Null, tipoUnitaRilevazione != null
	* @post: if(!listaCategorie.categoriaGiaPresente(categoria.getNomeCategoria())) 
	* 			listaCategorie.size() = ListaCategorie.size()@pre + 1 
	* @post: @forall k : [0..size()-2]
	* (k < (ListaCategorie.size()-1)) ==> element(k)@pre == element(k)) 
    */
	public static ListaCategorie inserisciCategoria(Categoria categoria, ListaCategorie listaCategorie)
	{	
		String nomeCategoria = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_CATEGORIA);
		
		if(!listaCategorie.categoriaGiaPresente(nomeCategoria))
		{
			categoria.setNomeCategoria(nomeCategoria);
			//controllo che la descrizione inserita non superi i 144 char come deciso nell'uml
			String descrizione;
			do {
				descrizione= InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DESCRIZIONE_CATEGORIA);
				if(descrizione.length()>Costanti.MAXCHAR)
					System.out.println(Messaggi.NUM_CARATTERI_MAX_SUPERATO);
			}while(descrizione.length()>Costanti.MAXCHAR);
			categoria.setDescrizioneCategoria(descrizione);
			
			//INSERIMENTO CATEGORIA ATTUATORI
			if(categoria instanceof CategoriaAttuatori)
			{
					//metodo che prima chiede l'inserimento delle infoRilevabili e poi aggiunge l'oggetto categoria all'arrayList
					listaCategorie.addCategoria(HelpMethod.inserisciModalitaOperative((CategoriaAttuatori)categoria));		
			}
			//INSERIMENTO CATEGORIA SENSORI
			else if (categoria instanceof CategoriaSensori)
			{ 
				String scelta = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
				if(scelta.equalsIgnoreCase(Costanti.SI))
				{
					categoria=HelpMethod.inserisciDominio((CategoriaSensori) categoria);
				}
					//metodo che prima chiede l'inserimento delle infoRilevabili e poi aggiunge l'oggetto categoria all'arrayList
					listaCategorie.addCategoria(HelpMethod.inserisciInfoRilevabili((CategoriaSensori)categoria));
			}
		}
		else
		{
			//in base a che tipo d'istanza è stampa se sensori o attuatori
			System.out.println(Costanti.CATEGORIA + Costanti.SPAZIO + (categoria instanceof CategoriaAttuatori ? Costanti.ATTUATORE : Costanti.SENSORE) + Messaggi.GIA_PRESENTE);
		}
		
		return listaCategorie;
	}
	
	
	/**
	 * Inserisci unita rilevazione.
	 *
	 * @param unita
	 * @param listaUnita 
	 * @param tipoUnita
	 * @param listaCategorie 
	 * @pre: unita != null, listaUnita ! = Null, tipoUnita != null , listaCategorie != null
	 * @post: if(listaCategorie.size()!=0 && !immobile.isEmpty())
	 * 			listaUnita.size() = ListaUnita.size()@pre + 1 
   	 * @post: @forall k : [0..size()-2]
	 * (k < (listaUnita.size()-1)) ==> element(k)@pre == element(k)) 
	 * @return the ArrayList listaUnita
	 */
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO UNITA' RILEVAZIONE (SENSORI / ATTUATORI)
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	public static ArrayList<UnitaRilevazione> inserisciUnitaRilevazione(UnitaRilevazione unita, ArrayList<UnitaRilevazione> listaUnita, ListaCategorie listaCategorie, UnitaImmobiliare immobile)
	{	
		/*
		 * se la lista delle categorie e la lista delle unità domotiche sono vuote non ha senso inserire un attuatore
		 * perchè un attuatore va associato ad una categoria e ad una stanza o artefatto
		 */
		if(listaCategorie.size()!=0 && !immobile.isEmpty())
		{
			//setto l'unità domotica
			System.out.println(Messaggi.ELENCO_UNITA_DOMOTICHE);
			System.out.println(immobile.stampaLista());
			
			int numeroUnitaDomotica = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_UNI_DOMOTICA, Costanti.MIN, ((immobile.getUnitList()).size())-1);
			unita.setUnitaDomotica(immobile.getUnitaDomotica(numeroUnitaDomotica));
			
			int numeroCategoria;
			
			System.out.println(Messaggi.ELENCO_CATEGORIE);
			int numeroCategorieDisponibili = 0;
			String [] stampaCategorie = listaCategorie.stampaLista();
			for(int i=0;i<stampaCategorie.length;i++)
			{	
				if(!listaUnita.isEmpty())
				{
					boolean esiste= false;
					for(int c=0; c<listaUnita.size() && !esiste;c++)
					{
						if(stampaCategorie[i].equalsIgnoreCase(listaUnita.get(c).getCategoria().getNomeCategoria()) && listaUnita.get(c).getUnitaDomotica().getUnitName().equalsIgnoreCase(immobile.getUnitaDomotica(numeroUnitaDomotica).getUnitName()))
									esiste=true;
					}
					if(!esiste) 
					{
						System.out.println(i+ Costanti.TRATTINO +stampaCategorie[i]);
						numeroCategorieDisponibili++;
					}
				}
				else {
					System.out.println(i+ Costanti.TRATTINO +stampaCategorie[i]);
					numeroCategorieDisponibili++;
				}
			}
					
			if(numeroCategorieDisponibili>0)
			{
				numeroCategoria = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_CATEGORIA, Costanti.MIN, listaCategorie.size());
				unita.setCategoria(listaCategorie.getElemento(numeroCategoria));
				
				//inserimento del nome dell'unità di rilevazione, fare try catch per il nome
				String nomeUnita = null;
				boolean eccezione = false;
				do {
					
					try {
						nomeUnita = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_UNITA_RILEVAZIONE);
						unita.setNomeUnita(nomeUnita
							+ (listaUnita.size())+Costanti.TRATTINO_BASSO+listaCategorie.getElemento(numeroCategoria).getNomeCategoria());
						eccezione = false;
					}
					catch (Exception e)
					{
						System.out.println(Messaggi.NOME_NON_VALIDO_INSERIRE_UN_ALTRO_NOME);
						eccezione=true;
					}
				}while(eccezione);
			
				if(unita instanceof Attuatore)
				{
					System.out.println(Messaggi.NOME_ATTUATORE + unita.getNomeUnita());
					
					listaUnita.add(HelpMethod.scegliModalitaOperativa((Attuatore)unita, (CategoriaAttuatori)listaCategorie.getElemento(numeroCategoria)));
					
				}
				else if (unita instanceof Sensore)
				{
					System.out.println(Messaggi.NOME_SENSORE + unita.getNomeUnita());
					
					String risposta;
					do 
					{
							System.out.println(((CategoriaSensori)listaCategorie.getElemento(numeroCategoria)).stampaInfoRilevabili());
					
					
							int numeroInfoRilevabile = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_INFO_RILEVABILI, Costanti.MIN, 
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
								System.out.println(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
							}
							
							
							risposta = InputDati.leggiStringa(Messaggi.ASSEGNARE_UN_ALTRA_INFORMAZIONE_RILEVABILE);
							
					}while(risposta.equalsIgnoreCase(Costanti.SI));
					
					
					listaUnita.add(unita);
				}
			}
			else
				System.out.println(Messaggi.CATEGORIE_GIA_TUTTE_ASSEGNATE+ immobile.getUnitaDomotica(numeroUnitaDomotica).getUnitName());
				
		}
		else
			System.out.println(Messaggi.INSERIMENTO_NON_VALIDO + 
			(immobile.isEmpty()? (Messaggi.LISTA_UNITA_DOMOTICHE + Messaggi.AVVISO_LISTA_UN_DOMOTICHE_VUOTA) : (Messaggi.LISTA_CATEGORIE + Messaggi.AVVISO_LISTA_CATEGORIE_VUOTA)));
		
		return listaUnita;
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * ASSEGNAMENTO MODALITA' OPERATIVA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Modifica modalita operativa.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: if(modOp.get(numeroModOp).parametriIsEmpty())
				immobile.getAttuatore(numeroAttuatore).setModOperativa(modOp.get(numeroModOp))
	 * @return the unita immobiliare
	 */
	public static UnitaImmobiliare modificaModalitaOperativa(UnitaImmobiliare immobile)
	{
		
		if(immobile.isEmptyAttuatori())
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		else
		{
			System.out.println(immobile.stampaListaAttuatoriAttivi());
			int numeroAttuatore = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_ATTUATORE, Costanti.MIN, immobile.sizeAttuatori()-1);
			
			ArrayList<ModalitaOperativa> modOp = immobile.getAttuatore(numeroAttuatore).getCategoria().getListaModalitaOperative();
			
			if(modOp != null)
			{
				for(int i =0; i<modOp.size();i++)
					System.out.println(i+Costanti.TRATTINO+modOp.get(i).getNomeModOperativa());
				int numeroModOp = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_MOD_OPERATIVA, Costanti.MIN, modOp.size()-1);
				
				if(modOp.get(numeroModOp).parametriIsEmpty())
					immobile.getAttuatore(numeroAttuatore).setModOperativa(modOp.get(numeroModOp));
				else
				{
					for(int c=0; c<modOp.get(numeroModOp).size();c++)
					{
						
						Double valore= InputDati.leggiDouble((Messaggi.MESSAGGIO_INSERIMENTO_VALORE_PARAMETRO + Costanti.SPAZIO + modOp.get(numeroModOp).getParametro(c).getNomeParametro()));
						modOp.get(numeroModOp).setParametro(modOp.get(numeroModOp).getParametro(c).getNomeParametro(), valore);
					}
				}

			}
			
		}
		
		return immobile;
		
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO INSERIMENTO REGOLA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Inserisci regola.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: if(!regola.isEmptyAntecedente()) immobile.setRegola(regola);
	 */
	public static UnitaImmobiliare inserisciRegola(UnitaImmobiliare immobile)
	{	
		if(immobile.isEmptySensori() || immobile.isEmptyAttuatori())
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA + Messaggi.LISTA_SENS_ATT_VUOTE);
		}
		else
		{	
			int scelta ;
			MyMenu menuRegole= new MyMenu(TitoliMenu.TITOLOREGOLE, VociMenu.VOCIREGOLE);
			Regola regola = new Regola();
			do
			{
				//ciclo che chiede gli antecedenti che l'utente vuole aggiungere ad una regola
				scelta=menuRegole.scegli();
				switch(scelta)
				{
					case 0: break;
					case 1:	//regole con due sensori
						if(immobile.sizeSensori() == 1)
							System.out.println(Messaggi.OPERANDI_DELLA_LISTA_NON_SUFFICIENTI);
						else
						{
							regola=HelpMethod.inserisciRegolaSensore(regola, immobile);
						}
						break;
					case 2: 
						//regole con sensore / costante
						regola=HelpMethod.inserisciRegolaCostante(regola, immobile);
						break;
					case 3:
						//regole con sensore e time
						regola = HelpMethod.inserisciRegolaTime(regola, immobile);
						break;
				}
				
			}while(scelta!=0);
			
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
		
		return immobile;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO CARICAMENTO LISTA CATEGORIE DA FILE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Inserisci categorie da file.
	 *
	 * @param <T> the generic type
	 * @param listaCategorie the lista categorie
	 * @param type the type
	 * @param path the path
	 * @pre: listaCategorie != null && type!=null && path !=null
	 * @post: if categoria read from file doesnìt exist in listaCategorie then listaCategorie.add(categoria)
	 * @return the lista categorie
	 */
	public static <T> ListaCategorie inserisciCategorieDaFile(ListaCategorie listaCategorie, Class<T> type,String path)
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
								System.out.println(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(c).getNomeCategoria());
								//inserisco dominio categoria
								String scelta = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
								if(scelta.equalsIgnoreCase(Costanti.SI))
									categoria=HelpMethod.inserisciDominio((CategoriaSensori) categoria);
								//inserisco infoRilevabili
								listaCategorie.addCategoria(HelpMethod.inserisciInfoRilevabili(categoria));
							}
							else if(type == CategoriaAttuatori.class)
							{
								CategoriaAttuatori categoria = new CategoriaAttuatori();
								categoria = (CategoriaAttuatori) categorieCaricate.getElemento(c);
								listaCategorie.addCategoria(HelpMethod.inserisciModalitaOperative(categoria));
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
						System.out.println(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(i).getNomeCategoria());
						//inserisco dominio categoria
						String scelta = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DOMINIO);
						if(scelta.equalsIgnoreCase(Costanti.SI))
							categoria=HelpMethod.inserisciDominio((CategoriaSensori) categoria);
						//inserisco infoRilevabili
						listaCategorie.addCategoria(HelpMethod.inserisciInfoRilevabili(categoria));
					}
					else if(type == CategoriaAttuatori.class)
					{
						CategoriaAttuatori categoria = new CategoriaAttuatori();
						categoria = (CategoriaAttuatori) categorieCaricate.getElemento(i);
						System.out.println(Costanti.CATEGORIAFILE + categorieCaricate.getElemento(i).getNomeCategoria());
						listaCategorie.addCategoria(HelpMethod.inserisciModalitaOperative(categoria));
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

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO CARICAMENTO LISTA IMMOBILI DA FILE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Carica immobili da file.
	 *
	 * @param lista the lista
	 * @param path the path
	 * @param catSensori the cat sensori
	 * @param catAttuatori the cat attuatori
	 * @pre: lista !=null && path !=null && catSensori != null && catAttuatori != null
	 * @post: if immobile read from file doesn't exist in lista then listaC.add(immobile)
	 * @return the lista unita immobiliari
	 */
	public static ListaUnitaImmobiliari caricaImmobiliDaFile(ListaUnitaImmobiliari lista, String path, boolean catSensori, boolean catAttuatori)
	{
		if(!catSensori && !catAttuatori)
		{
			ListaUnitaImmobiliari unitaCaricate = new ListaUnitaImmobiliari();
			if(!lista.isEmpty())
			{
				//confronto due liste
				try {
					unitaCaricate = GestioneFile.caricaImmobiliDaFile(path);
					for(int c=0;c<unitaCaricate.size();c++)
					{
						if(!lista.unitaImmobiliareGiaPresente(unitaCaricate.getUnitaImmobiliare(c).getdestinazione()))
							lista.addUnitaImmobiliare(new UnitaImmobiliare(unitaCaricate.getUnitaImmobiliare(c).getdestinazione()));
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				//popolo lista
				try {
					lista = GestioneFile.caricaImmobiliDaFile(path);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		else
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		return lista;
		
	}

}

	
