package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_Fruitore {

	private static Map<Integer, MenuCommand> menuFruitore = new HashMap<>();
    static 
    {
    	menuFruitore.put(Case.VISUALIZZA_LISTA_UNITA_IMMOBILIARI, new M_Visualizza_Unita_Immobiliari());
    	menuFruitore.put(Case.GESTISCI_IMMOBILE_COME_FRUITORE, new MenuImmobileFruitore());
    	menuFruitore.put(Case.VISUALIZZA_CATEGORIE_SENSORI_ATTUATORI, new M_Visualizza_Categorie());
    }
       
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(menuFruitore.get(operator));
    }
}
