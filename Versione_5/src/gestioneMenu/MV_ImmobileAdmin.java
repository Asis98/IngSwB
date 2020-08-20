package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_ImmobileAdmin {

    private static Map<Integer, MenuCommand> gestioneImmobileManutentore = new HashMap<>();
    static {
    	gestioneImmobileManutentore.put(Case.INSERISCI_UNITA_DOMOTICA, new MenuManutentore());
    	gestioneImmobileManutentore.put(Case.INSERISCI_ATTUATORE, new MenuFruitore());
    	gestioneImmobileManutentore.put(Case.INSERISCI_SENSORE, new MenuManutentore());
    	gestioneImmobileManutentore.put(Case.VISUALIZZA_UNITA_IMMOBILIARI, new MenuFruitore());
    	gestioneImmobileManutentore.put(Case.VISUALIZZA_CATEGORIE, new MenuManutentore());
    	gestioneImmobileManutentore.put(Case.STAMPA_SENSORI_ATTUATORI, new MenuFruitore());
    	gestioneImmobileManutentore.put(Case.CARICA_REGOLE, new MenuFruitore());
    	//per esci utilizzeremo un do while
    }
    
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(gestioneImmobileManutentore.get(operator));
    }
    
}
