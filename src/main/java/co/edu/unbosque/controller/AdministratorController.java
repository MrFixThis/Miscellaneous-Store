package co.edu.unbosque.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.service.impl.AdministratorServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class AdministratorController {

	private AdministratorServiceImpl administratorServiceImpl;

	/**
	 * 
	 * 
	 */
	@GetMapping("/administrators/create")
	public String createAdministrator(Model model) {
		model.addAttribute("type", "administrator");
		model.addAttribute("action", "post");
		model.addAttribute("employee", null);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/manage/create")
	public String createAdministrator(Administrator newAdministrator,
			@RequestParam(name = "birthDate") String birthDate,
			@RequestParam(name = "hireDate") String hireDate) {

		newAdministrator.setDateOfBirth(Date.valueOf(birthDate));
		newAdministrator.setDateOfHire(Date.valueOf(hireDate));
		administratorServiceImpl.createAdministrator(newAdministrator);

		return "redirect:/administrators";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/{id}")
	public String showAdministrator(@PathVariable(name = "id") Long id,
			Model model) {
		Administrator administrator =
			administratorServiceImpl.getAdministratorById(id).getBody();

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "get");
		model.addAttribute("employee", administrator);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators")
	public String showAdministrators(Model model) {
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();
		model.addAttribute("type", "administrator");
		model.addAttribute("employees", administrators);

		return "employees";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/update/{id}")
	public String updateAdministrator(@PathVariable(name = "id") Long id,
			Model model) {
		Administrator administrator =
			administratorServiceImpl.getAdministratorById(id).getBody();

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "put");
		model.addAttribute("employee", administrator);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/manage/update/{id}")
	public String updateAdministrator(Administrator updatedAdministrator,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "birthDate") String birthDate,
			@RequestParam(name = "hireDate") String hireDate) {

		updatedAdministrator.setDateOfBirth(Date.valueOf(birthDate));
		updatedAdministrator.setDateOfHire(Date.valueOf(hireDate));
		administratorServiceImpl.updateAdministratorById(id, updatedAdministrator);

		return String.format("redirect:/administrators/%d",
				updatedAdministrator.getId());
	}

	/**
	 * 
	 */
	@PostMapping("/administrators/manage/delete/{id}")
	public String deleteAdministrator(@PathVariable(name = "id") Long id) {
		administratorServiceImpl.deleteAdministratorById(id);
		return "redirect:/administrators";
	}
}
