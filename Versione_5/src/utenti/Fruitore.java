package utenti;

import costanti.Costanti;

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
		
	}

	@Override
	public void gestioneImmobileView() {
		// TODO Auto-generated method stub
		
	}
	

}
