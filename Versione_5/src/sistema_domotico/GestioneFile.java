package sistema_domotico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ambiente.UnitaImmobiliare;
import categorie.CategoriaAttuatori;
import categorie.CategoriaSensori;
import categorie.ModalitaOperativa;
import categorie.ValoreNonNumerico;
import categorie.ValoreNumerico;
import costanti.Costanti;
import costanti.Messaggi;
import it.unibs.fp.mylib.ServizioFile;
import liste.ListaCategorie;
import liste.ListaImmobili;
import regole.Regola;
import rilevazione.Attuatore;
import rilevazione.Sensore;

public class GestioneFile {

	/**
	 * Istanzia da file.
	 *
	 * @param <T> the generic type
	 * @param path 
	 * @param type 
	 * @pre: path!=null && type!=null
	 * @post: fileList!=null
	 * @return the <T> object instance
	 */
	@SuppressWarnings("deprecation")
	public static  <T>  T istanziaDaFile(String path, Class<T> type){
		File fileList = new File(path);
		if(fileList.exists() && fileList.length() !=0)
			return type.cast(ServizioFile.caricaSingoloOggetto(fileList));
		else
			try {
				return type.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				return null;
			}
	}
	
	/**
	 * Salva file.
	 *
	 * @param path 
	 * @param list 
	 * @pre: path!=null && list!=null 
	 * @post: file!=null
	 */
	public static void salvaFile(ArrayList<String> path, List<Serializable> list)
	{		
		for(int i=0;i<path.size();i++)
		{
			File file = new File (path.get(i));
			ServizioFile.salvaSingoloOggetto(file,list.get(i));
		}
	}
	
	/**
	 * Carica categoria sensori da file.
	 *
	 * @param <T> the generic type
	 * @param path the path
	 * @param type the type
	 * @pre: path !=null && type !=null
	 * @post: lista !=null
	 * @return the lista categorie
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <T> ListaCategorie caricaCategoriaSensoriDaFile(String path, Class <T> type) throws InstantiationException, IllegalAccessException, IOException
	{
		ListaCategorie lista = new ListaCategorie();
		try {
			 BufferedReader csvReader = new BufferedReader(new FileReader(path));
			 String row;
			while ((row = csvReader.readLine()) != null) 
			{
			     String[] data = row.split(Costanti.DIVISORE);
			     if(type == CategoriaSensori.class)
			    	 lista.addCategoria(new CategoriaSensori(data[0],data[1]));
			     else if(type == CategoriaAttuatori.class)
			     	 lista.addCategoria(new CategoriaAttuatori(data[0],data[1]));
			 }
			 csvReader.close();
		}
		 catch(FileNotFoundException e)
			{
				System.out.println(Costanti.FILE_NON_TROVATO);
			}
		 
		return lista;
	}
	
	/**
	 * Carica immobili da file.
	 *
	 * @param path the path
	 * @pre: path != null
	 * @post: lista !=null
	 * @return the lista unita immobiliari
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ListaImmobili caricaImmobiliDaFile(String path) throws IOException
	{
		ListaImmobili lista = new ListaImmobili();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(path));
			String row;
			while ((row = csvReader.readLine()) != null) 
			{
			     String[] data = row.split(Costanti.DIVISORE);
			     lista.addImmobile(new UnitaImmobiliare(data[0]));
			 }
			 csvReader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(Costanti.FILE_NON_TROVATO);
		}
		
		return lista;
		
	}
	
	
	/**
	 * Carica regole da file.
	 *
	 * @param immobile 
	 * @param path 
	 * @pre: immobile !=null && path!=null
	 * @post: immobile.listaRegole != null
	 * @return the unita immobiliare
	 */
	public static UnitaImmobiliare caricaRegoleDaFile(UnitaImmobiliare immobile, String path)
	{
		
		try {
			
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	
			NodeList listaRegole = doc.getElementsByTagName(Messaggi.REGOLA); 
			
				
			for(int c=0; c<listaRegole.getLength(); c++)
			{
				Regola regola = new Regola();
				Element currentRegola = (Element)listaRegole.item(c);
				NodeList listaAntecedenti = currentRegola.getElementsByTagName(Costanti.ANTECEDENTE); 
				NodeList listaConseguenti = currentRegola.getElementsByTagName(Costanti.CONSEGUENTE); 
				
				for (int i = 0; i < listaAntecedenti.getLength(); i++) 
				{
					Element currentAnt = (Element)listaAntecedenti.item(i);
					
					Sensore sensore = null;
					if((sensore = immobile.cercaSensore(getText(currentAnt.getElementsByTagName(Costanti.FIRSTSENSORE)))) != null)
			    	 {
				    	sensore.addMisurazione(sensore.cercaInfoRilevabile(getText(currentAnt.getElementsByTagName(Costanti.FIRSTINFORIL))));
				    	
				    	if(currentAnt.getElementsByTagName(Costanti.COSTANTE).getLength() != 0)
				    	{
					    	if(sensore.getInfoRilevabile(0).getValoreAttuale().getValore() instanceof String)	    	
					    		regola.setAntecedente(sensore, getText(currentAnt.getElementsByTagName(Costanti.OPREL)), 
					    				new ValoreNonNumerico(getText(currentAnt.getElementsByTagName(Costanti.COSTANTE))));
					    	else
					    		regola.setAntecedente(sensore, getText(currentAnt.getElementsByTagName(Costanti.OPREL)), 
					    				new ValoreNumerico(Double.parseDouble(getText(currentAnt.getElementsByTagName(Costanti.COSTANTE)))));
					    	
				    	} else if(currentAnt.getElementsByTagName(Costanti.SECONDSENSORE).getLength() != 0)
				    	{
				    		Sensore sensoreB = immobile.cercaSensore(getText(currentAnt.getElementsByTagName(Costanti.SECONDSENSORE)));
				    		regola.setAntecedente(sensore, getText(currentAnt.getElementsByTagName(Costanti.OPREL)), sensoreB);
				    	}	
				    }		
					
				}
				
	
				for(int j=0; j<listaConseguenti.getLength(); j++)
				{
					Element currentCons = (Element)listaConseguenti.item(j); 
					
					Attuatore attuatore = null;
					if((attuatore = immobile.cercaAttuatore(getText(currentCons.getElementsByTagName(Costanti.ATTUATOREXML)))) != null && !regola.isEmptyAntecedente())
			    	 {
							String modOp = getText(currentCons.getElementsByTagName(Costanti.MODALOP));
							ModalitaOperativa modOperativa = attuatore.getCategoria().cercaModOp(modOp);
					        regola.setConseguente(attuatore, modOperativa);
			    	 }				
				}
				
				immobile.setRegola(regola);
				
			}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
		
		
		return immobile;
		
	}
	
	/**
	 * Gets the text.
	 *
	 * @param node the node
	 * @pre: node !=null
	 * @post: node.item(0).getTextContent().trim() != null
	 * @return the text
	 */
	public static String getText(NodeList node) {
		return node.item(0).getTextContent().trim();
	}
		

}
