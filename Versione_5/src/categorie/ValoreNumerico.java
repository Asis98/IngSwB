/*
 * 
 */
package categorie;

import java.io.Serializable;

import costanti.Costanti;
import it.unibs.fp.mylib.NumeriCasuali;

public class ValoreNumerico implements Valore<Double> , Serializable{
	
	private static final long serialVersionUID = 1L;
	private Double valore;
	
	/**
	 * Instantiates a new valore numerico.
	 *
	 * @param valore the valore
	 * @pre: valore!=null
	 * @post: -
	 * @invariant: valore!=null
	 */
	public ValoreNumerico(Double valore) {
		super();
		this.valore = valore;
	}
	
	/**
	 * Instantiates a new valore numerico.
	 * pre: -
	 * @post: -
	 */
	public ValoreNumerico()
	{
		this.valore = this.estraiValoreNumerico();
	}
	
	/**
	 * Estrai valore numerico.
	 *
	 * @pre: -
	 * @post: -
	 * @return the double
	 */
	public Double estraiValoreNumerico()
	{
		return NumeriCasuali.estraiDouble(Costanti.MIN, Costanti.MAXVALUE);
	}
	
	/**
	 * Gets the valore.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the valore
	 */
	@Override
	public Double getValore() 
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
	public void setValore(Double valore) 
	{
		this.valore = valore;
	}
	
	public String toString() {
		return String.format("%.2f", this.valore);
	}

}
