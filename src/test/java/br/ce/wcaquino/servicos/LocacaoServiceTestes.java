package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmesSemEtoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTestes {
	private  LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public  void setup() {
		service = new LocacaoService();
		
	}
	
	
	
	@Test
	public  void testeLocadora() throws Exception {
		//cenario
		
		Usuario usuario = new Usuario("Usuario 1");
		List <Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilme(usuario,filmes);
		
		//verificacao
		error.checkThat(locacao.getValor(), CoreMatchers.is(0.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), new Date()), CoreMatchers.is(false));
	}
	
	@Test(expected = FilmesSemEtoqueException.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		//cenario
				
				Usuario usuario = new Usuario("Usuario 1");
				List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));
				
				//acao
				Locacao locacao = service.alugarFilme(usuario,filmes);
		
		
	}
	
	@Test
	public void testeLocadora_UsuarioVazio() throws FilmesSemEtoqueException {
		//cenario
		
		List <Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		
		//acao
		try {
			Locacao locacao = service.alugarFilme(null,filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuário vazio!"));
		}
		
	}
	
	@Test
	public void testeLocacao_FilmeVazio() throws FilmesSemEtoqueException, LocadoraException {
		//cenario
		
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("filme vazio!");
		
		//acao
		service.alugarFilme(usuario, null);
		
		
		
	}
	

}
