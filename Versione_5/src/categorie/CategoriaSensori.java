package categorie;

import java.util.ArrayList;
import costanti.Costanti;


// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaSensori.
 */
public class CategoriaSensori extends Categoria {


	private static final long serialVersionUID = 1L;
	private ArrayList<InfoRilevabile> infoRilevabili;
	private ArrayList<String> dominio;
	
	/**
	 * Instantiates a new categoria sensori.
	 *
	 * @param nomeCategoria the nome categoria
	 * @param descrizione the descrizione
	 * @param infoRilevabili the info rilevabili
	 * @pre: nomeCategoria != null && descrizione != null &&infoRilevabili!=null
	 * @post: infoRilevabili.isEmpty() throw IllegalStateException("Non può essere vuota")
	 * @invariant: infoRilevabili!=null && dominio!=null
	 */
	public CategoriaSensori(String nomeCategoria, String descrizione, ArrayList<InfoRilevabile> infoRilevabili){
		super(nomeCategoria, descrizione);
		if(infoRilevabili.isEmpty())
			throw new IllegalStateException("Non può essere vuota");
		this.infoRilevabili = new ArrayList<InfoRilevabile>();
		this.dominio= new ArrayList<String>();
	}
	
	/**
	 * Instantiates a new categoria sensori.
	 *
	 * @param nomeCategoria the nome categoria
	 * @param descrizione the descrizione
	 * @pre: nomeCategoria != null && descrizione != null 
	 * @invariant: infoRilevabili!=null && dominio!=null
	 */
	public CategoriaSensori(String nomeCategoria, String descrizione)
	{
		super(nomeCategoria,descrizione);
		this.infoRilevabili = new ArrayList<InfoRilevabile>();
		this.dominio = new ArrayList<String>();
	}
	
	/**
	 * Instantiates a new categoria sensori.
	 * 
	 * @pre:-
	 * @post: -
	 * @invariant: infoRilevabili!=null && dominio!=null
	 */
	public CategoriaSensori()
	{
		this.infoRilevabili = new ArrayList<InfoRilevabile>();
		this.dominio= new ArrayList<String>();
	}
	
	/**
	 * Sets the nome categoria.
	 *
	 * @param nome the new nome categoria
	 * @pre: nome!=null
	 * @post: -
	 */
	public void setNomeCategoria(String nome)
	{
		super.setNomeCategoria(nome);
	}
	
	/**
	 * Gets the nome categoria.
	 *
	 * @return the nome categoria
	 * @pre: -
	 * @post: super.getNomeCategoria()!=null
	 */
	public String getNomeCategoria()
	{
		return super.getNomeCategoria();
	}
	

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA INFO RILEVABILI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	

	/**
	 * Adds the info rilevabili.
	 *
	 * @param infoRilevabile the info rilevabile
	 * @return true, if successful
	 * @pre: infoRilevabile!=null
	 * @post: infoRilevabili.size() = infoRilevabili.size()@pre + 1
	 * @post: @forall k : [0..size()-2]
	 * (k < (infoRilevabili.size()-1)) ==> element(k)@pre == element(k)) 
	 */
	public void addInfoRilevabili(InfoRilevabile infoRilevabile)
	{
		infoRilevabili.add(infoRilevabile);
	}
	
	/**
	 * Removes the info rilevabili.
	 *
	 * @param infoRilevabile the info rilevabile
	 * @return true, if successful
	 * @pre:  infoRilevabili.size() > 0
	 * @post: infoRilevabili.size() = infoRilevabili.size()@pre - 1
	 * @post: @forall k : [0..size()-1]
	 * (k < infoRilevabili.getIndexOf(infoRilevabile) ==> element(k)@pre == element(k)) &&
	 * (k >= infoRilevabili.getIndexOf(infoRilevabile) ==> element(k+1)@pre == element(k))
	 */
	public boolean removeInfoRilevabili(InfoRilevabile infoRilevabile)
	{
		return (infoGiaPresente(infoRilevabile.getTipoInfoRilevabile())) ? infoRilevabili.remove(infoRilevabile) : false;
	}
	
	/**
	 * Info gia presente.
	 *
	 * @param infoRilevabile the info rilevabile
	 * @return true, if successful
	 * @pre: infoRilevabile!=null
	 * @post: -
	 */
	public boolean infoGiaPresente(String infoRilevabile)
	{
		for(int i =0; i<infoRilevabili.size();i++)
		{
			if(infoRilevabile.equalsIgnoreCase(infoRilevabili.get(i).getTipoInfoRilevabile()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Size.
	 *
	 * @return the int
	 * @pre: -
	 * @post: infoRilevabili.size()>=0
	 */
	public int size()
	{
		return infoRilevabili.size();
	}
	
	/**
	 * Gets the info rilevabili.
	 *
	 * @param i the i
	 * @return the info rilevabile
	 * @pre: i>=0
	 * @post: infoRilevabili.get(i)!=null
	 */
	public InfoRilevabile getInfoRilevabile(int i)
	{
		return infoRilevabili.get(i);
	}
	
	/**
	 * Stampa info rilevabili.
	 *
	 * @return the String
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaInfoRilevabili()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<infoRilevabili.size(); i++)
		{
			sb.append(i);
			sb.append(Costanti.TRATTINO);
			sb.append(infoRilevabili.get(i).getTipoInfoRilevabile());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI DOMINIO
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	/**
	 * Adds the dominio.
	 *
	 * @param elemento the elemento
	 * @return true, if successful
	 * @pre: elemento!=null
	 * @post: dominio.size() = dominio.size()@pre + 1
	 * @post: @forall k : [0..size()-2]
	 * (k < (dominio.size()-1)) ==> element(k)@pre == element(k)) 
	 */
	public boolean addDominio(String elemento)
	{
		if(dominio.isEmpty())
			return dominio.add(elemento);
		else
			return (dominio.contains(elemento)) ?  true : dominio.add(elemento);
	}
	
	/**
	 * Dominio gia presente.
	 *
	 * @param elemento the elemento
	 * @return true, if successful
	 * @pre: elemento!=null
	 * @post: -
	 */
	public boolean dominioGiaPresente(String elemento)
	{
		for(int i =0; i<dominio.size();i++)
		{
			if(elemento.equalsIgnoreCase(dominio.get(i).toString()))
			{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Size dominio.
	 *
	 * @return the int
	 * @pre: -
	 * @post: dominio.size()>=0
	 */
	public int sizeDominio()
	{
		return dominio.size();
	}

	/**
	 * Dominio is empty.
	 *
	 * @return true, if successful
	 * @pre: -
	 * @post: -
	 */
	public boolean dominioIsEmpty()
	{
		return dominio.isEmpty();
	}
	
	/**
	 * Gets the dominio.
	 *
	 * @param i the i
	 * @return the dominio
	 * @pre: i>=0
	 * @post: dominio.get(i)!=null
	 */
	public String getDominio(int i)
	{
		return dominio.get(i);
	}
	
}
