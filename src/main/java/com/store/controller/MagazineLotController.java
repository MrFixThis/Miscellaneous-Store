package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/magazine_lots")
public class MagazineLotController {

	/**
	 * Shows the MagazineLots' actions page.
	 *
	 * @param inventoryId id of the Inventory entity with which the MagazineLot
	 * entity being manipulated is related to.
	 * @param magazineLotIsbn isbn of the MagazineLot entity to manipulate.
	 * @return the MagazineLot's actions template.
	 */
	@GetMapping("/actions")
	public String showMagazineLotsActionsPage(
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "magazineLotIsbn") Long magazineLotIsbn) {
		return "magazineLotsActions";
	}
}
