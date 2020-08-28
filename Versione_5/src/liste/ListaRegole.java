package liste;

import java.util.ArrayList;
import java.util.Iterator;

import regole.Regola;
import regole.StatoRegola;

public class ListaRegole implements Iterable<Regola>{

	private ArrayList<Regola> listaRegole;
	
	public ListaRegole ()
	{
		listaRegole = new ArrayList <Regola>();
	}

	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA REGOLE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Sets the regola.
	 *
	 * @param regola the new regola
	 * @pre: regola!=null
	 * @post: listaRegole.size() = listaRegole.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaRegole.size()-1)) ==> element(k)@pre == element(k)) 
	 * 
	 */
	public void setRegola (Regola regola)
	{
		listaRegole.add(regola);
	}
	
	/**
	 * Gets the regola.
	 *
	 * @param i the i
	 * @return the regola
	 * @pre: i>=0
	 * @post: -
	 */
	public Regola getRegola(int i)
	{
		return listaRegole.get(i);
	}
	
	/**
	 * Size regole.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaRegole.size()>=0
	 */
	public int sizeRegole()
	{
		return listaRegole.size();
	}
	
	/**
	 * Checks if is empty regole.
	 *
	 * @return true, if is empty regole
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmptyRegole()
	{
		return listaRegole.isEmpty();
	}
	
	/**
	 * Gets the lista regole.
	 *
	 * @return the lista regole
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<Regola> getListaRegole()
	{
		return listaRegole;
	}
	
	/**
	 * Conta regole abilitate.
	 *
	 * @return the int
	 * @pre: -
	 * @post: if(listaRegole.get(i).getStato() == StatoRegola.ABILITATA) conto++;
	 */
	public int contaRegoleAbilitate()
	{
		int conto =0;
		for(int i=0;i<listaRegole.size();i++)
		{
			if(listaRegole.get(i).getStato() == StatoRegola.ABILITATA)
				conto++;
		}
		
		return conto;
	}
	
	/**
	 * Conta regole disabilitate.
	 *
	 * @return the int
	 * @pre: -
	 * @post: if(listaRegole.get(i).getStato() == StatoRegola.DISABILITATA) conto++;
	 */
	public int contaRegoleDisabilitate()
	{
		int conto =0;
		for(int i=0;i<listaRegole.size();i++)
		{
			if(listaRegole.get(i).getStato() == StatoRegola.DISABILITATA)
				conto++;
		}
		
		return conto;
	}


	@Override
	public Iterator<Regola> iterator() {
		return listaRegole.iterator();
	}
	
	
}
