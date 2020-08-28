package gestioneMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import costanti.Case;

public class MV_StanzaArtefatto {

	private static Map<Integer, MenuCommand> scelta_stanza_artefatto = new HashMap<>();
    static {
    	scelta_stanza_artefatto.put(Case.INSERISCI_STANZA, new M_Inserisci_Stanza());
    	scelta_stanza_artefatto.put(Case.INSERISCI_ARTEFATTO, new M_Inserisci_Artefatto());
    	scelta_stanza_artefatto.put(Case.ESCI, new MenuImmobileAdmin());
    }
       
    public static Optional<MenuCommand> getOperation(Integer operator) 
    {
        return Optional.ofNullable(scelta_stanza_artefatto.get(operator));
    }
}
