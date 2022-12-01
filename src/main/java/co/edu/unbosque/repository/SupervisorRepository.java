package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Supervisor;

/**
 * @author Bryan Baron
 */
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

	/**
	 * Retrieves a Supervisor entity by username and password
	 *
	 * @param username must not be {@literal null}.
	 * @param password must not be {@literal null}.
	 * @return a Supervisor entity related with the given information
	 * or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal clientId} is {@literal null}.
	 */
	Optional<Supervisor> findSupervisorByUsernameAndPassword(String username,
			String password);
}
