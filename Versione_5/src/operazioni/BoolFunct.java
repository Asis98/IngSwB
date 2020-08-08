package operazioni;

import categorie.Valore;

public interface BoolFunct {
	
	/**
	 * Confronto.
	 *
	 * @param a 
	 * @param b 
	 * @pre: a!=null b!=null
	 * @return true, if successful
	 */
	boolean confronto(Valore a, Valore b);

		
}
