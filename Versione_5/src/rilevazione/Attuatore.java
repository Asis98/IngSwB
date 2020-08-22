package rilevazione;

import ambiente.*;
import categorie.*;
import costanti.Costanti;

public class Attuatore extends UnitaRilevazione{

	private static final long serialVersionUID = 1L;
	private ModalitaOperativa modOperativa;
	
	
	/**
	 * Instantiates a new attuatore.
	 *
	 * @param nome the nome
	 * @param modOperativa the mod operativa
	 * @pre: nome!= null && modOperativa != null 
	 * @post: 
	 * @invariant modOperativa!=null
	 */
	public Attuatore(String nome, ModalitaOperativa modOperativa) {
		super(nome);
		this.modOperativa = new ModalitaOperativa(Costanti.IDLE);
	}
	
	public Attuatore(String nome)
	{
		super(nome);
		this.modOperativa = new ModalitaOperativa(Costanti.IDLE);
	}
	
	/**
	 * Instantiates a new attuatore.
	 * @pre -
	 * @post: -
	 * @invariant modOperativa!=null
	 */
	public Attuatore()
	{
		super();
		this.modOperativa = new ModalitaOperativa(Costanti.IDLE);
	}
	
	/**
	 * Sets the mod operativa.
	 *
	 * @param _modOperativa the new mod operativa
	 * @pre: _modOperativa != null 
	 * @post: -
	 */
	public void setModOperativa(ModalitaOperativa _modOperativa)
	{
		modOperativa= _modOperativa;
	}
	
	/**
	 * Gets the mod operativa.
	 *
	 * @return the mod operativa
	 * @pre: -
	 * @post:-
	 */
	public ModalitaOperativa getModOperativa()
	{
		return modOperativa;
	}
	
	/**
	 * Sets the nome unita.
	 *
	 * @param nome the new nome unita
	 * @pre: nome != null
	 * @post: -
	 */
	public void setNomeUnita(String nome)
	{
		super.setNomeUnita(nome);
	}
	
	/**
	 * Gets the nome unita.
	 *
	 * @return the nome unita
	 * @pre: -
	 * @post: super.getNomeUnita() != Null
	 */
	public String getNomeUnita()
	{
		return super.getNomeUnita();
	}
	
	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 * @pre: categoria != null
	 * @post:
	 */
	public void setCategoria(Categoria categoria)
	{
		super.setCategoria(categoria);
	}
	
	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 * @pre:
	 * @post: super.getCategoria() != null
	 */
	public CategoriaAttuatori getCategoria()
	{
		return (CategoriaAttuatori)super.getCategoria();
	}
	
	public String getNomeCategoria()
	{
		return super.getNomeCategoria();
	}
	
	/**
	 * Sets the unita domotica.
	 *
	 * @param unita the new unita domotica
	 * @pre: -
	 * @post: -
	 */
	public void setUnitaDomotica(UnitaDomotica unita)
	{
		super.setUnitaDomotica(unita);
	}
	
	/**
	 * Gets the unita domotica.
	 *
	 * @return the unita domotica
	 * @pre: -
	 * @post: super.getUnitaDomotica() != Null
	 */
	public UnitaDomotica getUnitaDomotica()
	{
		return super.getUnitaDomotica();
	}
	
	public String getNomeUnitaDomotica()
	{
		return super.getNomeUnitaDomotica();
	}
	/**
	 * Gets the stato.
	 *
	 * @return the stato
	 * @pre: -
	 * @post: super.getStato() != Null
	 */
	public Stato getStato()
	{
		return super.getStato();
	}
	
	/**
	 * Sets the stato.
	 *
	 * @param stato the new stato
	 * @pre: stato!=null
	 * @post: -
	 */
	public void setStato(Stato stato)
	{
		super.setStato(stato);
	}
	

}
