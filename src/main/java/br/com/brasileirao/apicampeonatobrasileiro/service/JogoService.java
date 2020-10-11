package br.com.brasileirao.apicampeonatobrasileiro.service;





import br.com.brasileirao.apicampeonatobrasileiro.dto.ClassificacaoDTO;
import br.com.brasileirao.apicampeonatobrasileiro.dto.ClassificacaoTimeDTO;
import br.com.brasileirao.apicampeonatobrasileiro.dto.JogoDTO;
import br.com.brasileirao.apicampeonatobrasileiro.dto.JogoFinalizadoDTO;
import br.com.brasileirao.apicampeonatobrasileiro.entity.Jogo;
import br.com.brasileirao.apicampeonatobrasileiro.entity.Time;
import br.com.brasileirao.apicampeonatobrasileiro.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;




@Service
public class JogoService {
	
	@Autowired
	JogoRepository jogoRepository;
	
    @Autowired
	TimeService timeService;
	
	/**
	 * 
	 * @param primeiraRodada Data da primeira rodada
	 */
	
	public void gerarJogos(LocalDateTime primeiraRodada) {
		final List<Time> times = timeService.findAll();
		List<Time> all1 = new ArrayList<>();
		List<Time> all2 = new ArrayList<>();
		all1.addAll(times);//.subList(0, times.size()/2));
        all2.addAll(times);//.subList(all1.size(), times.size()));
		
		
		jogoRepository.deleteAll();
		
		List<Jogo> jogos = new ArrayList<>();
		
		int t = times.size();
		int m = times.size() / 2;
		LocalDateTime dataJogo = primeiraRodada;
		Integer rodada = 0;
		for(int i = 0; i < t - 1; i++ ) {
			rodada = i + 1;
			for(int j = 0; j < m; j++) {
				//Teste para ajustar o mando de campo
				Time time1;
				Time time2;
				if (j % 2 == 1 || i % 2 == 1 && j ==0) {
					time1 = times.get(t - j - 1);
					time2 = times.get(j);
				} else {
					time1 = times.get(j);
					time2 = times.get(t -j -1);
				}
				if (time1 == null) {
					System.out.println("Time 1 null");
				}
				jogos.add(gerarJogo(dataJogo, rodada, time1, time2));
				dataJogo = dataJogo.plusDays(7);
			}
			//Gira os times no sentido horario , mantendo o primeiro no lugar
			times.add(1, times.remove(times.size() - 1 ));
		}
		
		jogos.forEach(jogo -> System.out.println(jogo));
		
		jogoRepository.saveAll(jogos);
		
		List<Jogo> jogos2 = new ArrayList<>();
		
		jogos.forEach(jogo -> {
			Time time1 = jogo.getTime2();
			Time time2 = jogo.getTime1();
			jogos2.add(gerarJogo(jogo.getData().plusDays(7 * jogos.size()), jogo.getRodada() + jogos.size(), time1, time2));
		});
			
			jogoRepository.saveAll(jogos2);

	}

	private Jogo gerarJogo(LocalDateTime dataJogo, Integer rodada, Time time1, Time time2) {
		Jogo jogo = new Jogo();
		jogo.setTime1(time1);
		jogo.setTime2(time2);
		jogo.setRodada(rodada);
		jogo.setData(dataJogo);
		jogo.setEncerrado(false);
		jogo.setGolsTime1(0);
		jogo.setGolsTime2(0);
		jogo.setPublicoPagante(0);
		return jogo;
	}
	
	private JogoDTO toDTO(Jogo entity) {
        JogoDTO dto = new JogoDTO();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setEncerrado(entity.getEncerrado());
        dto.setGolsTime1(entity.getGolsTime1());
        dto.setGolsTime2(entity.getGolsTime2());
        dto.setPublicoPagante(entity.getPublicoPagante());
        dto.setRodada(entity.getRodada());
        dto.setTime1(timeService.toDto(entity.getTime1()));
        dto.setTime2(timeService.toDto(entity.getTime2()));
        return dto;
    }
	
	public List<JogoDTO> listarJogos() {
		
		return jogoRepository.findAll().stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
	}

	public JogoDTO finalizar(Integer id, JogoFinalizadoDTO jogoDto) throws Exception {
		// TODO Auto-generated method stub
		
		Optional<Jogo> optionalJogo = jogoRepository.findById(id);
		if(optionalJogo.isPresent()) {
			final Jogo jogo = optionalJogo.get();
			jogo.setGolsTime1(jogoDto.getGolsTime1());
			jogo.setGolsTime2(jogoDto.getGolsTime2());
			jogo.setEncerrado(true);
			jogo.setPublicoPagante(jogoDto.getPublicoPagante());
			return toDTO(jogoRepository.save(jogo));
		} else {
			throw new Exception("Jogo não existe");
		}
		
	}

	public ClassificacaoDTO obterClassificacao() {
		ClassificacaoDTO classificacaoDto = new ClassificacaoDTO();
		final List<Time> times = timeService.findAll();
		// TODO Auto-generated method stub
		times.forEach(time -> {
			final List<Jogo> jogosMandantes = jogoRepository.findByTime1AndEncerrado(time, true);
			final List<Jogo> jogosVisitantes = jogoRepository.findByTime2AndEncerrado(time, true);
			AtomicReference<Integer> vitorias = new AtomicReference<>(0);
			AtomicReference<Integer> empates = new AtomicReference<>(0);
			AtomicReference<Integer>  derrotas = new AtomicReference<>(0);
			AtomicReference<Integer> golsSofridos = new AtomicReference<>(0);
			AtomicReference<Integer> golsMarcados = new AtomicReference<>(0);
			
			jogosMandantes.forEach(jogo -> {
					if(jogo.getGolsTime1() > jogo.getGolsTime2()) {
						vitorias.getAndSet(vitorias.get() + 1);
					} else if (jogo.getGolsTime1() > jogo.getGolsTime2()) {
						derrotas.getAndSet(derrotas.get() + 1);
					} else {
						empates.getAndSet(empates.get() + 1);
					}
					golsMarcados.set(golsMarcados.get() + jogo.getGolsTime1());
	                golsSofridos.getAndSet(golsSofridos.get() + jogo.getGolsTime2());
			});
			
			jogosVisitantes.forEach(jogo -> {
					if(jogo.getGolsTime2() > jogo.getGolsTime1()) {
								vitorias.getAndSet(vitorias.get() + 1);
							} else if (jogo.getGolsTime2() > jogo.getGolsTime1()) {
								derrotas.getAndSet(derrotas.get() + 1);
							} else {
								empates.getAndSet(empates.get() + 1);
							}
					golsMarcados.set(golsMarcados.get() + jogo.getGolsTime2());
	                golsSofridos.getAndSet(golsSofridos.get() + jogo.getGolsTime1());

			});
			
			ClassificacaoTimeDTO classificacaoTimeDto = new ClassificacaoTimeDTO();
			classificacaoTimeDto.setIdTime(time.getId());
            classificacaoTimeDto.setTime(time.getNome());
            classificacaoTimeDto.setPontos((vitorias.get() * 3) + empates.get());
            classificacaoTimeDto.setDerrotas(derrotas.get());
            classificacaoTimeDto.setEmpates(empates.get());
            classificacaoTimeDto.setVitorias(vitorias.get());
            classificacaoTimeDto.setGolsMarcados(golsMarcados.get());
            classificacaoTimeDto.setGolsSofridos(golsSofridos.get());
            classificacaoTimeDto.setJogos(derrotas.get() + empates.get() + vitorias.get());
            classificacaoDto.getTimes().add(classificacaoTimeDto);			
		});
		
		Collections.sort(classificacaoDto.getTimes(), Collections.reverseOrder());
		int posicao = 1;
		for (ClassificacaoTimeDTO time : classificacaoDto.getTimes()) {
			time.setPosicao(posicao++);
		}
		
		return classificacaoDto;
	}

	public JogoDTO obterJogos(Integer id) {
		// TODO Auto-generated method stub
		
		return toDTO(jogoRepository.findById(id).get());
	}

}
