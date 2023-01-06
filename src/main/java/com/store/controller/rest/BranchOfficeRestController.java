package com.store.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Administrator;
import com.store.model.BranchOffice;
import com.store.model.Inventory;
import com.store.service.AdministratorService;
import com.store.service.BranchOfficeService;
import com.store.service.InventoryService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class BranchOfficeRestController {

	private BranchOfficeService branchOfficeService;
	private InventoryService inventoryService;
	private AdministratorService administratorService;

	/**
	 * Creates a new BranchOffice entity.
	 *
	 * @param administratorId id of the Administrator entity with which the
	 * BranchOffice entity being created is related to.
	 * @param inventoryId id of the Inventory entity with which the
	 * BranchOffice entity being created is related to.
	 * @return response of the POST request.
	 */
	@PostMapping("/v1/branch_offices")
	public ResponseEntity<BranchOffice> createBranchOffice(
			@RequestParam(name = "administratorId") Long administratorId,
			@RequestParam(name = "inventoryId") Long inventoryId) {
		final BranchOffice newBranchoffice = new BranchOffice();
		final ResponseEntity<Administrator> administrator = administratorService
			.getAdministratorById(administratorId);
		final ResponseEntity<Inventory> inventory = inventoryService
			.getInventoryById(inventoryId);
		ResponseEntity<BranchOffice> createdBranchOffice = null;

		newBranchoffice.setAdministrator(administrator.getBody());
		newBranchoffice.setInventory(inventory.getBody());
		createdBranchOffice = branchOfficeService.createBranchOffice(newBranchoffice);
		return createdBranchOffice;
	}

	/**
	 * Retrieves a BranchOffice entity specified by id.
	 *
	 * @param id id of the BranchOffice entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/branch_offices/{id}")
	public ResponseEntity<BranchOffice> getBranchOffice(
			@PathVariable(name = "id") Long id) {
		final ResponseEntity<BranchOffice> branchOffice =
			branchOfficeService.getBranchOfficeById(id);
		return branchOffice;
	}

	/**
	 * Retrieves all the existent BranchOffice entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping("/v1/branch_offices")
	public ResponseEntity<List<BranchOffice>> getBranchOffices() {
		final ResponseEntity<List<BranchOffice>> branchOffices =
			branchOfficeService.getBranchOffices();
		return branchOffices;
	}

	/**
	 * Updates a BranchOffice entity specified by id.
	 *
	 * @param id id of the BranchOffice entity being updated.
	 * @param administratorId id of the Administrator entity with which the
	 * BranchOffice entity being updated is going to be related to.
	 * @return the responnse of the PUT request.
	 */
	@PutMapping("/v1/branch_offices/{id}")
	public ResponseEntity<BranchOffice> updateBranchOffice(
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "administratorId") Long administratorId) {
		final BranchOffice branchOffice =
			branchOfficeService.getBranchOfficeById(id).getBody();
		final Administrator administrator =
			administratorService.getAdministratorById(administratorId)
			.getBody();
		ResponseEntity<BranchOffice> updatedBranchOffice = null;

		branchOffice.setAdministrator(administrator);
		updatedBranchOffice =
			branchOfficeService.updateBranchOfficeById(id, branchOffice);
		return updatedBranchOffice;
	}

	/**
	 * Deletes a BranchOffice entity specified by id.
	 *
	 * @param id id of the BranchOffice entity being deleted.
	 * @return response of the DELETE request.
	 */
	@DeleteMapping("/v1/branch_offices/{id}")
	public ResponseEntity<?> deleteBranchOffice(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedBranchOffice =
			branchOfficeService.deleteBranchOfficeById(id);
		return deletedBranchOffice;
	}
}
