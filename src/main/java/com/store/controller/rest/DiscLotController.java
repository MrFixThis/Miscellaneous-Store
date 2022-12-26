package com.store.controller.rest;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.model.DiscLot;
import com.store.model.Inventory;
import com.store.service.DiscLotService;
import com.store.service.InventoryService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class DiscLotController {

	private DiscLotService discLotServiceImpl;
	private InventoryService inventoryServiceImpl;

	/**
	 * Creates a new disc lot entity
	 *
	 * @param inventoryId id of the inventory where the disc lot that is beeing
	 * created will be placed
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
	 */
	@GetMapping("/disc_lots/create/inventory={inventoryId}")
	public String createDiscLot(@PathVariable(name = "inventoryId")
			Long inventoryId, Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("inventoryId", inventoryId);
		model.addAttribute("discLot", null);

		return "discLotActions";
	}

	/**
	 * Creates a new disc lot entity
	 *
	 * @param newDiscLot POJO with the information of the disc lot
	 * entity that is beeing created.
	 * @param inventoryId id of the inventory where the disc lot that is beeing
	 * created will be placed
	 * @param publicationDate date of publication of the units from the disc
	 * lot entity that is beeing created
	 * @return the specified template view.
	 */
	@GetMapping("/disc_lots/manage/create/{inventoryId}")
	public String createDiscLot(DiscLot newDiscLot,
			@PathVariable(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "publicationDate") String publicationDate) {

		Inventory discLotInventory =
			inventoryServiceImpl.getInventoryById(inventoryId).getBody();
		Long branchOfficeId = discLotInventory.getBranchOffice().getId();

		newDiscLot.setPublicationDate(Date.valueOf(publicationDate));
		newDiscLot.setDiscLotInventory(discLotInventory);
		discLotServiceImpl.createDiscLot(newDiscLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
				
	}

	/**
	 * Retrieves a disc lot entity specified by id
	 *
	 * @param id id of the disc lot entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/disc_lots/{Id}")
	public String showDiscLot(@PathVariable(name = "id") Long id,
			Model model) {
		DiscLot discLot = discLotServiceImpl.getDiscLotById(id).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("discLot", discLot);

		return "discLotActions";
	}


	/**
	 * Updates a disc lot entity specified by id
	 *
	 * @param id id of the disc lot entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/disc_lots/update/{Id}")
	public String updateDiscLot(@PathVariable(name = "id") Long id,
			Model model) {
		DiscLot discLot = discLotServiceImpl.getDiscLotById(id).getBody();

		model.addAttribute("action", "put");
		model.addAttribute("discLot", discLot);

		return "discLotActions";
	}

	/**
	 * Updates a new disc lot entity specified by id
	 *
	 * @param updatedDiscLot POJO with the information of the disc lot
	 * entity that is beeing udpated.
	 * @param id id of the disc lot entity that is beeing updated
	 * @param publicationDate date of publication of the units from the disc
	 * lot entity that is beeing updated
	 * @return the specified template view.
	 */
	@GetMapping("/disc_lots/manage/update/{id}")
	public String updateDiscLot(DiscLot updatedDiscLot,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "publicationDate") String publicationDate) {

		Long branchOfficeId = discLotServiceImpl.getDiscLotById(id)
			.getBody().getDiscLotInventory().getBranchOffice().getId();

		updatedDiscLot.setPublicationDate(Date.valueOf(publicationDate));
		discLotServiceImpl.updateDiscLotById(id, updatedDiscLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);

	}

	/**
	 * Deletes a disc lot entity specified by id
	 *
	 * @param id id of the disc lot entity that is beeing deleted
	 * @return the specified template view
	 */
	@GetMapping("/disc_lots/manage/delete/{id}")
	public String deleteDiscLot(@PathVariable(name = "id") Long id) {
		Long branchOfficeId =
			discLotServiceImpl.getDiscLotById(id).getBody()
				.getDiscLotInventory().getBranchOffice().getId();
		discLotServiceImpl.deleteDiscLotById(id);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}
}
