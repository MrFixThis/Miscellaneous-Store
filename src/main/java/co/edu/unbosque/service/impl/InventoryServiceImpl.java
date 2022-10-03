package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Inventory;
import co.edu.unbosque.exception.InventoryNotFoundException;
import co.edu.unbosque.repository.InventoryRepository;
import co.edu.unbosque.service.InventoryService;
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
	 * @see co.edu.unbosque.repository.InventoryRepository#save(Inventory)
	 */
	@Override
	public ResponseEntity<Inventory> createInventory(Inventory inventory) {
		final Inventory savedInventory = inventoryRepository.save(inventory);
		return ResponseEntity.ok(savedInventory);
	}

	/**
	 * Retrives an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to retrive
	 * @return the result of the CRUD's retrive operation over Inventory
	 * @see co.edu.unbosque.repository.InventoryRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<Inventory> getInventoryById(Long id)
		throws InventoryNotFoundException {
		final Inventory inventory = inventoryRepository.findById(id)
			.orElseThrow(() -> new InventoryNotFoundException(
							String.format("inventory wit id %d not found", id)
						));
		return ResponseEntity.ok(inventory);
	}

	/**
	 * Retrives all the Inventory entities
	 *
	 * @return the result of the CRUD's retrive operation over Inventory
	 * @see co.edu.unbosque.repository.InventoryRepository#findAll()
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
	 * @see co.edu.unbosque.repository.InventoryRepository#save(Inventory)
	 */
	@Override
	public ResponseEntity<Inventory> updateInventoryById(Long id,
			Inventory updatedInventory)
			throws InventoryNotFoundException {
		Inventory inventory = inventoryRepository.findById(id)
			.orElseThrow(() -> new InventoryNotFoundException(
							String.format("inventory wit id %d not found", id)
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
	 * @see co.edu.unbosque.repository.InventoryRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteInventoryById(Long id)
		throws InventoryNotFoundException {
		Inventory inventory = inventoryRepository.findById(id)
			.orElseThrow(() -> new InventoryNotFoundException(
							String.format("inventory wit id %d not found", id)
						));
		inventoryRepository.delete(inventory);

		return ResponseEntity.noContent().build();
	}
}
