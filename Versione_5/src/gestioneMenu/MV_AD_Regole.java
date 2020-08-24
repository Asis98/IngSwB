package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;
import gestioneMenu.MenuCommand;

public class MV_AD_Regole 
{
	private static Map<Integer, MenuCommand> menuAbleDisableRegole = new HashMap<>();
    static 
    {
    	menuAbleDisableRegole.put(Case.ATTIVA_REGOLA, new AD_Attiva_Regola());
    	menuAbleDisableRegole.put(Case.DISATTIVA_REGOLA, new AD_Disattiva_Regola());
    }
       
   
	public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(menuAbleDisableRegole.get(operator));
    }
}
