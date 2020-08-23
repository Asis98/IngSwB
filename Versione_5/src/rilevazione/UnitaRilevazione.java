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
	
	public UnitaRilevazione(String nomeUnita) {
		if(!nomeUnita.matches("[A-Za-z]+[0-9][1-9]*_[a-zA-Zàèìòù]+")) //perchè il nome deve essere costituito: nome fantasia_nome categoria
			throw new IllegalStateException("Nome non valido");
		this.nomeUnita = nomeUnita;
		this.stato = Stato.ACCESO;
	}
	
	public UnitaRilevazione()
	{
		this.nomeUnita = Costanti.STRINGA_VUOTA;
		this.stato = Stato.ACCESO;
	}
	
	public void setNomeUnita(String _nomeUnita)
	{
		if(!_nomeUnita.matches("[A-Za-z]+[0-9][1-9]*_[a-zA-Zàèìòù]+")) //perchè il nome deve essere costituito: nome fantasia_nome categoria
			throw new IllegalStateException("Nome non valido");
		this.nomeUnita = _nomeUnita;
	}
	
	public String getNomeUnita()
	{
		return nomeUnita;
	}

	public void setCategoria(Categoria _categoria)
	{
		categoria=_categoria;
	}
	
	public Categoria getCategoria()
	{
		return categoria;
	}
	
	public String getNomeCategoria()
	{
		return categoria.getNomeCategoria();
	}

	public void setUnitaDomotica(UnitaDomotica _unitaDomotica)
	{
		unitaDomotica= _unitaDomotica;
	}
	
	public UnitaDomotica getUnitaDomotica()
	{
		return unitaDomotica;
	}
	
	public String getNomeUnitaDomotica()
	{
		return unitaDomotica.getUnitName();
	}

	public Stato getStato()
	{
		return stato;
	}
	
	public void setStato(Stato stato)
	{
		this.stato = stato;
	}
}
