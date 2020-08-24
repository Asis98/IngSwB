package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;
import gestioneMenu.M_Visualizza_Categorie;
import gestioneMenu.M_Visualizza_Unita_Immobiliari;
import gestioneMenu.MenuCommand;
import gestioneMenu.MenuImmobileFruitore;

public class MV_AD_Unita_Rilevazione 
{
	
	private static Map<Integer, MenuCommand> menuAbleDisable = new HashMap<>();
    static 
    {
    	menuAbleDisable.put(Case.ATTIVA_SENSORE, new AD_Attiva_Sensore());
    	menuAbleDisable.put(Case.ATTIVA_ATTUATORE, new AD_Attiva_Attuatore());
    	menuAbleDisable.put(Case.DISATTIVA_SENSORE, new AD_Disattiva_Sensore());
    	menuAbleDisable.put( Case.DISATTIVA_ATTUATORE, new AD_Disattiva_Attuatore());
    }
       
   
	public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(menuAbleDisable.get(operator));
    }
}
