package operazioni;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Costanti;

public class BooleanOperator {
	
	
	private static Map<String, BoolFunct> operationMap = new HashMap<>();
	    static {
	        operationMap.put(Costanti.MAGGIORE, new Maggiore());
	        operationMap.put(Costanti.MAGGIORE_UGUALE, new MaggioreUguale());
	        operationMap.put(Costanti.MINORE, new Minore());
	        operationMap.put(Costanti.MINORE_UGUALE, new MinoreUguale());
	        operationMap.put(Costanti.UGUALE, new Uguale());
	        // more operators
	    }
	 
    	public static Optional<BoolFunct> getOperation(String operator) 
	    {
	        return Optional.ofNullable(operationMap.get(operator));
	    }
	    
    	public static String stampaOperatore()
	    {
	    	int i =0;
			StringBuilder sb = new StringBuilder();
			 for (String variableName : operationMap.keySet())
		      {
		            String variableKey = variableName;
		            sb.append(i);
		            sb.append(Costanti.TRATTINO);
		            sb.append(variableKey);
		            sb.append("\n");
		            i++;
		      }
			 return sb.toString();
	    }

}
