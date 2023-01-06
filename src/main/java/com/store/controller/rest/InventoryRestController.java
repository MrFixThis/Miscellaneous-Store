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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Inventory;
import com.store.service.InventoryService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class InventoryRestController {

	private InventoryService InventoryService;

	/**
	 * Creates a new Inventory entity.
	 *
	 * @param inventory request body with the information of the Inventory
	 * entity being created.
	 * @return the response of the POST request.
	 */
	@PostMapping("/v1/inventories")
	public ResponseEntity<Inventory> createInventory(
			@RequestBody Inventory inventory) {
		final ResponseEntity<Inventory> createdInventory =
			InventoryService.createInventory(inventory);
		return createdInventory;
	}

	/**
	 * Retrieves an Inventory entity specified by id.
	 *
	 * @param id of the Inventory entity being searched.
	 * @param branchOfficeId id of the BranchOffice entity related to the
	 * Inventory entity to retrieve.
	 * @return response of the GET request.
	 */
	@GetMapping("/v1/inventories/{id}")
	public ResponseEntity<Inventory> getInventory(
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "branchOfficeId", required = false) Long branchOfficeId) {
		final ResponseEntity<Inventory> inventory = branchOfficeId != null
			? InventoryService.getInventoryByBranchOfficeId(branchOfficeId)
			: InventoryService.getInventoryById(id);
		return inventory;
	}

	/**
	 * Retrieves all the existent Inventory entities.
	 *
	 * @return response of the GET request.
	 */
	@GetMapping("/v1/inventories")
	public ResponseEntity<List<Inventory>> getInventories() {
		final ResponseEntity<List<Inventory>> inventories =
			InventoryService.getInventories();
		return inventories;
	}

	/**
	* Updates an Inventory entity specified by id.
	*
	* @param id id of the Inventory entity being updated.
	* @param inventory request body with the information of the Inventory
	* entity being updated.
	* @retrun the response of the PUT request.
	 */
	@PutMapping("/v1/inventories/{id}")
	public ResponseEntity<Inventory> updateInventory(
			@PathVariable(name = "id") Long id, @RequestBody Inventory inventory) {
		final ResponseEntity<Inventory> updatedInventory =
			InventoryService.updateInventoryById(id, inventory);
		return updatedInventory;
	}

	/**
	 * Deletes an Inventory entity specified by id.
	 *
	 * @param id id of the Inventory entity being deleted.
	 * @return response of the DELETE request.
	 */
	@DeleteMapping("/v1/inventories/{id}")
	public ResponseEntity<?> deleteInventory(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedInventory =
			InventoryService.deleteInventoryById(id);
		return deletedInventory;
	}
}
