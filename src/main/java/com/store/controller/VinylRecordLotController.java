package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/vinylRecord_lots")
public class VinylRecordLotController {

	/**
	 * Shows the VinylRecordLots' actions page.
	 *
	 * @param inventoryId id of the Inventory entity with which the VinylRecordLot
	 * entity being manipulated is related to.
	 * @param vinylRecordLotId id of the VinylRecordLot entity to manipulate.
	 * @return the VinylRecordLot's actions template.
	 */
	@GetMapping("/actions")
	public String showVinylRecordLotsActionsPage(
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "vinylRecordLotId") Long vinylRecordLotId) {
		return "vinylRecordLotsActions";
	}
}
