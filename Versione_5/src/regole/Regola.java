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

	/**
     * Instantiates a new regola.
     *
     * @param listaAntecedenti 
     * @param listaConseguenti 
     * @pre: listaAntecedenti!=null && listaConseguenti!=null
     * @post: -
     * @invariant: listaAntecedenti!=null && listaConseguenti!=null && stato!=null
     * 
     */
	public Regola(ArrayList<Antecedente> listaAntecedenti, ArrayList<Conseguente> listaConseguenti) 
	{
		this.listaAntecedenti = listaAntecedenti;
		this.listaConseguenti = listaConseguenti;
		this.stato = StatoRegola.ABILITATA;
	}
	
	/**
  	* Instantiates a new regola.
  	* 
  	* @param: -
  	* @pre: listaAntecedenti!=null && listaConseguenti!=null
    * @post: -
    * @invariant: listaAntecedenti!=null && listaConseguenti!=null && stato!=null
    */
	public Regola()
	{
		this.listaAntecedenti = new ArrayList<Antecedente>();
		this.listaConseguenti = new ArrayList<Conseguente>();
		this.stato = StatoRegola.ABILITATA;
	}
	
	/**
	 * Sets the antecedente.
	 *
	 * @param a 
	 * @param opRelazionale 
	 * @param costante 
	 * @pre: a!=null && opRelazionale!=null && costante!=null
	 * @post: if(!(new AntecedenteSensore(a, opRelazionale , b).confrontaElementiAntecedente())) 
	 * listaAntecedenti.size() = listaAntecedenti.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaAntecedenti.size()-1)) ==> element(k)@pre == element(k)) 
	 */
	public void  setAntecedente(Sensore a,  String opRelazionale , Sensore b)
	{	
		if(!(new AntecedenteSensore(a, opRelazionale , b).confrontaElementiAntecedente()))
		{
			listaAntecedenti.add(new AntecedenteSensore(a,opRelazionale,b));
		}
	}
	
	/**
	 * Sets the antecedente.
	 *
	 * @param a 
	 * @param opRelazionale 
	 * @param b 
	 * @pre: a!=null && opRelazionale!=null && b!=null
	 * @post: listaAntecedenti.size() = listaAntecedenti.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaAntecedenti.size()-1)) ==> element(k)@pre == element(k)) 
	 */
	public void  setAntecedente(Sensore a,  String opRelazionale, Valore costante)
	{
		listaAntecedenti.add(new AntecedenteCostante(a,opRelazionale, costante));
	}
	
	/**
	 * Sets the antecedente.
	 *
	 * @param opRelazionale 
	 * @param ora_b 
	 * @pre: opRelazionale!=null && ora_b!=null
	 * @post: listaAntecedenti.size() = listaAntecedenti.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaAntecedenti.size()-1)) ==> element(k)@pre == element(k)) 
	 */
	public void  setAntecedente(String opRelazionale, Time ora_b)
	{
		listaAntecedenti.add(new AntecedenteTime(opRelazionale, ora_b));
	}
	
	/**
	 * Sets the conseguente.
	 *
	 * @param attuatore 
	 * @param modOp 
	 * @pre: attuatore!=null && modOp!=null && star!=null
	 * @post: listaConseguenti.size() = listaConseguenti.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaConseguenti.size()-1)) ==> element(k)@pre == element(k)) 
	 */	
	public void setConseguente(Attuatore attuatore, ModalitaOperativa modOp, Time start)
	{
		listaConseguenti.add(new Conseguente(attuatore,modOp,start));
	}
	
	/**
	 * Sets the conseguente.
	 *
	 * @param attuatore 
	 * @param modOp 
	 * @pre:  attuatore!=null && modOp!=null
	 * @post: listaConseguenti.size() = listaConseguenti.size()@pre + 1 
	 * @post: @forall k : [0..size()-2]
	 * (k < (listaConseguenti.size()-1)) ==> element(k)@pre == element(k)) 
	 */
	public void setConseguente(Attuatore attuatore, ModalitaOperativa modOp)
	{
		listaConseguenti.add(new Conseguente(attuatore,modOp,null));
	}
	
	/**
	 * Gets the stato.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the stato
	 */
	public StatoRegola getStato() 
	{
		return stato;
	}

	/**
	 * Sets the stato.
	 *
	 * @param stato the new stato
	 * @pre: stato!=null
	 * @post: -
	 */
	public void setStato(StatoRegola stato) 
	{
		this.stato = stato;
	}
	
	/**
	 * Valuta regola.
	 *
	 * @pre: -
	 * @post: if(!listaAntecedenti.get(i).valutaAntecedente())=true then @forall listaConseguenti.get(i) esegui conseguenti
	 * @return true, if successful
	 */	
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
	
	/**
	 * Stampa regola.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */	
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
		
	/**
	 * Checks if is empty antecedente.
	 * @pre: -
	 * @post: -
	 * @return true, if is empty antecedente
	 */
	public boolean isEmptyAntecedente()
	{
		return listaAntecedenti.isEmpty();
	}
	
	/**
	 * Gets the antecedente.
	 *
	 * @param i
	 * @pre: i>=0
	 * @post: listaAntecedenti.get(i)!=null
	 * @return the antecedente
	 */
	public Antecedente getAntecedente(int i)
	{
		return listaAntecedenti.get(i);
	}
	
	/**
	 * Gets the conseguente.
	 *
	 * @param i 
	 * @pre: i>=0
	 * @post: listaConseguenti.get(i)!=null
	 * @return the conseguente
	 */
	public Conseguente getConseguente(int i)
	{
		return listaConseguenti.get(i);
	}
	
	/**
	 * Size antecedenti.
	 * 
	 * @pre: -
	 * @post: listaAntecedenti.size()>=0
	 * @return the int
	 */
	public int sizeAntecedenti()
	{
		return listaAntecedenti.size();
	}
	
	/**
	 * Size conseguenti.
	 *
	 * @pre: -
	 * @post: listaAntecedenti.size()>=0
	 * @return the int
	 */
	public int sizeConseguenti()
	{
		return listaConseguenti.size();
	}
	
	/**
	 * Antecedenti attivi.
	 * 
	 * @pre: -
	 * @post: 
	 * @return true, if successful
	 */
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
	
	/**
	 * Conseguenti attivi.
	 * 
	 * @pre: -
	 * @post: -
	 * @return true, if(!listaConseguenti.get(i).getAttuatore().getStato() == Stato.SPENTO)
	 */
	public boolean conseguentiAttivi()
	{
		for(int i=0; i<listaConseguenti.size(); i++)
		{
			if(listaConseguenti.get(i).getStatoConseguente() == Stato.SPENTO)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Cerca sensore.
	 *
	 * @param sensore
	 * @pre: sensore!=null 
	 * @post: -
	 * @return true, if successful
	 */
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
	
	/**
	 * Attuatore gia presente.
	 *
	 * @param attuatore 
	 * @pre: attuatore!=null
	 * @post: listaConseguenti.get(i)!=null
	 * @return true, if successful
	 */	
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
