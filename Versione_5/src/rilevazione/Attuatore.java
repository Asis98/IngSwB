package rilevazione;

import ambiente.*;
import categorie.*;
import costanti.Costanti;

public class Attuatore extends UnitaRilevazione{

	private static final long serialVersionUID = 1L;
	private ModalitaOperativa modOperativa;
	
	
	public Attuatore(String nome, ModalitaOperativa modOperativa) {
		super(nome);
		this.modOperativa = new ModalitaOperativa(Costanti.IDLE);
	}
	
	public Attuatore(String nome)
	{
		super(nome);
		this.modOperativa = new ModalitaOperativa(Costanti.IDLE);
	}
	
	public Attuatore()
	{
		super();
		this.modOperativa = new ModalitaOperativa(Costanti.IDLE);
	}
	
	public void setModOperativa(ModalitaOperativa _modOperativa)
	{
		modOperativa= _modOperativa;
	}
	
	public ModalitaOperativa getModOperativa()
	{
		return modOperativa;
	}
	
	public void setNomeUnita(String nome)
	{
		super.setNomeUnita(nome);
	}
	
	public String getNomeUnita()
	{
		return super.getNomeUnita();
	}

	public void setCategoria(Categoria categoria)
	{
		super.setCategoria(categoria);
	}
	
	public CategoriaAttuatori getCategoria()
	{
		return (CategoriaAttuatori)super.getCategoria();
	}
	
	public String getNomeCategoria()
	{
		return super.getNomeCategoria();
	}
	
	public void setUnitaDomotica(UnitaDomotica unita)
	{
		super.setUnitaDomotica(unita);
	}
	
	public UnitaDomotica getUnitaDomotica()
	{
		return super.getUnitaDomotica();
	}
	
	public String getNomeUnitaDomotica()
	{
		return super.getNomeUnitaDomotica();
	}

	public Stato getStato()
	{
		return super.getStato();
	}
	

	public void setStato(Stato stato)
	{
		super.setStato(stato);
	}
	

}
