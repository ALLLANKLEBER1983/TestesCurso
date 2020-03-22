package br.ce.wcaquino.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import br.ce.wcaquino.servicos.CalculadoraTest;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTestes;

@RunWith(Suite.class)
@SuiteClasses({
               CalculadoraTest.class,
               CalculoValorLocacaoTest.class,
               LocacaoServiceTestes.class
               
               
               
})
public class SuiteExecucao {
	
	

}
