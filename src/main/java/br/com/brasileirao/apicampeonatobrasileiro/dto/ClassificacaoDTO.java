package br.com.brasileirao.apicampeonatobrasileiro.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;



@Data
public class ClassificacaoDTO {
	
	private List<ClassificacaoTimeDTO> times = new ArrayList<>();

	public List<ClassificacaoTimeDTO> getTimes() {
		return times;
	}

	public void setTimes(List<ClassificacaoTimeDTO> times) {
		this.times = times;
	}

	
}
