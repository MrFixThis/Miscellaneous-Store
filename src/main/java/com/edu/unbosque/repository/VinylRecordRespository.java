package com.edu.unbosque.repository;

import java.util.Optional;

import com.edu.unbosque.entity.Disc;
import com.edu.unbosque.entity.VinylRecord;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface VinylRecordRespository extends JpaRepository<VinylRecord, Long> {

	/**
	 * Retrieves a vinyl record by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<Disc> findByName(String name);
}
