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
	
	public AntecedenteCostante(Sensore operando_a,  String operatoreRelazionale, Valore costante) 
	{
		this.operando_a = operando_a;
		this.costante = costante;
		this.operatoreRelazionale = operatoreRelazionale;
	}
	
	public boolean valutaAntecedente()
	{
		BoolFunct targetOperation = BooleanOperator
							.getOperation(operatoreRelazionale)
							.orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		return targetOperation.confronto(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale(), costante);
	}
	
	public <T> void setAntecedente(T a, String opRelazionale, T costante)
	{
		this.operando_a = (Sensore) a;
		this.costante = (Valore) costante;
		this.operatoreRelazionale= opRelazionale;
	}

	public String getSensoreAntecedente() 
	{
		return operando_a.getNomeUnita();
	}

	public Valore getCostante() 
	{
		return costante;
	}
	
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
