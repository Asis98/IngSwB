package regole;

import java.io.Serializable;
import java.util.ArrayList;

import categorie.ModalitaOperativa;
import categorie.Stato;
import categorie.Valore;
import costanti.Costanti;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import time.Orologio;
import time.Time;

public class Regola implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Antecedente> listaAntecedenti;
	private ArrayList <Conseguente> listaConseguenti ;
	private StatoRegola stato ;

	public Regola(ArrayList<Antecedente> listaAntecedenti, ArrayList<Conseguente> listaConseguenti) 
	{
		this.listaAntecedenti = listaAntecedenti;
		this.listaConseguenti = listaConseguenti;
		this.stato = StatoRegola.ABILITATA;
	}
	
	public Regola()
	{
		this.listaAntecedenti = new ArrayList<Antecedente>();
		this.listaConseguenti = new ArrayList<Conseguente>();
		this.stato = StatoRegola.ABILITATA;
	}
	
	public void  setAntecedente(Sensore a,  String opRelazionale , Sensore b)
	{	
		if(!(new AntecedenteSensore(a, opRelazionale , b).confrontaElementiAntecedente()))
		{
			listaAntecedenti.add(new AntecedenteSensore(a,opRelazionale,b));
		}
	}
	
	public void  setAntecedente(Sensore a,  String opRelazionale, Valore costante)
	{
		listaAntecedenti.add(new AntecedenteCostante(a,opRelazionale, costante));
	}
	
	public void  setAntecedente(String opRelazionale, Time ora_b)
	{
		listaAntecedenti.add(new AntecedenteTime(opRelazionale, ora_b));
	}
	
	public void setConseguente(Attuatore attuatore, ModalitaOperativa modOp, Time start)
	{
		listaConseguenti.add(new Conseguente(attuatore,modOp,start));
	}

	public void setConseguente(Attuatore attuatore, ModalitaOperativa modOp)
	{
		listaConseguenti.add(new Conseguente(attuatore,modOp,null));
	}
	
	public StatoRegola getStato() 
	{
		return stato;
	}

	public void setStato(StatoRegola stato) 
	{
		this.stato = stato;
	}
	
	public boolean valutaRegola()
	{
		for(int i=0;i<listaAntecedenti.size(); i++)
		{
			  if(!listaAntecedenti.get(i).valutaAntecedente())
					  return false;
		} 
		//setto conseguente
		for(int i=0;i<listaConseguenti.size();i++)
		{
			if(listaConseguenti.get(i).getStart() == null)
				listaConseguenti.get(i).setStart(new Orologio());
			
			listaConseguenti.get(i).eseguiConseguente();
				
		}
			
			return true;
	}
	
	public String stampaRegola()
	{	
			StringBuilder sb = new StringBuilder();
			int i=0;
			sb.append(Costanti.IF);
			for(Antecedente elemento : listaAntecedenti)
			{
				sb.append(elemento.stampaAntecedente());
				if(listaAntecedenti.size()>1 && i<listaAntecedenti.size()-1)
					sb.append(Costanti.AND);
				sb.append("\n");
				i++;
			}
			
			sb.append("\n");
			
			for(Conseguente elemento : listaConseguenti)
			{
				sb.append(elemento.stampaConseguente());
				sb.append("\n");
			}
			
			return sb.toString();

	}
		
	public boolean isEmptyAntecedente()
	{
		return listaAntecedenti.isEmpty();
	}
	
	public Antecedente getAntecedente(int i)
	{
		return listaAntecedenti.get(i);
	}
	
	public Conseguente getConseguente(int i)
	{
		return listaConseguenti.get(i);
	}
	
	public int sizeAntecedenti()
	{
		return listaAntecedenti.size();
	}
	
	public int sizeConseguenti()
	{
		return listaConseguenti.size();
	}

	public boolean antecedentiAttivi()
	{
		//torna vero se tutti i sensori sono attivi, falso se ne esiste almeno uno disattivo
		for(int i=0; i<listaAntecedenti.size(); i++)
		{
			if(listaAntecedenti.get(i) instanceof AntecedenteSensore && 
			   (((AntecedenteSensore)listaAntecedenti.get(i)).getStatoSensoreA() == Stato.SPENTO ||
			   ((AntecedenteSensore)listaAntecedenti.get(i)).getStatoSensoreB() == Stato.SPENTO))
			{
				return false;
			}
			else if(((AntecedenteCostante)listaAntecedenti.get(i)).getStatoSensoreA() == Stato.SPENTO)
			{
				return false;
			}
		}
		
		return true;
	}

	public boolean conseguentiAttivi()
	{
		for(int i=0; i<listaConseguenti.size(); i++)
		{
			if(listaConseguenti.get(i).getStatoConseguente() == Stato.SPENTO)
				return false;
		}
		
		return true;
	}
	
	//verifica se sensore sia contenuto in un antecedente
	public boolean cercaSensore(Sensore sensore)
	{
		for(int i=0;i<listaAntecedenti.size();i++)
		{
			//verifica che sensore.nomeUnita sia uguale a operando_a.nomeUnita o operando_b.nomeUnita
			if(listaAntecedenti.get(i) instanceof AntecedenteSensore 
				&& (((AntecedenteSensore)listaAntecedenti.get(i)).getSensoreAntecedente().equalsIgnoreCase(sensore.getNomeUnita())
				|| ((AntecedenteSensore)listaAntecedenti.get(i)).getOperando_b().equalsIgnoreCase(sensore.getNomeUnita())))
				return true;
			//verifica che sensore.nomeUnita sia uguale a operando_a.nomeUnita
			else if(((AntecedenteCostante)listaAntecedenti.get(i)).getSensoreAntecedente().equalsIgnoreCase(sensore.getNomeUnita()))
					return true;
		}
		return false;
	}
	
	//verifica se attuatore sia contenuto in un conseguente della regola
	public boolean cercaAttuatore(Attuatore attuatore)
	{
		for(int i=0;i<listaConseguenti.size();i++)
		{
			if(listaConseguenti.get(i).getNomeConseguente().equalsIgnoreCase(attuatore.getNomeUnita()))
				return true;
		}
		return false;
	}
	
}
