package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Client;
import co.edu.unbosque.exception.ClientNotFoundException;
import co.edu.unbosque.repository.ClientRepository;
import co.edu.unbosque.service.ClientService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	/**
	 * Creates a new Client
	 *
	 * @param client the creating Client instance
	 * @return the result of the CRUD's create operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#save(Client)
	 */
	@Override
	public ResponseEntity<Client> createClient(Client client) {
		final Client savedClient = clientRepository.save(client);
		return ResponseEntity.ok(savedClient);
	}

	/**
	 * Retrives an id-specified Client entity
	 *
	 * @param id the id of the Client entity to retrive
	 * @return the result of the CRUD's retrive operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<Client> getClientById(Long id)
		throws ClientNotFoundException {
		final Client client = clientRepository.findById(id)
			.orElseThrow(() -> new ClientNotFoundException(
							String.format("client with id %d not found", id)));
		return ResponseEntity.ok(client);
	}

	/**
	 * Retrives all the Client entities
	 *
	 * @return the result of the CRUD's retrive operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Client>> getClients() {
		final List<Client> clients = clientRepository.findAll();
		return ResponseEntity.ok(clients);
	}

	/**
	 * Updates an id-specified Client entity
	 *
	 * @param id the id of the Client entity to update
	 * @param updatedClient the Client instance with the updating information
	 * @return the result of the CRUD's update operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#save(Client)
	 */
	@Override
	public ResponseEntity<Client> updateClientById(Long id,
			Client updatedClient) throws ClientNotFoundException {
		Client client = clientRepository.findById(id)
			.orElseThrow(() -> new ClientNotFoundException(
							String.format("client with id %d not found", id)));

		client.setFirstName(updatedClient.getFirstName());
		client.setMiddleName(updatedClient.getMiddleName());
		client.setIdentificationNumber(updatedClient.getIdentificationNumber());
		client.setIdentificationType(updatedClient.getIdentificationType());
		client.setDateOfBirth(updatedClient.getDateOfBirth());
		client.setPhoneNumber(updatedClient.getPhoneNumber());
		client.setEmailAddress(updatedClient.getEmailAddress());

		client = clientRepository.save(client);
		return ResponseEntity.ok(client);
	}

	/**
	 * Deletes an id-specified Client entity
	 *
	 * @param id the id of the Client entity to delete
	 * @return the result of the CRUD's delete operation over Client
	 * @see co.edu.unbosque.repository.ClientRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteClientById(Long id)
		throws ClientNotFoundException {
		Client client = clientRepository.findById(id)
			.orElseThrow(() -> new ClientNotFoundException(
							String.format("client with id %d not found", id)));
		clientRepository.delete(client);

		return ResponseEntity.noContent().build();
	}
}