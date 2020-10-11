package br.com.brasileirao.apicampeonatobrasileiro.rest;


import br.com.brasileirao.apicampeonatobrasileiro.dto.ClassificacaoDTO;
import br.com.brasileirao.apicampeonatobrasileiro.dto.JogoDTO;
import br.com.brasileirao.apicampeonatobrasileiro.dto.JogoFinalizadoDTO;
import br.com.brasileirao.apicampeonatobrasileiro.entity.Jogo;
import br.com.brasileirao.apicampeonatobrasileiro.service.JogoService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;



@RestController
@RequestMapping(value = "/jogos")
public class JogoRestController {
	
    @Autowired
    private JogoService jogoService;
	
	@PostMapping(value = "/gerarJogos")
	public ResponseEntity<Void> gerarJogos() {
		jogoService.gerarJogos(LocalDateTime.now());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<JogoDTO>> obterJogos() {
		return ResponseEntity.ok().body(jogoService.listarJogos());
	}
	
	@PostMapping(value = "/finalizar/{id}")
	public ResponseEntity<JogoDTO> finalizar(@PathVariable Integer id, @RequestBody JogoFinalizadoDTO jogoDto) throws Exception {
		return ResponseEntity.ok().body(jogoService.finalizar(id, jogoDto));
	}
	
	@GetMapping(value = "/classificacao")
	public ResponseEntity<ClassificacaoDTO> classificacao() {
		return ResponseEntity.ok().body(jogoService.obterClassificacao());
	}
	
	@GetMapping(value = "/jogo/{id}")
	public ResponseEntity<JogoDTO> obterJogo(@PathVariable Integer id) {
		return ResponseEntity.ok().body(jogoService.obterJogos(id));
	}
	
	
}
