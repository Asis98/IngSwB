package operazioni;

import categorie.Valore;
import categorie.ValoreNumerico;

public class Minore implements BoolFunct {
	
	/**
	 * Confronto.
	 *
	 * @param a 
	 * @param b 
	 * @pre: a!=null b!=null
	 * @post:((ValoreNumerico)a).getValore()!=null && ((ValoreNumerico) b).getValore()!=null
	 * @return true, if successful
	 */
	@Override
	public boolean confronto(Valore a, Valore b) {
		return ((ValoreNumerico)a).getValore() < ((ValoreNumerico) b).getValore() ? true : false;
	}
	

}
