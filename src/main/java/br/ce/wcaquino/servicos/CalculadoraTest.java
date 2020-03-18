package br.ce.wcaquino.servicos;

import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

import org.junit.Assert;
import org.junit.Before;;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	@Test
	public void deveSomarDoisValores() {
		int a = 5;
		int b = 3;
	
		
		int resultado = calc.somar(a,b);
		
		Assert.assertEquals(8,resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		int a = 8;
		int b = 5;
		
		
		int res = calc.subtrair(a,b);
		
		Assert.assertEquals(3,res);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		
		int a = 6;
		int b = 3;
		
		
		int res = calc.dividir(a,b);
		
		Assert.assertEquals(2,res);
		
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarUmaExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		int a = 10;
		int b = 0;
		
		
		calc.dividir(a, b);
	}
	

}
