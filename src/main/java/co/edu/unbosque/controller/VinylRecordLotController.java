package co.edu.unbosque.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.VinylRecordLot;
import co.edu.unbosque.entity.Inventory;
import co.edu.unbosque.service.impl.VinylRecordLotServiceImpl;
import co.edu.unbosque.service.impl.InventoryServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class VinylRecordLotController {

	private VinylRecordLotServiceImpl vinylRecordLotServiceImpl;
	private InventoryServiceImpl inventoryServiceImpl;

	/**
	 * Creates a new vinyl record lot entity
	 *
	 * @param inventoryId id of the inventory where the vinyl record lot that
	 * is beeing created will be placed
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
	 */
	@GetMapping("/vinyl_record_lots/create/inventory={inventoryId}")
	public String createVinylRecordLot(@PathVariable(name = "inventoryId")
			Long inventoryId, Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("inventoryId", inventoryId);
		model.addAttribute("vinylRecordLot", null);

		return "vinylRecordLotActions";
	}

	/**
	 * Creates a new vinyl record lot entity
	 *
	 * @param newVinylRecordLot POJO with the information of the vinyl record lot
	 * entity that is beeing created.
	 * @param inventoryId id of the inventory where the vinyl record lot that is beeing
	 * created will be placed
	 * @param publicationDate date of publication of the units from the disc
	 * lot entity that is beeing created
	 * @return the specified template view.
	 */
	@GetMapping("/vinyl_record_lots/manage/create/{inventoryId}")
	public String createVinylRecordLot(VinylRecordLot newVinylRecordLot,
			@PathVariable(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "publicationDate") String publicationDate) {

		Inventory vinylRecordLotInventory =
			inventoryServiceImpl.getInventoryById(inventoryId).getBody();
		Long branchOfficeId = vinylRecordLotInventory.getBranchOffice().getId();

		newVinylRecordLot.setPublicationDate(Date.valueOf(publicationDate));
		newVinylRecordLot.setVinylRecordLotInventory(vinylRecordLotInventory);
		vinylRecordLotServiceImpl.createVinylRecordLot(newVinylRecordLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
				
	}

	/**
	 * Retrieves a vinyl record lot entity specified by id
	 *
	 * @param id id of the vinyl record lot entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/vinyl_record_lots/{Id}")
	public String showVinylRecordLot(@PathVariable(name = "Id") Long id,
			Model model) {
		VinylRecordLot vinylRecordLot =
			vinylRecordLotServiceImpl.getVinylRecordLotById(id).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("vinylRecordLot", vinylRecordLot);

		return "vinylRecordLotActions";
	}


	/**
	 * Updates a vinyl record lot entity specified by id
	 *
	 * @param id id of the vinyl record lot entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/vinyl_record_lots/update/{Id}")
	public String updateVinylRecordLot(@PathVariable(name = "Id") Long id,
			Model model) {
		VinylRecordLot vinylRecordLot =
			vinylRecordLotServiceImpl.getVinylRecordLotById(id).getBody();

		model.addAttribute("action", "put");
		model.addAttribute("vinylRecordLot", vinylRecordLot);

		return "vinylRecordLotActions";
	}

	/**
	 * Updates a new vinyl record lot entity specified by id
	 *
	 * @param updatedVinylRecordLot POJO with the information of the vinyl record lot
	 * entity that is beeing udpated.
	 * @param id id of the vinyl record lot entity that is beeing updated
	 * @param publicationDate date of publication of the units from the disc
	 * lot entity that is beeing updated
	 * @return the specified template view.
	 */
	@GetMapping("/vinyl_record_lots/manage/update/{Id}")
	public String updateVinylRecordLot(VinylRecordLot updatedVinylRecordLot,
			@PathVariable(name = "Id") Long id,
			@RequestParam(name = "publicationDate") String publicationDate) {

		Long branchOfficeId = vinylRecordLotServiceImpl.getVinylRecordLotById(id)
			.getBody().getVinylRecordLotInventory().getBranchOffice().getId();

		updatedVinylRecordLot.setPublicationDate(Date.valueOf(publicationDate));
		vinylRecordLotServiceImpl.updateVinylRecordLotById(id, updatedVinylRecordLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);

	}

	/**
	 * Deletes a vinyl record lot entity specified by id
	 *
	 * @param id id of the vinyl record lot entity that is beeing deleted
	 * @return the specified template view
	 */
	@GetMapping("/vinyl_record_lots/manage/delete/{Id}")
	public String deleteVinylRecordLot(@PathVariable(name = "Id") Long id) {
		Long branchOfficeId =
			vinylRecordLotServiceImpl.getVinylRecordLotById(id).getBody()
				.getVinylRecordLotInventory().getBranchOffice().getId();
		vinylRecordLotServiceImpl.deleteVinylRecordLotById(id);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}
}
