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
	 * @param operation specifier of the operation to perform over the
	 * BranchOffice entity to manipulate.
	 * @param branchOfficeId id of the BranchOffice entity to manipulate.
	 * @return the BranchOffice's actions template.
	 */
	@GetMapping("/actions")
	public String showBranchOfficesActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "branchOfficeId", required = false) Long branchOfficeId) {
		return "branchOfficesActions";
	}
}
