package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.unbosque.bean.BirthDate;
import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.entity.Client;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import co.edu.unbosque.service.impl.ClientServiceImpl;
import co.edu.unbosque.util.DateManager;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class ClientController {

	private BranchOfficeServiceImpl branchOfficeServiceImpl;
	private ClientServiceImpl clientServiceImpl;

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
	public String createClient(Client newClient, BirthDate birthDate) {
		newClient.setDateOfBirth(DateManager.createSQLDate(birthDate));
		clientServiceImpl.createClient(newClient);

		return "redirect:/clients";
	}

	/**
	 * 
	 */
	@GetMapping("/clients/{id}")
	public String showClient(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Client client = clientServiceImpl.getClientById(id).getBody();
		List<BranchOffice> branchOffices =
			branchOfficeServiceImpl.getBranchOffices().getBody();
		BirthDate bDate = DateManager.transformStringDate(client.getDateOfBirth()
				.toString(), new BirthDate());

		model.addAttribute("action", "get");
		model.addAttribute("client", client);
		model.addAttribute("branchOffices", branchOffices);
		model.addAttribute("bDate", bDate);

		return "clientActions";
	}

	/**
	 * 
	 */
	@GetMapping("/clients")
	public String showClients(Model model) {
		List<Client> clients = clientServiceImpl.getClients().getBody();
		model.addAttribute("clients", clients);

		return "clients";
	}

	/**
	 * 
	 */
	@GetMapping("/clients/update/{id}")
	public String updateClient(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Client client = clientServiceImpl.getClientById(id).getBody();
		BirthDate bDate = DateManager.transformStringDate(client.getDateOfBirth()
				.toString(), new BirthDate());

		model.addAttribute("action", "put");
		model.addAttribute("client", client);
		model.addAttribute("bDate", bDate);

		return "clientActions";
	}

	/**
	 * 
	 */
	@GetMapping("/clients/manage/update/{id}")
	public String updateClient( @PathVariable(value = "id", required = true)
			Long id, Client updatedClient, BirthDate birthDate) {
		updatedClient.setDateOfBirth(DateManager.createSQLDate(birthDate));
		clientServiceImpl.updateClientById(id, updatedClient);

		return String.format("redirect:/clients/%d", updatedClient.getId());
	}

	/**
	 * 
	 */
	@PostMapping("/clients/manage/delete/{id}")
	public String deleteClient(
			@PathVariable(value = "id", required = true) Long id) {
		clientServiceImpl.deleteClientById(id);
		return "redirect:/clients";
	}
}
