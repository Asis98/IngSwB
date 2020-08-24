package gestioneMenu;
import ambiente.Immobile; 
import costanti.Costanti;
import costanti.Messaggi;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import regole.StatoRegola;

public class AD_Disattiva_Regola implements MenuCommand
{
	DatiUtente input= new DatiUtente();
	
	@Override
	public void esegui() {
		// TODO Auto-generated method stub
		
		//Immobile immobile;
	   /*if(immobile.contaRegoleAbilitate() == Costanti.MIN )
		{
			System.out.println("Non esistono regole abilitate");
		}
		else
		{
			Visualizzazione.visualizzaRegoleAttive(immobile);
			int sceltaRegolaAbilitata = input.leggiIntero(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_DISATTIVARE, 
							Costanti.MIN, immobile.contaRegoleAbilitate()-1);
				immobile.getRegola(sceltaRegolaAbilitata).setStato(StatoRegola.DISABILITATA);
				System.out.println(Messaggi.REGOLA_DISABILITATA);		
		}*/
		
		   // Immobile immobile;
		    if(immobile.getListaRegole().contaRegoleAbilitate() == Costanti.MIN)
			{
				System.out.println("Non esistono regole abilitate");
			}
			else
			{
				Visualizzazione.visualizzaRegoleAttive(immobile);
				int sceltaRegolaAbilitata = input.leggiIntero(Messaggi.INSERISCI_IL_NUMERO_DELLA_REGOLA_DA_DISATTIVARE, 
								Costanti.MIN, immobile.getListaRegole().contaRegoleAbilitate()-1);
					immobile.getNumeroRegola(sceltaRegolaAbilitata).setStato(StatoRegola.DISABILITATA);
					System.out.println(Messaggi.REGOLA_DISABILITATA);		
			}
		
	}
}
