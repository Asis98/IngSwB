package categorie;

import java.io.Serializable;


public class InfoRilevabile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoInfoRilevabile; //dominio
	private boolean nonnumerica;
	private Valore valoreAttuale;
	
	public InfoRilevabile(String _tipoInfoRilevabile)
	{
		tipoInfoRilevabile=_tipoInfoRilevabile;
	}
	
	public void setTipoInfoRilevabile(String _tipoInfoRilevabile)
	{
		tipoInfoRilevabile=_tipoInfoRilevabile;
	}
	
	public String getTipoInfoRilevabile()
	{
		return tipoInfoRilevabile;
	}
	
	public boolean isNonnumerica() 
	{
		return nonnumerica;
	}

	public void setNonnumerica(boolean nonnumerica) 
	{
		this.nonnumerica = nonnumerica;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI VALORE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	public Valore getValoreAttuale() 
	{
		return valoreAttuale;
	}

	public void setValore(Valore valore) 
	{
		this.valoreAttuale = valore;
	}


}
