package sistema_domotico;

import ambiente.Artefatto;
import ambiente.Stanza;
import ambiente.UnitaImmobiliare;
import costanti.Costanti;
import costanti.Messaggi;

public class ModelVisualizzazione {
	
	public boolean visualizzazioneUnitaImmobiliari(UnitaImmobiliare immobile, int i)
	{
		if(immobile.getUnitaDomotica(i) instanceof Artefatto && ((Artefatto)immobile.getUnitaDomotica(i)).getStanza() != null )
		{
			return true;
		}
		return false;
	
	}
	
	public boolean sizeValoriSensori(UnitaImmobiliare immobile)
	{
		if(immobile.sizeSensori()!=0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isEmptyAttuatori(UnitaImmobiliare immobile)
	{
		if(!immobile.isEmptyAttuatori())
		{
			return true;
		}
		return false;
	}
	
	public boolean isEmptySensori(UnitaImmobiliare immobile)
	{
		if(!immobile.isEmptySensori())
		{
			return true;
		}
		return false;
	}

}
