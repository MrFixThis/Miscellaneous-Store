package com.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.VinylRecordLot;

/**
 * @author Bryan Baron
 */
public interface VinylRecordLotRepository extends JpaRepository<VinylRecordLot, Long> {

	/**
	 * Retrieves a vinyl record lot by its record production name.
	 *
	 * @param recordProductionName must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<VinylRecordLot> findByRecordProductionName(String recordProductionName);
}
