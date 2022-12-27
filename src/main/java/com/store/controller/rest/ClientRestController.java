package com.store.controller.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Client;
import com.store.service.ClientService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api/v1/clients")
@AllArgsConstructor
public class ClientRestController {

	private ClientService clientService;
	// private BranchOfficeService branchOfficeService; //TODO: Fix the relationship

	/**
	 * Creates a new Client entity.
	 *
	 * @param client request body with the information of the Client entity
	 * being created.
	 * @param birthDate date of brith of the Client entity being created.
	 * @return the response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client,
			@RequestParam(name = "birthDate") String birthDate) {
		ResponseEntity<Client> createdClient = null;
		client.setDateOfBirth(Date.valueOf(birthDate));
		createdClient = clientService.createClient(client);
		return createdClient;
	}

	/**
	 * Retrieves a Client entity specified by id.
	 *
	 * @param id id of the Client entity being searched
	 * @return the response of the GET request.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@PathVariable(name = "id") Long id) {
		final ResponseEntity<Client> client = clientService.getClientById(id);
		return client;
	}

	/**
	 * Retrieves all the existent Client entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping
	public ResponseEntity<List<Client>> getClients() {
		final ResponseEntity<List<Client>> clients = clientService.getClients();
		return clients;
	}

	/**
	 * Updates a Client entity specified by id.
	 *
	 * @param id id of the client entity being updated.
	 * @param client request body with the information of the Client entity
	 * being updated.
	 * @param birthDate date of brith of the Client entity being updated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(
			@PathVariable(name = "id") Long id, @RequestBody Client client,
			@RequestParam(name = "birthDate") String birthDate) {
		ResponseEntity<Client> updatedClient = null;
		client.setDateOfBirth(Date.valueOf(birthDate));
		updatedClient = clientService.updateClientById(id, client);
		return updatedClient;
	}

	/**
	 * Deletes a Client entity specified by id.
	 *
	 * @param id id of the Client entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedClient =
			clientService.deleteClientById(id);
		return deletedClient;
	}
}
