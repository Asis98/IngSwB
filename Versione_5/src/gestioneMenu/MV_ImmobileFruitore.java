package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_ImmobileFruitore {

	 private static Map<Integer, MenuCommand> gestioneImmobileFruitore = new HashMap<>();
	    static {
	    	gestioneImmobileFruitore.put(Case.VISUALIZZA_DESCRIZIONE_UNITA_IMMOBILIARE, new M_Visualizza_Descrizione_Unita_Immobiliare());
	    	gestioneImmobileFruitore.put(Case.VISUALIZZA_VALORI_SENSORI, new M_Visualizza_Valori_Sensori());
	    	gestioneImmobileFruitore.put(Case.ASSEGNA_MODALITA_OPERATIVA, new M_Assegna_Modalita_Operativa());
	    	gestioneImmobileFruitore.put(Case.VISUALIZZA_ATTUATORI_MODALITA, new M_Visualizza_Attuatori_Modalita());
	    	gestioneImmobileFruitore.put(Case.CREA_REGOLA, new M_Crea_Regola());
	    	gestioneImmobileFruitore.put(Case.VISUALIZZA_REGOLE_ATTIVE, new M_Visualizza_Regole_Attive());
	    	gestioneImmobileFruitore.put(Case.ATTIVA_DISATTIVA_REGOLA, new M_Attiva_Disattiva_Regole());
	    	gestioneImmobileFruitore.put(Case.ATTIVA_DISATTIVA_UNITA_RILEVAZIONE, new M_Attiva_Disattiva_Unita_Rilevazione());
	    	//per esci utilizzeremo un do while
	    }
	    
	    public static Optional<MenuCommand> getOperation(Integer operator) 
	    {
	        return Optional.ofNullable(gestioneImmobileFruitore.get(operator));
	    }
}
