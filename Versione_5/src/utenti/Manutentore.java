package utenti;

import ambiente.UnitaImmobiliare;
import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MV_ImmobileAdmin;
import gestioneMenu.MV_Manutentore;
import gestioneMenu.MV_User;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import utility.MyMenu;

public class Manutentore implements Utente{
	
private String nomeUtente;
	
	/**
	 * Instantiates a new utente.
	 *
	 * @param nomeUtente 
	 * @pre: nomeUtente!=null
	 * @post: -
	 * @invariant: nomeUtente!=null 
	 */
	public Manutentore(String nomeUtente)
	{
		this.nomeUtente = nomeUtente;
	}
	
	/**
	 * Instantiates a new utente.
	 */
	public Manutentore()
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
		MyMenu menuManutentore= new MyMenu(TitoliMenu.TITOLOMANUTENTORE,VociMenu.VOCIMANUTENTORE);
		menuManutentore.stampaMenu();
		
		MenuCommand targetOperation = MV_Manutentore
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui();
	}

	@Override
	public void gestioneImmobileView() {
		// TODO Auto-generated method stub
		MyMenu gestioneImmobileManutentore = new MyMenu(TitoliMenu.TITOLOMENUIMMOBILE, VociMenu.VOCIIMMOBILE);
		gestioneImmobileManutentore.stampaMenu();
		
		MenuCommand targetOperation = MV_ImmobileAdmin
			      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		
		targetOperation.esegui();
		
		
	}
	

}
