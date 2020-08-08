package time;

import java.io.Serializable;

import costanti.Costanti;

public class Time implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int ora;
	private int minuti;
	
	/**
	 * Instantiates a new time.
	 *
	 * @param orario 
	 * @pre: orario!=null
	 * @post: -
	 * @invariant: ora!=null && minuti!=null
	 * 
	 */
	public Time(Orologio orario)
	{
		this.ora=orario.getOra();
		this.minuti=orario.getMinuti();
	}
	
	/**
	 * Instantiates a new time.
	 * 
	 * @param: -
	 * @pre: - 
	 * @post: -
	 * @invariant: ora!=null && minuti!=null
	 */
	public Time()
	{
		this.ora=Costanti.MIN;
		this.minuti=Costanti.MIN;
	}
	
	/**
	 * Instantiates a new time.
	 *
	 * @param ora 
	 * @param minuti 
	 * @pre: ora!=null && minuti!=null
	 * @post: -
	 * @invariant: ora!=null && minuti!=null
	 */
	public Time(int ora, int minuti) {
		this.ora = ora;
		this.minuti = minuti;
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
	 * Sets the ora.
	 * 
	 * @pre: ora!=null
	 * @post: -
	 * @param ora the new ora
	 */
	public void setOra(int ora) 
	{
		this.ora = ora;
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
	 * Sets the minuti.
	 * 
	 * @pre: minuti!=null
	 * @post: -
	 * @param minuti the new minuti
	 */
	public void setMinuti(int minuti) 
	{
		this.minuti = minuti;
	}
	
	public String toString()
	{
		return ora+"."+minuti;
	}
	
	/**
	 * Minuti totali.
	 * 
	 * @pre: -
	 * @post: (double) ((ora*60)+minuti)!=null
	 * @return the double
	 */
	public Double minutiTotali()
	{
		return (double) ((ora*60)+minuti);
	}
	

}
