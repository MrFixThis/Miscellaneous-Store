package com.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.store.exception.SupervisorNotFoundException;
import com.store.model.Supervisor;

/**
 * @author Bryan Baron
 */
public interface SupervisorService {

	/**
	 * Creates a new Supervisor
	 *
	 * @param supervisor the creating Supervisor instance
	 * @return the result of the CRUD's create operation over Supervisor
	 */
	ResponseEntity<Supervisor> createSupervisor(
			Supervisor supervisor);

	/**
	 * Retrives an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to retrive
	 * @return the result of the CRUD's retrive operation over Supervisor
	 */
	ResponseEntity<Supervisor> getSupervisorById(Long id)
			throws SupervisorNotFoundException;

	/**
	 * Retrives an id-specified Supervisor entity
	 *
	 * @param username the username of the Supervisor entity to retrive
	 * @param password the password of the Supervisor entity to retrive
	 * @return the result of the CRUD's retrive operation over Supervisor
	 */
	ResponseEntity<Supervisor> getSupervisorByUsernameAndPassword(String username,
			String password) throws SupervisorNotFoundException;

	/**
	 * Retrives all the Supervisor entities
	 *
	 * @return the result of the CRUD's retrive operation over Supervisor
	 */
	ResponseEntity<List<Supervisor>> getSupervisors();

	/**
	 * Updates an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to update
	 * @param updatedSupervisor the Supervisor instance with the updating information
	 * @return the result of the CRUD's update operation over Supervisor
	 */
	ResponseEntity<Supervisor> updateSupervisorById(Long id,
			Supervisor updatedSupervisor)
			throws SupervisorNotFoundException;

	/**
	 * Deletes an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to delete
	 * @return the result of the CRUD's delete operation over Supervisor
	 */
	ResponseEntity<?> deleteSupervisorById(Long id)
			throws SupervisorNotFoundException;
}
