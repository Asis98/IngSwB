package gestioneMenu;

import ambiente.Immobile;
import categorie.Stato;
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.StatoRegola;
import rilevazione.Attuatore;

public class AD_Attiva_Attuatore implements MenuCommand 
{
	DatiUtente input= new DatiUtente();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
		/*if(immobile.sizeListaAttuatoriDisattivati() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
		
			System.out.println(immobile.stampaListaAttuatoriDisattivi());
			int	numeroAttuatore = input.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.sizeAttuatoriDisattivati()-1);
			Attuatore attuatore = (Attuatore) immobile.getListaAttuatoriDisattivi().get(numeroAttuatore);
			attuatore.setStato(Stato.ACCESO);
			for(int i=0; i<immobile.getListaRegole().size(); i++) 
			{
				/*attivo una regola <-> in un suo conseguente è contenuto attuatore e tutti gli attuatori
				 * contenuti nei conseguenti sono attivi
				 * e tutti i sensori contenuti nei conseguenti sono attivi
				 */
			/*	if(immobile.getRegola(i).cercaAttuatore(attuatore) && immobile.getRegola(i).antecedentiAttivi() && immobile.getRegola(i).conseguentiAttivi())
					immobile.getRegola(i).setStato(StatoRegola.ABILITATA);
			} 
		} */
		
	//	Immobile immobile;
		//if(immobile.getListaAttuatori().getListaUnitaDisattive().size() == 0)
		if(immobile.getAttuatoriDisattivi().size() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{
		
			System.out.println(immobile.getListaAttuatori().stampaListaUnitaDisattive());
		//	int	numeroAttuatore = input.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.getListaAttuatori().getListaUnitaDisattive().size()-1);
			int	numeroAttuatore = input.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.getAttuatoriDisattivi().size()-1);
		//	Attuatore attuatore = (Attuatore) immobile.getListaAttuatori().getListaUnitaDisattive().get(numeroAttuatore);
			Attuatore attuatore = (Attuatore) immobile.getNumeroAttuatoreDisattivo(numeroAttuatore);
			attuatore.setStato(Stato.ACCESO);
			for(int i=0; i<immobile.getListaRegole().contaRegoleDisabilitate(); i++) 
			{
				/*attivo una regola <-> in un suo conseguente è contenuto attuatore e tutti gli attuatori
				 * contenuti nei conseguenti sono attivi
				 * e tutti i sensori contenuti nei conseguenti sono attivi
				 */
			//	if(immobile.getListaRegole().getRegola(i).cercaAttuatore(attuatore) && immobile.getListaRegole().getRegola(i).antecedentiAttivi() && immobile.getListaRegole().getRegola(i).conseguentiAttivi())
			//		immobile.getListaRegole().getRegola(i).setStato(StatoRegola.ABILITATA);
				
				if(immobile.getNumeroRegola(i).cercaAttuatore(attuatore) && immobile.getNumeroRegola(i).antecedentiAttivi() && immobile.getNumeroRegola(i).conseguentiAttivi())
					immobile.getNumeroRegola(i).setStato(StatoRegola.ABILITATA);
			} 
		} 
		
	}
}
