package com.store.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Supervisor;
import com.store.service.SupervisorService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class SupervisorRestController {

	private SupervisorService supervisorService;

	/**
	 * Creates a new Supervisor entity.
	 *
	 * @param supervisor request body with the information of the Supervisor
	 * entity being created.
	 * @return the response of the POST request.
	 */
	@PostMapping("/v1/supervisors")
	public ResponseEntity<Supervisor> createSupervisor(
			@RequestBody Supervisor supervisor) {
		final ResponseEntity<Supervisor> createdSupervisor =
			supervisorService.createSupervisor(supervisor);
		return createdSupervisor;
	}

	/**
	 * Retrieves a Supervisor entity specified by id.
	 *
	 * @param id id of the Supervisor entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/supervisors/{id}")
	public ResponseEntity<Supervisor> getSupervisor(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<Supervisor> supervisor =
			supervisorService.getSupervisorById(id);
		return supervisor;
	}

	/**
	 * Retrieves all the existent Supervisor entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/supervisors")
	public ResponseEntity<List<Supervisor>> getSupervisors() {
		final ResponseEntity<List<Supervisor>> supervisors =
			supervisorService.getSupervisors();
		return supervisors;
	}


	/**
	 * Updates a Supervisor entity specified by id.
	 *
	 * @param id id of the Supervisor entity being updated.
	 * @param supervisor request body with the information of the Supervisor
	 * entity being updated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/v1/supervisors/{id}")
	public ResponseEntity<Supervisor> updateSupervisor(
			@PathVariable(name = "id") Long id,
			@RequestBody Supervisor supervisor) {
		final ResponseEntity<Supervisor> updatedSupervisor =
			supervisorService.updateSupervisorById(id, supervisor);
		return updatedSupervisor;
	}

	/**
	 * Deletes a Supervisor entity specified by id.
	 *
	 * @param id id of the Supervisor entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@DeleteMapping("/v1/supervisors/{id}")
	public ResponseEntity<?> deleteSupervisor(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedSupervisor =
			supervisorService.deleteSupervisorById(id);
		return deletedSupervisor;
	}
}
