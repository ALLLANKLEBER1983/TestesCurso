package br.ce.wcaquino.builder;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {
	
	private Filme filme;
	
	private FilmeBuilder() {}
	
	public static FilmeBuilder umFilme() {
		FilmeBuilder fBuilder = new FilmeBuilder();
		fBuilder.filme = new Filme();
		fBuilder.filme.setEstoque(2);
		fBuilder.filme.setNome("Filme1");
		fBuilder.filme.setPrecoLocacao(4.0);
		return fBuilder;
	}
	
	public FilmeBuilder semEstoque() {
		filme.setEstoque(0);
		return this;
	}
	
	public FilmeBuilder comValor(Double valor) {
		filme.setPrecoLocacao(valor);
		return this;
		
	}
	
	public Filme agora() {
		return filme;
	}


}
