package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Supervisor;
import co.edu.unbosque.exception.SupervisorNotFoundException;

/**
 * @author Bryan Baron
 */
public interface SupervisorService {

	/**
	 * Creates a new Supervisor
	 *
	 * @param supervisor the creating Supervisor instance
	 * @return the result of the CRUD's create operation over Supervisor
	 * @see co.edu.unbosque.repository.SupervisorRepository#save(Supervisor)
	 */
	ResponseEntity<Supervisor> createSupervisor(
			Supervisor supervisor);

	/**
	 * Retrives an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to retrive
	 * @return the result of the CRUD's retrive operation over Supervisor
	 * @see co.edu.unbosque.repository.SupervisorRepository#findById(Long)
	 */
	ResponseEntity<Supervisor> getSupervisorById(Long id)
			throws SupervisorNotFoundException;

	/**
	 * Retrives an id-specified Supervisor entity
	 *
	 * @param username the username of the Supervisor entity to retrive
	 * @param password the password of the Supervisor entity to retrive
	 * @return the result of the CRUD's retrive operation over Supervisor
	 * @see co.edu.unbosque.repository.SupervisorRepository#findByUsernameAndPassword(String, String)
	 */
	ResponseEntity<Supervisor> getSupervisorByUsernameAndPassword(String username,
			String password) throws SupervisorNotFoundException;

	/**
	 * Retrives all the Supervisor entities
	 *
	 * @return the result of the CRUD's retrive operation over Supervisor
	 * @see co.edu.unbosque.repository.SupervisorRepository#findAll()
	 */
	ResponseEntity<List<Supervisor>> getSupervisors();

	/**
	 * Updates an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to update
	 * @param updatedSupervisor the Supervisor instance with the updating information
	 * @return the result of the CRUD's update operation over Supervisor
	 * @see co.edu.unbosque.repository.SupervisorRepository#save(Supervisor)
	 */
	ResponseEntity<Supervisor> updateSupervisorById(Long id,
			Supervisor updatedSupervisor)
			throws SupervisorNotFoundException;

	/**
	 * Deletes an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to delete
	 * @return the result of the CRUD's delete operation over Supervisor
	 * @see co.edu.unbosque.repository.SupervisorRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteSupervisorById(Long id)
			throws SupervisorNotFoundException;
}
