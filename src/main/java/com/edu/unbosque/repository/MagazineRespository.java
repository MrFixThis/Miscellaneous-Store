package com.edu.unbosque.repository;

import java.util.Optional;

import com.edu.unbosque.entity.Magazine;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface MagazineRespository extends JpaRepository<Magazine, String> {

	/**
	 * Retrieves a magazine by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<Magazine> findByName(String name);
}
