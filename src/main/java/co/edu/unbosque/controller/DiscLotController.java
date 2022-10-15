package co.edu.unbosque.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.DiscLot;
import co.edu.unbosque.entity.Inventory;
import co.edu.unbosque.service.impl.DiscLotServiceImpl;
import co.edu.unbosque.service.impl.InventoryServiceImpl;
import co.edu.unbosque.util.DateManager;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class DiscLotController {

	private DiscLotServiceImpl discLotServiceImpl;
	private InventoryServiceImpl inventoryServiceImpl;

	/**
	 *
	 */
	@GetMapping("/disc_lots/create/{inventoryId}")
	public String createDiscLot(@PathVariable(name = "inventoryId")
			Long inventoryId, Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("inventoryId", inventoryId);
		model.addAttribute("discLot", null);

		return "discLotActions";
	}

	/**
	 *
	 */
	@GetMapping("/disc_lots/manage/create/{inventoryId}")
	public String createDiscLot(DiscLot newDiscLot,
			@PathVariable(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "pDay") String pDay,
			@RequestParam(name = "pMonth") String pMonth,
			@RequestParam(name = "pYear") String pYear) {

		Inventory discLotInventory =
			inventoryServiceImpl.getInventoryById(inventoryId).getBody();
		Long branchOfficeId = discLotInventory.getBranchOffice().getId();
		newDiscLot.setPublicationDate(Date.valueOf(
					String.format("%s-%s-%s", pYear, pMonth, pDay)));
		newDiscLot.setDiscLotInventory(discLotInventory);
		discLotServiceImpl.createDiscLot(newDiscLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
				
	}

	/**
	 *
	 */
	@GetMapping("/disc_lots/{Id}")
	public String showDiscLot(@PathVariable(name = "Id") Long Id,
			Model model) {
		DiscLot discLot =
			discLotServiceImpl.getDiscLotById(Id).getBody();
		String[] pDate = DateManager.transformStringDate(
				discLot.getPublicationDate().toString());

		model.addAttribute("action", "get");
		model.addAttribute("discLot", discLot);
		model.addAttribute("pDate", pDate);

		return "discLotActions";
	}


	/**
	 *
	 */
	@GetMapping("/disc_lots/update/{Id}")
	public String updateDiscLot(@PathVariable(name = "Id") Long Id,
			Model model) {
		DiscLot discLot =
			discLotServiceImpl.getDiscLotById(Id).getBody();
		String[] pDate = DateManager.transformStringDate(
				discLot.getPublicationDate().toString());

		model.addAttribute("action", "put");
		model.addAttribute("discLot", discLot);
		model.addAttribute("pDate", pDate);

		return "discLotActions";
	}

	/**
	 *
	 */
	@GetMapping("/disc_lots/manage/update/{Id}")
	public String updateDiscLot(DiscLot updatedDiscLot,
			@PathVariable(name = "Id") Long Id,
			@RequestParam(name = "pDay") String pDay,
			@RequestParam(name = "pMonth") String pMonth,
			@RequestParam(name = "pYear") String pYear) {

		Long branchOfficeId = discLotServiceImpl.getDiscLotById(Id)
			.getBody().getDiscLotInventory().getBranchOffice().getId();
		updatedDiscLot.setPublicationDate(Date.valueOf(
					String.format("%s-%s-%s", pYear, pMonth, pDay)));
		discLotServiceImpl.updateDiscLotById(Id, updatedDiscLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);

	}

	/**
	 *
	 */
	@GetMapping("/disc_lots/manage/delete/{Id}")
	public String deleteDiscLot(@PathVariable(name = "Id") Long Id) {
		Long branchOfficeId =
			discLotServiceImpl.getDiscLotById(Id).getBody()
				.getDiscLotInventory().getBranchOffice().getId();
		discLotServiceImpl.deleteDiscLotById(Id);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}
}
