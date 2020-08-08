package costanti;

import java.util.ArrayList;
import java.util.Arrays;

public class NomiFile {

	//Nomi file
			public static final String NOME_FILE_LISTA_CATEGORIE_ATTUATORI = "ListaCategorieAttuatori.ser";
			public static final String NOME_FILE_LISTA_CATEGORIE_SENSORI = "ListaCategorieSensori.ser";
			public static final String NOME_FILE_UNITA_IMMOBILIARE = "ListaUnitaImmobiliari.ser";
			public static final ArrayList<String> PATH = new ArrayList<String>(Arrays.asList(
					NOME_FILE_LISTA_CATEGORIE_ATTUATORI,
					NOME_FILE_LISTA_CATEGORIE_SENSORI,
					NOME_FILE_UNITA_IMMOBILIARE
					));
			public static final String REGOLE_XML = "Regole.xml";
			public static final String IMMOBILI_CSV = "Immobili.csv";
			public static final String CATEGORIE_ATTUATORI_CSV = "CategorieAttuatori.csv";
			public static final String CATEGORIE_SENSORI_CSV = "CategorieSensori.csv";
}
