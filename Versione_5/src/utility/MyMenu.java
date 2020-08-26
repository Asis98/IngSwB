package utility;

import inputUtente.*;

/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu

*/
public class MyMenu
{
	  final private static String CORNICE = "--------------------------------";
	  final private static String VOCE_USCITA = "0\tEsci";
	  final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";
	
	  private String titolo;
	  private String [] voci;
	  private DatiUtente input;
	
		
	  public MyMenu (String titolo, String [] voci)
	  {
			this.titolo = titolo;
			this.voci = voci;
			this.input = new DatiUtente();
	  }
	
	  public int scegli ()
	  {
			stampaMenu();
			return input.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);	 
	  }
			
	  public String stampaMenu ()
	  {
			StringBuilder sb = new StringBuilder();
			sb.append(CORNICE);
			sb.append("\n");
			sb.append("\t"+titolo.toUpperCase()); //\t e toUpperCase nuovi
			sb.append("\n");
			sb.append(CORNICE);
			sb.append("\n");
		    for (int i=0; i<voci.length; i++)
			 {
		    	sb.append( (i+1) + "\t" + voci[i]);
		    	sb.append("\n");
			 }
		    sb.append("\n");
		    sb.append(VOCE_USCITA);
		    sb.append("\n");
		    
		    return sb.toString();
	  }
			
}

