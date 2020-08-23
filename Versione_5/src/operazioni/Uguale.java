package operazioni;

import categorie.Valore;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;

public class Uguale implements BoolFunct {
	
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
