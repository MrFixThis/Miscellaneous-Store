package com.store.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.DiscLot;
import com.store.model.Inventory;
import com.store.service.DiscLotService;
import com.store.service.InventoryService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api/v1/disc_lots")
@AllArgsConstructor
public class DiscLotRestController {

	private DiscLotService discLotService;
	private InventoryService inventoryService;

	/**
	 * Creates a new DiscLot entity.
	 *
	 * @param discLot request body with the information of the
	 * DiscLot entity being created.
	 * @param inventoryId id of the Inventory entity with which the
	 * DiscLot entity being created is related to.
	 * @return the response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<DiscLot> createDiscLot(@RequestBody DiscLot discLot,
			@RequestParam(name = "inventoryId") Long inventoryId) {
		final ResponseEntity<Inventory> discLotInventory =
			inventoryService.getInventoryById(inventoryId);
		ResponseEntity<DiscLot> createdDiscLot = null;

		discLot.setDiscLotInventory(discLotInventory.getBody());
		createdDiscLot =  discLotService.createDiscLot(discLot);
		return createdDiscLot;
	}

	/**
	 * Retrieves a DiscLot entity specified by id.
	 *
	 * @param id id of the DiscLot entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DiscLot> getDiscLot(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<DiscLot> discLot = discLotService.getDiscLotById(id);
		return discLot;
	}

	/**
	 * Retrieves all the existent DiscLot entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping
	public ResponseEntity<List<DiscLot>> getDiscLots() {
		final ResponseEntity<List<DiscLot>> discLots = discLotService.getDiscLots();
		return discLots;
	}

	/**
	 * Updates a DiscLot entity specified by id.
	 *
	 * @param id id of the DiscLot entity being updated.
	 * @param discLot request body with the information of the
	 * DiscLot entity being udpated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<DiscLot> updateDiscLot(
			@PathVariable(name = "id") Long id, @RequestBody DiscLot discLot) {
		final ResponseEntity<DiscLot> updatedDiscLot =
			discLotService.updateDiscLotById(id, discLot);
		return updatedDiscLot;
	}

	/**
	 * Deletes a DiscLot entity specified by id.
	 *
	 * @param id id of the DiscLot entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@PostMapping("/{id}")
	public ResponseEntity<?> deleteDiscLot(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedDiscLot = discLotService.deleteDiscLotById(id);
		return deletedDiscLot;
	}
}
