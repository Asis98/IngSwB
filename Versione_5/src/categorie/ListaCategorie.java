package categorie;

import java.io.Serializable;
import java.util.ArrayList;


public class ListaCategorie implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Categoria> listaCategorie;
	
	/**
	 * Instantiates a new lista categorie.
	 * 
	 * @pre:- 
	 * @post: -
	 * @invariant: listaCategorie!=null
	 */
	public ListaCategorie()
	{
		listaCategorie = new ArrayList <Categoria>();
	}
		 
	/**
	 * Adds the categoria.
	 *
	 * @param categoria 
	 * @pre: categorie!=null 
	 * @post: listaCategorie.size() = listaCategorie.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaCategorie.size()-1)) ==> element(k)@pre == element(k)) 
	 * @return true, if successful
	 */
	public boolean addCategoria(Categoria categoria)
	{
		return listaCategorie.add(categoria);
	}
	
	/**
	 * Removes the categoria.
	 *
	 * @param categoria 
	 * @pre: listaCategorie.size() > 0
	 * @post: listaCategorie.size() = listaCategorie.size()@pre - 1
	 * @post: @forall k : [0..size()-1] 
	 * (k < listaCategorie.getIndexOf(categoria) ==> element(k)@pre == element(k)) &&
	 * (k >= listaCategorie.getIndexOf(categoria) ==> element(k+1)@pre == element(k))
	 * @return true, if successful
	 */
	public boolean removeCategoria(Categoria categoria)
	{
		return (listaCategorie.contains(categoria)) ? listaCategorie.remove(categoria) : false;
	}
	 
	/**
	 * Categoria gia presente.
	 *
	 * @param nomeCategoria 
	 * @pre: nomeCategoria!=null 
	 * @post: -
	 * @return true, if successful
	 */
	public boolean categoriaGiaPresente(String nomeCategoria)
	{
		for(int i=0; i<listaCategorie.size();i++)
		{

			if(listaCategorie.get(i).getNomeCategoria().equalsIgnoreCase(nomeCategoria))
			{
				return true;
			}
		}
		
		return false;		
	}
	
	/**
	 * Size.
	 * @param: -
	 * @pre: -
	 * @post: listaCategorie.size()>=0
	 * @return the int
	 */
	public int size() 
	{
		return listaCategorie.size();
	}
	
	/**
	 * Gets the elemento.
	 *
	 * @param i 
	 * @pre:  i>=0
	 * @post:  listaCategorie.get(i)!=null
	 * @return the elemento
	 */
	public Categoria getElemento(int i)
	{
		return listaCategorie.get(i);
	}
	
	/**
	 * Stampa lista.
	 * 
	 * @pre: -
	 * @post: nomiCategorie!=null && 0<= i <= nomiCategorie.length
	 * @return the string[]

	 */
	public String[] stampaLista()
	{
		String[] nomiCategorie = new String[listaCategorie.size()];
		
		
		for(int i=0; i<listaCategorie.size(); i++)
		{
			nomiCategorie[i] = listaCategorie.get(i).getNomeCategoria();
		}
		
		return nomiCategorie;
	}
	
	/**
	 * Stampa lista string.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */
	public String stampaListaString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(Categoria categoria: listaCategorie)
		{
			sb.append(categoria.getNomeCategoria());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * ListaCategorie is empty.
	 *
	 * @return true, if successful
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmpty()
	{
		return listaCategorie.isEmpty();
	}
	 
	 
}
