package regole;


import java.io.Serializable;

import categorie.ValoreNumerico;
import operazioni.BoolFunct;
import operazioni.BooleanOperator;
import rilevazione.Sensore;
import time.Orologio;
import time.Time;

public class AntecedenteTime implements Antecedente, Serializable{
	
	private static final long serialVersionUID = 1L;
	private Time operando_a;
	private Time valore;
	private String operatoreRelazionale;
	
	/**
	 * Instantiates a new antecedente time.
	 *
	 * @param operatoreRelazionale
	 * @param operando_b 
	 * @pre: operatoreRelazionale!=null && operando_b!=null
	 * @post: -
	 * @invariant: operando_a!=null && valore!=null && operatoreRelazionale!=null
	 */
	public AntecedenteTime(String operatoreRelazionale , Time operando_b) 
	{
		this.operando_a = new Time(new Orologio());
		this.valore = operando_b;
		this.operatoreRelazionale = operatoreRelazionale;
	}

	/**
	 * Valuta antecedente.
	 * 
	 * @pre: - 
	 * @post: -
	 * @return true, if successful
	 */
	@Override
	public boolean valutaAntecedente() {
		
		BoolFunct targetOperation = BooleanOperator
				.getOperation(operatoreRelazionale)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		return targetOperation.confronto(new ValoreNumerico(operando_a.minutiTotali()), new ValoreNumerico(valore.minutiTotali()));
	}

	/**
	 * Sets the antecedente.
	 *
	 * @param <T> the generic type
	 * @param operatore_a 
	 * @param operatoreRelazionale 
	 * @param operatore_b 
	 * @pre: operatore_a!=null && operatoreRelazionale!=null && operatore_b!=null
	 */
	@Override
	public <T> void setAntecedente(T operatore_a, String operatoreRelazionale, T operatore_b) {

		this.operando_a = (Time) operatore_a;
		this.operatoreRelazionale = operatoreRelazionale;
		this.valore=(Time) operatore_b;
		
	}
	
	/**
	 * Gets the operando a.
	 *
	 * @pre: -
	 * @post: -
	 * @return the operando a
	 */
	public Time getOperando_a() 
	{
		return operando_a;
	}

	/**
	 * Gets the valore.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the valore
	 */
	public Time getValore() 
	{
		return valore;
	}

	/**
	 * Stampa antecedente.
	 *
	 * @pre: -
	 * @post: sb!=null
	 * @return the string
	 */
	@Override
	public String stampaAntecedente() {
		StringBuilder sb = new StringBuilder();
		sb.append(operando_a.toString());
		
		sb.append("  ");
		sb.append(operatoreRelazionale);
		sb.append("  ");

		sb.append("  ");
		sb.append(valore.toString());
		sb.append("\n");
		
		return sb.toString();
	}


}
