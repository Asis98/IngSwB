package ambiente;

import java.io.Serializable;

import costanti.Costanti;


public abstract class UnitaDomotica implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String unitName;
	
	/**
	 * Instantiates a new unita domotica.
	 *
	 * @param unitName 
	 * @pre: unitName!=null
	 * @post:-
	 * @invariant: unitName!=null
	 */
	public UnitaDomotica(String unitName)
	{
		this.unitName = unitName;
	}
	
	/**
	 * Instantiates a new unita domotica, with no unitName
	 * @pre: -
	 * @post:-
	 * @invariant: unitName!=null
	 */
	public UnitaDomotica()
	{
		this.unitName = Costanti.STRINGA_VUOTA;
	}
	
	/**
	 * Sets the unit name.
	 *
	 * @param unitName 
	 * @pre: unitName!=null
	 * @post: -
	 */
	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}
	
	/**
	 * Gets the unit name.
	 *
	 * @pre: -
	 * @post: -
	 * @return unitName
	 */
	public String getUnitName()
	{
		return unitName;
	}
	
		
}
