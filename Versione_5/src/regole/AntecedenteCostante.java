package regole;

import java.io.Serializable;

import categorie.Stato;
import categorie.Valore;
import costanti.Costanti;
import operazioni.BoolFunct;
import operazioni.BooleanOperator;
import rilevazione.Sensore;

public class AntecedenteCostante implements Antecedente, Serializable, StatoOperandoA{
	
	private static final long serialVersionUID = 1L;
	private Sensore operando_a;
	private Valore costante;
	private String operatoreRelazionale;
	
	/**
	 * Instantiates a new antecedente.
	 *
	 * @param operando_a 
	 * @param operatoreRelazionale 
	 * @param costante
	 * @pre:  operando_a!=null && operatoreRelazionale!=null && costante!=null
	 * @post: -
	 * @invariant: operando_a!=null && operatoreRelazionale!=null && costante!=null
	 */
	public AntecedenteCostante(Sensore operando_a,  String operatoreRelazionale, Valore costante) 
	{
		this.operando_a = operando_a;
		this.costante = costante;
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
		return targetOperation.confronto(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale(), costante);
	}
	
	/**
	 * Sets the antecedente.
	 *
	 * @param a 
	 * @param opRelazionale
	 * @param costante
	 * @pre: a!=null && opRelazionale!=null && costante!=null
	 * @post: -
	 */
	public <T> void setAntecedente(T a, String opRelazionale, T costante)
	{
		this.operando_a = (Sensore) a;
		this.costante = (Valore) costante;
		this.operatoreRelazionale= opRelazionale;
	}

	
	/**
	 * Gets the sensore antecedente.
	 *
	 * @param a 
	 * @param opRelazionale
	 * @param costante
	 * @pre: a!=null && opRelazionale!=null && costante!=null
	 * @post: -
	 */
	public String getSensoreAntecedente() 
	{
		return operando_a.getNomeUnita();
	}

	/**
	 * Gets the costante.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the costante
	 */
	public Valore getCostante() 
	{
		return costante;
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
		sb.append(".");
		sb.append(operando_a.getInfoRilevabile(Costanti.MIN).getTipoInfoRilevabile());
		sb.append(" ");
		sb.append(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore());
		sb.append("  ");
		sb.append(operatoreRelazionale);
		sb.append("  ");

		sb.append("  ");
		sb.append(costante.getValore());
		sb.append("\n");
		
		return sb.toString();
	}

	@Override
	public Stato getStatoSensoreA() {
		return operando_a.getStato();
	}
	

}
