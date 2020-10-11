package br.com.brasileirao.apicampeonatobrasileiro.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class JogoDTO {
	
	 private Integer id;
	    private LocalDateTime data;
	    private Integer golsTime1;
	    private Integer golsTime2;
	    private Integer publicoPagante;
	    private Boolean encerrado;
	    private Integer rodada;
	    private TimeDto time1;
	    private TimeDto time2;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public LocalDateTime getData() {
			return data;
		}
		public void setData(LocalDateTime data) {
			this.data = data;
		}
		public Integer getGolsTime1() {
			return golsTime1;
		}
		public void setGolsTime1(Integer golsTime1) {
			this.golsTime1 = golsTime1;
		}
		public Integer getGolsTime2() {
			return golsTime2;
		}
		public void setGolsTime2(Integer golsTime2) {
			this.golsTime2 = golsTime2;
		}
		public Integer getPublicoPagante() {
			return publicoPagante;
		}
		public void setPublicoPagante(Integer publicoPagante) {
			this.publicoPagante = publicoPagante;
		}
		public Boolean getEncerrado() {
			return encerrado;
		}
		public void setEncerrado(Boolean encerrado) {
			this.encerrado = encerrado;
		}
		public Integer getRodada() {
			return rodada;
		}
		public void setRodada(Integer rodada) {
			this.rodada = rodada;
		}
		public TimeDto getTime1() {
			return time1;
		}
		public void setTime1(TimeDto time1) {
			this.time1 = time1;
		}
		public TimeDto getTime2() {
			return time2;
		}
		public void setTime2(TimeDto time2) {
			this.time2 = time2;
		}

}
