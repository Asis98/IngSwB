package regole;

import categorie.Stato;
import rilevazione.Sensore;

public interface Antecedente{

	public boolean valutaAntecedente();
	
	public <T> void setAntecedente(T operatore_a, String operatoreRelazionale, T operatore_b);
	
	public String stampaAntecedente();
	
	
}
