package sistema_domotico;

import java.io.IOException;

import ambiente.Artefatto;
import ambiente.Immobile;
import ambiente.Stanza;
import ambiente.UnitaDomotica;
import ambiente.UnitaImmobiliare;
import categorie.Categoria;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.InfoRilevabile;
import categorie.ModalitaOperativa;
import categorie.Parametro;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;
import costanti.Costanti;
import costanti.Messaggi;
import liste.ListaCategorie;
import liste.ListaImmobili;
import liste.ListaUnitaDomotiche;
import rilevazione.Attuatore;
import rilevazione.Sensore;
import rilevazione.UnitaRilevazione;
import utility.NumeriCasuali;

public class Modelinserimento {

	public Modelinserimento()
	{

	}
	
	/**
	 * Inserisci unita immobiliare.
	 */
	public boolean inserimentoUnitaImmobiliare(String destinazione, ListaImmobili listaImmobili)
	{
		if(!listaImmobili.unitaImmobiliareGiaPresente(destinazione))
		{
			UnitaImmobiliare unitaImmobiliare = new UnitaImmobiliare(destinazione);
			//immobile.setdestinazione(destinazione);
			listaImmobili.addImmobile(new Immobile(unitaImmobiliare));
			return true;
		}
		else
			return false;
	}
	
	public ListaUnitaDomotiche inserisciStanza(ListaUnitaDomotiche unitList, String nome)
	{
		Stanza stanza = new Stanza(nome);
		if(!this.verificaPresenzaUnitaD(unitList, nome)) {
			unitList.addUnitaDomotica(stanza);
		}
		return unitList;
	}
	
	public boolean verificaPresenzaUnitaD(ListaUnitaDomotiche unitList, String nome)
	{
		return unitList.verificaPresenzaUnitaDomotica(nome, Stanza.class);
	}
	
	public ListaUnitaDomotiche inserimentoArtefatto(ListaUnitaDomotiche unitList, String nomeArtefatto, String nomeStanza) 
	{
		if(!unitList.verificaPresenzaUnitaDomotica(nomeArtefatto, Artefatto.class))
			unitList.addUnitaDomotica(new Artefatto(nomeArtefatto, nomeStanza));
		return unitList;
	}
	public boolean inserimentoCategoria(String nome, String descrizione, ListaCategorie listaCategorie, Categoria categoria)
	{
		if(!listaCategorie.categoriaGiaPresente(nome) && descrizione.length()<Costanti.MAXCHAR)
		{
			categoria.setNomeCategoria(nome);
			categoria.setDescrizioneCategoria(descrizione);
			return true;
		}
		return false;
	}
	
	public boolean verificaModOp(ModalitaOperativa modOp, CategoriaAttuatori categoria)
	{
		return categoria.modalitaGiaPresente(modOp.getNomeModOperativa());
	}
	
	public boolean verificaParametro(ModalitaOperativa modOp, String nome)
	{
		if(modOp.parametroGiaPresente(nome))
		{
			return true;
		}
		return false;
	}
	
	public Parametro ParametroCreator(String nomeParametro)
	{
		return new Parametro(nomeParametro);
	}
	
	public boolean verificaDominio(CategoriaSensori categoria, String elemento)
	{
		return categoria.dominioGiaPresente(elemento.trim());
	}
	
	public boolean verificaInfoRilevabile(CategoriaSensori categoria, InfoRilevabile infoRil)
	{
		return categoria.infoGiaPresente(infoRil.getTipoInfoRilevabile());
	}
	
	public InfoRilevabile setStatoInfoRilevabile(InfoRilevabile infoRil, String tipo)
	{
		if(tipo.equalsIgnoreCase(Costanti.NUMERICA))
			infoRil.setNonnumerica(false);
		else if (tipo.equalsIgnoreCase(Costanti.NONNUMERICA))
			infoRil.setNonnumerica(true);
		return infoRil;
	}
	
	public UnitaRilevazione settingUnitaRilevazione(UnitaDomotica unitaDomotica, Categoria categoria, String nomeUnita, UnitaRilevazione unita, int numero)
	{
		unita.setUnitaDomotica(unitaDomotica);
		unita.setCategoria(categoria);
		unita.setNomeUnita(nomeUnita
				+ numero+Costanti.TRATTINO_BASSO+categoria.getNomeCategoria());
		return unita;
	}
	
	public CategoriaAttuatori setValoreParametro(CategoriaAttuatori categoria, int numeroModalitaOperativa, int numeroParametro, double valore)
	{
		categoria.getModalitaOperative(numeroModalitaOperativa).getParametro(numeroParametro).setValore(valore);
		return categoria;
	}
	
	public Attuatore setModalitaOperativa(CategoriaAttuatori categoria, int numeroModalitaOperativa, Attuatore attuatore)
	{
		attuatore.setModOperativa(categoria.getModalitaOperative(numeroModalitaOperativa));
		return attuatore;
	}
	
	public boolean presenzaInfoRil(Sensore unita, InfoRilevabile infoRil)
	{
		return unita.nomeInfoRilPresente(infoRil.getTipoInfoRilevabile());
	}
	
	public Sensore valoreNumericoCasuale(Sensore unita, InfoRilevabile info)
	{
		info.setValore(new ValoreNumerico());
		unita.addMisurazione(info); 
		return unita;
	}
	
	public Sensore valoreNonNumericoCasuale(Sensore unita, CategoriaSensori categoria, InfoRilevabile info)
	{
		
		int numeroDominio = NumeriCasuali.estraiIntero(0, (categoria.sizeDominio()-1));
		ValoreNonNumerico valore = new ValoreNonNumerico();
		valore.setValore(categoria.getDominio(numeroDominio));
		info.setValore(valore);
		unita.addMisurazione(info);
		return unita;
	}
	
	public ListaImmobili caricaImmobilidaFile(String path, ListaImmobili lista)
	{
		ListaImmobili unitaCaricate = new ListaImmobili();
		if(!lista.isEmpty())
		{
			//confronto due liste
			try {
				unitaCaricate = GestioneFile.caricaImmobiliDaFile(path);
				for(int c=0;c<unitaCaricate.size();c++)
				{
					if(!lista.unitaImmobiliareGiaPresente(unitaCaricate.getImmobile(c).getDestinazioneUnita()))
						lista.addImmobile(new Immobile(new UnitaImmobiliare(unitaCaricate.getImmobile(c).getDestinazioneUnita())));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			//popolo lista
			try {
				lista = GestioneFile.caricaImmobiliDaFile(path);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return lista;
	}


}
