package br.com.pucrs.sensorumidade.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pucrs.sensorumidade.modelo.Planta;

public interface PlantaRepositorio extends JpaRepository<Planta, Long>{

	Optional<Planta> findAllByOrderByNomeAsc();
	
}
