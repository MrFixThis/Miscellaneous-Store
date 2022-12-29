package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/book_lots")
public class BookLotController {

	/**
	 * Shows the BookLots' actions page.
	 *
	 * @param inventoryId id of the Inventory entity with which the BookLot
	 * entity being manipulated is related to.
	 * @param bookLotIsbn isbn of the BookLot entity to manipulate.
	 * @return the BookLot's actions template.
	 */
	@GetMapping("/actions")
	public String showBookLotsActionsPage(
			@RequestParam(name = "inventoryId") Long inventoryId,
			@RequestParam(name = "bookLotIsbn") Long bookLotIsbn) {
		return "bookLotsActions";
	}
}
