package categorie;

import java.util.ArrayList;

import costanti.Costanti;


// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaAttuatori.
 */
public class CategoriaAttuatori extends Categoria{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lista mod operative. */
	private ArrayList<ModalitaOperativa> listaModOperative;
	
	/**
	 * Instantiates a new categoria attuatori.
	 *
	 * @param nomeCategoria the nome categoria
	 * @param descrizione the descrizione
	 * @param listModOperativa the list mod operativa
	 * @pre: nomeCategoria != null && descrizione != null && listModOperativa!=null
	 * @post: listaModOperativa.isEmpty() throw IllegalStateException("Non può essere vuota")
	 * @invariant: listModOperativa != Null
	 */
	public CategoriaAttuatori(String nomeCategoria, String descrizione, ArrayList<ModalitaOperativa> listModOperativa){
		super(nomeCategoria, descrizione);
		if(listModOperativa.isEmpty())
			throw new IllegalStateException("Non può essere vuota");
		this.listaModOperative = listModOperativa;				
	}
	
	/**
	 * Instantiates a new categoria attuatori.
	 * @pre: -
	 * @post: -
	 * @invariant: listModOperativa != Null
	 */
	public CategoriaAttuatori()
	{
		this.listaModOperative = new ArrayList<>();

	}
	
	/**
	 * Instantiates a new categoria attuatori.
	 *
	 * @param nomeCategoria the nome categoria
	 * @param descrizione the descrizione
	 * @pre: nomeCategoria != null && descrizione != null 
	 * @post:-
	 * @invariant: listModOperativa != Null
	 */
	public CategoriaAttuatori(String nomeCategoria,String descrizione)
	{
		super(nomeCategoria, descrizione);
		this.listaModOperative = new ArrayList<>();
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
	 * METODI LISTA MODALITA' OPERATIVE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Adds the modalita operativa.
	 *
	 * @param modalitaOp the modalita op
	 * @return true, if successful
	 * @pre: modalitaOp!=null
	 * @post: listaModOperative.size() = listaModOperative.size()@pre + 1
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaModOperative.size()-1)) ==> element(k)@pre == element(k))
	 */
	public boolean addModalitaOperativa(ModalitaOperativa modalitaOp)
	{
		return (listaModOperative.contains(modalitaOp)) ? false : listaModOperative.add(modalitaOp);
	}

	/**
	 * Removes the modalita operativa.
	 *
	 * @param modalitaOp the modalita op
	 * @return true, if successful
	 * @pre:  listaModOperative.size() > 0
	 * @post: listaModOperative.size() = listaModOperative.size()@pre - 1
	 * @post: @forall k : [0..size()-1]
	 * (k < listaModOperative.getIndexOf(modalitaOp) ==> element(k)@pre == element(k)) &&
	 * (k >= listaModOperative.getIndexOf(modalitaOp) ==> element(k+1)@pre == element(k))
	 */
	public boolean removeModalitaOperativa(ModalitaOperativa modalitaOp)
	{
		return (listaModOperative.contains(modalitaOp)) ? false : listaModOperative.remove(modalitaOp);
	}

	/**
	 * Stampa modalita operative.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaModalitaOperative()
	{
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(ModalitaOperativa nomeModOperativa: listaModOperative)
		{
			sb.append(i);
			sb.append(Costanti.TRATTINO);
			sb.append(nomeModOperativa.getNomeModOperativa());
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}
	
	/**
	 * Gets the modalita operative.
	 *
	 * @param i the i
	 * @return the modalita operative
	 * @pre: i>=0
	 * @post: listaModOperative.get(i)!=null
	 */
	public ModalitaOperativa getModalitaOperative(int i)
	{
		return listaModOperative.get(i);
	}
	
	/**
	 * Size.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaModOperative.size()>=0
	 */
	public int size()
	{
		return listaModOperative.size();
	}
	
	/**
	 * Modalita gia presente.
	 *
	 * @param nomeModalita the nome modalita
	 * @return true, if successful
	 * @pre: nomeModalita!=null
	 * @post: -
	 */
	public boolean modalitaGiaPresente(String nomeModalita)
	{
		for(int i=0;i<listaModOperative.size();i++)
		{
			if(nomeModalita.equalsIgnoreCase(listaModOperative.get(i).getNomeModOperativa()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the lista modalita operative.
	 *
	 * @return the lista modalita operative
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<ModalitaOperativa> getListaModalitaOperative()
	{
		return listaModOperative;
	}
	
	/**
	 * Sets the lista modalita operative.
	 *
	 * @param lista the new lista modalita operative
	 * @pre: lista!=null
	 * @post: -
	 */
	public void setListaModalitaOperative (ArrayList<ModalitaOperativa>lista)
	{
		listaModOperative = lista;
	}
	
	/**
	 * Cerca mod op.
	 *
	 * @param nome the nome
	 * @pre: nome !=null
	 * @post: if(listaModOperative.get(i).getNomeModOperativa().equalsIgnoreCase(nome))
				return listaModOperative.get(i);
			else null
	 * @return the modalita operativa
	 */
	public ModalitaOperativa cercaModOp(String nome)
	{
		for (int i=0;i< listaModOperative.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(listaModOperative.get(i).getNomeModOperativa().equalsIgnoreCase(nome))
				return listaModOperative.get(i);
		}
		
		return null;
	}

	
}

