package gestioneMenu;


import gestioneMenu.MenuCommand;  
import sistema_domotico.ControllerAbleDisable;
import utility.Dati;

public class AD_Attiva_Sensore implements MenuCommand
{
	
	ControllerAbleDisable controllerAD= new ControllerAbleDisable();
	
	@Override
	public void esegui(Dati dati) 
	{
		// TODO Auto-generated method stub
		
		controllerAD.esegui_attivaSensore(dati.getImmobile());
		
	}
 
}
