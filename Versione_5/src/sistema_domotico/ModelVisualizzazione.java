package sistema_domotico;

import ambiente.Artefatto;
import ambiente.Immobile;
import regole.Regola;
import regole.StatoRegola;

public class ModelVisualizzazione {
	
	public boolean visualizzazioneUnitaImmobiliari(Immobile immobile, int i)
	{
		if(immobile.getUnitList().getUnitaDomotica(i) instanceof Artefatto && ((Artefatto)immobile.getUnitList().getUnitaDomotica(i)).getStanza() != null )
		{
			return true;
		}
		return false;
	
	}
	
	public boolean sizeValoriSensori(Immobile immobile)
	{
		if(immobile.getListaSensori().size()!=0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isEmptyAttuatori(Immobile immobile)
	{
		if(!immobile.getListaAttuatori().isEmpty())
		{
			return true;
		}
		return false;
	}
	
	public boolean isEmptySensori(Immobile immobile)
	{
		if(!immobile.getListaAttuatori().isEmpty())
		{
			return true;
		}
		return false;
	}
	
	public boolean controllaRegolaAttiva(Regola elemento)
	{
		if(elemento.getStato() == StatoRegola.ABILITATA)
		{
			return true;
		}
		return false;
	}
	
	public boolean controllaRegolaDisattiva(Regola elemento)
	{
		if(elemento.getStato() == StatoRegola.DISABILITATA)
		{
			return true;
		}
		return false;
	}

}
