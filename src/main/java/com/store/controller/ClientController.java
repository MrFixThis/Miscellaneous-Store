package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/clients")
public class ClientController {

	/**
	 * Shows the Clients' home page.
	 *
	 * @param model context's attributes holder.
	 * @return the Client's home template.
	 */
	@GetMapping
	public String showClientsHomePage() {
		return "clients";
	}

	/**
	 * Shows the Clients' actions page.
	 *
	 * @param operation specifier of the operation to perform over the
	 * Client entity to manipulate.
	 * @param clientId id of the Client entity to manipulate.
	 * @return the Client's actions template.
	 */
	@GetMapping("/actions")
	public String showClientsActionsPage(
			@RequestParam(name = "operation", defaultValue = "inspect") String operation,
			@RequestParam(name = "clientId", required = false) Long clientId) {
		return "clientsActions";
	}
}
