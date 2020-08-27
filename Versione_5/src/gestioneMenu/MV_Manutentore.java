package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_Manutentore {

	private static Map<Integer, MenuCommand> menuManutentore = new HashMap<>();
    static {
    	menuManutentore.put(Case.CARICA_CATEGORIE_SENSORI, new M_Carica_Categorie_Sensori());
    	menuManutentore.put(Case.CARICA_CATEGORIE_ATTUATORI, new M_Carica_Categorie_Attuatori());
    	menuManutentore.put(Case.CARICA_UNITA_IMMOBILIARI, new M_Carica_Unita_Immobiliari());
    	menuManutentore.put(Case.INSERISCI_UNITA_IMMOBILIARE, new M_Inserisci_Unita_Immobiliare());
    	menuManutentore.put(Case.GESTISCI_IMMOBILE_COME_MANUTENTORE, new MenuImmobileAdmin());
    	menuManutentore.put(Case.STAMPA_UNITA_IMMOBILIARI, new M_Stampa_Unita_Immobiliari());
    	menuManutentore.put(Case.INSERISCI_CATEGORIA_ATTUATORE, new M_inserisci_Categoria_Attuatore());
    	menuManutentore.put(Case.INSERISCI_CATEGORIA_SENSORE, new M_Inserisci_Categoria_Sensore());
    	menuManutentore.put(Case.STAMPA_CATEGORIE, new M_Stampa_Categorie());
    	menuManutentore.put(Case.ESCI, new MenuHome());
    }
       
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(menuManutentore.get(operator));
    }
}
