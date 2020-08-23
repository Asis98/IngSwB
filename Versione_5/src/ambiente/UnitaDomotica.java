package ambiente;

import java.io.Serializable;

import costanti.Costanti;


public abstract class UnitaDomotica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String unitName;
	
	public UnitaDomotica(String unitName)
	{
		this.unitName = unitName;
	}

	public UnitaDomotica()
	{
		this.unitName = Costanti.STRINGA_VUOTA;
	}

	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}
	
	public String getUnitName()
	{
		return unitName;
	}
		
}
