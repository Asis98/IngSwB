package gestioneMenu;

import ambiente.Immobile;
import categorie.Stato;
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.StatoRegola;
import rilevazione.Attuatore;

public class AD_Disattiva_Attuatore implements MenuCommand 
{
	DatiUtente input= new DatiUtente();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
	/*	if(immobile.sizeAttuatoriAttivati() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{	
			System.out.println(immobile.stampaListaAttuatoriAttivi());
			int	numeroAttuatoreDaDisattivare = input.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.sizeAttuatoriAttivati()-1);
			Attuatore attuatore2 = (Attuatore) immobile.getListaAttuatoriAttivi().get(numeroAttuatoreDaDisattivare);
			attuatore2.setStato(Stato.SPENTO);
			for(int i=0; i<immobile.getListaRegole().size(); i++)
			{
				if(immobile.getRegola(i).cercaAttuatore(attuatore2))
					immobile.getRegola(i).setStato(StatoRegola.DISABILITATA);
			}
		}*/
		
	//	Immobile immobile;
		//if(immobile.getListaAttuatori().getListaUnitaAttive().size() == 0)
		if(immobile.getAttuatoriAttivi().size() == 0)
		{
			System.out.println(Messaggi.MESSAGGIO_LISTA_VUOTA);
		}
		else
		{	
			System.out.println(immobile.getListaAttuatori().stampaListaUnitaAttive());
		  //int	numeroAttuatoreDaDisattivare = input.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.getListaAttuatori().getListaUnitaAttive().size()-1);
			int	numeroAttuatoreDaDisattivare = input.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.getAttuatoriAttivi().size()-1);
			//Attuatore attuatore2 = (Attuatore) immobile.getListaAttuatori().getListaUnitaAttive().get(numeroAttuatoreDaDisattivare);
			Attuatore attuatore2 = (Attuatore) immobile.getNumeroAttuatoreAttivo(numeroAttuatoreDaDisattivare);
			attuatore2.setStato(Stato.SPENTO);
			for(int i=0; i<immobile.getListaRegole().contaRegoleAbilitate(); i++)
			{
				if(immobile.getNumeroRegola(i).cercaAttuatore(attuatore2))
					immobile.getNumeroRegola(i).setStato(StatoRegola.DISABILITATA);
			}
		}
		
	}
}
