package com.store.controller.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.model.BranchOffice;
import com.store.model.Client;
import com.store.service.BranchOfficeService;
import com.store.service.ClientService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class ClientController {

	private ClientService clientServiceImpl;
	private BranchOfficeService branchOfficeServiceImpl;

	/**
	 * Creates a new client
	 *
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
	 */
	@GetMapping("/clients/create")
	public String createClient(Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("client", null);

		return "clientActions";
	}

	/**
	 * Creates a new client entity
	 *
	 * @param newClient POJO with the information of the client entity that is
	 * beeing created.
	 * @param birthDate date of brith of the client entity beeing created
	 * @return the specified template view
	 */
	@GetMapping("/clients/manage/create")
	public String createClient(Client newClient,
			@RequestParam(name = "birthDate") String birthDate) {

		Integer newClientPN = newClient.getPurchasesNumber();
		newClient.setDateOfBirth(Date.valueOf(birthDate));
		newClient.setPurchasesNumber(newClientPN == null ? 0 : newClientPN);
		clientServiceImpl.createClient(newClient);

		return "redirect:/clients";
	}

	/**
	 * Retrieves a client entity specified by id
	 *
	 * @param id id of the client entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/clients/{id}")
	public String showClient(@PathVariable(name = "id") Long id, Model model) {
		Client client = clientServiceImpl.getClientById(id).getBody();
		List<BranchOffice> cBranchOffices =
			branchOfficeServiceImpl.getBranchOfficesByClientsId(id).getBody();

		model.addAttribute("action", "get");
		model.addAttribute("client", client);
		model.addAttribute("cBranchOffices", cBranchOffices);

		return "clientActions";
	}

	/**
	 * Retrieves all the existent client entities
	 *
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/clients")
	public String showClients(Model model) {
		List<Client> clients = clientServiceImpl.getClients().getBody();

		model.addAttribute("action", "get");
		model.addAttribute("clients", clients);

		return "clients";
	}

	/**
	 * Updates a client entity specified by id
	 *
	 * @param id id of the client entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/clients/update/{id}")
	public String updateClient(@PathVariable(name = "id") Long id,
			Model model) {
		Client client = clientServiceImpl.getClientById(id).getBody();

		model.addAttribute("action", "put");
		model.addAttribute("client", client);

		return "clientActions";
	}

	/**
	 * Updates a client entity specified by id
	 *
	 * @param updatedClient POJO with the information of the client entity
	 * that is beeing updated.
	 * @param id id of the client entity that is beeing updated
	 * @param birthDate date of brith of the client entity beeing updated
	 * @return the specified template view
	 */
	@GetMapping("/clients/manage/update/{id}")
	public String updateClient(Client updatedClient,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "birthDate") String birthDate) {

		updatedClient.setDateOfBirth(Date.valueOf(birthDate));
		clientServiceImpl.updateClientById(id, updatedClient);

		return String.format("redirect:/clients/%d", id);
	}

	/**
	 * Deletes a client entity specified by id
	 *
	 * @param id id of the client entity that is beeing deleted
	 * @return the specified template view
	 */
	@PostMapping("/clients/manage/delete/{id}")
	public String deleteClient(@PathVariable(name = "id") Long id) {
		clientServiceImpl.deleteClientById(id);
		return "redirect:/clients";
	}
}
