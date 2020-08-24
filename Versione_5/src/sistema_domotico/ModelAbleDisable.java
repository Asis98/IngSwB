package sistema_domotico;

import ambiente.Immobile;
import ambiente.UnitaImmobiliare;
import categorie.Stato;
import regole.AntecedenteSensore;

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
}
