package rilevazione;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import ambiente.*;
import categorie.*;
import costanti.Costanti;


public class Sensore extends UnitaRilevazione{

	private static final long serialVersionUID = 1L;

	private ArrayList<InfoRilevabile> listaMisurazioni;

	public Sensore(String nome) {
		super(nome);
		listaMisurazioni = new ArrayList<InfoRilevabile>();
	}
	

	public Sensore()
	{
		super();
		listaMisurazioni = new ArrayList<InfoRilevabile>();
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
	
	public CategoriaSensori getCategoria()
	{
		return (CategoriaSensori)super.getCategoria();
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
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA MISURAZIONI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	public void addMisurazione(InfoRilevabile infoRilevabile)
	{
			//nomeInfoRil= nomeInfoRil + " Misura " + listaMisurazioni.size() + " :";
			listaMisurazioni.add(infoRilevabile);
	}
	
	public int size()
	{
		return listaMisurazioni.size();
	}
	
	public ArrayList<InfoRilevabile> getListaMisurazioni()
	{
		return  listaMisurazioni;
	}

	public String printListaMisurazioni()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<listaMisurazioni.size();i++)
		{
			sb.append(i);
			sb.append(Costanti.TRATTINO);
			sb.append(listaMisurazioni.get(i).getTipoInfoRilevabile());
			sb.append(":");
			if(listaMisurazioni.get(i).getValoreAttuale() instanceof ValoreNumerico)
				sb.append(((ValoreNumerico)listaMisurazioni.get(i).getValoreAttuale()));
			else
				sb.append(((ValoreNonNumerico)listaMisurazioni.get(i).getValoreAttuale()).getValore());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean nomeInfoRilPresente(String nomeInfoRilevabile)
	{
		boolean presente = false;
		 for(int i=0; i<listaMisurazioni.size() && !presente;i++)    
		 {
			 if(listaMisurazioni.get(i).getTipoInfoRilevabile().equalsIgnoreCase(nomeInfoRilevabile))
				 presente = true;
		 }
		 return presente;
	}
	
	
	public InfoRilevabile getInfoRilevabile(int i)
	{
		return listaMisurazioni.get(i);
	}
	
	public InfoRilevabile cercaInfoRilevabile(String nome)
	{
		for (int i=0;i< listaMisurazioni.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(listaMisurazioni.get(i).getTipoInfoRilevabile().equalsIgnoreCase(nome))
				return listaMisurazioni.get(i);
		}
		
		return null;
	}
	
}
