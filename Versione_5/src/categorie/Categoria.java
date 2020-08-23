package categorie;

import java.io.Serializable;

import costanti.Costanti;


public abstract class Categoria implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	private String nomeCategoria;
	private String descrizione; 
	
	public Categoria(String nomeCategoria, String descrizione)
	{
		this.nomeCategoria = nomeCategoria;
		this.descrizione = descrizione;		
	}
	
	public Categoria()
	{
		this.nomeCategoria = Costanti.STRINGA_VUOTA;
		this.descrizione = Costanti.STRINGA_VUOTA;	
	}
	
	public void setNomeCategoria(String nomeCategoria)
	{
		this.nomeCategoria = nomeCategoria;
	}
	
	public String getNomeCategoria()
	{
		return nomeCategoria;
	}
	
	public void setDescrizioneCategoria(String descrizioneCategoria)
	{
		this.descrizione = descrizioneCategoria;
	}
	
	public String getDescrizioneCategoria()
	{
		return descrizione;
	}

}
