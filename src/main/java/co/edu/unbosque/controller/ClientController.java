package co.edu.unbosque.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.entity.Client;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import co.edu.unbosque.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class ClientController {

	private ClientServiceImpl clientServiceImpl;
	private BranchOfficeServiceImpl branchOfficeServiceImpl;

	/**
	 *
	 */
	@GetMapping("/clients/create")
	public String createClient(Model model) {
		model.addAttribute("action", "post");
		model.addAttribute("client", null);

		return "clientActions";
	}

	/**
	 *
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
	 *
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
	 *
	 */
	@GetMapping("/clients")
	public String showClients(Model model) {
		List<Client> clients = clientServiceImpl.getClients().getBody();

		model.addAttribute("action", "get");
		model.addAttribute("clients", clients);

		return "clients";
	}

	/**
	 *
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
	 *
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
	 *
	 */
	@PostMapping("/clients/manage/delete/{id}")
	public String deleteClient(@PathVariable(name = "id") Long id) {
		clientServiceImpl.deleteClientById(id);
		return "redirect:/clients";
	}
}
