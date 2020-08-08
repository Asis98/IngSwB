package time;

import java.io.Serializable;
import java.util.Date;

public class Orologio implements Serializable{

	private static final long serialVersionUID = 1L;
	private int minuti;
	private int ora;
	

	/**
	 * Instantiates a new orologio.
	 * @pre: -
	 * @post: -
	 * @invariant: minuti!=null && ora!=null
	 */
	public Orologio()
	{
		Date dt = new Date(); 
		this.ora = dt.getHours();
		this.minuti= dt.getMinutes();
	}
	
	/**
	 * Stampa ora.
	 * 
	 * @pre: -
	 * @post: this.ora+"."+this.minuti != null
	 * @return the string
	 */
	public String stampaOra()
	{
		return String.format("%02d.%02d", this.ora, this.minuti);
	}
	
	/**
	 * Gets the ora.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the ora
	 */
	public int getOra()
	{
		return ora;
	}
	
	/**
	 * Gets the minuti.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the minuti
	 */
	public int getMinuti()
	{
		return minuti;
	}
	
	/**
	 * Avanza ore.
	 * 
	 * @pre: intervallo!=null
	 * @post: -
	 * @param intervallo the intervallo
	 */
	public void avanzaOre(int intervallo)
	{
		this.ora= this.ora+intervallo;
		
	}
	
	/**
	 * Avanza minuti.
	 * 
	 * @pre: intervallo!=null
	 * @post: -
	 * @param intervallo the intervallo
	 */
	public void avanzaMinuti(int intervallo)
	{
		this.minuti = minuti + intervallo;
	}
	
	
	
}
