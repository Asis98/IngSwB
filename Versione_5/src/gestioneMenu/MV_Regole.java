package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_Regole {

	private static Map<Integer, MenuCommand> scelta_regole = new HashMap<>();
    static {
    	scelta_regole.put(Case.REGOLE_CON_DUE_SENSORI, new M_Regole_Due_Sensori());
    	scelta_regole.put(Case.REGOLE_SENSORE_COSTANTE, new M_Regole_Sensore_Costante());
    	scelta_regole.put(Case.REGOLE_SENSORE_TIME, new M_Regole_Sensore_Time());
    }
       
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(scelta_regole.get(operator));
    }
}
