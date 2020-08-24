package gestioneMenu;

import ambiente.Immobile;
import categorie.Stato;
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.StatoRegola;
import rilevazione.Sensore;

public class AD_Attiva_Sensore implements MenuCommand
{
	DatiUtente input= new DatiUtente();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
		/*if(immobile.sizeSensoriDisattivati() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
			System.out.println(immobile.stampaListaSensoriDisattivi());
			int	numeroSensore = input.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.sizeSensoriDisattivati()-1);
			Sensore sensore = (Sensore) immobile.getListaSensoriDisattivi().get(numeroSensore);
			sensore.setStato(Stato.ACCESO);
			
			for(int i=0; i<immobile.getListaRegole().size(); i++)*/
			{
				/*attivo una regola <-> in un suo antecedente è contenuto sensore e tutti gli attuatori
				 * contenuti nei conseguenti sono attivi
				 * e tutti i sensori contenuti nei conseguenti sono attivi
				 */
			/*	if(immobile.getRegola(i).cercaSensore(sensore) && immobile.getRegola(i).antecedentiAttivi() && immobile.getRegola(i).conseguentiAttivi())
					immobile.getRegola(i).setStato(StatoRegola.ABILITATA);
			}
		}*/
		
		//Immobile immobile;
		//if(immobile.getListaSensori().getListaUnitaDisattive().size() == 0)
		if (immobile.getSensoriDisattivi().size() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
			System.out.println(immobile.getListaSensori().stampaListaUnitaDisattive());
		//	int	numeroSensore = input.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.getListaSensori().getListaUnitaDisattive().size()-1);
			int	numeroSensore = input.leggiIntero(Messaggi.SCEGLI_UN_SENSORE, Costanti.MIN, immobile.getSensoriDisattivi().size()-1);
		//	Sensore sensore = (Sensore) immobile.getListaSensori().getListaUnitaDisattive().get(numeroSensore);
			Sensore sensore = (Sensore) immobile.getNumeroSensoreDisattivo(numeroSensore);
			sensore.setStato(Stato.ACCESO);
			
			for(int i=0; i<immobile.getListaRegole().contaRegoleDisabilitate(); i++)
			{
				/*attivo una regola <-> in un suo antecedente è contenuto sensore e tutti gli attuatori
				 * contenuti nei conseguenti sono attivi
				 * e tutti i sensori contenuti nei conseguenti sono attivi
				 */
				if(immobile.getNumeroRegola(i).cercaSensore(sensore) && immobile.getNumeroRegola(i).antecedentiAttivi() && immobile.getNumeroRegola(i).conseguentiAttivi())
					immobile.getNumeroRegola(i).setStato(StatoRegola.ABILITATA);
			}
		}
		
	}
 }
}
