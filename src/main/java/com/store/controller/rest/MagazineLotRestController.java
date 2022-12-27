package com.store.controller.rest;

import java.util.List;
import java.util.UUID;

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
import com.store.model.MagazineLot;
import com.store.service.InventoryService;
import com.store.service.MagazineLotService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api/v1/magazine_lots")
@AllArgsConstructor
public class MagazineLotRestController {

	private MagazineLotService magazineLotService;
	private InventoryService inventoryService;

	/**
	 * Creates a new MagazineLot entity.
	 *
	 * @param magazineLot request body with the information of the
	 * MagazineLot entity being created.
	 * @param inventoryId id of the Inventory entity with which the
	 * MagazineLot entity being created is related to.
	 * @return the response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<MagazineLot> createMagazineLot(
			@RequestBody MagazineLot magazineLot,
			@RequestParam(name = "inventoryId") Long inventoryId) {
		final ResponseEntity<Inventory> magazineLotInventory =
			inventoryService.getInventoryById(inventoryId);
		ResponseEntity<MagazineLot> createdMagazineLot = null;

		magazineLot.setMagazineLotInventory(magazineLotInventory.getBody());
		createdMagazineLot =  magazineLotService.createMagazineLot(magazineLot);
		return createdMagazineLot;
	}

	/**
	 * Retrieves a MagazineLot entity specified by isbn.
	 *
	 * @param isbn isbn of the MagazineLot entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/{isbn}")
	public ResponseEntity<MagazineLot> getMagazineLot(
			@PathVariable(name = "isbn") UUID isbn) {
		final ResponseEntity<MagazineLot> magazineLot =
			magazineLotService.getMagazineLotByIsbn(isbn);
		return magazineLot;
	}

	/**
	 * Retrieves all the existent MagazineLot entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping
	public ResponseEntity<List<MagazineLot>> getMagazineLots() {
		final ResponseEntity<List<MagazineLot>> magazineLots =
			magazineLotService.getMagazineLots();
		return magazineLots;
	}

	/**
	 * Updates a MagazineLot entity specified by isbn.
	 *
	 * @param isbn isbn of the MagazineLot entity being updated.
	 * @param magazineLot request body with the information of the
	 * MagazineLot entity being udpated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/{isbn}")
	public ResponseEntity<MagazineLot> updateMagazineLot(
			@PathVariable(name = "isbn") UUID isbn,
			@RequestBody MagazineLot magazineLot) {
		final ResponseEntity<MagazineLot> updatedMagazineLot =
			magazineLotService.updateMagazineLotByIsbn(isbn, magazineLot);
		return updatedMagazineLot;
	}

	/**
	 * Deletes a MagazineLot entity specified by isbn.
	 *
	 * @param isbn isbn of the MagazineLot entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@PostMapping("/{isbn}")
	public ResponseEntity<?> deleteMagazineLot(
			@PathVariable(name = "isbn") UUID isbn) {
		final ResponseEntity<?> deletedMagazineLot =
			magazineLotService.deleteMagazineLotByIsbn(isbn);
		return deletedMagazineLot;
	}
}
