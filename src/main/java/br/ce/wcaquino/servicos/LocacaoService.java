package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmesSemEtoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;



public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario,  List<Filme>filmes) throws FilmesSemEtoqueException, LocadoraException  {
		
		if(usuario == null) {
			throw new LocadoraException("Usu·rio vazio!");
		}
		
		if(filmes == null ||filmes.isEmpty()) {
			throw new LocadoraException("filme vazio!");
		}
		
		for(Filme filme: filmes) {
			if(filme.getEstoque() == 0) {
				throw new FilmesSemEtoqueException();
			}
		}
		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		double valorTotal = 0d;
		for(Filme filme: filmes) { 
		valorTotal = filme.getPrecoLocacao();
		}

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar m√©todo para salvar
		
		return locacao;
	}
	
	
}