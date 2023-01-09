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
	 * @param operation specifier of the operation to perform over the
	 * vinylRecordLot entity to manipulate.
	 * @param inventoryId id of the Inventory entity with which the VinylRecordLot
	 * entity being manipulated is related to.
	 * @param vinylRecordLotId id of the VinylRecordLot entity to manipulate.
	 * @return the VinylRecordLot's actions template.
	 */
	@GetMapping("/actions")
	public String showVinylRecordLotsActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "vinylRecordLotId", required = false) Long vinylRecordLotId) {
		return "vinylRecordLotsActions";
	}
}
