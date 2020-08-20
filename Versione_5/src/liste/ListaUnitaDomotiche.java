package liste;

import java.util.ArrayList;

import ambiente.UnitaDomotica;
import ambiente.UnitaImmobiliare;

public class ListaUnitaDomotiche {

	private ArrayList<UnitaDomotica> unitList;
	
	public ListaUnitaDomotiche ()
	{
		unitList = new ArrayList <UnitaDomotica>();
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA UNITA' DOMOTICHE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	

	/**
	 * Adds the unita domotica.
	 *
	 * @param unitaDomotica the unita domotica
	 * @pre: unitaDomotica!=null
	 * @post: unitList.size() = unitList.size()@pre + 1
	 * @post: @forall k : [0..size()-2]
	 * (k < (unitList.size()-1)) ==> element(k)@pre == element(k))
	 */
	public void addUnitaDomotica(UnitaDomotica unitaDomotica)
	{
		unitList.add(unitaDomotica);
	}
	
	/**
	 * Verifica presenza unita domotica.
	 *
	 * @param nomeUnita the nome unita
	 * @param classType the class type
	 * @return true, if successful
	 * @pre: nomeUnita!=null && classType!=null
	 * @post: -
	 */
	public boolean verificaPresenzaUnitaDomotica(String nomeUnita, Class<?> classType)
	{
		for (int i=0;i< unitList.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(unitList.get(i).getUnitName().equalsIgnoreCase(nomeUnita) && (classType.isInstance(unitList.get(i))))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Stampa lista.
	 *
	 * @return the String
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaLista()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<unitList.size(); i++)
		{
			sb.append(i);
			sb.append("-");
			sb.append(unitList.get(i).getUnitName());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Gets the unita domotica.
	 *
	 * @param i the i
	 * @return the unita domotica
	 * @pre: i>=0
	 * @post: unitList.get(i)!=null
	 */
	public UnitaDomotica getUnitaDomotica(int i)
	{
		return unitList.get(i);
	}
	
	/**
	 * Size.
	 *
	 * @return unitList.size()
	 * @pre: -
	 * @post: unitList.size()>=0
	 */
	public int size()
	{
		return unitList.size();
	}
	
	/**
	 * Gets the unit list.
	 *
	 * @return unitList
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<UnitaDomotica> getUnitList()
	{
		return unitList;
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmpty()
	{
		return unitList.isEmpty();
	}


	
	
}
