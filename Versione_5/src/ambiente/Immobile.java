package ambiente;

import liste.*;

public class Immobile {

	private UnitaImmobiliare unita;
	private ListaUnitaDomotiche unitList;
	private ListaUnitaRilevazione listaAttuatori;
	private ListaUnitaRilevazione listaSensori;
	private ListaRegole listaRegole;
	
	public Immobile()
	{
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
	
	public void setUnitList(ListaUnitaDomotiche unitList) 
	{
		this.unitList = unitList;
	}
	
	public ListaUnitaRilevazione getListaAttuatori() 
	{
		return listaAttuatori;
	}
	
	public void setListaAttuatori(ListaUnitaRilevazione listaAttuatori) 
	{
		this.listaAttuatori = listaAttuatori;
	}
	
	public ListaUnitaRilevazione getListaSensori() 
	{
		return listaSensori;
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

	
	
	
}
