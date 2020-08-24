package utenti;

import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MV_Fruitore;
import gestioneMenu.MV_ImmobileFruitore;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import utility.MyMenu;

public class Fruitore implements Utente{
	
private String nomeUtente;
	
	/**
	 * Instantiates a new utente.
	 *
	 * @param nomeUtente 
	 * @pre: nomeUtente!=null
	 * @post: -
	 * @invariant: nomeUtente!=null 
	 */
	public Fruitore(String nomeUtente)
	{
		this.nomeUtente = nomeUtente;
	}
	
	/**
	 * Instantiates a new utente.
	 */
	public Fruitore()
	{
		this.nomeUtente = Costanti.STRINGA_VUOTA;
	}
	
	/**
	 * Gets the nome utente.
	 * 
	 * @pre: -
	 * @post: -
	 * @return nomeUtente
	 */
	public String getNomeUtente()
	{
		return nomeUtente;
	}
	
	/**
	 * Sets the nome utente.
	 *
	 * @param nomeUtente the new nome utente
	 * @pre: nomeUtente!=null
	 * @post: -
	 */
	public void setNomeUtente(String nomeUtente)
	{
		this.nomeUtente = nomeUtente; 
	}

	@Override
	public void menuPersonalizzato() {
		// TODO Auto-generated method stub
		MyMenu menuFruitore= new MyMenu(TitoliMenu.TITOLOFRUITORE,VociMenu.VOCIMENUFRUITORE);
		menuFruitore.stampaMenu();
		
		MenuCommand targetOperation = MV_Fruitore
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui();
		
	}

	@Override
	public void gestioneImmobileView() {
		// TODO Auto-generated method stub
		
		MyMenu gestioneImmobileFruitore = new MyMenu(TitoliMenu.TITOLOMENUIMMOBILE,VociMenu.VOCISOTTOMENUFRUITORE);
		gestioneImmobileFruitore.stampaMenu();
		
		MenuCommand targetOperation = MV_ImmobileFruitore
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui();
		
		
	}
	
	

}
