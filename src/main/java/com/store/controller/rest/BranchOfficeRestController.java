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
@RequestMapping(path = "/api/v1/branch_offices")
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
	 * @param inventory request body with the information of the new Inventory
	 * entity related to the BranchOffice entity being created.
	 * @return response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<BranchOffice> createBranchOffice(
			@RequestParam(name = "administratorId") Long administratorId,
			@RequestBody Inventory inventory) {
		final BranchOffice newBranchoffice = new BranchOffice();
		final ResponseEntity<Administrator> administrator = administratorService
			.getAdministratorById(administratorId);
		ResponseEntity<BranchOffice> createdBranchOffice = null;

		newBranchoffice.setAdministrator(administrator.getBody());
		newBranchoffice.setInventory(inventory);
		createdBranchOffice = branchOfficeService.createBranchOffice(newBranchoffice);
		return createdBranchOffice;
	}

	/**
	 * Retrieves a BranchOffice entity specified by id.
	 *
	 * @param id id of the BranchOffice entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/{id}")
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
	@GetMapping
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
	 * BranchOffice entity being created is related to.
	 * @param inventory request body with the information of the new Inventory
	 * entity related to the BranchOffice entity being updated.
	 * @return the responnse of the PUT request.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<BranchOffice> updateBranchOffice(
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "administratorId", required = false)
				Long administratorId,
			@RequestBody Inventory inventory) {

		final BranchOffice branchOffice =
			branchOfficeService.getBranchOfficeById(id).getBody();
		ResponseEntity<BranchOffice> updatedBranchOffice = null;
		if(administratorId != null) {
			final Administrator administrator =
				administratorService.getAdministratorById(administratorId)
				.getBody();

			branchOffice.setAdministrator(administrator);
			updatedBranchOffice =
				branchOfficeService.updateBranchOfficeById(id, branchOffice);
		} else {
			final Inventory currentInventory = branchOffice.getInventory();
			inventoryService.updateInventoryById(currentInventory.getId(),
					inventory);
		}
		return updatedBranchOffice;
	}

	/**
	 * Deletes a BranchOffice entity specified by id.
	 *
	 * @param id id of the BranchOffice entity being deleted.
	 * @return response of the DELETE request.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBranchOffice(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedBranchOffice =
			branchOfficeService.deleteBranchOfficeById(id);
		return deletedBranchOffice;
	}
}
