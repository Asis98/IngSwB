package sistema_domotico;

import ambiente.Immobile;
import ambiente.UnitaImmobiliare;
import categorie.Stato;
import costanti.Messaggi;
import gestioneMenu.MV_AD_Regole;
import gestioneMenu.MV_AD_Unita_Rilevazione;
import gestioneMenu.MV_Fruitore;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.AntecedenteSensore;

public class ControllerAbleDisable 
{
	
	private UIAbleDisable view= new UIAbleDisable();
	private ModelAbleDisable model= new ModelAbleDisable();
	
	public boolean valutaSensoriDisattivi(Immobile immobile, int sceltaRegolaAbilitata)
	{
		return model.model_valutaSensoriDisattivi( immobile, sceltaRegolaAbilitata);
	}
	
	public boolean valutaAttuatoriDisattivi(Immobile immobile, int sceltaRegolaAbilitata)
	{
		return model.model_valutaAttuatoriDisattivi( immobile, sceltaRegolaAbilitata);
	}
	
	public Immobile gestisciStatoUnitaRilevazione(Immobile immobile)
	{
		if(!immobile.getListaAttuatori().isEmpty() && !immobile.getListaSensori().isEmpty())
		{
		view.AD_stampa_menu_unita_rilevazione();
		//do {
		MenuCommand targetOperation = MV_AD_Unita_Rilevazione
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui();
		//} while
		}
		return immobile;
	}
	
	public  Immobile gestisciStatoRegole(Immobile immobile)
	{
		if(!immobile.getListaRegole().isEmptyRegole())
		{
			view.AD_stampa_menu_regole();
			//do {
			MenuCommand targetOperation = MV_AD_Regole
				      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
			
			targetOperation.esegui();
			//} while
		}
		else 
			view.AD_messaggio_lista_vuota();
		return immobile;
	}
}
