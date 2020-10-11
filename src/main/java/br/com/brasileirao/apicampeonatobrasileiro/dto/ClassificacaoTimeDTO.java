package br.com.brasileirao.apicampeonatobrasileiro.dto;

import lombok.Data;

@Data
public class ClassificacaoTimeDTO implements Comparable<ClassificacaoTimeDTO> {
	
	 	private String time;
	    private Integer idTime;
	    private Integer posicao;
	    private Integer pontos;
	    private Integer jogos;
	    private Integer vitorias;
	    private Integer empates;
	    private Integer derrotas;
	    private Integer golsMarcados;
	    private Integer golsSofridos;
	    
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public Integer getIdTime() {
			return idTime;
		}
		public void setIdTime(Integer idTime) {
			this.idTime = idTime;
		}
		public Integer getPosicao() {
			return posicao;
		}
		public void setPosicao(Integer posicao) {
			this.posicao = posicao;
		}
		public Integer getPontos() {
			return pontos;
		}
		public void setPontos(Integer pontos) {
			this.pontos = pontos;
		}
		public Integer getJogos() {
			return jogos;
		}
		public void setJogos(Integer jogos) {
			this.jogos = jogos;
		}
		public Integer getVitorias() {
			return vitorias;
		}
		public void setVitorias(Integer vitorias) {
			this.vitorias = vitorias;
		}
		public Integer getEmpates() {
			return empates;
		}
		public void setEmpates(Integer empates) {
			this.empates = empates;
		}
		public Integer getDerrotas() {
			return derrotas;
		}
		public void setDerrotas(Integer derrotas) {
			this.derrotas = derrotas;
		}
		public Integer getGolsMarcados() {
			return golsMarcados;
		}
		public void setGolsMarcados(Integer golsMarcados) {
			this.golsMarcados = golsMarcados;
		}
		public Integer getGolsSofridos() {
			return golsSofridos;
		}
		public void setGolsSofridos(Integer golsSofridos) {
			this.golsSofridos = golsSofridos;
		}
		@Override
		public int compareTo(ClassificacaoTimeDTO o) {
			// TODO Auto-generated method stub
			return this.getPontos().compareTo(o.getPontos());
		}

}
