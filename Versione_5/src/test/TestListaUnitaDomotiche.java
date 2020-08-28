package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ambiente.Stanza;
import liste.ListaUnitaDomotiche;

class TestListaUnitaDomotiche {

	@Test
	//test lunghezzaArrayList = 0 + key Assente
	void test() {
		ListaUnitaDomotiche lista = new ListaUnitaDomotiche();
		boolean risultato = false;
		String nomeUnitaDaCercare = "cucina";
		Assert.assertEquals(risultato, lista.verificaPresenzaUnitaDomotica(nomeUnitaDaCercare, Stanza.class));
	}
	
	@Test
	//test lunghezzaArrayList = 1
	void test2() {
		ListaUnitaDomotiche lista = new ListaUnitaDomotiche();
		lista.addUnitaDomotica(new Stanza("cucina"));
		boolean risultato = false;
		String nomeUnitaDaCercare = "salotto";
		Assert.assertEquals(risultato, lista.verificaPresenzaUnitaDomotica(nomeUnitaDaCercare, Stanza.class));
	}
	
	@Test
	//test lunghezzaArrayList > 1 + key presente
	void test3() {
		ListaUnitaDomotiche lista = new ListaUnitaDomotiche();
		lista.addUnitaDomotica(new Stanza("cucina"));
		lista.addUnitaDomotica(new Stanza("salotto"));
		lista.addUnitaDomotica(new Stanza("camera"));
		boolean risultato = true;
		String nomeUnitaDaCercare = "salotto";
		Assert.assertEquals(risultato, lista.verificaPresenzaUnitaDomotica(nomeUnitaDaCercare, Stanza.class));
	}
	


}
