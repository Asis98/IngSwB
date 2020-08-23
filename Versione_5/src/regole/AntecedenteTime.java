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
	

	public AntecedenteTime(String operatoreRelazionale , Time operando_b) 
	{
		this.operando_a = new Time(new Orologio());
		this.valore = operando_b;
		this.operatoreRelazionale = operatoreRelazionale;
	}

	@Override
	public boolean valutaAntecedente() {
		
		BoolFunct targetOperation = BooleanOperator
				.getOperation(operatoreRelazionale)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		return targetOperation.confronto(new ValoreNumerico(operando_a.minutiTotali()), new ValoreNumerico(valore.minutiTotali()));
	}

	
	@Override
	public <T> void setAntecedente(T operatore_a, String operatoreRelazionale, T operatore_b) {

		this.operando_a = (Time) operatore_a;
		this.operatoreRelazionale = operatoreRelazionale;
		this.valore=(Time) operatore_b;
		
	}
	
	public Time getOperando_a() 
	{
		return operando_a;
	}

	public Time getValore() 
	{
		return valore;
	}

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
