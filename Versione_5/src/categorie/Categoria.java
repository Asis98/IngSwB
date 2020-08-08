package categorie;

import java.io.Serializable;

import costanti.Costanti;


public abstract class Categoria implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	private String nomeCategoria;
	private String descrizione; 
	
	/**
	 * Instantiates a new categoria.
	 *
	 * @param nomeCategoria 
	 * @param descrizione 
	 * @pre: nomeCategoria != null && descrizione !=null
	 * @post: descrizione.lenght()<MAX_CARATTERI throw IllegalStateException
	 * @invariant: nomeCategoria!=null && descrizione!= null && descrizione.lenght()<MAX_CARATTERI
	 */
	public Categoria(String nomeCategoria, String descrizione)
	{
		this.nomeCategoria = nomeCategoria;
		this.descrizione = descrizione;		
	}
	
	/**
	 * Instantiates a new categoria.
	 * 
	 * @pre: -
	 * @post: descrizione.lenght()<MAX_CARATTERI throw IllegalStateException
	 * @invariant: nomeCategoria!=null && descrizione!= null && descrizione.lenght()<MAX_CARATTERI
	 */
	public Categoria()
	{
		this.nomeCategoria = Costanti.STRINGA_VUOTA;
		this.descrizione = Costanti.STRINGA_VUOTA;	
	}
	
	/**
	 * Sets the nome categoria.
	 *
	 * @param nomeCategoria the new nome categoria
	 * @pre: nomeCategoria != Null
	 * @post: -
	 */
	public void setNomeCategoria(String nomeCategoria)
	{
		this.nomeCategoria = nomeCategoria;
	}
	
	/**
	 * Gets the nome categoria.
	 *
	 * @pre: -
	 * @post: -
	 * @return the nome categoria
	 */
	public String getNomeCategoria()
	{
		return nomeCategoria;
	}
	
	/**
	 * Sets the descrizione categoria.
	 *
	 * @param descrizioneCategoria the new descrizione categoria
	 * @pre: descrizioneCategoria != Null
	 * @post: descrizione.lenght()<MAX_CARATTERI throw IllegalStateException
	 * 
	 */
	public void setDescrizioneCategoria(String descrizioneCategoria)
	{
		this.descrizione = descrizioneCategoria;
	}
	
	/**
	 * Gets the descrizione categoria.
	 *
	 * @pre: -
	 * @post:-
	 * @return the descrizione categoria
	 */
	public String getDescrizioneCategoria()
	{
		return descrizione;
	}

		
	
}
