package utenti;

import costanti.Costanti;
import costanti.Messaggi;
import costanti.TitoliMenu;
import costanti.VociMenu;
import gestioneMenu.MV_ImmobileAdmin;
import gestioneMenu.MV_Manutentore;
import gestioneMenu.MV_User;
import gestioneMenu.MenuCommand;
import inputUtente.DatiUtente;
import utility.Dati;
import utility.MyMenu;

public class Manutentore implements Utente{
	
private String nomeUtente;
	
	public Manutentore(String nomeUtente)
	{
		this.nomeUtente = nomeUtente;
	}
	
	public Manutentore()
	{
		this.nomeUtente = Costanti.STRINGA_VUOTA;
	}
	
	public String getNomeUtente()
	{
		return nomeUtente;
	}
	
	public void setNomeUtente(String nomeUtente)
	{
		this.nomeUtente = nomeUtente; 
	}

	@Override
	public void menuPersonalizzato(Dati dati) {
		
		int scelta = 0;
		do {
			MyMenu menuManutentore= new MyMenu(TitoliMenu.TITOLOMANUTENTORE,VociMenu.VOCIMANUTENTORE);
			System.out.println(menuManutentore.stampaMenu());
		
			scelta =new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE);
			MenuCommand targetOperation = MV_Manutentore
				      .getOperation(scelta)
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
			
			targetOperation.esegui(dati);
		}while(scelta!=0);
	}

	@Override
	public void gestioneImmobileView(Dati dati) {
	
		int scelta = 0;
		do {
		MyMenu gestioneImmobileManutentore = new MyMenu(TitoliMenu.TITOLOMENUIMMOBILE, VociMenu.VOCIIMMOBILE);
		System.out.println(gestioneImmobileManutentore.stampaMenu());
		
		
		MenuCommand targetOperation = MV_ImmobileAdmin
				      .getOperation(new DatiUtente().leggiIntero(Messaggi.SCEGLI_VOCE))
				      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
			
			targetOperation.esegui(dati);
		}while(scelta!=0);
		
		
	}
	

}
