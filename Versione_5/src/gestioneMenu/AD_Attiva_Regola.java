package gestioneMenu;

import ambiente.Immobile;
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.StatoRegola;
import sistema_domotico.ControllerAbleDisable;

public class AD_Attiva_Regola implements MenuCommand
{
	DatiUtente input= new DatiUtente();
	ControllerAbleDisable controller= new ControllerAbleDisable();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
		/*if(immobile.contaRegoleDisabilitate() == Costanti.MIN)
		{
			System.out.println("Non esistono regole disabilitate");
		}
		else
		{
			Visualizzazione.visualizzaRegoleDisattive(immobile);
			int sceltaRegola = input.leggiIntero(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_ATTIVARE, 
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
		}*/
		
		//Immobile immobile;
		
		if(immobile.getListaRegole().contaRegoleDisabilitate() == Costanti.MIN)
		{
			System.out.println("Non esistono regole disabilitate");
		}
		else
		{
			Visualizzazione.visualizzaRegoleDisattive(immobile);
			int sceltaRegola = input.leggiIntero(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_ATTIVARE, 
							Costanti.MIN, immobile.getListaRegole().contaRegoleDisabilitate()-1);
			//verificare che le istanze di sensori e attuatori siano attive
			if(controller.valutaSensoriDisattivi(immobile,sceltaRegola))
			{
				System.out.println(Messaggi.AVVISO_SENSORI_DISATTIVI);
			}
			else if(controller.valutaAttuatoriDisattivi(immobile,sceltaRegola))
			{
				//cerco se ci sono attuatori disattivi
				System.out.println(Messaggi.AVVISO_ATTUATORI_DISATTIVI);
			}
			else
			{
				//immobile.getListaRegole().getRegola(sceltaRegola).setStato(StatoRegola.ABILITATA);
				immobile.getNumeroRegola(sceltaRegola).setStato(StatoRegola.ABILITATA);
				System.out.println(Messaggi.REGOLA_ABILITATA);
			}
		}
		
		
	}
}
