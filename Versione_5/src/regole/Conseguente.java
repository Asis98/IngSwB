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
	
	public Conseguente(Attuatore attuatore, ModalitaOperativa nuovaModalita,Time start) 
	{
		this.attuatore = attuatore;
		this.nuovaModalita = nuovaModalita;
		this.start = start;
	}

	public Conseguente()
	{
		this.attuatore = new Attuatore();
		this.nuovaModalita = null;
		this.start= new Time(0,0);
	}
	
	public Attuatore getAttuatore() 
	{
		return attuatore;
	}
	
	public void setAttuatore(Attuatore attuatore) 
	{
		this.attuatore = attuatore;
	}
	

	public ModalitaOperativa getNuovaModalita() 
	{
		return nuovaModalita;
	}

	public void setNuovaModalita(ModalitaOperativa nuovaModalita) 
	{
		this.nuovaModalita = nuovaModalita;
	}
	
	public Time getStart() 
	{
		return start;
	}
	
	public void setStart(Time start) 
	{
		this.start = start;
	}
	
	public void setStart(Orologio orario) 
	{
		if(start == null)
			start = new Time(0,0);
		this.start.setOra(new Orologio().getOra());
		this.start.setMinuti(new Orologio().getMinuti());
	}
	
	public void eseguiConseguente()
	{
		this.attuatore.setModOperativa(this.nuovaModalita);
	}
	
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
