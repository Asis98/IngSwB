package test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import categorie.ModalitaOperativa;
import categorie.Parametro;

class TestModalitaOperativa {

	@Test
	//nomeParametro null
	void test() {
		//fail("Not yet implemented");
		ModalitaOperativa modOp = new ModalitaOperativa("provaModalita");
		String nomeParametro = null;
		boolean expected = false;
		Assert.assertEquals(expected, modOp.parametroGiaPresente(nomeParametro));
	}
	
	@Test
	//nomeParametro esistente
	void test2() {
		//fail("Not yet implemented");
		ModalitaOperativa modOp = new ModalitaOperativa("provaModalita");
		modOp.addParametro(new Parametro("gradi"));
		String nomeParametro = "gradi";
		boolean expected = true;
		Assert.assertEquals(expected, modOp.parametroGiaPresente(nomeParametro));
	}
	
	@Test
	//nomeParametro non esistente
	void test3() {
		//fail("Not yet implemented");
		ModalitaOperativa modOp = new ModalitaOperativa("provaModalita");
		modOp.addParametro(new Parametro("gradi"));
		String nomeParametro = "metri";
		boolean expected = false;
		Assert.assertEquals(expected, modOp.parametroGiaPresente(nomeParametro));
	}
	

}
