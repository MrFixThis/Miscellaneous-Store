package co.edu.unbosque.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.entity.Client;
import co.edu.unbosque.exception.BranchOfficeNotFoundException;
import co.edu.unbosque.exception.ClientNotFoundException;
import co.edu.unbosque.repository.BranchOfficeRepository;
import co.edu.unbosque.service.BranchOfficeService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class BranchOfficeServiceImpl implements BranchOfficeService {

	private BranchOfficeRepository branchOfficeRepository;
	private ClientServiceImpl clientServiceImpl;

	/**
	 * Creates a new BranchOffice
	 *
	 * @param branchOffice the creating BranchOffice instance
	 * @return the result of the CRUD's create operation over BranchOffice
	 */
	@Override
	public ResponseEntity<BranchOffice> createBranchOffice(
			BranchOffice branchOffice) {
		final BranchOffice savedBranchOffice =
			branchOfficeRepository.save(branchOffice);
		return ResponseEntity.ok(savedBranchOffice);
	}

	/**
	 * Retrives an id-specified BranchOffice entity
	 *
	 * @param id the id of the BranchOffice entity to retrive
	 * @return the result of the CRUD's retrive operation over BranchOffice
	 */
	@Override
	public ResponseEntity<BranchOffice> getBranchOfficeById(Long id)
		throws BranchOfficeNotFoundException {
		final BranchOffice branchOffice = branchOfficeRepository.findById(id)
			.orElseThrow(() -> new BranchOfficeNotFoundException(
							String.format("Branch office with id %d not found",
								id)
							));
		return ResponseEntity.ok(branchOffice);
	}

	/**
	 * Retrives all the BranchOffice entities
	 *
	 * @return the result of the CRUD's retrive operation over BranchOffice
	 */
	@Override
	public ResponseEntity<List<BranchOffice>> getBranchOffices() {
		final List<BranchOffice> branchOffices = branchOfficeRepository.findAll();
		return ResponseEntity.ok(branchOffices);
	}

	/**
	 * Retrieves all the BranchOffice entities by a Client's entity id.
	 *
	 * @param clientId the id of the Client's entity related to BranchOffice's entities
	 * @return the result of the CRUD's retrive operation over BranchOffice
	 */
	@Override
	public ResponseEntity<List<BranchOffice>> getBranchOfficesByClientsId(
			Long clientId) {
		final List<BranchOffice> branchOffices
			= branchOfficeRepository.findBranchOfficesByClientsId(clientId)
				.orElseThrow(() -> new ClientNotFoundException(
								String.format("Client with id %d not found",
									clientId)
								));
		return ResponseEntity.ok(branchOffices);

	}

	/**
	 * Updates an id-specified BranchOffice entity
	 *
	 * @param id the id of the BranchOffice entity to update
	 * @param updatedBranchOffice the BranchOffice instance with the updating information
	 * @return the result of the CRUD's update operation over BranchOffice
	 */
	@Override
	public ResponseEntity<BranchOffice> updateBranchOfficeById(Long id,
			BranchOffice updatedBranchOffice)
			throws BranchOfficeNotFoundException {
		BranchOffice branchOffice = branchOfficeRepository.findById(id)
			.orElseThrow(() -> new BranchOfficeNotFoundException(
							String.format("Branch office with id %d not found",
								id)
							));

		branchOffice.setAdministrator(updatedBranchOffice.getAdministrator());
		branchOffice.setInventory(updatedBranchOffice.getInventory());
        branchOffice.getClients().addAll(updatedBranchOffice
				.getClients()
				.stream()
				.map(c -> {
					Client cl =
						clientServiceImpl.getClientById(c.getId()).getBody();
					cl.getBranchOffices().add(updatedBranchOffice);
					return cl;
				}).collect(Collectors.toList()));

		branchOffice = branchOfficeRepository.save(branchOffice);
		return ResponseEntity.ok(branchOffice);
	}

	/**
	 * Deletes an id-specified BranchOffice entity
	 *
	 * @param id the id of the BranchOffice entity to delete
	 * @return the result of the CRUD's delete operation over BranchOffice
	 */
	@Override
	public ResponseEntity<?> deleteBranchOfficeById(Long id)
		throws BranchOfficeNotFoundException {
		BranchOffice branchOffice = branchOfficeRepository.findById(id)
			.orElseThrow(() -> new BranchOfficeNotFoundException(
							String.format("Branch office with id %d not found",
								id)
							));
		branchOfficeRepository.delete(branchOffice);

		return ResponseEntity.noContent().build();
	}
}
