package sistema_domotico;

import ambiente.Immobile;
import ambiente.UnitaImmobiliare;
import categorie.Stato;
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MV_AD_Regole;
import gestioneMenu.MV_AD_Unita_Rilevazione;
import gestioneMenu.MV_Fruitore;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.AntecedenteSensore;
import regole.StatoRegola;
import rilevazione.Attuatore;
import rilevazione.Sensore;

public class ControllerAbleDisable 
{
	
	private UserInterface view= new UserInterface();
	private ModelAbleDisable model= new ModelAbleDisable();
	private ControllerVisualizzazione controllerVisualizzazione= new ControllerVisualizzazione ();
	
	
	
	public Immobile gestisciStatoUnitaRilevazione(Immobile immobile)
	{
		if(!immobile.getListaAttuatori().isEmpty() && !immobile.getListaSensori().isEmpty())
		{
			int scelta;
			view.AD_stampa_menu_unita_rilevazione();
			do {
				scelta= view.inputLeggiIntero(Messaggi.SCEGLI_VOCE);
		
				MenuCommand targetOperation = MV_AD_Unita_Rilevazione
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
				targetOperation.esegui();
		    } while(scelta!=0);
		}
		return immobile;
	}
	
	public  Immobile gestisciStatoRegole(Immobile immobile)
	{
		if(!immobile.getListaRegole().isEmptyRegole())
		{
			int scelta;
			view.AD_stampa_menu_regole();
			do {
				scelta= view.inputLeggiIntero(Messaggi.SCEGLI_VOCE);
				MenuCommand targetOperation = MV_AD_Regole
				      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
			
			targetOperation.esegui();
			} while(scelta!=0);
		}
		else 
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA);
			
		return immobile;
	}
	
	public void esegui_attivaAttuatore(Immobile immobile)
	{

		if(immobile.getAttuatoriDisattivi().size() == 0)
		{
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
		
			view.stampaMessaggio(immobile.getListaAttuatori().stampaListaUnitaDisattive());
			int	numeroAttuatore = view.inputLeggiInteroIntervallo(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.getAttuatoriDisattivi().size()-1);
			Attuatore attuatore = (Attuatore) immobile.getNumeroAttuatoreDisattivo(numeroAttuatore);
			attuatore.setStato(Stato.ACCESO);
			model.model_attivaRegola(immobile, attuatore);
		}
	}
	
	public void esegui_attivaRegola(Immobile immobile)
	{
		if(immobile.getListaRegole().contaRegoleDisabilitate() == Costanti.MIN)
		{
			view.stampaMessaggio(Messaggi.MESSAGGIO_INESISTENZA_REGOLE_DISABILITATE);
		}
		else
		{
			controllerVisualizzazione.visualizzaRegoleDisattive(immobile);
			int sceltaRegola = view.inputLeggiInteroIntervallo(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_ATTIVARE, 
							Costanti.MIN, immobile.getListaRegole().contaRegoleDisabilitate()-1);
			//verificare che le istanze di sensori e attuatori siano attive
			if(model.model_valutaSensoriDisattivi(immobile,sceltaRegola))
			{
				view.stampaMessaggio(Messaggi.AVVISO_SENSORI_DISATTIVI);
			}
			else if(model.model_valutaAttuatoriDisattivi(immobile,sceltaRegola))
			{
				//cerco se ci sono attuatori disattivi
				view.stampaMessaggio(Messaggi.AVVISO_ATTUATORI_DISATTIVI);
			}
			else
			{
				immobile.getNumeroRegola(sceltaRegola).setStato(StatoRegola.ABILITATA);
				view.stampaMessaggio(Messaggi.REGOLA_ABILITATA);
			}
		}
	}
	
	public void esegui_attivaSensore(Immobile immobile)
	{
		if (immobile.getSensoriDisattivi().size() == 0)
		{
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
			view.stampaMessaggio(immobile.getListaSensori().stampaListaUnitaDisattive());
			int	numeroSensore = view.inputLeggiInteroIntervallo(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.getSensoriDisattivi().size()-1);
			Sensore sensore = (Sensore) immobile.getNumeroSensoreDisattivo(numeroSensore);
			sensore.setStato(Stato.ACCESO);
			model.model_attivaRegola(immobile, sensore);
		}
	}
	
	public void esegui_disattivaAttuatore(Immobile immobile)
	{
		if(immobile.getAttuatoriAttivi().size() == 0)
		{
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{	
			view.stampaMessaggio(immobile.getListaAttuatori().stampaListaUnitaAttive());
		 
			int	numeroAttuatoreDaDisattivare = view.inputLeggiInteroIntervallo(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.getAttuatoriAttivi().size()-1);
			Attuatore attuatore2 = (Attuatore) immobile.getNumeroAttuatoreAttivo(numeroAttuatoreDaDisattivare);
			attuatore2.setStato(Stato.SPENTO);
			model.model_disattivaRegola(immobile, attuatore2);
		}
	}
	
	public void esegui_disattivaRegola(Immobile immobile)
	{
		 if(immobile.getListaRegole().contaRegoleAbilitate() == Costanti.MIN)
			{
				System.out.println(Messaggi.MESSAGGIO_INESISTENZA_REGOLE_ABILITATE);
			}
			else
			{
				controllerVisualizzazione.visualizzaRegoleAttive(immobile);
				int sceltaRegolaAbilitata = view.inputLeggiInteroIntervallo(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_DISATTIVARE, 
								Costanti.MIN, immobile.getListaRegole().contaRegoleAbilitate()-1);
					immobile.getNumeroRegola(sceltaRegolaAbilitata).setStato(StatoRegola.DISABILITATA);
					view.stampaMessaggio(Messaggi.REGOLA_DISABILITATA);		
			}
	}
	
	public void esegui_disattivaSensore(Immobile immobile)
	{
		if(immobile.getSensoriAttivi().size() == 0)
		{
			view.stampaMessaggio(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
			view.stampaMessaggio(immobile.getListaSensori().stampaListaUnitaAttive());
		
			int	numeroSensoreDaDisattivare = view.inputLeggiInteroIntervallo(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.getSensoriAttivi().size()-1);
			Sensore sensore2 = (Sensore) immobile.getNumeroSensoreAttivo(numeroSensoreDaDisattivare);
			sensore2.setStato(Stato.SPENTO);
			model.model_disattivaRegola(immobile, sensore2);
		}
	}
}
