package com.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.MagazineLot;

/**
 * @author Bryan Baron
 */
public interface MagazineLotRepository extends JpaRepository<MagazineLot, UUID> {

	/**
	 * Retrieves a MagazineLot instance by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<MagazineLot> findByName(String name);
}
