package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.MagazineLot;

/**
 * @author Bryan Baron
 */
public interface MagazineLotRepository extends JpaRepository<MagazineLot, String> {

	/**
	 * Retrieves a MagazineLot instance by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<MagazineLot> findByName(String name);
}
