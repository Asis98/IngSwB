package liste;

import java.io.Serializable;
import java.util.ArrayList;

import ambiente.Immobile;
import costanti.Costanti;


public class ListaImmobili implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Immobile> listaImmobili;
	
	
	public ListaImmobili()
	{
		listaImmobili= new ArrayList<>();
	}

	public boolean addImmobile(Immobile immobile)
	{		
		return (listaImmobili.contains(immobile)) ? false : listaImmobili.add(immobile);
	}

	public boolean removeUnitaImmobiliare(Immobile immobile)
	{
		if(listaImmobili.contains(immobile)) 
		{
			listaImmobili.remove(immobile);
			return true;
		}
		return false;
	}
	
	public String stampaListaUnitaImmobiliari()
	{
		StringBuilder sb = new StringBuilder();
		int i=0;
		
		for(Immobile immobile: listaImmobili)
		{
			sb.append(i);
			sb.append(" ");
			sb.append(Costanti.TRATTINO);
			sb.append(immobile.getDestinazioneUnita());
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}
	
	public boolean unitaImmobiliareGiaPresente(String nomeUnitaImmobiliare)
	{
		for(int i=0;i<listaImmobili.size();i++)
		{
			if(nomeUnitaImmobiliare.equalsIgnoreCase((listaImmobili.get(i)).getDestinazioneUnita()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Immobile getImmobile(int i)
	{
		return listaImmobili.get(i);
	}
	
	public int size()
	{
		return listaImmobili.size();
	}
	
	public boolean isEmpty()
	{
		return listaImmobili.isEmpty();
	}
}
