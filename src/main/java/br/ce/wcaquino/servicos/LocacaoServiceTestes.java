package br.ce.wcaquino.servicos;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.builder.FilmeBuilder;
import br.ce.wcaquino.builder.UsuarioBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmesSemEtoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matchers.DiaSemanaMatcher;
import br.ce.wcaquino.matchers.MatchersProprios;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import buildermaster.BuilderMaster;

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
	public  void deveAlugarFilme() throws Exception {
		
		//Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(),Calendar.SATURDAY));
		//cenario
		
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		List <Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().comValor(5.0).agora());
		
		//acao
		Locacao locacao = service.alugarFilme(usuario,filmes);
		
		//verificacao
		error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(locacao.getDataRetorno(),MatchersProprios.ehHojeComDiferencaDeDias(1));
	}
	
	@Test(expected = FilmesSemEtoqueException.class)
	public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
		//cenario
				
				Usuario usuario = UsuarioBuilder.umUsuario().agora();
				List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().semEstoque().agora());
				
				//acao
				Locacao locacao = service.alugarFilme(usuario,filmes);
		
		
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmesSemEtoqueException {
		//cenario
		
		List <Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
		
		
		//acao
		try {
			Locacao locacao = service.alugarFilme(null,filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuário vazio!"));
		}
		
	}
	
	@Test
	public void NaoDeveAlugarFilmeSemFilme() throws FilmesSemEtoqueException, LocadoraException {
		//cenario
		
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("filme vazio!");
		
		//acao
		service.alugarFilme(usuario, null);
		
		
		
	}
	
	
	@Test
	public void naoDeveDevolverFilmeNoDomingo() throws FilmesSemEtoqueException, LocadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(),Calendar.SATURDAY));
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
		
		Locacao retorno = service.alugarFilme(usuario, filmes);
		
		Boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(),Calendar.MONDAY);
		
		Assert.assertTrue(ehSegunda);
		
		//assertThat(retorno.getDataRetorno(),new DiaSemanaMatcher(Calendar.MONDAY));
		assertThat(retorno.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
	}
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}
	

}
