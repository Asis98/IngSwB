package categorie;

import java.io.Serializable;

import costanti.Costanti;

public class ValoreNonNumerico implements Valore<String> , Serializable{
	
	private static final long serialVersionUID = 1L;
	private String valore;

	/**
	 * Instantiates a new valore non numerico.
	 *
	 * @param valore the valore
	 * @pre: valore!=null
	 * @post: -
	 * @invariant: valore!=null
	 */
	public ValoreNonNumerico(String valore) {
		super();
		this.valore = valore;
	}
	
	/**
	 * Instantiates a new valore non numerico.
	 * 
	 * @param -
	 * @pre: -
	 * @post: -
	 * @invariant: valore!=null
	 */
	public ValoreNonNumerico() {
		super();
		this.valore = Costanti.STRINGA_VUOTA;
	}
	
	/**
	 * Gets the valore.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the valore
	 */
	@Override
	public String getValore() 
	{
		return valore;
	}

	/**
	 * Sets the valore.
	 *
	 * @pre: valore!=null
	 * @param valore the new valore
	 */
	@Override
	public void setValore(String valore) 
	{
		this.valore = valore;
	}


}
