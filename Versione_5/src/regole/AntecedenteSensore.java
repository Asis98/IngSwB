package regole;

import java.io.Serializable;

import categorie.Stato;
import categorie.Valore;
import costanti.Costanti;
import operazioni.BoolFunct;
import operazioni.BooleanOperator;
import rilevazione.Sensore;

public class AntecedenteSensore implements Antecedente, Serializable, StampaSensoreAntecedente,GetSensoreAntecedente, StatoOperandoA{
	

	private static final long serialVersionUID = 1L;
	
	private Sensore operando_a;
	private Sensore operando_b;
	private String operatoreRelazionale;
	
	/**
	 * Instantiates a new antecedente.
	 *
	 * @param operando_a 
	 * @param operatoreRelazionale 
	 * @param operando_b 
	 * @pre: operando_a!=null && operatoreRelazionale!=null && operando_b!=null
	 * @post: -
	 * @invariant: operando_a!=null && operando_b!=nul && operatoreRelazionale!=null 
	 */
	public AntecedenteSensore(Sensore operando_a,  String operatoreRelazionale , Sensore operando_b) 
	{
		this.operando_a = operando_a;
		this.operando_b = operando_b;
		this.operatoreRelazionale = operatoreRelazionale;
	}
	
	/**
	 * Valuta antecedente.
	 *
	 * @pre: -
	 * @post: -
	 * @return true, if successful
	 */
	public boolean valutaAntecedente()
	{
		BoolFunct targetOperation = BooleanOperator
				      .getOperation(operatoreRelazionale)
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		return targetOperation.confronto(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale(), operando_b.getInfoRilevabile(0).getValoreAttuale());
	}
	
	/**
	 * Sets the antecedente.
	 *
	 * @param a 
	 * @param opRelazionale 
	 * @param b 
	 * @pre: a!=null && opRelazionale!=null && b!=null
	 * @post: -
	 */
	public <T> void setAntecedente(T a,  String opRelazionale , T b)
	{
		this.operando_a = (Sensore) a;
		this.operando_b = (Sensore) b;
		this.operatoreRelazionale= opRelazionale;
	}
	
	/**
	 * Gets the operando a.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the operando a
	 */
	public String getSensoreAntecedente() 
	{
		return operando_a.getNomeUnita();
	}

	/**
	 * Gets the operando b.
	 *
	 * @pre: -
	 * @post: -
	 * @return the operando b
	 */
	public String getOperando_b() 
	{
		return operando_b.getNomeUnita();
	}

	/**
	 * Stampa antecedente.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */
	public String stampaAntecedente()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(operando_a.getNomeUnita());
		sb.append(Costanti.PUNTO);
		sb.append(operando_a.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile());
		sb.append(Costanti.SPAZIO);
		sb.append(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore());
		sb.append(Costanti.DOPPIOSPAZIO);
		sb.append(operatoreRelazionale);
		sb.append(Costanti.DOPPIOSPAZIO);

		sb.append(operando_b.getNomeUnita());
		sb.append(Costanti.PUNTO);
		sb.append(operando_b.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile());
		sb.append(Costanti.SPAZIO);
		sb.append(operando_b.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore());
		sb.append(Costanti.DOPPIOSPAZIO);
		
		return sb.toString();
	}
	
	/**
	 * Stampa sensore antecedente.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */
	public String stampaSensoreAntecedente()
	{ 
		StringBuilder sb = new StringBuilder();
		sb.append(operando_a.getNomeUnita());
		sb.append(Costanti.PUNTO);
		sb.append(operando_a.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile());
		sb.append(Costanti.SPAZIO);
		
		return sb.toString();
	}
	
	/**
	 * Stampa sensore B.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */
	public String stampaSensoreB()
	{ 
		StringBuilder sb = new StringBuilder();
		sb.append(operando_b.getNomeUnita());
		sb.append(Costanti.PUNTO);
		sb.append(operando_b.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile());
		sb.append(Costanti.SPAZIO);
		
		return sb.toString();
	}
	
	/**
	 * Confronta elementi antecedente.
	 * 
	 * @pre: -
	 * @post: -
	 * @return true, if(operando_a.getNomeUnita().equalsIgnoreCase(operando_b.getNomeUnita()) 
				&& operando_a.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile().equalsIgnoreCase(operando_b.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile()))
	 */
	//vogliamo confrontare operando_a.inforil con operando_b.inforil
	public boolean confrontaElementiAntecedente()
	{
			if(operando_a.getNomeUnita().equalsIgnoreCase(operando_b.getNomeUnita()) 
				&& operando_a.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile().equalsIgnoreCase(operando_b.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile()))
			{
				return true;
			}
			
		return false;
	}
	
	
	public Stato getStatoSensoreA()
	{
		return operando_a.getStato();
	}
	
	public Stato getStatoSensoreB()
	{
		return operando_a.getStato();
	}
	
	
	
	



	









	
	
	
	
	
	

}
