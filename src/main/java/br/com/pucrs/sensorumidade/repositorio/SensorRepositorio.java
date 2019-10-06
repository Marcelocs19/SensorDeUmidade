package br.com.pucrs.sensorumidade.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pucrs.sensorumidade.modelo.Sensor;

public interface SensorRepositorio extends JpaRepository<Sensor, Long>{

	Optional<Sensor> findAllOrderByNomeAsc();	


}
