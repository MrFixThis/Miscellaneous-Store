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
@RequestMapping(path = "/workers")
public class WorkerController {

	/**
	 * Shows the Workers' home page.
	 *
	 * @param model context's attributes holder.
	 * @return the Worker's home template.
	 */
	@GetMapping
	public String showWorkersHomePage(Model model) {
		model.addAttribute("employeeType", "worker");
		return "employees";
	}

	/**
	 * Shows the Workers' actions page.
	 *
	 * @param operation specifier of the operation to perform over the
	 * Worker entity to manipulate.
	 * @param workerId id of the Worker entity to manipulate.
	 * @return the Worker's actions template.
	 */
	@GetMapping("/actions")
	public String showWorkersActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "workerId") Long workerId) {
		return "employeesActions";
	}
}
