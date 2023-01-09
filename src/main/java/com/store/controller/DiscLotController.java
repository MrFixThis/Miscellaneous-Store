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
	 * @param operation specifier of the operation to perform over the
	 * DiscLot entity to manipulate.
	 * @param inventoryId id of the Inventory entity with which the DiscLot
	 * entity being manipulated is related to.
	 * @param discLotId id of the DiscLot entity to manipulate.
	 * @return the DiscLot's actions template.
	 */
	@GetMapping("/actions")
	public String showDiscLotsActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "discLotId", required = false) Long discLotId) {
		return "discLotsActions";
	}
}
