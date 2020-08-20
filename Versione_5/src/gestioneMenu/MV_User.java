package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_User {
	
	private static Map<Integer, MenuCommand> scelta_menu_utente = new HashMap<>();
    static {
    	scelta_menu_utente.put(Case.MANUTENTORE, new MenuManutentore());
    	scelta_menu_utente.put(Case.FRUITORE, new MenuFruitore());
    	scelta_menu_utente.put(Case.ESCI, new M_Esci());
    }
       
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(scelta_menu_utente.get(operator));
    }
  

}
