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
		
	/**
	 * Instantiates a new sensore.
	 *
	 * @param nome 
	 * @pre: nome!=null
	 * @post: -
	 * @invariant: valoreRilevato!=null && listaMisurazioni!=null
	 */
	public Sensore(String nome) {
		super(nome);
		listaMisurazioni = new ArrayList<InfoRilevabile>();
	}
	
	/**
	 * Instantiates a new sensore.
	 * 
	 * @pre: -
	 * @post: -
	 * @invariant: valoreRilevato!=null && listaMisurazioni!=null
	 */
	public Sensore()
	{
		super();
		listaMisurazioni = new ArrayList<InfoRilevabile>();
	}
	
	
	/**
	 * Sets the nome unita.
	 *
	 * @param nome the new nome unita
	 * @pre: nome!=null
	 * @post: -
	 */
	public void setNomeUnita(String nome)
	{
		super.setNomeUnita(nome);
	}
	
	/**
	 * Gets the nome unita.
	 *
	 * @pre: -
	 * @post: super.getNomeUnita()!=null
	 * @return super.getNomeUnita()
	 */
	public String getNomeUnita()
	{
		return super.getNomeUnita();
	}
	
	/**
	 * Sets the categoria.
	 *
	 * @param categoria 
	 * @pre: categoria!=null
	 * @post: -
	 */
	public void setCategoria(Categoria categoria)
	{
		super.setCategoria(categoria);
	}
	
	/**
	 * Gets the categoria.
	 *
	 * @pre: -
	 * @post: (CategoriaSensori)super.getCategoria()!=null
	 * @return (CategoriaSensori)super.getCategoria()
	 */
	public CategoriaSensori getCategoria()
	{
		return (CategoriaSensori)super.getCategoria();
	}
	
	/**
	 * Sets the unita domotica.
	 *
	 * @param unita the new unita domotica
	 * @pre unita!=null
	 * @post: -
	 */
	public void setUnitaDomotica(UnitaDomotica unita)
	{
		super.setUnitaDomotica(unita);
	}
	
	/**
	 * Gets the unita domotica.
	 *
	 * @pre: -
	 * @post: super.getUnitaDomotica()!=null
	 * @return super.getUnitaDomotica()
	 */
	public UnitaDomotica getUnitaDomotica()
	{
		return super.getUnitaDomotica();
	}
	
	/**
	 * Gets the stato.
	 *
	 * @pre: -
	 * @post: super.getStato()!=null
	 * @return super.getStato()
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
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA MISURAZIONI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Adds the misurazione.
	 *
	 * @param nomeSensore
	 * @param valore 
	 * @pre: nomeSensore!=null && misuraSensore!=null 
	 * @post: listaMisurazioni.size() = listaMisurazioni.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaMisurazioni.size()-1)) ==> element(k)@pre == element(k))
	 */
	public void addMisurazione(InfoRilevabile infoRilevabile)
	{
			//nomeInfoRil= nomeInfoRil + " Misura " + listaMisurazioni.size() + " :";
			listaMisurazioni.add(infoRilevabile);
	}
	
	/**
	 * Size.
	 * @pre: -
	 * @post: listaMisurazioni.size()>=0
	 * @return the int
	 */
	public int size()
	{
		return listaMisurazioni.size();
	}
	
	/**
	 * Gets the lista misurazioni.
	 * 
	 * @pre: -
	 * @post: (ArrayList -InfoRilevabile-)listaMisurazioni!=null
	 * @return the lista misurazioni
	 */
	public ArrayList<InfoRilevabile> getListaMisurazioni()
	{
		return  listaMisurazioni;
	}
	
	/**
	 * Prints the key value.
	 * 
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 
	 */
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
	
	/**
	 * InfoRilevabile gia presente.
	 *
	 * @param nomeInfoRilevabile 
	 * @pre: nomeInfoRilevabile !=null 
	 * @post: -
	 * @return true, if successful
	 */
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
	
	
	/**
	 * Gets the info rilevabile.
	 *
	 * @param i 
	 * @pre: i>=0
	 * @post: listaMisurazioni.get(i)!=null
	 * @return the info rilevabile
	 */
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
