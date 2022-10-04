package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Magazine;

/**
 * @author Bryan Baron
 */
public interface MagazineRepository extends JpaRepository<Magazine, String> {

	/**
	 * Retrieves a magazine by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<Magazine> findByName(String name);
}