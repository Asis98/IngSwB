package gestioneMenu;

import ambiente.Immobile;
import costanti.Costanti; 
import costanti.Messaggi;
import costanti.NomiFile;
import inputUtente.DatiUtente;
import inputUtente.InputDati;
import liste.ListaImmobili;
import utenti.Fruitore;

public class MenuImmobileFruitore implements MenuCommand {

	private Fruitore fruitore= new Fruitore();
	
	@Override
	public void esegui() {
		
		//ListaImmobili listaUnitaImmobiliari;
		//Immobile immobile;
		// TODO Auto-generated method stub
		DatiUtente input= new DatiUtente();
		if(listaUnitaImmobiliari.isEmpty())
		{
			System.out.println(Messaggi.LISTA_IMMOBILI_VUOTA);
		}
		else {
				System.out.println(listaUnitaImmobiliari.stampaListaUnitaImmobiliari());
				int numeroUnitaImmobiliare = input.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_IMMOBILE, Costanti.MIN, ((listaUnitaImmobiliari.size())-1));
				//immobile = listaUnitaImmobiliari.getUnitaImmobiliare(numeroUnitaImmobiliare);
				immobile = listaUnitaImmobiliari.getImmobile(numeroUnitaImmobiliare);
				
				fruitore.gestioneImmobileView();
				
		}
		
	}
}
