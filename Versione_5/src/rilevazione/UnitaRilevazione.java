package rilevazione;

import java.io.Serializable;

import ambiente.*;
import categorie.*;
import costanti.Costanti;


public abstract class UnitaRilevazione implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeUnita;
	private Stato stato;
	private Categoria categoria;
	private UnitaDomotica unitaDomotica;
	
	/**
	 * Instantiates a new unita rilevazione.
	 *
	 * @param nomeUnita 
	 * @pre: nomeUnita!=null
	 * @post: -
	 * @invariant: nomeUnita!=null && stato!=null && categoria!=null && unitaDomotica!=null 
	 * if(!nomeUnita.matches("[A-Za-z]+[0-9][1-9]*_[a-zA-Zàèìòù]+")) {throw new IllegalStateException("Nome non valido");}
	 */
	public UnitaRilevazione(String nomeUnita) {
		if(!nomeUnita.matches("[A-Za-z]+[0-9][1-9]*_[a-zA-Zàèìòù]+")) //perchè il nome deve essere costituito: nome fantasia_nome categoria
			throw new IllegalStateException("Nome non valido");
		this.nomeUnita = nomeUnita;
		this.stato = Stato.ACCESO;
	}
	
	/**
	 * Instantiates a new unita rilevazione.
	 * 
	 * @pre: - 
	 * @post: -
	 */
	public UnitaRilevazione()
	{
		this.nomeUnita = Costanti.STRINGA_VUOTA;
		this.stato = Stato.ACCESO;
	}
	

	/**
	 * Sets the nome unita.
	 *
	 * @param _nomeUnita the new nomeUnita
	 * @pre: _nomeUnita!=null
	 * @post: -
	 */
	public void setNomeUnita(String _nomeUnita)
	{
		if(!_nomeUnita.matches("[A-Za-z]+[0-9][1-9]*_[a-zA-Zàèìòù]+")) //perchè il nome deve essere costituito: nome fantasia_nome categoria
			throw new IllegalStateException("Nome non valido");
		this.nomeUnita = _nomeUnita;
	}
	
	/**
	 * Gets the nome unita.
	 * 
	 * @pre: -
	 * @post: -
	 * @return nomeUnita
	 */
	public String getNomeUnita()
	{
		return nomeUnita;
	}
	
	/**
	 * Sets the categoria.
	 *
	 * @param _categoria the new categoria
	 * @pre: _categoria!=null
	 * @post: -
	 */
	public void setCategoria(Categoria _categoria)
	{
		categoria=_categoria;
	}
	
	/**
	 * Gets the categoria.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the categoria
	 */
	public Categoria getCategoria()
	{
		return categoria;
	}
	
	/**
	 * Sets the unita domotica.
	 *
	 * @param _unitaDomotica the new unita domotica
	 * @pre: _unitaDomotica!=null
	 * @post: -
	 */
	public void setUnitaDomotica(UnitaDomotica _unitaDomotica)
	{
		unitaDomotica= _unitaDomotica;
	}
	
	/**
	 * Gets the unita domotica.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the unita domotica
	 */
	public UnitaDomotica getUnitaDomotica()
	{
		return unitaDomotica;
	}
	
	/**
	 * Gets the stato.
	 * 
	 * @pre: -
	 * @post: -
	 * @return stato
	 */
	public Stato getStato()
	{
		return stato;
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
		this.stato = stato;
	}
}
