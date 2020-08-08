package ambiente;

import java.io.Serializable;
import java.util.ArrayList;

import categorie.Stato;
import costanti.Costanti;
import regole.Regola;
import regole.StatoRegola;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import rilevazione.UnitaRilevazione;

// TODO: Auto-generated Javadoc
/**
 * The Class UnitaImmobiliare.
 */
public class UnitaImmobiliare implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String destinazione;
	private ArrayList<UnitaDomotica> unitList;
	private ArrayList<UnitaRilevazione> listaSensori;
	private ArrayList<UnitaRilevazione> listaAttuatori;
	private ArrayList<Regola> listaRegole;
	
	/**
	 * Instantiates a new unita immobiliare.
	 *
	 * @param _destinazione the destinazione
	 * @pre: _destinazione!=null
	 * @post: -
	 * @invariant destinazione!=null && unitList!=null && listaSensori!=null && listaAttuatori!=null && listaRegole!=null
	 */
	public UnitaImmobiliare(String _destinazione)
	{
		this.destinazione= _destinazione;
		unitList= new ArrayList<UnitaDomotica>();
		listaSensori = new ArrayList<UnitaRilevazione>();
		listaAttuatori = new ArrayList<UnitaRilevazione>();
		listaRegole = new ArrayList<Regola>();
	}
	
	/**
	 * Instantiates a new unita immobiliare, with no destination.
	 *
	 * @pre: -
	 * @post: -
	 * @invariant destinazione!=null && unitList!=null && listaSensori!=null && listaAttuatori!=null && listaRegole!=null
	 */
	public UnitaImmobiliare()
	{
		this.destinazione= Costanti.STRINGA_VUOTA;
		unitList= new ArrayList<UnitaDomotica>();
		listaSensori = new ArrayList<UnitaRilevazione>();
		listaAttuatori = new ArrayList<UnitaRilevazione>();
		listaRegole = new ArrayList<Regola>();
	}
	
	
	/**
	 * Sets the destinazione.
	 *
	 * @param _destinazione the new destinazione
	 * @pre: _destinazione!=null
	 * @post: -
	 */
	public void setdestinazione(String _destinazione)
	{
		destinazione= _destinazione;
	}
	
	/**
	 * Gets the destinazione.
	 *
	 * @return destinazione
	 * @pre: -
	 * @post: -
	 */
	public String getdestinazione()
	{
		return destinazione;
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA UNITA' DOMOTICHE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	

	/**
	 * Adds the unita domotica.
	 *
	 * @param unitaDomotica the unita domotica
	 * @pre: unitaDomotica!=null
	 * @post: unitList.size() = unitList.size()@pre + 1
	 * @post: @forall k : [0..size()-2]
	 * (k < (unitList.size()-1)) ==> element(k)@pre == element(k))
	 */
	public void addUnitaDomotica(UnitaDomotica unitaDomotica)
	{
		unitList.add(unitaDomotica);
	}
	
	/**
	 * Verifica presenza unita domotica.
	 *
	 * @param nomeUnita the nome unita
	 * @param classType the class type
	 * @return true, if successful
	 * @pre: nomeUnita!=null && classType!=null
	 * @post: -
	 */
	public boolean verificaPresenzaUnitaDomotica(String nomeUnita, Class<?> classType)
	{
		for (int i=0;i< unitList.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(unitList.get(i).getUnitName().equalsIgnoreCase(nomeUnita) && (classType.isInstance(unitList.get(i))))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Stampa lista.
	 *
	 * @return the String
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaLista()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<unitList.size(); i++)
		{
			sb.append(i);
			sb.append("-");
			sb.append(unitList.get(i).getUnitName());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Gets the unita domotica.
	 *
	 * @param i the i
	 * @return the unita domotica
	 * @pre: i>=0
	 * @post: unitList.get(i)!=null
	 */
	public UnitaDomotica getUnitaDomotica(int i)
	{
		return unitList.get(i);
	}
	
	/**
	 * Size.
	 *
	 * @return unitList.size()
	 * @pre: -
	 * @post: unitList.size()>=0
	 */
	public int size()
	{
		return unitList.size();
	}
	
	/**
	 * Gets the unit list.
	 *
	 * @return unitList
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<UnitaDomotica> getUnitList()
	{
		return unitList;
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmpty()
	{
		return unitList.isEmpty();
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA SENSORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Gets the lista sensori.
	 *
	 * @return the lista sensori
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<UnitaRilevazione> getListaSensori() {
		return listaSensori;
	}

	/**
	 * Sets the lista sensori.
	 *
	 * @param listaSensori the new lista sensori
	 * @pre: listaSensori!=null
	 * @post: -
	 */
	public void setListaSensori(ArrayList<UnitaRilevazione> listaSensori) {
		this.listaSensori = listaSensori;
	}
	
	/**
	 * Checks if is empty sensori.
	 *
	 * @return true, if is empty sensori
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmptySensori()
	{
		return listaSensori.isEmpty();
	}
	
	/**
	 * Stampa lista sensori.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaSensori()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<listaSensori.size(); i++)
		{	
			
			sb.append(i);
			sb.append("-");
			sb.append(listaSensori.get(i).getNomeUnita());
			sb.append("\n");
			sb.append(listaSensori.get(i).getStato());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Stampa lista sensori attivi.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaSensoriAttivi()
	{
		StringBuilder sb = new StringBuilder();
		int index=0;
		for(int i=0; i<listaSensori.size(); i++)
		{	
			if(listaSensori.get(i).getStato() == Stato.ACCESO)
			{
				sb.append(index++);
				sb.append("-");
				sb.append(listaSensori.get(i).getNomeUnita());
				sb.append("\n");
			}

		}
		
		return sb.toString();
	}
	
	/**
	 * Gets lista sensori attivi.
	 *
	 * @return the lista sensori attivi
	 * @pre: -
	 * @post: if(listaSensori.get(i).getStato() == Stato.ACCESO) for all i element then
	 * 			attivi ! = null
	 */
	public ArrayList<UnitaRilevazione> getListaSensoriAttivi()
	{
		ArrayList<UnitaRilevazione> attivi = new ArrayList<UnitaRilevazione>();
		
		for(int i=0; i<listaSensori.size(); i++)
		{	
			if(listaSensori.get(i).getStato() == Stato.ACCESO)
			{
				attivi.add(listaSensori.get(i));
			}
		}
		
		return attivi;
	}
	
	/**
	 * Gets lista sensori disattivi.
	 *
	 * @return the lista sensori disattivi
	 * @pre: -
	 * @post: if(listaSensori.get(i).getStato() == Stato.SPENTO) for all i element then
	 * 			disattivi ! = null
	 */
	public ArrayList<UnitaRilevazione> getListaSensoriDisattivi()
	{
		ArrayList<UnitaRilevazione> disattivi = new ArrayList<UnitaRilevazione>();
		
		for(int i=0; i<listaSensori.size(); i++)
		{	
			if(listaSensori.get(i).getStato() == Stato.SPENTO)
			{
				disattivi.add(listaSensori.get(i));
			}
		}
		
		return disattivi;
	}
	
	/**
	 * Stampa lista sensori disattivi.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaSensoriDisattivi()
	{
		StringBuilder sb = new StringBuilder();
		int index=0;
		for(int i=0; i<listaSensori.size(); i++)
		{	
			if(listaSensori.get(i).getStato() == Stato.SPENTO)
			{
				sb.append(index++);
				sb.append("-");
				sb.append(listaSensori.get(i).getNomeUnita());
				sb.append("\n");
			}

		}
		
		return sb.toString();
	}
	
	/**
	 * Size lista sensori.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaSensori.size()>=0
	 */
	public int sizeSensori()
	{
		return listaSensori.size();
	}
	
	/**
	 * Gets the sensore.
	 *
	 * @param i the i
	 * @return the sensore
	 * @pre: i>=0
	 * @post: (Sensore) listaSensori.get(i)!=null
	 */
	public Sensore getSensore(int i)
	{
		return (Sensore) listaSensori.get(i);
	}
	
	/**
	 * Size sensori disattivati.
	 *@pre: -
	 *@post: if(listaSensori.get(i).getStato() == Stato.SPENTO) for all i element then
	 * 			conta != 0
	 *@return the int
	 */
	public int sizeSensoriDisattivati()
	{
		int conta = 0;
		
		for(int i=0; i<listaSensori.size(); i++)
		{
			if(listaSensori.get(i).getStato() == Stato.SPENTO)
			{
				conta++;
			}
		}
		
		return conta;
	}
	
	
	
	/**
	 * Size sensori attivati.
	 *
	 * @return the int
	 */
	public int sizeSensoriAttivati()
	{
		return listaSensori.size() - sizeSensoriDisattivati();
	}
	
	
	/**
	 * Cerca sensore.
	 *
	 * @param nome the nome
	 * @pre: nome !=null
	 * @post: if(listaSensori.get(i).getNomeUnita().equalsIgnoreCase(nome))
				return (Sensore) listaSensori.get(i);
			else null
	 * @return the sensore
	 */
	public Sensore cercaSensore(String nome)
	{
		for (int i=0;i< listaSensori.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(listaSensori.get(i).getNomeUnita().equalsIgnoreCase(nome))
				return (Sensore) listaSensori.get(i);
		}
		
		return null;
	}

	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA ATTUATORI
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Gets the lista attuatori.
	 *
	 * @return the lista attuatori
	 * @pre: 
	 * @post: 
	 */
	public ArrayList<UnitaRilevazione> getListaAttuatori() 
	{
		return listaAttuatori;
	}

	/**
	 * Sets the lista attuatori.
	 *
	 * @param listaAttuatori the new lista attuatori
	 * @pre: listaAttuatori!=null
	 * @post: -
	 */
	public void setListaAttuatori(ArrayList<UnitaRilevazione> listaAttuatori) 
	{
		this.listaAttuatori = listaAttuatori;
	}
	
	/**
	 * Checks if is empty attuatori.
	 *
	 * @return true, if is empty attuatori
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmptyAttuatori()
	{
		return listaAttuatori.isEmpty();
	}
	
	/**
	 * Stampa lista attuatori.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaAttuatori()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<listaAttuatori.size(); i++)
		{
			sb.append(i);
			sb.append("-");
			sb.append(listaAttuatori.get(i).getNomeUnita());
			sb.append("\n");
			sb.append(listaAttuatori.get(i).getStato());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Stampa lista attuatori attivi.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaAttuatoriAttivi()
	{
		StringBuilder sb = new StringBuilder();
		int index=0;
		for(int i=0; i<listaAttuatori.size(); i++)
		{
			if(listaAttuatori.get(i).getStato() == Stato.ACCESO)
			{
				sb.append(index++);
				sb.append("-");
				sb.append(listaAttuatori.get(i).getNomeUnita());
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Stampa lista attuatori disattivi.
	 *
	 * @return the string
	 * @pre: -
	 * @post: sb!=null
	 */
	public String stampaListaAttuatoriDisattivi()
	{
		StringBuilder sb = new StringBuilder();
		int index=0;
		for(int i=0; i<listaAttuatori.size(); i++)
		{
			if(listaAttuatori.get(i).getStato() == Stato.SPENTO)
			{
				sb.append(index++);
				sb.append("-");
				sb.append(listaAttuatori.get(i).getNomeUnita());
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Gets the lista attuatori attivi.
	 * @pre: -
	 * @post: if(listaSensori.get(i).getStato() == Stato.ACCESO) for all i element then
	 * 			attivi ! = null
	 * @return the lista attuatori attivi
	 */
	public ArrayList<UnitaRilevazione> getListaAttuatoriAttivi()
	{
		ArrayList<UnitaRilevazione> attivi = new ArrayList<UnitaRilevazione>();
		
		for(int i=0; i<listaAttuatori.size(); i++)
		{	
			if(listaAttuatori.get(i).getStato() == Stato.ACCESO)
			{
				attivi.add(listaAttuatori.get(i));
			}
		}
		
		return attivi;
	}
	
	/**
	 * Gets the lista attuatori disattivi.
	 * @pre: -
	 * @post: if(listaSensori.get(i).getStato() == Stato.SPENTO) for all i element then
	 * 			disattivi ! = null
	 * @return the lista attuatori disattivi
	 */
	public ArrayList<UnitaRilevazione> getListaAttuatoriDisattivi()
	{
		ArrayList<UnitaRilevazione> disattivi = new ArrayList<UnitaRilevazione>();
		
		for(int i=0; i<listaAttuatori.size(); i++)
		{	
			if(listaAttuatori.get(i).getStato() == Stato.SPENTO)
			{
				disattivi.add(listaAttuatori.get(i));
			}
		}
		
		return disattivi;
	}
	
	/**
	 * Size lista attuatori.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaAttuatori.size()>=0
	 */
	public int sizeAttuatori()
	{
		return listaAttuatori.size();
	}
	
	/**
	 * Gets the attuatore.
	 *
	 * @param i the i
	 * @return the attuatore
	 * @pre: i>=0
	 * @post: (Attuatore) listaAttuatori.get(i)!=null
	 */
	public Attuatore getAttuatore(int i)
	{
		return (Attuatore) listaAttuatori.get(i);
	}
	
	/**
	 * Size attuatori disattivati.
	 *
	 * @return the int
	 * @pre: -
	 * @post: if(listaAttuatori.get(i).getStato() == Stato.SPENTO) conta++ && conta>=0
	 */
	public int sizeAttuatoriDisattivati()
	{
		int conta = 0;
		
		for(int i=0; i<listaAttuatori.size(); i++)
		{
			if(listaAttuatori.get(i).getStato() == Stato.SPENTO)
			{
				conta++;
			}
		}
		
		return conta;
	}
	
	/**
	 * Cerca attuatore.
	 *
	 * @param nome the nome
	 * @pre: nome != null
	 * @post: if(listaAttuatori.get(i).getNomeUnita().equalsIgnoreCase(nome))
				return (Attuatore) listaAttuatori.get(i);
			else null
	 * @return the attuatore
	 */
	public Attuatore cercaAttuatore(String nome)
	{
		for (int i=0;i< listaAttuatori.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(listaAttuatori.get(i).getNomeUnita().equalsIgnoreCase(nome))
				return (Attuatore) listaAttuatori.get(i);
		}
		
		return null;
	}
	
	/**
	 * Size attuatori attivati.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaAttuatori.size() - sizeAttuatoriDisattivati() >= 0
	 */
	public int sizeAttuatoriAttivati()
	{
		return listaAttuatori.size() - sizeAttuatoriDisattivati();
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA REGOLE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Sets the regola.
	 *
	 * @param regola the new regola
	 * @pre: regola!=null
	 * @post: listaRegole.size() = listaRegole.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaRegole.size()-1)) ==> element(k)@pre == element(k)) 
	 * 
	 */
	public void setRegola (Regola regola)
	{
		listaRegole.add(regola);
	}
	
	/**
	 * Gets the regola.
	 *
	 * @param i the i
	 * @return the regola
	 * @pre: i>=0
	 * @post: -
	 */
	public Regola getRegola(int i)
	{
		return listaRegole.get(i);
	}
	
	/**
	 * Size regole.
	 *
	 * @return the int
	 * @pre: -
	 * @post: listaRegole.size()>=0
	 */
	public int sizeRegole()
	{
		return listaRegole.size();
	}
	
	/**
	 * Checks if is empty regole.
	 *
	 * @return true, if is empty regole
	 * @pre: -
	 * @post: -
	 */
	public boolean isEmptyRegole()
	{
		return listaRegole.isEmpty();
	}
	
	/**
	 * Gets the lista regole.
	 *
	 * @return the lista regole
	 * @pre: -
	 * @post: -
	 */
	public ArrayList<Regola> getListaRegole()
	{
		return listaRegole;
	}
	
	/**
	 * Conta regole abilitate.
	 *
	 * @return the int
	 * @pre: -
	 * @post: if(listaRegole.get(i).getStato() == StatoRegola.ABILITATA) conto++;
	 */
	public int contaRegoleAbilitate()
	{
		int conto =0;
		for(int i=0;i<listaRegole.size();i++)
		{
			if(listaRegole.get(i).getStato() == StatoRegola.ABILITATA)
				conto++;
		}
		
		return conto;
	}
	
	/**
	 * Conta regole disabilitate.
	 *
	 * @return the int
	 * @pre: -
	 * @post: if(listaRegole.get(i).getStato() == StatoRegola.DISABILITATA) conto++;
	 */
	public int contaRegoleDisabilitate()
	{
		int conto =0;
		for(int i=0;i<listaRegole.size();i++)
		{
			if(listaRegole.get(i).getStato() == StatoRegola.DISABILITATA)
				conto++;
		}
		
		return conto;
	}
	
	
	
}
