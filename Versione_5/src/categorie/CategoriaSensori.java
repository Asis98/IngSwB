package categorie;

import java.util.ArrayList;
import costanti.Costanti;

public class CategoriaSensori extends Categoria {


	private static final long serialVersionUID = 1L;
	private ArrayList<InfoRilevabile> infoRilevabili;
	private ArrayList<String> dominio;

	public CategoriaSensori(String nomeCategoria, String descrizione, ArrayList<InfoRilevabile> infoRilevabili){
		super(nomeCategoria, descrizione);
		if(infoRilevabili.isEmpty())
			throw new IllegalStateException("Non può essere vuota");
		this.infoRilevabili = new ArrayList<InfoRilevabile>();
		this.dominio= new ArrayList<String>();
	}
	
	public CategoriaSensori(String nomeCategoria, String descrizione)
	{
		super(nomeCategoria,descrizione);
		this.infoRilevabili = new ArrayList<InfoRilevabile>();
		this.dominio = new ArrayList<String>();
	}
	
	public CategoriaSensori()
	{
		this.infoRilevabili = new ArrayList<InfoRilevabile>();
		this.dominio= new ArrayList<String>();
	}
	
	public void setNomeCategoria(String nome)
	{
		super.setNomeCategoria(nome);
	}

	public String getNomeCategoria()
	{
		return super.getNomeCategoria();
	}
	

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA INFO RILEVABILI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	

	public void addInfoRilevabili(InfoRilevabile infoRilevabile)
	{
		infoRilevabili.add(infoRilevabile);
	}
	
	public boolean removeInfoRilevabili(InfoRilevabile infoRilevabile)
	{
		return (infoGiaPresente(infoRilevabile.getTipoInfoRilevabile())) ? infoRilevabili.remove(infoRilevabile) : false;
	}
	
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
	
	public int size()
	{
		return infoRilevabili.size();
	}
	
	public InfoRilevabile getInfoRilevabile(int i)
	{
		return infoRilevabili.get(i);
	}
	
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
	
	public boolean addDominio(String elemento)
	{
		if(dominio.isEmpty())
			return dominio.add(elemento);
		else
			return (dominio.contains(elemento)) ?  true : dominio.add(elemento);
	}
	
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

	public int sizeDominio()
	{
		return dominio.size();
	}

	public boolean dominioIsEmpty()
	{
		return dominio.isEmpty();
	}
	
	public String getDominio(int i)
	{
		return dominio.get(i);
	}
	
}
