package operazioni;

import categorie.Valore;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;

public class Uguale implements BoolFunct {
	
	/**
	 * Confronto.
	 *
	 * @param a 
	 * @param b 
	 * @pre: a!=null b!=null
	 * @post: if(a instanceof ValoreNonNumerico) a.getValore()!=null && b.getValore()!=null
	 * else ((ValoreNumerico)a).getValore()!=null && ((ValoreNumerico) b).getValore()!=null
	 * @return true, if successful
	 */
	@Override
	public boolean confronto(Valore a, Valore b) 
	{
		if(a instanceof ValoreNonNumerico)
		{
			return a.getValore().equals(b.getValore());
		}
		else return ((ValoreNumerico)a).getValore() == ((ValoreNumerico) b).getValore() ? true : false;
	}

}
