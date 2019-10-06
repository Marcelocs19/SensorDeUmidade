package br.com.pucrs.sensorumidade.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pucrs.sensorumidade.modelo.Leitura;

public interface LeituraRepositorio  extends JpaRepository<Leitura, Long>{

	Optional<Leitura> findAllOrderByNomeAsc();

}
