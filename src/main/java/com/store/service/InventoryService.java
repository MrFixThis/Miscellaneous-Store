package com.store.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.store.exception.InventoryNotFoundException;
import com.store.model.Inventory;

/**
 * @author Bryan Baron
 */
public interface InventoryService {

	/**
	 * Creates a new Inventory
	 *
	 * @param inventory the creating Inventory instance
	 * @return the result of the CRUD's create operation over Inventory
	 */
	ResponseEntity<Inventory> createInventory(Inventory inventory);

	/**
	 * Retrives an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to retrive
	 * @return the result of the CRUD's retrive operation over Inventory
	 */
	ResponseEntity<Inventory> getInventoryById(Long id)
			throws InventoryNotFoundException;

	/**
	 * Retrives all the Inventory entities
	 *
	 * @return the result of the CRUD's retrive operation over Inventory
	 */
	ResponseEntity<List<Inventory>> getInventories();

	/**
	 * Updates an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to update
	 * @param updatedInventory the Inventory instance with the updating information
	 * @return the result of the CRUD's update operation over Inventory
	 */
	ResponseEntity<Inventory> updateInventoryById(Long id,
			Inventory updatedInventory)
			throws InventoryNotFoundException;

	/**
	 * Deletes an id-specified Inventory entity
	 *
	 * @param id the id of the Inventory entity to delete
	 * @return the result of the CRUD's delete operation over Inventory
	 */
	ResponseEntity<?> deleteInventoryById(Long id)
			throws InventoryNotFoundException;
}
