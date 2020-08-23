package categorie;

import java.io.Serializable;

import costanti.Costanti;


public class Parametro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nomeParametro;
	private double valore;
	
	public Parametro(String _nomeParametro, double _valore)
	{
		nomeParametro = _nomeParametro;
		valore = _valore;
	}
	
	public Parametro()
	{
		//costruttore vuoto, per quando i valori non sono settati
		this.nomeParametro = Costanti.STRINGA_VUOTA;
	}
	
	public Parametro(String nomeParametro) {
		
		this.nomeParametro = nomeParametro;
	}
	
	public void setNomeParametro(String _nomeParametro)
	{
		nomeParametro = _nomeParametro;
	}
	public String getNomeParametro() 
	{
		return nomeParametro;
	}
	
	public double getValore() 
	{
		return valore;
	}

	public void setValore(double _valore) 
	{
		valore = _valore;
	}

}
