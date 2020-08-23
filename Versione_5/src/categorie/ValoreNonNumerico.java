package categorie;

import java.io.Serializable;

import costanti.Costanti;

public class ValoreNonNumerico implements Valore<String> , Serializable{
	
	private static final long serialVersionUID = 1L;
	private String valore;

	public ValoreNonNumerico(String valore) {
		super();
		this.valore = valore;
	}
	
	public ValoreNonNumerico() {
		super();
		this.valore = Costanti.STRINGA_VUOTA;
	}
	
	@Override
	public String getValore() 
	{
		return valore;
	}

	@Override
	public void setValore(String valore) 
	{
		this.valore = valore;
	}

}
