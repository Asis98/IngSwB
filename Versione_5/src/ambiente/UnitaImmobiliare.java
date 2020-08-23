package ambiente;

import java.io.Serializable;
import costanti.Costanti;

public class UnitaImmobiliare implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String destinazione;
	
	public UnitaImmobiliare(String _destinazione)
	{
		this.destinazione= _destinazione;
	}

	public UnitaImmobiliare()
	{
		this.destinazione= Costanti.STRINGA_VUOTA;
	}
	
	public void setdestinazione(String _destinazione)
	{
		destinazione= _destinazione;
	}
	
	public String getdestinazione()
	{
		return destinazione;
	}

}
