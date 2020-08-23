package operazioni;

import categorie.Valore;
import categorie.ValoreNumerico;

public class MaggioreUguale implements BoolFunct {
	
	@Override
	public boolean confronto(Valore a, Valore b) {
		return ((ValoreNumerico)a).getValore() >= ((ValoreNumerico) b).getValore() ? true : false;
	}
	
}
