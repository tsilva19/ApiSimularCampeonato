package br.com.brasileirao.apicampeonatobrasileiro.repository;

import br.com.brasileirao.apicampeonatobrasileiro.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TimeRepository extends JpaRepository<Time, Integer>{

}
