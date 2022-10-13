package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Client;
import co.edu.unbosque.exception.ClientNotFoundException;

/**
 * @author Bryan Baron
 */
public interface ClientService {

	/**
	 * Creates a new Client
	 *
	 * @param client the creating Client instance
	 * @return the result of the CRUD's create operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#save(Client)
	 */
	ResponseEntity<Client> createClient(Client client);

	/**
	 * Retrives an id-specified Client entity
	 *
	 * @param id the id of the Client entity to retrive
	 * @return the result of the CRUD's retrive operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#findById(Long)
	 */
	ResponseEntity<Client> getClientById(Long id)
			throws ClientNotFoundException;

	/**
	 * Retrives all the Client entities
	 *
	 * @return the result of the CRUD's retrive operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#findAll()
	 */
	ResponseEntity<List<Client>> getClients();

	/**
	 * Retrieves all the Client entities by a BranchOffice's entity id.
	 *
	 * @param branchOfficeId the id of the BranchOffice's entity related to Client's entities
	 * @return the result of the CRUD's retrive operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#findClientsByBranchOfficesId(Long)
	 */
	ResponseEntity<List<Client>> getClientsByBranchOfficesId(Long branchOfficeId);

	/**
	 * Updates an id-specified Client entity
	 *
	 * @param id the id of the Client entity to update
	 * @param updatedClient the Client instance with the updating information
	 * @return the result of the CRUD's update operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#save(Client)
	 */
	ResponseEntity<Client> updateClientById(Long id, Client updatedClient)
			throws ClientNotFoundException;

	/**
	 * Deletes an id-specified Client entity
	 *
	 * @param id the id of the Client entity to delete
	 * @return the result of the CRUD's delete operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteClientById(Long id)
			throws ClientNotFoundException;
}
