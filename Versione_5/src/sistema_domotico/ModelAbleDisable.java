package sistema_domotico;

import ambiente.Immobile; 
import categorie.Stato;
import regole.AntecedenteSensore;
import regole.StatoRegola;
import rilevazione.Attuatore;
import rilevazione.Sensore;


public class ModelAbleDisable 
{
	public boolean model_valutaSensoriDisattivi(Immobile immobile, int sceltaRegolaAbilitata)
	{
		//for(int i =0;i<immobile.getListaRegole().getRegola(sceltaRegolaAbilitata).sizeAntecedenti();i++)
		for(int i =0;i<immobile.getNumeroRegola(sceltaRegolaAbilitata).sizeAntecedenti();i++)
		{
			//if(immobile.getListaRegole.getRegola(sceltaRegolaAbilitata).getAntecedente(i) instanceof AntecedenteSensore)
			if(immobile.getNumeroRegola(sceltaRegolaAbilitata).getAntecedente(i) instanceof AntecedenteSensore)
			{
				//devo verificare che i due sensori di Antecedente siano contenuti in listaSensori
				String nomeOperandoA = ((AntecedenteSensore)immobile.getNumeroRegola(sceltaRegolaAbilitata).getAntecedente(i)).getSensoreAntecedente();
				String nomeOperandoB = ((AntecedenteSensore)immobile.getNumeroRegola(sceltaRegolaAbilitata).getAntecedente(i)).getOperando_b();
				//if(immobile.cercaSensore(nomeOperandoA).getStato()==Stato.SPENTO ||
				//		immobile.cercaSensore(nomeOperandoB).getStato()==Stato.SPENTO)
				if(immobile.getListaSensori().cercaUnitaRilevazione(nomeOperandoA).getStato()==Stato.SPENTO ||
						immobile.getListaSensori().cercaUnitaRilevazione(nomeOperandoB).getStato()==Stato.SPENTO)
					return true;
			}
			else
			{
			//	if(immobile.cercaSensore(immobile.getRegola(i).getAntecedente(i).getSensoreAntecedente().getNomeUnita()).getStato() == Stato.SPENTO)
				if(immobile.getListaSensori().cercaUnitaRilevazione(((AntecedenteSensore)immobile.getNumeroRegola(i).getAntecedente(i)).getSensoreAntecedente()).getStato() == Stato.SPENTO)
					return true;
			}
		}
		return false;
	}
	
	public boolean model_valutaAttuatoriDisattivi(Immobile immobile, int sceltaRegolaAbilitata)
	{
		//cerco nei conseguenti gli attuattori disattivi
		for(int i=0; i<immobile.getNumeroRegola(sceltaRegolaAbilitata).sizeConseguenti();i++)
		{
			//if(immobile.getListaAttuatori().cercaUnitaRilevazione(immobile.getNumeroRegola(sceltaRegolaAbilitata).getConseguente(i).getAttuatore().getNomeUnita()).getStato() == Stato.SPENTO)
			if(immobile.getListaAttuatori().cercaUnitaRilevazione(immobile.getNumeroRegola(sceltaRegolaAbilitata).getNomeAttuatoreConseguente(i)).getStato() == Stato.SPENTO)	
			return true;
		}
		
		return false;
	}
	
	public void model_attivaRegola(Immobile immobile, Attuatore attuatore)
	{
		for(int i=0; i<immobile.getListaRegole().contaRegoleDisabilitate(); i++) 
		{
			/*attivo una regola <-> in un suo conseguente è contenuto attuatore e tutti gli attuatori
			 * contenuti nei conseguenti sono attivi
			 * e tutti i sensori contenuti nei conseguenti sono attivi
			 */
			
			if(immobile.getNumeroRegola(i).cercaAttuatore(attuatore) && immobile.getNumeroRegola(i).antecedentiAttivi() && immobile.getNumeroRegola(i).conseguentiAttivi())
				immobile.getNumeroRegola(i).setStato(StatoRegola.ABILITATA);
		}
	}
	
	public void model_attivaRegola(Immobile immobile, Sensore sensore)
	{
		for(int i=0; i<immobile.getListaRegole().contaRegoleDisabilitate(); i++)
		{
			/*attivo una regola <-> in un suo antecedente è contenuto sensore e tutti gli attuatori
			 * contenuti nei conseguenti sono attivi
			 * e tutti i sensori contenuti nei conseguenti sono attivi
			 */
			if(immobile.getNumeroRegola(i).cercaSensore(sensore) && immobile.getNumeroRegola(i).antecedentiAttivi() && immobile.getNumeroRegola(i).conseguentiAttivi())
				immobile.getNumeroRegola(i).setStato(StatoRegola.ABILITATA);
		}
	}
	
	public void model_disattivaRegola(Immobile immobile, Attuatore attuatore2)
	{
		for(int i=0; i<immobile.getListaRegole().contaRegoleAbilitate(); i++)
		{
			if(immobile.getNumeroRegola(i).cercaAttuatore(attuatore2))
				immobile.getNumeroRegola(i).setStato(StatoRegola.DISABILITATA);
		}
	}
	
	public void model_disattivaRegola(Immobile immobile, Sensore sensore2)
	{
		for(int i=0; i<immobile.getListaRegole().contaRegoleAbilitate(); i++)
		{
			if(immobile.getNumeroRegola(i).cercaSensore(sensore2))
				immobile.getNumeroRegola(i).setStato(StatoRegola.DISABILITATA);
		}
	}
}
