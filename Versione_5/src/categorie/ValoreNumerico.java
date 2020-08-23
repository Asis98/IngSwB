/*
 * 
 */
package categorie;

import java.io.Serializable;

import costanti.Costanti;
import utility.NumeriCasuali;

public class ValoreNumerico implements Valore<Double> , Serializable{
	
	private static final long serialVersionUID = 1L;
	private Double valore;

	public ValoreNumerico(Double valore) {
		super();
		this.valore = valore;
	}

	public ValoreNumerico()
	{
		this.valore = this.estraiValoreNumerico();
	}
	
	public Double estraiValoreNumerico()
	{
		return NumeriCasuali.estraiDouble(Costanti.MIN, Costanti.MAXVALUE);
	}
	
	@Override
	public Double getValore() 
	{
		return valore;
	}

	@Override 
	public void setValore(Double valore) 
	{
		this.valore = valore;
	}
	
	public String toString() {
		return String.format("%.2f", this.valore);
	}

}
