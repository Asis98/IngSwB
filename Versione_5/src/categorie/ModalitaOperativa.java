package categorie;

import java.io.Serializable;
import java.util.ArrayList;

import costanti.Costanti;


public class ModalitaOperativa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String nomeModOperativa;
	private String descrizione;
	private ArrayList<Parametro> listaParametri;
		
	/**
	 * Instantiates a new modalita operativa.
	 *
	 * @param _nomeModOperativa 
	 * @param _descrizione 
	 * @pre: _nomeModOperativa!=null && _descrizione!=null
	 * @post:-
	 * @invariant: nomeModOperativa!=null && descrizione!=null && listaParametri!=null
	 */
	public ModalitaOperativa(String _nomeModOperativa, String _descrizione)
	{
		this.nomeModOperativa=_nomeModOperativa;
		this.descrizione=_descrizione;
		listaParametri = new ArrayList<Parametro>();
	}
	
	/**
	 * Instantiates a new modalita operativa.
	 *
	 * @param _nomeModOperativa 
	 * @pre:  _nomeModOperativa!=null
	 * @post: -
	 * @invariant: nomeModOperativa!=null && descrizione!=null && listaParametri!=null
	 */
	public ModalitaOperativa(String _nomeModOperativa)
	{
		this.nomeModOperativa=_nomeModOperativa;
		listaParametri = new ArrayList<Parametro>();
	}
	
	/**
	 * Sets the nome mod operativa.
	 *
	 * @param _nomeModOp the new nome mod operativa
	 * @pre: _nomeModOp!=null
	 * @post: -
	 */
	public void setNomeModOperativa(String _nomeModOp)
	{
		this.nomeModOperativa=_nomeModOp;
	}
	
	/**
	 * Sets the descrizione.
	 *
	 * @param _descrizione the new descrizione
	 * @pre: _descrizione!=null
	 * @post: -
	 */
	public void setDescrizione(String _descrizione)
	{
		this.descrizione=_descrizione;
	}
	
	/**
	 * Gets the nome mod operativa.
	 *
	 * @pre: -
	 * @post:-
	 * @return nomeModOperativa
	 */
	public String getNomeModOperativa()
	{
		return nomeModOperativa;
	}
	
	/**
	 * Gets the descrizione.
	 * 
	 * @pre: -
	 * @post: -
	 * @return descrizione
	 */
	public String getDescrizione()
	{
		return descrizione;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA PARAMETRI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Adds the parametro.
	 *
	 * @param param 
	 * @pre: param!=null 
	 * @post: listaParametri.size() = listaParametri.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaParametri.size()-1)) ==> element(k)@pre == element(k))
	 * @return true, if successful
	 */
	public boolean addParametro(Parametro param)
	{	
		return  listaParametri.add(param);	
	}
	
	/**
	 * Removes the parametro.
	 *
	 * @pre:  listaParametri.size() > 0
	 * @post: listaParametri.size() = listaParametri.size()@pre - 1
	 * @post: @forall k : [0..size()-1] 
	 * (k < listaParametri.getIndexOf(param) ==> element(k)@pre == element(k)) &&
	 * (k >= listaParametri.getIndexOf(param) ==> element(k+1)@pre == element(k))
	 * @param param
	 * @return true, if successful
	 */
	public boolean removeParametro(Parametro param)
	{
		return listaParametri.remove(param);
	}

	/**
	 * Parametro gia presente.
	 * 
	 * @param nomeParametro
	 * @pre: nomeParametro!=null 
	 * @post: -
	 * @return true, if successful
	 */
	public boolean parametroGiaPresente(String nomeParametro)
	{
		for(int i=0;i<listaParametri.size();i++)
		{
			if(nomeParametro.equalsIgnoreCase(listaParametri.get(i).getNomeParametro()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the parametro.
	 *
	 * @param i 
	 * @pre: i>=0
	 * @post: listaParametri.get(i)!=null
	 * @return the parametro 
	 */
	public Parametro getParametro(int i)
	{
		return listaParametri.get(i); 
	}
	
	/**
	 * Size.
	 *
	 * @post: listaParametri.size()>=0
	 * @return the int
	 */
	public int size()
	{
		return listaParametri.size();
	}
	
	/**
	 * Stampa lista parametri.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the String
	 */
	public String stampaListaParametri()
	{
		StringBuilder sb = new StringBuilder();
		for(Parametro nomeParametro: listaParametri)
		{
			sb.append(nomeParametro.getNomeParametro());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Parametri is empty.
	 *
	 * @pre: -
	 * @post: -
	 * @return true, if successful
	 */
	public boolean parametriIsEmpty()
	{
		return listaParametri.isEmpty();
	}
	
	/**
	 * Sets the parametro.
	 *
	 * @param nomeParametro
	 * @param valore 
	 * @pre: nomeParametro!=null 
	 * @pre: valore!=null
	 * @post: if(listaParametri.get(i).getNomeParametro().equalsIgnoreCase(nomeParametro)) setto il valore
	 */
	public void setParametro(String nomeParametro, Double valore)
	{
		boolean presente = false;
		for(int i =0; i<listaParametri.size() && !presente; i++)
		{
			if(listaParametri.get(i).getNomeParametro().equalsIgnoreCase(nomeParametro))
				listaParametri.get(i).setValore(valore);
		}
	}
	
	/**
	 * Prints the mod name param value.
	 * 
	 * @pre: -
	 * @post: sb!=null
	 * @return the String
	 */
	public String printModNameParamValue()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(nomeModOperativa);
		if(!listaParametri.isEmpty())
		{
			for(Parametro nomeParametro: listaParametri)
			{
				sb.append(nomeParametro.getNomeParametro());
				sb.append(Costanti.TRATTINO);
				sb.append(nomeParametro.getValore());
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	

	
	
	
}
