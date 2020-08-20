package regole;

import java.io.Serializable;

import categorie.ModalitaOperativa;
import categorie.Stato;
import costanti.Costanti;
import rilevazione.Attuatore;
import time.Orologio;
import time.Time;

public class Conseguente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Attuatore attuatore;
	private ModalitaOperativa nuovaModalita;
	private Time start;
	
	/**
	 * Instantiates a new conseguente.
	 *
	 * @param attuatore
	 * @param nuovaModalita 
	 * @pre: attuatore!=null && nuovaModalita!=null && start!=null
	 * @post: -
	 * @invariant: attuatore!=null && nuovaModalita!=null && start!=null
	 */
	public Conseguente(Attuatore attuatore, ModalitaOperativa nuovaModalita,Time start) 
	{
		this.attuatore = attuatore;
		this.nuovaModalita = nuovaModalita;
		this.start = start;
	}
	/**
	 * Instantiates a new conseguente.
	 * 
	 * @param -
	 * @pre: -
	 * @post: -
	 * @invariant: attuatore!=null && nuovaModalita!=null && start!=null
	 */
	public Conseguente()
	{
		this.attuatore = new Attuatore();
		this.nuovaModalita = null;
		this.start= new Time(0,0);
	}
	
	/**
	 * Gets the attuatore.
	 *
	 * @pre: -
	 * @post: -
	 * @return the attuatore
	 */
	public Attuatore getAttuatore() 
	{
		return attuatore;
	}
	
	/**
	 * Sets the attuatore.
	 * 
	 * @pre: attuatore!=null
	 * @post: -
	 * @param attuatore the new attuatore
	 */
	public void setAttuatore(Attuatore attuatore) 
	{
		this.attuatore = attuatore;
	}
	
	/**
	 * Gets the nuova modalita.
	 *
	 * @pre: -
	 * @post: -
	 * @return the nuova modalita
	 */
	public ModalitaOperativa getNuovaModalita() 
	{
		return nuovaModalita;
	}
	
	/**
	 * Sets the nuova modalita.
	 *
	 * @param nuovaModalita the new nuova modalita
	 * @pre: nuovaModalita!=null 
	 * @post: -
	 */
	public void setNuovaModalita(ModalitaOperativa nuovaModalita) 
	{
		this.nuovaModalita = nuovaModalita;
	}
	
	/**
	 * Gets the start.
	 *
	 * @pre: -
	 * @post: -
	 * @return the start
	 */
	public Time getStart() 
	{
		return start;
	}
	
	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 * @pre: start!=null
	 * @post: -
	 */
	public void setStart(Time start) 
	{
		this.start = start;
	}
	
	/**
	 * Sets the start.
	 *
	 * @param orario the new start
	 * @pre: orario!=null
	 * @post: -
	 */
	public void setStart(Orologio orario) 
	{
		if(start == null)
			start = new Time(0,0);
		this.start.setOra(new Orologio().getOra());
		this.start.setMinuti(new Orologio().getMinuti());
	}
	
	/**
	 * Esegui conseguente.
	 * 
	 * @pre: -
	 * @post: sets a new attuatore.modalitaOperativa
	 */
	public void eseguiConseguente()
	{
		this.attuatore.setModOperativa(this.nuovaModalita);
	}
	
	/**
	 * Stampa conseguente.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */
	public String stampaConseguente()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(attuatore.getNomeUnita());
		sb.append(Costanti.UGUALE);
		sb.append(nuovaModalita.getNomeModOperativa());
		sb.append("\n"); 
		if(!nuovaModalita.parametriIsEmpty())
		{
			sb.append(Costanti.LISTA_PARAMETRI);
			sb.append("\n");
			for(int i =0;i<nuovaModalita.size();i++)
			{
				sb.append(nuovaModalita.getParametro(i).getNomeParametro());
				sb.append(Costanti.UGUALE);	
				sb.append(nuovaModalita.getParametro(i).getValore());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public Stato getStatoConseguente()
	{
		return attuatore.getStato();
	}
	
	public String getNomeConseguente()
	{
		return attuatore.getNomeUnita();
	}
}
