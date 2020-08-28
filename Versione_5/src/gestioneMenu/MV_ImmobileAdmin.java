package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_ImmobileAdmin {

    private static Map<Integer, MenuCommand> gestioneImmobileManutentore = new HashMap<>();
    static {
    	gestioneImmobileManutentore.put(Case.INSERISCI_UNITA_DOMOTICA, new M_Inserisci_Unita_Domotica());
    	gestioneImmobileManutentore.put(Case.INSERISCI_ATTUATORE, new M_Inserisci_Attuatore());
    	gestioneImmobileManutentore.put(Case.INSERISCI_SENSORE, new M_Inserisci_Sensore());
    	gestioneImmobileManutentore.put(Case.VISUALIZZA_UNITA_IMMOBILIARI, new M_Visualizza_Unita_Immobiliari());
    	gestioneImmobileManutentore.put(Case.VISUALIZZA_CATEGORIE, new M_Visualizza_Categorie());
    	gestioneImmobileManutentore.put(Case.STAMPA_SENSORI_ATTUATORI, new M_Stampa_Sensori_Attuatori());
    	gestioneImmobileManutentore.put(Case.CARICA_REGOLE, new M_Carica_Regole());
    	gestioneImmobileManutentore.put(Case.ESCI, new MenuManutentore());
    }
    
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(gestioneImmobileManutentore.get(operator));
    }
    
}
