package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Worker;

/**
 * @author Bryan Baron
 */
public interface WorkerRepository extends JpaRepository<Worker, Long> {
	
}
