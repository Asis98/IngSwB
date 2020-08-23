package categorie;

import java.io.Serializable;
import java.util.ArrayList;

import costanti.Costanti;


public class ModalitaOperativa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String nomeModOperativa;
	private String descrizione;
	private ArrayList<Parametro> listaParametri;
		

	public ModalitaOperativa(String _nomeModOperativa, String _descrizione)
	{
		this.nomeModOperativa=_nomeModOperativa;
		this.descrizione=_descrizione;
		listaParametri = new ArrayList<Parametro>();
	}
	
	public ModalitaOperativa(String _nomeModOperativa)
	{
		this.nomeModOperativa=_nomeModOperativa;
		listaParametri = new ArrayList<Parametro>();
	}

	public void setNomeModOperativa(String _nomeModOp)
	{
		this.nomeModOperativa=_nomeModOp;
	}
	
	public void setDescrizione(String _descrizione)
	{
		this.descrizione=_descrizione;
	}
	
	public String getNomeModOperativa()
	{
		return nomeModOperativa;
	}

	public String getDescrizione()
	{
		return descrizione;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA PARAMETRI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public boolean addParametro(Parametro param)
	{	
		return  listaParametri.add(param);	
	}

	public boolean removeParametro(Parametro param)
	{
		return listaParametri.remove(param);
	}

	public boolean parametroGiaPresente(String nomeParametro)
	{
		for(int i=0;i<listaParametri.size();i++)
		{
			if(nomeParametro.equalsIgnoreCase(listaParametri.get(i).getNomeParametro()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Parametro getParametro(int i)
	{
		return listaParametri.get(i); 
	}

	public int size()
	{
		return listaParametri.size();
	}

	public String stampaListaParametri()
	{
		StringBuilder sb = new StringBuilder();
		for(Parametro nomeParametro: listaParametri)
		{
			sb.append(nomeParametro.getNomeParametro());
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public boolean parametriIsEmpty()
	{
		return listaParametri.isEmpty();
	}
	
	public void setParametro(String nomeParametro, Double valore)
	{
		boolean presente = false;
		for(int i =0; i<listaParametri.size() && !presente; i++)
		{
			if(listaParametri.get(i).getNomeParametro().equalsIgnoreCase(nomeParametro))
				listaParametri.get(i).setValore(valore);
		}
	}

	public String printModNameParamValue()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(nomeModOperativa);
		if(!listaParametri.isEmpty())
		{
			for(Parametro nomeParametro: listaParametri)
			{
				sb.append(nomeParametro.getNomeParametro());
				sb.append(Costanti.TRATTINO);
				sb.append(nomeParametro.getValore());
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	

	public String getNomeParametro(int i)
	{
		return listaParametri.get(i).getNomeParametro();
	}
	
}
