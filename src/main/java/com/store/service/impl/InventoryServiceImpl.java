package com.store.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.exception.BranchOfficeNotFoundException;
import com.store.exception.InventoryNotFoundException;
import com.store.model.Inventory;
import com.store.repository.InventoryRepository;
import com.store.service.InventoryService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private InventoryRepository inventoryRepository;

	/**
	 * Creates a new Inventory
	 *
	 * @param inventory the creating Inventory instance
	 * @return the result of the CRUD's create operation over Inventory
	 */
	@Override
	public ResponseEntity<Inventory> createInventory(Inventory inventory) {
		final Inventory savedInventory = inventoryRepository.save(inventory);
		return ResponseEntity.created(
				URI.create(String.format("/api/v1/inventories/%d",
					savedInventory.getId())))
						.allow(HttpMethod.GET).body(savedInventory);
	}

	/**
	 * Retrives an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to retrive
	 * @return the result of the CRUD's retrive operation over Inventory
	 */
	@Override
	public ResponseEntity<Inventory> getInventoryById(Long id)
		throws InventoryNotFoundException {
		final Inventory inventory = inventoryRepository.findById(id)
			.orElseThrow(() -> new InventoryNotFoundException(
							String.format("Inventory with id %d not found", id)
						));
		return ResponseEntity.ok(inventory);
	}

	/**
	 * Retrives an id-specified Inventory entity
	 *
	 * @param branchOfficeId id of the BranchOffice entity related to the
	 * Inventory entity to retrieve.
	 * @return the result of the CRUD's retrive operation over Inventory.
	 */
	@Override
	public ResponseEntity<Inventory> getInventoryByBranchOfficeId(Long branchOfficeId)
			throws InventoryNotFoundException {
		final Inventory inventory =
			inventoryRepository.findInventoryByBranchOfficeId(branchOfficeId)
				.orElseThrow(() -> new BranchOfficeNotFoundException(
							String.format("Branch office with id %d not found",
								branchOfficeId)));
		return ResponseEntity.ok(inventory);
	}

	/**
	 * Retrives all the Inventory entities
	 *
	 * @return the result of the CRUD's retrive operation over Inventory
	 */
	@Override
	public ResponseEntity<List<Inventory>> getInventories() {
		final List<Inventory> inventories = inventoryRepository.findAll();
		return ResponseEntity.ok(inventories);
	}

	/**
	 * Updates an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to update
	 * @param updatedInventory the Inventory instance with the updating information
	 * @return the result of the CRUD's update operation over Inventory
	 */
	@Override
	public ResponseEntity<Inventory> updateInventoryById(Long id,
			Inventory updatedInventory)
			throws InventoryNotFoundException {
		Inventory inventory = inventoryRepository.findById(id)
			.orElseThrow(() -> new InventoryNotFoundException(
							String.format("Inventory with id %d not found", id)
						));

		inventory.setDescription(updatedInventory.getDescription());

		inventory = inventoryRepository.save(inventory);
		return ResponseEntity.ok(inventory);
	}

	/**
	 * Deletes an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to delete
	 * @return the result of the CRUD's delete operation over Inventory
	 */
	@Override
	public ResponseEntity<?> deleteInventoryById(Long id)
		throws InventoryNotFoundException {
		Inventory inventory = inventoryRepository.findById(id)
			.orElseThrow(() -> new InventoryNotFoundException(
							String.format("Inventory with id %d not found", id)
						));
		inventoryRepository.delete(inventory);

		return ResponseEntity.noContent().build();
	}
}
