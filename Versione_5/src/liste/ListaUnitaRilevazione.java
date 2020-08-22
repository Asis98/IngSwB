package liste;

import java.util.ArrayList;
import java.util.Iterator;

import ambiente.UnitaImmobiliare;
import categorie.Stato;
import rilevazione.UnitaRilevazione;

public class ListaUnitaRilevazione implements Iterable<UnitaRilevazione>{
	
	private ArrayList<UnitaRilevazione> listaUnitaRilevazione;
	
	public ListaUnitaRilevazione ()
	{
		listaUnitaRilevazione = new ArrayList <UnitaRilevazione>();
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA SENSORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Gets the lista sensori.
	 *
	 * @return the lista sensori
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<UnitaRilevazione> getListaUnitaRilevazione() {
		return listaUnitaRilevazione;
	}

	/**
	 * Sets the lista sensori.
	 *
	 * @param listaSensori the new lista sensori
	 * @pre: listaSensori!=null
	 * @post: -
	 */
	public void setListaUnitaRilevazione(ArrayList<UnitaRilevazione> lista) {
		this.listaUnitaRilevazione = lista;
	}
	
	/**
	 * Checks if is empty sensori.
	 *
	 * @return true, if is empty sensori
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmpty()
	{
		return listaUnitaRilevazione.isEmpty();
	}
	
	/**
	 * Stampa lista sensori.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaUnitaRilevazione()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<listaUnitaRilevazione.size(); i++)
		{	
			
			sb.append(i);
			sb.append("-");
			sb.append(listaUnitaRilevazione.get(i).getNomeUnita());
			sb.append("\n");
			sb.append(listaUnitaRilevazione.get(i).getStato());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Stampa lista sensori attivi.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaUnitaAttive()
	{
		StringBuilder sb = new StringBuilder();
		int index=0;
		for(int i=0; i<listaUnitaRilevazione.size(); i++)
		{	
			if(listaUnitaRilevazione.get(i).getStato() == Stato.ACCESO)
			{
				sb.append(index++);
				sb.append("-");
				sb.append(listaUnitaRilevazione.get(i).getNomeUnita());
				sb.append("\n");
			}

		}
		
		return sb.toString();
	}
	
	/**
	 * Gets lista sensori attivi.
	 *
	 * @return the lista sensori attivi
	 * @pre: -
	 * @post: if(listaSensori.get(i).getStato() == Stato.ACCESO) for all i element then
	 * 			attivi ! = null
	 */
	public ArrayList<UnitaRilevazione> getListaUnitaAttive()
	{
		ArrayList<UnitaRilevazione> attivi = new ArrayList<UnitaRilevazione>();
		
		for(int i=0; i<listaUnitaRilevazione.size(); i++)
		{	
			if(listaUnitaRilevazione.get(i).getStato() == Stato.ACCESO)
			{
				attivi.add(listaUnitaRilevazione.get(i));
			}
		}
		
		return attivi;
	}
	
	/**
	 * Gets lista sensori disattivi.
	 *
	 * @return the lista sensori disattivi
	 * @pre: -
	 * @post: if(listaSensori.get(i).getStato() == Stato.SPENTO) for all i element then
	 * 			disattivi ! = null
	 */
	public ArrayList<UnitaRilevazione> getListaUnitaDisattive()
	{
		ArrayList<UnitaRilevazione> disattivi = new ArrayList<UnitaRilevazione>();
		
		for(int i=0; i<listaUnitaRilevazione.size(); i++)
		{	
			if(listaUnitaRilevazione.get(i).getStato() == Stato.SPENTO)
			{
				disattivi.add(listaUnitaRilevazione.get(i));
			}
		}
		
		return disattivi;
	}
	
	/**
	 * Stampa lista sensori disattivi.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaUnitaDisattive()
	{
		StringBuilder sb = new StringBuilder();
		int index=0;
		for(int i=0; i<listaUnitaRilevazione.size(); i++)
		{	
			if(listaUnitaRilevazione.get(i).getStato() == Stato.SPENTO)
			{
				sb.append(index++);
				sb.append("-");
				sb.append(listaUnitaRilevazione.get(i).getNomeUnita());
				sb.append("\n");
			}

		}
		
		return sb.toString();
	}
	
	/**
	 * Size lista sensori.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaSensori.size()>=0
	 */
	public int size()
	{
		return listaUnitaRilevazione.size();
	}
	
	/**
	 * Gets the sensore.
	 *
	 * @param i the i
	 * @return the sensore
	 * @pre: i>=0
	 * @post: (Sensore) listaSensori.get(i)!=null
	 */
	public UnitaRilevazione getElemento(int i)
	{
		return listaUnitaRilevazione.get(i);
	}
	
	/**
	 * Size sensori disattivati.
	 *@pre: -
	 *@post: if(listaSensori.get(i).getStato() == Stato.SPENTO) for all i element then
	 * 			conta != 0
	 *@return the int
	 */
	public int contaUnitaDisattive()
	{
		int conta = 0;
		
		for(int i=0; i<listaUnitaRilevazione.size(); i++)
		{
			if(listaUnitaRilevazione.get(i).getStato() == Stato.SPENTO)
			{
				conta++;
			}
		}
		
		return conta;
	}
	
	
	
	/**
	 * Size sensori attivati.
	 *
	 * @return the int
	 */
	public int contaUnitaAttive()
	{
		return listaUnitaRilevazione.size() - contaUnitaDisattive();
	}
	
	
	/**
	 * Cerca sensore.
	 *
	 * @param nome the nome
	 * @pre: nome !=null
	 * @post: if(listaSensori.get(i).getNomeUnita().equalsIgnoreCase(nome))
				return (Sensore) listaSensori.get(i);
			else null
	 * @return the sensore
	 */
	public UnitaRilevazione cercaUnitaRilevazione(String nome)
	{
		for (int i=0;i< listaUnitaRilevazione.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(listaUnitaRilevazione.get(i).getNomeUnita().equalsIgnoreCase(nome))
				return listaUnitaRilevazione.get(i);
		}
		
		return null;
	}

	@Override
	public Iterator<UnitaRilevazione> iterator() {
		return listaUnitaRilevazione.iterator();
	}

	public String nomeCategoria(int i)
	{
		return this.getElemento(i).getNomeCategoria();
	}
	
	public String nomeUnitaDomotica(int i)
	{
		return this.getElemento(i).getNomeUnitaDomotica();
	}
	
	

}
