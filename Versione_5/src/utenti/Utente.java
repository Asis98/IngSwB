package utenti;

import ambiente.UnitaImmobiliare;
import costanti.Costanti;

public interface Utente {

	/**
	 * Gets the nome utente.
	 * 
	 * @pre: -
	 * @post: -
	 * @return nomeUtente
	 */
	public String getNomeUtente();
	
	/**
	 * Sets the nome utente.
	 *
	 * @param nomeUtente the new nome utente
	 * @pre: nomeUtente!=null
	 * @post: -
	 */
	public void setNomeUtente(String nomeUtente);
	
	public void menuPersonalizzato();
	
	public void gestioneImmobileView();
	
}
