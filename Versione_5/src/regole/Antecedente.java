package regole;

import categorie.Stato;
import rilevazione.Sensore;

public interface Antecedente{

	/**
	 * Valuta antecedente.
	 * 
	 * @pre: - 
	 * @post: -
	 * @return true, if successful
	 */
	public boolean valutaAntecedente();
	
	/**
	 * Sets the antecedente.
	 *
	 * @param <T> the generic type
	 * @param operatore_a 
	 * @param operatoreRelazionale 
	 * @param operatore_b 
	 * @pre: operatore_a!=null && operatoreRelazionale!=null && operatore_b!=null
	 */
	public <T> void setAntecedente(T operatore_a, String operatoreRelazionale, T operatore_b);
	
	/**
	 * Stampa antecedente.
	 *
	 * @pre: -
	 * @post: -
	 * @return the string
	 */
	public String stampaAntecedente();
	
	
}
