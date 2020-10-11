package br.com.brasileirao.apicampeonatobrasileiro.repository;


import br.com.brasileirao.apicampeonatobrasileiro.entity.Jogo;
import br.com.brasileirao.apicampeonatobrasileiro.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {
	
	// vai ficar assim SELECT * FROM JOGO WHERE TIME1 =:TIME1 AND ENCERRADO = :ENCERRADO
	List<Jogo> findByTime1AndEncerrado(Time time, Boolean encerrado);
	List<Jogo> findByTime2AndEncerrado(Time time, Boolean encerrado);

}
