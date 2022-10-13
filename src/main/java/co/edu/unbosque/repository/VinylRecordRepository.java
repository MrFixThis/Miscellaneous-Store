package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.VinylRecord;

/**
 * @author Bryan Baron
 */
public interface VinylRecordRepository extends JpaRepository<VinylRecord, Long> {

	/**
	 * Retrieves a vinyl record by its record production name.
	 *
	 * @param recordProductionName must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<VinylRecord> findByRecordProductionName(String recordProductionName);
}
