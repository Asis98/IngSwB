package sistema_domotico;

import ambiente.UnitaImmobiliare;
import categorie.Stato;
import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import regole.AntecedenteSensore;
import regole.StatoRegola;
import rilevazione.Attuatore;
import rilevazione.Sensore;

public class AbleDisable {

	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO ATTIVA/DISATTIVA REGOLA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	/**
	 * Gestisci stato regole.
	 *
	 * @param immobile the immobile
	 * @pre: immobile!=null
	 * @post: if(!immobile.isEmptyRegole()) set stato regola
	 * @return the unita immobiliare
	 */
	public static UnitaImmobiliare gestisciStatoRegole(UnitaImmobiliare immobile)
	{
		if(!immobile.isEmptyRegole())
		{
			//menù dal quale l'utente sceglie se disattivare o attivare una regola
			
				int scelta ;
				MyMenu menuRegole= new MyMenu(TitoliMenu.TITOLOATTIVADISATTIVA, VociMenu.VOCISTATOREGOLE);
				do
				{
					//ciclo che chiede gli antecedenti che l'utente vuole aggiungere ad una regola
					scelta=menuRegole.scegli();
					switch(scelta)
					{
						case 0: break;
						case 1:	//Attiva Regola
							
							//stampo lista Regole disattivate
							if(immobile.contaRegoleDisabilitate() == Costanti.MIN)
							{
								System.out.println("Non esistono regole disabilitate");
							}
							else
							{
								Visualizzazione.visualizzaRegoleDisattive(immobile);
								int sceltaRegola = InputDati.leggiIntero(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_ATTIVARE, 
												Costanti.MIN, immobile.contaRegoleDisabilitate()-1);
								//verificare che le istanze di sensori e attuatori siano attive
								if(valutaSensoriDisattivi(immobile,sceltaRegola))
								{
									System.out.println(Messaggi.AVVISO_SENSORI_DISATTIVI);
								}
								else if(valutaAttuatoriDisattivi(immobile,sceltaRegola))
								{
									//cerco se ci sono attuatori disattivi
									System.out.println(Messaggi.AVVISO_ATTUATORI_DISATTIVI);
								}
								else
								{
									immobile.getRegola(sceltaRegola).setStato(StatoRegola.ABILITATA);
									System.out.println(Messaggi.REGOLA_ABILITATA);
								}
							}
							break;
							
						case 2: //Disattiva Regola
							//stampo lista Regole disattivate
							if(immobile.contaRegoleAbilitate() == Costanti.MIN )
							{
								System.out.println("Non esistono regole abilitate");
							}
							else
							{
								Visualizzazione.visualizzaRegoleAttive(immobile);
								int sceltaRegolaAbilitata = InputDati.leggiIntero(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_DISATTIVARE, 
												Costanti.MIN, immobile.contaRegoleAbilitate()-1);
									immobile.getRegola(sceltaRegolaAbilitata).setStato(StatoRegola.DISABILITATA);
									System.out.println(Messaggi.REGOLA_DISABILITATA);		
							}
							
							break;
					}
					
				}while(scelta!=0);
			
		}
		else
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		return immobile;
	}
	
	/**
	 * Valuta sensori disattivi.
	 *
	 * @param immobile 
	 * @param sceltaRegolaAbilitata 
	 * @pre: immobile!=null && sceltaRegolaAbilitata!=null
	 * @post: -
	 * @return true, if successful
	 */
	private static boolean valutaSensoriDisattivi(UnitaImmobiliare immobile, int sceltaRegolaAbilitata)
	{
		for(int i =0;i<immobile.getRegola(sceltaRegolaAbilitata).sizeAntecedenti();i++)
		{
			if(immobile.getRegola(sceltaRegolaAbilitata).getAntecedente(i) instanceof AntecedenteSensore)
			{
				//devo verificare che i due sensori di Antecedente siano contenuti in listaSensori
				String nomeOperandoA = immobile.getRegola(sceltaRegolaAbilitata).getAntecedente(i).getSensoreAntecedente().getNomeUnita();
				String nomeOperandoB = ((AntecedenteSensore)immobile.getRegola(sceltaRegolaAbilitata).getAntecedente(i)).getOperando_b().getNomeUnita();
				if(immobile.cercaSensore(nomeOperandoA).getStato()==Stato.SPENTO ||
						immobile.cercaSensore(nomeOperandoB).getStato()==Stato.SPENTO)
					return true;
			}
			else
			{
				if(immobile.cercaSensore(immobile.getRegola(i).getAntecedente(i).getSensoreAntecedente().getNomeUnita()).getStato() == Stato.SPENTO)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Valuta attuatori disattivi.
	 *
	 * @param immobile 
	 * @param sceltaRegolaAbilitata
	 * @pre: immobile!=null && sceltaRegolaAbilitata!=null
	 * @return true, if successful
	 */
	public static boolean valutaAttuatoriDisattivi(UnitaImmobiliare immobile, int sceltaRegolaAbilitata)
	{
		//cerco nei conseguenti gli attuattori disattivi
		for(int i=0; i<immobile.getRegola(sceltaRegolaAbilitata).sizeConseguenti();i++)
		{
			if(immobile.cercaAttuatore(immobile.getRegola(sceltaRegolaAbilitata).getConseguente(i).getAttuatore().getNomeUnita()).getStato() == Stato.SPENTO)
				return true;
		}
		
		return false;
	}
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO ATTIVA/DISATTIVA UNITA' DI RILEVAZIONE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Gestisci stato unita rilevazione.
	 *
	 * @param immobile 
	 * @pre: immobile!=null
	 * @post: if(!immobile.isEmptyAttuatori() && !immobile.isEmptySensori()) set stato unità rilevazione
	 * @return the unita immobiliare
	 */
	public static UnitaImmobiliare gestisciStatoUnitaRilevazione(UnitaImmobiliare immobile)
	{
		if(!immobile.isEmptyAttuatori() && !immobile.isEmptySensori())
		{
			int scelta ;
			MyMenu menuGestioneStatoUnitaRilevazione = new MyMenu(TitoliMenu.TITOLOSTATOUNITARIL, VociMenu.VOCISTATOUNITARILEVAZIONE);
			do
			{
				//ciclo che chiede gli antecedenti che l'utente vuole aggiungere ad una regola
				scelta=menuGestioneStatoUnitaRilevazione.scegli();
				switch(scelta)
				{
					case 0: break;
					
					case 1:	//Attiva Sensore
					
					if(immobile.sizeSensoriDisattivati() == 0)
					{
						System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
					}
					else
					{
						System.out.println(immobile.stampaListaSensoriDisattivi());
						int	numeroSensore = InputDati.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.sizeSensoriDisattivati()-1);
						Sensore sensore = (Sensore) immobile.getListaSensoriDisattivi().get(numeroSensore);
						sensore.setStato(Stato.ACCESO);
						
						for(int i=0; i<immobile.getListaRegole().size(); i++)
						{
							/*attivo una regola <-> in un suo antecedente è contenuto sensore e tutti gli attuatori
							 * contenuti nei conseguenti sono attivi
							 * e tutti i sensori contenuti nei conseguenti sono attivi
							 */
							if(immobile.getRegola(i).cercaSensore(sensore) && immobile.getRegola(i).antecedentiAttivi() && immobile.getRegola(i).conseguentiAttivi())
								immobile.getRegola(i).setStato(StatoRegola.ABILITATA);
						}
					}
						break;
						
					case 2: //Attiva Attuatore	
					
						if(immobile.sizeAttuatoriDisattivati() == 0)
						{
							System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
						}
						else
						{
						
							System.out.println(immobile.stampaListaAttuatoriDisattivi());
							int	numeroAttuatore = InputDati.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.sizeAttuatoriDisattivati()-1);
							Attuatore attuatore = (Attuatore) immobile.getListaAttuatoriDisattivi().get(numeroAttuatore);
							attuatore.setStato(Stato.ACCESO);
							for(int i=0; i<immobile.getListaRegole().size(); i++)
							{
								/*attivo una regola <-> in un suo conseguente è contenuto attuatore e tutti gli attuatori
								 * contenuti nei conseguenti sono attivi
								 * e tutti i sensori contenuti nei conseguenti sono attivi
								 */
								if(immobile.getRegola(i).cercaAttuatore(attuatore) && immobile.getRegola(i).antecedentiAttivi() && immobile.getRegola(i).conseguentiAttivi())
									immobile.getRegola(i).setStato(StatoRegola.ABILITATA);
							}
						}
						break;
						
					case 3: //Disattiva Sensore
					if(immobile.sizeSensoriAttivati() == 0)
					{
						System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
					}
					else
					{
						System.out.println(immobile.stampaListaSensoriAttivi());
						int	numeroSensoreDaDisattivare = InputDati.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.sizeSensoriAttivati()-1);
						Sensore sensore2 = (Sensore) immobile.getListaSensoriAttivi().get(numeroSensoreDaDisattivare);
						sensore2.setStato(Stato.SPENTO);
						for(int i=0; i<immobile.getListaRegole().size(); i++)
						{
							if(immobile.getRegola(i).cercaSensore(sensore2))
								immobile.getRegola(i).setStato(StatoRegola.DISABILITATA);
						}
					}
						break;
						
					case 4: //Disattiva Attuatore
						if(immobile.sizeAttuatoriAttivati() == 0)
						{
							System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
						}
						else
						{	
							System.out.println(immobile.stampaListaAttuatoriAttivi());
							int	numeroAttuatoreDaDisattivare = InputDati.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.sizeAttuatoriAttivati()-1);
							Attuatore attuatore2 = (Attuatore) immobile.getListaAttuatoriAttivi().get(numeroAttuatoreDaDisattivare);
							attuatore2.setStato(Stato.SPENTO);
							for(int i=0; i<immobile.getListaRegole().size(); i++)
							{
								if(immobile.getRegola(i).cercaAttuatore(attuatore2))
									immobile.getRegola(i).setStato(StatoRegola.DISABILITATA);
							}
						}
						break;
				}
				
			}while(scelta!=0);
			
		}
		
		return immobile;
	}

}
