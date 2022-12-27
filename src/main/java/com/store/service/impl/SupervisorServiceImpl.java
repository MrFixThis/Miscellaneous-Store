package com.store.service.impl;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.exception.SupervisorNotFoundException;
import com.store.model.Supervisor;
import com.store.repository.SupervisorRepository;
import com.store.service.SupervisorService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {

	private SupervisorRepository supervisorRepository;

	/**
	 * Creates a new Supervisor
	 *
	 * @param supervisor the creating Supervisor instance
	 * @return the result of the CRUD's create operation over Supervisor
	 */
	@Override
	public ResponseEntity<Supervisor> createSupervisor(Supervisor supervisor) {
		Supervisor savedSupervisor = null;

		supervisor.setPassword(sha256Hex(supervisor.getPassword()));
		savedSupervisor = supervisorRepository.save(supervisor);

		return ResponseEntity.created(URI.create(String.format("/api/v1/supervisors/%d",
					savedSupervisor.getId()))).allow(HttpMethod.GET).build();
	}

	/**
	 * Retrives an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to retrive
	 * @return the result of the CRUD's retrive operation over Supervisor
	 */
	@Override
	public ResponseEntity<Supervisor> getSupervisorById(Long id)
		throws SupervisorNotFoundException {
		final Supervisor supervisor = supervisorRepository.findById(id)
			.orElseThrow(() -> new SupervisorNotFoundException(
							String.format("Supervisor with id %d not found", id)));
		return ResponseEntity.ok(supervisor);
	}

	/**
	 * Retrives all the Supervisor entities
	 *
	 * @return the result of the CRUD's retrive operation over Supervisor
	 */
	@Override
	public ResponseEntity<List<Supervisor>> getSupervisors() {
		final List<Supervisor> supervisors = supervisorRepository.findAll();
		return ResponseEntity.ok(supervisors);
	}

	/**
	 * Retrives an id-specified Supervisor entity
	 *
	 * @param username the username of the Supervisor entity to retrive
	 * @param password the password of the Supervisor entity to retrive
	 * @return the result of the CRUD's retrive operation over Supervisor
	 */
	@Override
	public ResponseEntity<Supervisor> getSupervisorByUsernameAndPassword(
			String username, String password) throws SupervisorNotFoundException {
		final Supervisor supervisor =
			supervisorRepository.findSupervisorByUsernameAndPassword(username,
					sha256Hex(password))
						.orElseThrow(() -> new SupervisorNotFoundException(
							"Supervisor with credentials specified not found"));
		return ResponseEntity.ok(supervisor);
	}

	/**
	 * Updates an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to update
	 * @param updatedSupervisor the Supervisor instance with the updating information
	 * @return the result of the CRUD's update operation over Supervisor
	 */
	@Override
	public ResponseEntity<Supervisor> updateSupervisorById(Long id,
			Supervisor updatedSupervisor) throws SupervisorNotFoundException {
		Supervisor supervisor = supervisorRepository.findById(id)
			.orElseThrow(() -> new SupervisorNotFoundException(
							String.format("Supervisor with id %d not found", id)));

		supervisor.setName(updatedSupervisor.getName());
		supervisor.setSurname(updatedSupervisor.getSurname());
		supervisor.setUsername(updatedSupervisor.getUsername());
		supervisor.setPassword(sha256Hex(updatedSupervisor.getPassword()));

		supervisor = supervisorRepository.save(supervisor);
		return ResponseEntity.ok(supervisor);
	}

	/**
	 * Deletes an id-specified Supervisor entity
	 *
	 * @param id the id of the Supervisor entity to delete
	 * @return the result of the CRUD's delete operation over Supervisor
	 */
	@Override
	public ResponseEntity<?> deleteSupervisorById(Long id)
		throws SupervisorNotFoundException {
		Supervisor supervisor = supervisorRepository.findById(id)
			.orElseThrow(() -> new SupervisorNotFoundException(
							String.format("Supervisor with id %d not found", id)));
		supervisorRepository.delete(supervisor);

		return ResponseEntity.noContent().build();
	}
}
