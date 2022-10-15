package co.edu.unbosque.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.unbosque.entity.Inventory;
import co.edu.unbosque.entity.MagazineLot;
import co.edu.unbosque.service.impl.InventoryServiceImpl;
import co.edu.unbosque.service.impl.MagazineLotServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class MagazineLotController {

	private MagazineLotServiceImpl magazineLotServiceImpl;
	private InventoryServiceImpl inventoryServiceImpl;

	/**
	 *
	 *
	 */
	@GetMapping("/magazine_lots/create/{inventoryId}")
	public String createMagazineLot(@PathVariable(name = "inventoryId")
			Long inventoryId, Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("inventoryId", inventoryId);
		model.addAttribute("magazineLot", null);

		return "magazineLotActions";
	}

	/**
	 *
	 */
	@GetMapping("/magazine_lots/manage/create/{inventoryId}")
	public String createMagazineLot(MagazineLot newMagazineLot,
			@PathVariable(name = "inventoryId") Long inventoryId) {
		Inventory magazineLotInventory =
			inventoryServiceImpl.getInventoryById(inventoryId).getBody();
		Long branchOfficeId = magazineLotInventory.getBranchOffice().getId();
		newMagazineLot.setMagazineLotInventory(magazineLotInventory);
		magazineLotServiceImpl.createMagazineLot(newMagazineLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
				
	}

	/**
	 *
	 */
	@GetMapping("/magazine_lots/{isbn}")
	public String showMagazineLot(@PathVariable(name = "isbn") UUID isbn,
			Model model) {
		MagazineLot magazineLot =
			magazineLotServiceImpl.getMagazineLotByIsbn(isbn).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("magazineLot", magazineLot);

		return "magazineLotActions";
	}


	/**
	 *
	 */
	@GetMapping("/magazine_lots/update/{isbn}")
	public String updateMagazineLot(@PathVariable(name = "isbn") UUID isbn,
			Model model) {
		MagazineLot magazineLot =
			magazineLotServiceImpl.getMagazineLotByIsbn(isbn).getBody();

		model.addAttribute("action", "put");
		model.addAttribute("magazineLot", magazineLot);

		return "magazineLotActions";
	}

	/**
	 *
	 */
	@GetMapping("/magazine_lots/manage/update/{isbn}")
	public String updateMagazineLot(MagazineLot updatedMagazineLot,
			@PathVariable(name = "isbn") UUID isbn) {
		Long branchOfficeId = magazineLotServiceImpl.getMagazineLotByIsbn(isbn)
			.getBody().getMagazineLotInventory().getBranchOffice().getId();
		magazineLotServiceImpl.updateMagazineLotByIsbn(isbn, updatedMagazineLot);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);

	}

	/**
	 *
	 */
	@GetMapping("/magazine_lots/manage/delete/{isbn}")
	public String deleteMagazineLot(@PathVariable(name = "isbn") UUID isbn) {
		Long branchOfficeId =
			magazineLotServiceImpl.getMagazineLotByIsbn(isbn).getBody()
				.getMagazineLotInventory().getBranchOffice().getId();
		magazineLotServiceImpl.deleteMagazineLotByIsbn(isbn);

		return String.format("redirect:/branch_offices/update/%d",
				branchOfficeId);
	}
}
