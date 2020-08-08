package categorie;

import java.io.Serializable;


public class InfoRilevabile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoInfoRilevabile; //dominio
	private boolean nonnumerica;
	private Valore valoreAttuale;
	

	/**
	 * Instantiates a new info rilevabile.
	 *
	 * @param _tipoInfoRilevabile 
	 * @pre: _tipoInfoRilevabile!=null
	 * @post:-
	 * @invariant: listaMisurazioni!=null && nonnumerica!=null && valore!=null
	 */
	public InfoRilevabile(String _tipoInfoRilevabile)
	{
		tipoInfoRilevabile=_tipoInfoRilevabile;
	}
	
	/**
	 * Sets the tipo info rilevabile.
	 *
	 * @param _tipoInfoRilevabile the new tipo info rilevabile
	 * @pre: _tipoInfoRilevabile!=null
	 * @post: -
	 */
	public void setTipoInfoRilevabile(String _tipoInfoRilevabile)
	{
		tipoInfoRilevabile=_tipoInfoRilevabile;
	}
	
	/**
	 * Gets the tipo info rilevabile.
	 *
	 * @pre: -
	 * @post: tipoInfoRilevabile!=null
	 * @return the tipo info rilevabile
	 */
	public String getTipoInfoRilevabile()
	{
		return tipoInfoRilevabile;
	}
	
	/**
	 * Checks if is nonnumerica.
	 *
	 * @pre: -
	 * @post: -
	 * @return true, if is nonnumerica
	 */
	public boolean isNonnumerica() 
	{
		return nonnumerica;
	}

	/**
	 * Sets the nonnumerica.
	 * 
	 * @pre: nonnumerica!=null
	 * @post: -
	 * @param nonnumerica the new nonnumerica
	 */
	public void setNonnumerica(boolean nonnumerica) 
	{
		this.nonnumerica = nonnumerica;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI VALORE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Gets the valore.
	 *
	 * @pre: -
	 * @post: -
	 * @return the valore
	 */
	public Valore getValoreAttuale() 
	{
		return valoreAttuale;
	}

	/**
	 * Sets the valore.
	 *
	 * @param valore the new valore
	 * @pre: valore!=null
	 * @post: -
	 */
	public void setValore(Valore valore) 
	{
		this.valoreAttuale = valore;
	}


}
