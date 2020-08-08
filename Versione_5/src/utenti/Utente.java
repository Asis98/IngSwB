package utenti;

import costanti.Costanti;

public class Utente {

	private String nomeUtente;
	
	/**
	 * Instantiates a new utente.
	 *
	 * @param nomeUtente 
	 * @pre: nomeUtente!=null
	 * @post: -
	 * @invariant: nomeUtente!=null 
	 */
	public Utente(String nomeUtente)
	{
		this.nomeUtente = nomeUtente;
	}
	
	/**
	 * Instantiates a new utente.
	 */
	public Utente()
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
	
}
