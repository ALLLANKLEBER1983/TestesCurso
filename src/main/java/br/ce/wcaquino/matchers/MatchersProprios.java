package br.ce.wcaquino.matchers;

public class MatchersProprios {
	
	public static DiaSemanaMatcher caiEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DataDiferencaDiasMatcher ehHojeComDiferencaDeDias(Integer qtdDias) {
		return new DataDiferencaDiasMatcher(qtdDias);
	}

}
