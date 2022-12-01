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
	 * Creates a new magazine lot entity
	 *
	 * @param inventoryId id of the inventory where the magazine lot that is beeing
	 * created will be placed
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
	 */
	@GetMapping("/magazine_lots/create/inventory={inventoryId}")
	public String createMagazineLot(@PathVariable(name = "inventoryId")
			Long inventoryId, Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("inventoryId", inventoryId);
		model.addAttribute("magazineLot", null);

		return "magazineLotActions";
	}

	/**
	 * Creates a new magazine lot entity
	 *
	 * @param newMagazineLot POJO with the information of the magazine lot
	 * entity that is beeing created.
	 * @param inventoryId id of the inventory where the magazine lot that is beeing
	 * created will be placed
	 * @return the specified template view.
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
	 * Retrieves a magazine lot entity specified by isbn
	 *
	 * @param isbn isbn of the magazine lot entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
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
	 * Updates a magazine lot entity specified by isbn
	 *
	 * @param isbn isbn of the magazine lot entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
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
	 * Updates a new magazine lot entity specified by isbn
	 *
	 * @param updatedMagazineLot POJO with the information of the magazine lot
	 * entity that is beeing udpated.
	 * @param isbn isbn of the magazine lot entity that is beeing updated
	 * @return the specified template view.
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
	 * Deletes a magazine lot entity specified by isbn
	 *
	 * @param isbn isbn of the magazine lot entity that is beeing deleted
	 * @return the specified template view
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
