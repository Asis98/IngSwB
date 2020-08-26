package ambiente;

import java.util.ArrayList;

import liste.*;
import regole.Regola;
import rilevazione.UnitaRilevazione;

public class Immobile {

	private UnitaImmobiliare unita;
	private ListaUnitaDomotiche unitList;
	private ListaUnitaRilevazione listaAttuatori;
	private ListaUnitaRilevazione listaSensori;
	private ListaRegole listaRegole;
	
	public Immobile(UnitaImmobiliare unita)
	{
		this.unita = unita;
		this.unitList= new ListaUnitaDomotiche();
		this.listaAttuatori= new ListaUnitaRilevazione();
		this.listaSensori= new ListaUnitaRilevazione();
		this.listaRegole= new ListaRegole();
	}
	
	public Immobile(UnitaImmobiliare unita, ListaUnitaDomotiche unitList, ListaUnitaRilevazione listaAttuatori, ListaUnitaRilevazione listaSensori,ListaRegole listaRegole)
	{
		this.unita= unita;
		this.unitList = unitList;
		this.listaAttuatori = listaAttuatori;
		this.listaSensori = listaSensori;
		this.listaRegole = listaRegole;
	}
	
	public UnitaImmobiliare getUnita() 
	{
		return unita;
	}
	
	public String getDestinazioneUnita() 
	{
		return unita.getdestinazione();
	}
	
	public void setUnita(UnitaImmobiliare unita) 
	{
		this.unita = unita;
	}
	
	public ListaUnitaDomotiche getUnitList() 
	{
		return unitList;
	}
	
	public String getNomeUnitaDomotica(int i) 
	{
		return unitList.getNomeUnitaDomotica(i);
	}
	
	public UnitaDomotica getUnitaDomoticaUnitList(int i)
	{
		return unitList.getUnitaDomotica(i);
	}
	
	public void setUnitList(ListaUnitaDomotiche unitList) 
	{
		this.unitList = unitList;
	}
	
	public ListaUnitaRilevazione getListaAttuatori() 
	{
		return listaAttuatori;
	}
	
	public UnitaRilevazione getElementoListaAttuatori(int i) 
	{
		return listaAttuatori.getElemento(i);
	}
	
	public void setListaAttuatori(ListaUnitaRilevazione listaAttuatori) 
	{
		this.listaAttuatori = listaAttuatori;
	}
	
	public ListaUnitaRilevazione getListaSensori() 
	{
		return listaSensori;
	}
	
	public UnitaRilevazione getElementoListaSensori(int i) 
	{
		return listaSensori.getElemento(i);
	}
	
	public void setListaSensori(ListaUnitaRilevazione listaSensori) 
	{
		this.listaSensori = listaSensori;
	}
	
	public ListaRegole getListaRegole() 
	{
		return listaRegole;
	}
	
	public void setListaRegole(ListaRegole listaRegole) 
	{
		this.listaRegole = listaRegole;
	}

	public ArrayList<UnitaRilevazione> getAttuatoriAttivi()
	{
		return listaAttuatori.getListaUnitaAttive();
	}
	
	public ArrayList<UnitaRilevazione> getAttuatoriDisattivi()
	{
		return listaAttuatori.getListaUnitaDisattive();
	}
	
	public ArrayList<UnitaRilevazione> getSensoriAttivi()
	{
		return listaSensori.getListaUnitaAttive();
	}
	
	public ArrayList<UnitaRilevazione> getSensoriDisattivi()
	{
		return listaSensori.getListaUnitaDisattive();
	}
	
	public UnitaRilevazione getNumeroAttuatoreDisattivo(int numero)
	{
		return listaAttuatori.getListaUnitaDisattive().get(numero);
	}
	
	public UnitaRilevazione getNumeroSensoreDisattivo(int numero)
	{
		return listaSensori.getListaUnitaDisattive().get(numero);
	}
	
	public UnitaRilevazione getNumeroAttuatoreAttivo(int numero)
	{
		return listaAttuatori.getListaUnitaAttive().get(numero);
	}
	
	public UnitaRilevazione getNumeroSensoreAttivo(int numero)
	{
		return listaSensori.getListaUnitaAttive().get(numero);
	}
	
	public Regola getNumeroRegola(int i)
	{
		return listaRegole.getRegola(i);
	}

	
	
}
