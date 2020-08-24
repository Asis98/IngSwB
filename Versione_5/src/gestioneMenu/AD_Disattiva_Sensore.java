package gestioneMenu;

import ambiente.Immobile;
import categorie.Stato;
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.StatoRegola;
import rilevazione.Sensore;

public class AD_Disattiva_Sensore implements MenuCommand
{
	DatiUtente input= new DatiUtente();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
		/*if(immobile.sizeSensoriAttivati() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
			System.out.println(immobile.stampaListaSensoriAttivi());
			int	numeroSensoreDaDisattivare = input.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.sizeSensoriAttivati()-1);
			Sensore sensore2 = (Sensore) immobile.getListaSensoriAttivi().get(numeroSensoreDaDisattivare);
			sensore2.setStato(Stato.SPENTO);
			for(int i=0; i<immobile.getListaRegole().size(); i++)
			{
				if(immobile.getRegola(i).cercaSensore(sensore2))
					immobile.getRegola(i).setStato(StatoRegola.DISABILITATA);
			}
		}*/
		
	//	Immobile immobile;
		//if(immobile.getListaSensori().getListaUnitaAttive().size() == 0)
		if(immobile.getSensoriAttivi().size() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
			System.out.println(immobile.getListaSensori().stampaListaUnitaAttive());
		//	int	numeroSensoreDaDisattivare = input.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.getListaSensori().getListaUnitaAttive().size()-1);
			int	numeroSensoreDaDisattivare = input.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.getSensoriAttivi().size()-1);
		//	Sensore sensore2 = (Sensore) immobile.getListaSensori().getListaUnitaAttive().get(numeroSensoreDaDisattivare);
			Sensore sensore2 = (Sensore) immobile.getNumeroSensoreAttivo(numeroSensoreDaDisattivare);
			sensore2.setStato(Stato.SPENTO);
			for(int i=0; i<immobile.getListaRegole().contaRegoleAbilitate(); i++)
			{
				if(immobile.getNumeroRegola(i).cercaSensore(sensore2))
					immobile.getNumeroRegola(i).setStato(StatoRegola.DISABILITATA);
			}
		}
		
	}
}
