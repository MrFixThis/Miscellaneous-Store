package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/administrators")
public class AdministratorController {

	/**
	 * Shows the Administrators' home page.
	 *
	 * @param model context's attributes holder.
	 * @return the Administrator's home template.
	 */
	@GetMapping
	public String showAdministratorsHomePage(Model model) {
		model.addAttribute("employeeType", "administrator");
		return "employees";
	}

	/**
	 * Shows the Administrators' actions page.
	 *
	 * @param operation specifier of the operation to perform over the
	 * Administrator entity to manipulate.
	 * @param administratorId id of the Administrator entity to manipulate.
	 * @return the Administrator's actions template.
	 */
	@GetMapping("/actions")
	public String showAdministratorsActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "administratorId", required = false) Long administratorId) {
		return "employeesActions";
	}
}
