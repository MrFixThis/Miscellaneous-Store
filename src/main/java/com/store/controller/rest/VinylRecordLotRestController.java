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

import com.store.model.Inventory;
import com.store.model.VinylRecordLot;
import com.store.service.InventoryService;
import com.store.service.VinylRecordLotService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api/v1/vinyl_record_lots")
@AllArgsConstructor
public class VinylRecordLotRestController {

	private VinylRecordLotService vinylRecordLotService;
	private InventoryService inventoryService;

	/**
	 * Creates a new VinylRecordLot entity.
	 *
	 * @param vinylRecordLot request body with the information of the
	 * VinylRecordLot entity being created.
	 * @param inventoryId id of the Inventory entity with which the
	 * VinylRecordLot entity being created is related to.
	 * @return the response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<VinylRecordLot> createVinylRecordLot(
			@RequestBody VinylRecordLot vinylRecordLot,
			@RequestParam(name = "inventoryId") Long inventoryId) {
		final ResponseEntity<Inventory> vinylRecordLotInventory =
			inventoryService.getInventoryById(inventoryId);
		ResponseEntity<VinylRecordLot> createdVinylRecordLot = null;

		vinylRecordLot.setVinylRecordLotInventory(vinylRecordLotInventory
				.getBody());
		createdVinylRecordLot =
			vinylRecordLotService.createVinylRecordLot(vinylRecordLot);
		return createdVinylRecordLot;
	}

	/**
	 * Retrieves a VinylRecordLot entity specified by id.
	 *
	 * @param id id of the VinylRecordLot entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<VinylRecordLot> getVinylRecordLot(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<VinylRecordLot> vinylRecordLot =
			vinylRecordLotService.getVinylRecordLotById(id);
		return vinylRecordLot;
	}

	/**
	 * Retrieves all the existent VinylRecordLot entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping
	public ResponseEntity<List<VinylRecordLot>> getVinylRecordLots() {
		final ResponseEntity<List<VinylRecordLot>> vinylRecordLots =
			vinylRecordLotService.getVinylRecordLots();
		return vinylRecordLots;
	}

	/**
	 * Updates a VinylRecordLot entity specified by id.
	 *
	 * @param id id of the VinylRecordLot entity being updated.
	 * @param vinylRecordLot request body with the information of the
	 * VinylRecordLot entity being udpated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<VinylRecordLot> updateVinylRecordLot(
			@PathVariable(name = "id") Long id,
			@RequestBody VinylRecordLot vinylRecordLot) {
		final ResponseEntity<VinylRecordLot> updatedVinylRecordLot =
			vinylRecordLotService.updateVinylRecordLotById(id, vinylRecordLot);
		return updatedVinylRecordLot;
	}

	/**
	 * Deletes a VinylRecordLot entity specified by id.
	 *
	 * @param id id of the VinylRecordLot entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@PostMapping("/{id}")
	public ResponseEntity<?> deleteVinylRecordLot(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedVinylRecordLot =
			vinylRecordLotService.deleteVinylRecordLotById(id);
		return deletedVinylRecordLot;
	}
}
