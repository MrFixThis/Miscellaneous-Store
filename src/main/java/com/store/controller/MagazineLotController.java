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
	 * @param operation specifier of the operation to perform over the
	 * MagazineLot entity to manipulate.
	 * @param inventoryId id of the Inventory entity with which the MagazineLot
	 * entity being manipulated is related to.
	 * @param magazineLotIsbn isbn of the MagazineLot entity to manipulate.
	 * @return the MagazineLot's actions template.
	 */
	@GetMapping("/actions")
	public String showMagazineLotsActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "magazineLotIsbn", required = false) Long magazineLotIsbn) {
		return "magazineLotsActions";
	}
}
