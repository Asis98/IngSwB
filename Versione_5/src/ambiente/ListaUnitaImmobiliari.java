package ambiente;

import java.io.Serializable;
import java.util.ArrayList;

import costanti.Costanti;


public class ListaUnitaImmobiliari implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<UnitaImmobiliare> listaUnitaImmobiliari;
	
	/**
	 * Instantiates a new lista unita immobiliari.
	 * 
	 * @pre: -
	 * @post: -
	 * @invariant: listaUnitaImmobiliari!=null
	 */
	public ListaUnitaImmobiliari()
	{
		listaUnitaImmobiliari= new ArrayList<>();
	}

	/**
	 * Adds the unita immobiliare.
	 *
	 * @param unitaImmobiliare
	 * @pre: unitaImmobiliare!=null 
	 * @post: listaUnitaImmobiliari.size() = listaUnitaImmobiliari.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaUnitaImmobiliari.size()-1)) ==> element(k)@pre == element(k))
	 * @return true, if successful
	 */
	public boolean addUnitaImmobiliare(UnitaImmobiliare unitaImmobiliare)
	{		
		return (listaUnitaImmobiliari.contains(unitaImmobiliare)) ? false : listaUnitaImmobiliari.add(unitaImmobiliare);
	}
	
	/**
	 * Removes the unitaImmobiliare.
	 * 
	 * @param unitaImmobiliare
	 * @pre:  listaUnitaImmobiliari.size() > 0
	 * @post: listaUnitaImmobiliari.size() = listaUnitaImmobiliari.size()@pre - 1
	 * @post: @forall k : [0..size()-1] 
	 * (k < listaUnitaImmobiliari.getIndexOf(unitaImmobiliare) ==> element(k)@pre == element(k)) &&
	 * (k >= listaUnitaImmobiliari.getIndexOf(unitaImmobiliare) ==> element(k+1)@pre == element(k))
	 * @return true, if successful
	 */
	public boolean removeUnitaImmobiliare(UnitaImmobiliare unitaImmobiliare)
	{
		if(listaUnitaImmobiliari.contains(unitaImmobiliare)) 
		{
			listaUnitaImmobiliari.remove(unitaImmobiliare);
			return true;
		}
		return false;
	}
	
	/**
	 * Stampa lista unità immobiliari.
	 *
	 * @pre: -
	 * @post: sb!=null 
	 * @return String
	 */
	public String stampaListaUnitaImmobiliari()
	{
		StringBuilder sb = new StringBuilder();
		int i=0;
		
		for(UnitaImmobiliare unitaImmobiliare: listaUnitaImmobiliari)
		{
			sb.append(i);
			sb.append(" ");
			sb.append(Costanti.TRATTINO);
			sb.append(unitaImmobiliare.getdestinazione());
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}
	
	/**
	 * UnitaImmobiliare gia presente.
	 * 
	 * @param nomeUnitaImmobiliare 
	 * @pre: nomeUnitaImmobiliare!=null 
	 * @post: -
	 * @return true, if successful
	 */
	public boolean unitaImmobiliareGiaPresente(String nomeUnitaImmobiliare)
	{
		for(int i=0;i<listaUnitaImmobiliari.size();i++)
		{
			if(nomeUnitaImmobiliare.equalsIgnoreCase((listaUnitaImmobiliari.get(i)).getdestinazione()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the unita immobiliare.
	 *
	 * @param i 
	 * @pre: i>=0
	 * @post: listaUnitaImmobiliari.get(i) !=null
	 * @return the unita immobiliare
	 */
	public UnitaImmobiliare getUnitaImmobiliare(int i)
	{
		return listaUnitaImmobiliari.get(i);
	}
	
	/**
	 * Size.
	 *
	 * @pre: -
	 * @post: listaCategorie.size()>=0
	 * @return the int
	 */
	public int size()
	{
		return listaUnitaImmobiliari.size();
	}
	
	/**
	 * Checks if is empty.
	 * 
	 * @pre: -
	 * @post: -
	 * @return true, if is empty
	 */
	public boolean isEmpty()
	{
		return listaUnitaImmobiliari.isEmpty();
	}
}
