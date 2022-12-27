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

import com.store.model.Administrator;
import com.store.service.AdministratorService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api/v1/administrators")
@AllArgsConstructor
public class AdministratorRestController {

	private AdministratorService administratorService;

	/**
	 * Creates a new Administrator entity.
	 *
	 * @param administrator request body with the information of the Administrator
	 * entity being created.
	 * @return the response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<Administrator>
		createAdministrator(@RequestBody Administrator administrator) {
		final ResponseEntity<Administrator> createdAdministrador =
			administratorService.createAdministrator(administrator);
		return createdAdministrador;
	}

	/**
	 * Retrieves an Administrator entity specified by id.
	 *
	 * @param id id of the Administrator entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Administrator> getAdministrator(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<Administrator> administrator =
			administratorService.getAdministratorById(id);
		return administrator;
	}

	/**
	 * Retrieves all the existent Administrator entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping
	public ResponseEntity<List<Administrator>> getAdministrators() {
		final ResponseEntity<List<Administrator>> administrators =
			administratorService.getAdministrators();
		return administrators;
	}

	/**
	 * Updates an Administrator entity specified by id.
	 *
	 * @param id id of the Administrator entity being updated.
	 * @param administrator request body with the information of the
	 * Administrator entity being updated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Administrator> updateAdministrator(@PathVariable Long id,
			@RequestBody Administrator administrator) {
		final ResponseEntity<Administrator> updatedAdministrator =
			administratorService.updateAdministratorById(id, administrator);
		return updatedAdministrator;
	}

	/**
	 * Deletes an Administrator entity specified by id.
	 *
	 * @param id id of the Administrator entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAdministrator(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedAdministrator =
			administratorService.deleteAdministratorById(id);
		return deletedAdministrator;
	}
}
