package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/disc_lots")
public class DiscLotController {

	/**
	 * Shows the DiscLots' actions page.
	 *
	 * @param inventoryId id of the Inventory entity with which the DiscLot
	 * entity being manipulated is related to.
	 * @param discLotId id of the DiscLot entity to manipulate.
	 * @return the DiscLot's actions template.
	 */
	@GetMapping("/actions")
	public String showDiscLotsActionsPage(
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "discLotId") Long discLotId) {
		return "discLotsActions";
	}
}
