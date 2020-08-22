package categorie;

import java.io.Serializable;

import costanti.Costanti;


public class Parametro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nomeParametro;
	private double valore;
	
	/**
	 * Instantiates a new parametro.
	 *
	 * @param _nomeParametro
	 * @param _valore 
	 * @pre: _nomeParametro!=null && _valore!=null
	 * @post: -
	 * @invariant nomeParametro!=null && valore!=null
	 */
	public Parametro(String _nomeParametro, double _valore)
	{
		nomeParametro = _nomeParametro;
		valore = _valore;
	}
	
	/**
	 * Instantiates a new parametro, without nomeParametro and valore
	 * 
	 * @pre: -
	 * @post: -
	 * @invariant nomeParametro!=null && valore!=null
	 */
	public Parametro()
	{
		//costruttore vuoto, per quando i valori non sono settati
		this.nomeParametro = Costanti.STRINGA_VUOTA;
	}
	
	public Parametro(String nomeParametro) {
		
		this.nomeParametro = nomeParametro;
	}

	/**
	 * Sets the nome parametro.
	 *
	 * @param _nomeParametro 
	 * @pre: _nomeParametro!=null
	 * @post: -
	 */
	public void setNomeParametro(String _nomeParametro)
	{
		nomeParametro = _nomeParametro;
	}
	
	/**
	 * Gets the nome parametro.
	 *
	 * @pre:-
	 * @post:-
	 * @return the nome parametro
	 */
	public String getNomeParametro() 
	{
		return nomeParametro;
	}
	
	/**
	 * Gets the valore.
	 *
	 * @pre:-
	 * @post:-
	 * @return the valore
	 */
	public double getValore() 
	{
		return valore;
	}

	/**
	 * Sets the valore.
	 *
	 * @param valore the new valore
	 * @pre: _valore!=null
	 * @post:-
	 */
	public void setValore(double _valore) 
	{
		valore = _valore;
	}



	
}
