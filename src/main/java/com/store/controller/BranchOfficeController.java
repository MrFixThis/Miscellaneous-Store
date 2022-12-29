package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping("/branch_offices")
public class BranchOfficeController {

	/**
	 * Shows the BranchOffices' home page.
	 *
	 * @return the BranchOffice's home template.
	 */
	@GetMapping
	public String showBranchOfficesHomePage() {
		return "branchOffices";
	}

	/**
	 * Shows the BranchOffices' actions page.
	 *
	 * @param branchOfficeId id of the BranchOffice entity to manipulate.
	 * @return the BranchOffice's actions template.
	 */
	@GetMapping("/actions")
	public String showBranchOfficesActionsPage(
			@RequestParam(name = "branchOfficeId") Long branchOfficeId) {
		return "branchOfficesActions";
	}
}
