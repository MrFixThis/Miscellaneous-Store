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
	 *
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
	 *
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
	 *
	 */
	@GetMapping("/vinyl_record_lots/{Id}")
	public String showVinylRecordLot(@PathVariable(name = "Id") Long Id,
			Model model) {
		VinylRecordLot vinylRecordLot =
			vinylRecordLotServiceImpl.getVinylRecordLotById(Id).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("vinylRecordLot", vinylRecordLot);

		return "vinylRecordLotActions";
	}


	/**
	 *
	 */
	@GetMapping("/vinyl_record_lots/update/{Id}")
	public String updateVinylRecordLot(@PathVariable(name = "Id") Long Id,
			Model model) {
		VinylRecordLot vinylRecordLot =
			vinylRecordLotServiceImpl.getVinylRecordLotById(Id).getBody();

		model.addAttribute("action", "put");
		model.addAttribute("vinylRecordLot", vinylRecordLot);

		return "vinylRecordLotActions";
	}

	/**
	 *
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
	 *
	 */
	@GetMapping("/vinyl_record_lots/manage/delete/{Id}")
	public String deleteVinylRecordLot(@PathVariable(name = "Id") Long Id) {
		Long branchOfficeId =
			vinylRecordLotServiceImpl.getVinylRecordLotById(Id).getBody()
				.getVinylRecordLotInventory().getBranchOffice().getId();
		vinylRecordLotServiceImpl.deleteVinylRecordLotById(Id);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}
}
