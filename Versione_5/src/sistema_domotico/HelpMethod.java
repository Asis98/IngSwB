package sistema_domotico;

import ambiente.UnitaImmobiliare;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.InfoRilevabile;
import categorie.ModalitaOperativa;
import categorie.Parametro;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;
import costanti.Costanti;
import costanti.Messaggi;
import it.unibs.fp.mylib.InputDati;
import operazioni.BooleanOperator;
import regole.AntecedenteSensore;
import regole.Regola;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import time.Orologio;
import time.Time;

// TODO: Auto-generated Javadoc
/**
 * The Class HelpMethod.
 */
public class HelpMethod {

	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO CATEGORIE ATTUATORI E SENSORI 
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */
	
	
	/**
	 * Inserisci info rilevabili.
	 *
	 * @param categoria the categoria
	 * @return the categoria sensori
	 * @pre: categoria != Null && categoria.tipo = SENSORE
	 * @post: categoria != null && categoria.size() = categoria.size()@pre+n
	 */
	public static CategoriaSensori inserisciInfoRilevabili(CategoriaSensori categoria)
	{
		System.out.println(Messaggi.MESSAGGIO_INSERIMENTO_INFO_RILEVABILI);
		String risposta;
		do
		{
			//Chiedere se vuole inseire una infoRilevabile numerica o non
			InfoRilevabile infoRil = new InfoRilevabile(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_INFO_RILEVABILE));
			//anche le info rilevabili devono essere univoche per cui ho aggiunto questo controllo
			if(!categoria.infoGiaPresente(infoRil.getTipoInfoRilevabile()))
			{
				String sceltaTipo;
				do
				{	
					sceltaTipo = (InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_TIPO_INFO_RILEVABILE)).trim();
					if(sceltaTipo.equalsIgnoreCase(Costanti.NUMERICA))
						infoRil.setNonnumerica(false);
					else if (sceltaTipo.equalsIgnoreCase(Costanti.NONNUMERICA))
						infoRil.setNonnumerica(true);
				}while(!sceltaTipo.equalsIgnoreCase(Costanti.NUMERICA) && !sceltaTipo.equalsIgnoreCase(Costanti.NONNUMERICA));
				
				System.out.println(infoRil.isNonnumerica());
				
				if(infoRil.isNonnumerica() && ((CategoriaSensori)categoria).dominioIsEmpty())
					System.out.println(Messaggi.INSERIMENTO_NON_VALIDO);
				else
					categoria.addInfoRilevabili(infoRil);
				
			}
			else
				System.out.println(Messaggi.INFO_RILEVABILE_GIA_ESISTENTE);
			
			risposta=InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRE_INFO_RILEVABILI);
			
		}while(risposta.equalsIgnoreCase(Costanti.SI));
		
		return categoria;
	}
	
	
	/**
	 * Inserisci dominio.
	 *
	 * @param categoria the categori
	 * @pre: categoria !=null
	 * @post: categoria.dominio != null
	 * @return the categoria sensori
	 */
	public static CategoriaSensori inserisciDominio(CategoriaSensori categoria)
	{
		do
		{
			String elemento = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_VALORE_DOMINIO);
			if(((CategoriaSensori)categoria).dominioGiaPresente(elemento.trim()))
				System.out.println(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
			else
			{
				System.out.println(Messaggi.MESSAGGIO_SUCCESSO_INPUT);
				((CategoriaSensori)categoria).addDominio(elemento.trim());
			
			}
			
		}while(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRO_DOMINIO).equalsIgnoreCase(Costanti.SI));
		return categoria;
	}
	
	
	/**
	 * Inserisci modalita operative.
	 *
	 * @param categoria the categoria
	 * @return the categoria attuatori
	 * @pre categoria != null && categoria.tipo = ATTUATORE
	 * @post categoria!=null && && categoria.size() = categoria.size()@pre+n
	 */
	public static CategoriaAttuatori inserisciModalitaOperative(CategoriaAttuatori categoria)
	{
		//prima modalit‡ operativa sempre idle di default
		categoria.addModalitaOperativa(new ModalitaOperativa(Costanti.IDLE));
		System.out.println(Messaggi.MESSAGGIO_INSERIMENTO__MODALITA_OPERATIVE);
		String risposta;
		do
		{
			ModalitaOperativa modOp = new ModalitaOperativa(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_MODALITA_OPERATIVA), InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_DESCRIZIONE_MODALITA_OPERATIVA));
			//anche le modalit‡ operative devono essere univoche per cui ho aggiunto questo controllo
			if(!categoria.modalitaGiaPresente(modOp.getNomeModOperativa()))
			{
				String scelta= InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_PARAMETRO);
				if(scelta.equalsIgnoreCase(Costanti.SI))
				{
					do {
							String nomeParametro = InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_NOME_PARAMETRO);
							if(!modOp.parametroGiaPresente(nomeParametro))
							{
								Parametro parametro = new Parametro();
								parametro.setNomeParametro(nomeParametro);
								modOp.addParametro(parametro);
								System.out.println(modOp.stampaListaParametri());
							}
							else
								System.out.println(Messaggi.MESSAGGIO_ELEMENTO_ESISTENTE);
						
					}while(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRO_PARAMETRO).equalsIgnoreCase(Costanti.SI));
					
				}
				categoria.addModalitaOperativa(modOp);
			}
			else
				System.out.println(Messaggi.MODALITA_OPERATIVA_GIA_PRESENTE);
			do
			{
				risposta=InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRE_MOD_OPERATIVE);
				if(!risposta.equalsIgnoreCase(Costanti.SI) && !risposta.equalsIgnoreCase(Costanti.NO))
					System.out.println(Messaggi.STRINGA_NON_VALIDA);
			}while(!risposta.equalsIgnoreCase(Costanti.SI) && !risposta.equalsIgnoreCase(Costanti.NO));
			
		}while(risposta.equalsIgnoreCase(Costanti.SI));
		
		return categoria;
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI INSERIMENTO UNITA' RILEVAZIONE (SENSORI / ATTUATORI)
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */		
	
	/**
	 * Scegli modalita operativa.
	 *
	 * @param attuatore the attuatore
	 * @param categoria the categoria
	 * @return the attuatore
	 * @pre: attuatore != null, categorie!= null && categoria.tipo == CategoriaAttuatore
	 * @post: attuatore.modalit‡Operativa(n) != null
	 */
	public static Attuatore scegliModalitaOperativa(Attuatore attuatore, CategoriaAttuatori categoria)
	{
		//setto la modalit‡ operativa
		System.out.println(Costanti.LISTA_MOD_OPERATIVE);
		System.out.println(categoria.stampaModalitaOperative());
		int numeroModalitaOperativa = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_NUMERO_MOD_OPERATIVA,Costanti.MIN, categoria.size()-1);
		if(!categoria.getModalitaOperative(numeroModalitaOperativa).parametriIsEmpty())
		{
			for(int i=0;i<categoria.getModalitaOperative(numeroModalitaOperativa).size();i++)
			{
				System.out.println(Costanti.PARAMETRO+ categoria.getModalitaOperative(numeroModalitaOperativa).getParametro(i).getNomeParametro());
				categoria.getModalitaOperative(numeroModalitaOperativa).getParametro(i).setValore(InputDati.leggiDouble(Messaggi.MESSAGGIO_INSERIMENTO_VALORE_PARAMETRO));;
			}
		}
		
		attuatore.setModOperativa(categoria.getModalitaOperative(numeroModalitaOperativa));
		return attuatore;
	}
	
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODO INSERIMENTO REGOLA
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	/**
	 * Inserisci regola sensore.
	 *
	 * @param regola the regola
	 * @param immobile the immobile
	 * @return the regola
	 * @pre: regola!=null && immobile!=null
	 * @post: if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
	 * 			regola.setAntecedente(operando_a, Costanti.UGUALE, HelpMethod.scegliSensore(immobile));
	 * 			else
	 * 			regola.setAntecedente(operando_a, HelpMethod.scegliOperatore(),HelpMethod.scegliSensore(immobile));
	 */
	public static Regola inserisciRegolaSensore(Regola regola, UnitaImmobiliare immobile)
	{
		//caso in cui operandoB = Sensore
		Sensore operando_a = new Sensore();
		operando_a = HelpMethod.scegliSensore(immobile);
		String operatoreRelazionale = null;
		if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
		{
			operatoreRelazionale = Costanti.UGUALE;
		}
		else
			operatoreRelazionale = HelpMethod.scegliOperatore();
			
		Sensore operando_b= new Sensore();
		operando_b=HelpMethod.scegliSensore(immobile);
		
		if(operando_a.getInfoRilevabile(0).getValoreAttuale().getValore().getClass() == String.class && 
				operando_b.getInfoRilevabile(0).getValoreAttuale().getValore().getClass() != String.class)
		{
			System.out.println(Costanti.OPERANDI_INVALIDI);
		}
		else if(new AntecedenteSensore(operando_a,operatoreRelazionale,operando_b).confrontaElementiAntecedente())
		{
			System.out.println(Messaggi.INSERIMENTO_NON_VALIDO);
		}
		else
			regola.setAntecedente(operando_a,operatoreRelazionale,operando_b);
		
		return regola;
	}
	
	/**
	 * Inserisci regola costante.
	 *
	 * @param regola the regola
	 * @param immobile the immobile
	 * @return the regola
	 * @pre: regola!=null && immobile!=null
	 * @post: if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
	 * 			regola.setAntecedente(operando_a, Costanti.UGUALE, HelpMethod.scegliCostanteStringa());
	 * 			else
	 * 			regola.setAntecedente(operando_a, HelpMethod.scegliOperatore(),HelpMethod.scegliCostante());
	 */
	public static Regola inserisciRegolaCostante(Regola regola, UnitaImmobiliare immobile)
	{
		//caso in cui operandoB = Costante
		Sensore operando_a = new Sensore();
		operando_a = HelpMethod.scegliSensore(immobile);
		
		if(operando_a.getInfoRilevabile(Costanti.MIN).getValoreAttuale().getValore().getClass() == String.class)
			regola.setAntecedente(operando_a, Costanti.UGUALE, HelpMethod.scegliCostanteStringa(operando_a));
		else
			regola.setAntecedente(operando_a, HelpMethod.scegliOperatore(),HelpMethod.scegliCostante());
		
		return regola;
	}
	
	/**
	 * Inserisci regola time.
	 *
	 * @param regola the regola
	 * @param immobile the immobile
	 * @return the regola
	 * @pre: regola!=null && immobile!=null
	 * @post: regola.setAntecedente(HelpMethod.scegliOperatore(), HelpMethod.scegliOra());
	 */
	public static Regola inserisciRegolaTime(Regola regola, UnitaImmobiliare immobile)
	{
		System.out.println(Messaggi.VALORE_DEL_PRIMO_ORARIO + new Orologio().toString());
		regola.setAntecedente(HelpMethod.scegliOperatore(), HelpMethod.scegliOra());
		return regola;
	}
	
	/**
	 * Scegli sensore.
	 *
	 * @param immobile the immobile
	 * @return the sensore
	 * @pre: immobile!=null
	 * @post: listaMisurazioni.size() = listaMisurazioni.size()@pre +1
	 */
	public static Sensore scegliSensore(UnitaImmobiliare immobile)
	{
		System.out.println(immobile.stampaListaSensoriAttivi());
		
		//setto il primo operando della regola
		int	numeroSensore = InputDati.leggiIntero(Messaggi.MESSAGGIO_SCELTA_OPERANDO, Costanti.MIN, immobile.sizeSensori()-1);
		Sensore operando = new Sensore(immobile.getSensore(numeroSensore).getNomeUnita()); 
		operando.setCategoria(immobile.getSensore(numeroSensore).getCategoria());
		//stampo le infoRilevabili del sensore
		System.out.println(immobile.getSensore(numeroSensore).printListaMisurazioni());
		int	numeroInfoRil = InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_INFORIL_OPERANDO, Costanti.MIN, immobile.getSensore(numeroSensore).size()-1);
		//assegno infoRilevabile scelta
		operando.addMisurazione(immobile.getSensore(numeroSensore).getInfoRilevabile(numeroInfoRil));
		
		return operando;
	}
	
	/**
	 * Scegli costante.
	 *
	 * @return the valore numerico
	 * @pre: -
	 * @post: new ValoreNumerico(InputDati.leggiDouble(Costanti.MESSAGGIO_INSERIMENTO_COSTANTE))!=null
	 */
	public static ValoreNumerico scegliCostante()
	{
		return new ValoreNumerico(InputDati.leggiDouble(Messaggi.MESSAGGIO_INSERIMENTO_COSTANTE));
	}
	
	/**
	 * Scegli costante stringa.
	 *
	 * @param operando_a the operando a
	 * @return the valore non numerico
	 * @pre: -
	 * @post: new ValoreNonNumerico(operando_a.getCategoria().getDominio(sceltaDominio))!=null
	 */
	public static ValoreNonNumerico scegliCostanteStringa(Sensore operando_a)
	{	
		for(int i=0;i<operando_a.getCategoria().sizeDominio();i++)
			System.out.println(i+operando_a.getCategoria().getDominio(i));
		int sceltaDominio= InputDati.leggiIntero(Messaggi.INSERIRE_IL_NUMERO_DEL_DOMINIO, Costanti.MIN, operando_a.getCategoria().sizeDominio());
		
		return new ValoreNonNumerico(operando_a.getCategoria().getDominio(sceltaDominio));
	}
	
	/**
	 * Scegli ora.
	 *
	 * @return the time
	 * @pre: -
	 * @post: -
	 */
	public static Time scegliOra()
	{
		return new Time(InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_ORA, Costanti.MIN, 23),
						InputDati.leggiIntero(Messaggi.MESSAGGIO_INSERIMENTO_MINUTI, Costanti.MIN, 59));
	}
	
	/**
	 * Scegli operatore.
	 *
	 * @return the string
	 * @pre: -
	 * @post: -
	 */
	public static String scegliOperatore()
	{
		//assegnare ad una stringa la chiave della mappa
		System.out.println(BooleanOperator.stampaOperatore());
		return InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_OPERATORE);
	}
	
	/**
	 * Inserisci conseguente.
	 *
	 * @param immobile the immobile
	 * @param regola the regola
	 * @return the regola
	 * @pre: immobile!=null && regola!=null
	 * @post: if(!regola.cercaAttuatore(attuatore)) regola.setConseguente
	 */
	public static Regola inserisciConseguente(UnitaImmobiliare immobile, Regola regola)
	{
		do {	
				//scelgo l'attuatore
				System.out.println(immobile.stampaListaAttuatoriAttivi());
				int	numeroAttuatore = InputDati.leggiIntero(Messaggi.SCEGLI_UN_ATTUATORE, Costanti.MIN, immobile.sizeAttuatori()-1);
				
				Attuatore attuatore = new Attuatore();
				attuatore = immobile.getAttuatore(numeroAttuatore);
				
				//stampo l'attuale mod operativa dell'attuatore scelto
				System.out.println(Messaggi.MODALITA_OPERATIVA_ATTUALE + immobile.getAttuatore(numeroAttuatore).getModOperativa().getNomeModOperativa());
				if(!immobile.getAttuatore(numeroAttuatore).getModOperativa().parametriIsEmpty())
					System.out.println(immobile.getAttuatore(numeroAttuatore).getModOperativa().printModNameParamValue());
				
				//scelgo nuova modalit‡ operativa e se Ë parametrica imposto i parametri
				System.out.println(immobile.getAttuatore(numeroAttuatore).getCategoria().stampaModalitaOperative());
				int numeroModalitaOperativa = InputDati.leggiIntero(Messaggi.SCEGLI_UNA_NUOVA_MODALITA_OPERATIVA, Costanti.MIN, immobile.getAttuatore(numeroAttuatore).getCategoria().size()-1);
				ModalitaOperativa modOp= immobile.getAttuatore(numeroAttuatore).getCategoria().getModalitaOperative(numeroModalitaOperativa);
				if(!modOp.parametriIsEmpty())
				{
					for(int i =0;i<modOp.size(); i++)
					{
						System.out.println(modOp.getParametro(i).getNomeParametro());
						modOp.getParametro(i).setValore(InputDati.leggiDouble(Messaggi.INSERIRE_IL_NUOVO_VALORE_DEL_PARAMETRO));
					}
				}
				
				attuatore.setModOperativa(modOp);
				
				//setto il conseguente
				if(!regola.cercaAttuatore(attuatore))
				{	
					//se l'utente vuole assegno a start un'orario
					if(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ORARIO_INIZIO).equalsIgnoreCase(Costanti.SI))
						regola.setConseguente(attuatore, modOp, HelpMethod.scegliOra());
					else
						regola.setConseguente(attuatore, modOp); 
				}
				else
					System.out.println(Messaggi.GIA_PRESENTE);
				
		}while(InputDati.leggiStringaNonVuota(Messaggi.MESSAGGIO_INSERIMENTO_ALTRO_CONSEGUENTE).equalsIgnoreCase(Costanti.SI));

		return regola;		
	}
	
}
