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

	
	
}
