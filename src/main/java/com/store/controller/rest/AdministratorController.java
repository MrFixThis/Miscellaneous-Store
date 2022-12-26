package com.store.controller.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.model.Administrator;
import com.store.service.AdministratorService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class AdministratorController {

	private AdministratorService administratorServiceImpl;

	/**
	 * Creates a new administrator
	 *
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
	 */
	@GetMapping("/administrators/create")
	public String createAdministrator(Model model) {
		model.addAttribute("type", "administrator");
		model.addAttribute("action", "post");
		model.addAttribute("employee", null);

		return "employeeActions";
	}

	/**
	 * Creates a new administrator entity
	 *
	 * @param newAdministrator POJO with the information of the administrator
	 * entity that is beeing created.
	 * @param birthDate date of brith of the administrator entity beeing created
	 * @param hireDate date of hire of the administrator entity beeing created
	 * @return the specified template view
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
	 * Retrieves an administrator entity specified by id
	 *
	 * @param id id of the administrator entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
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
	 * Retrieves all the existent administrator entities
	 *
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/administrators")
	public String showAdministrators(Model model) {
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "get");
		model.addAttribute("employees", administrators);

		return "employees";
	}

	/**
	 * Updates an administrator entity specified by id
	 *
	 * @param id id of the administrator entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
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
	 * Updates an administrator entity specified by id
	 *
	 * @param updatedAdministrator POJO with the information of the
	 * administrator entity that is beeing updated.
	 * @param id id of the administrator entity that is beeing updated
	 * @param birthDate date of brith of the administrator entity beeing updated
	 * @param hireDate date of hire of the administrator entity beeing updated
	 * @return the specified template view
	 */
	@GetMapping("/administrators/manage/update/{id}")
	public String updateAdministrator(Administrator updatedAdministrator,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "birthDate") String birthDate,
			@RequestParam(name = "hireDate") String hireDate) {

		updatedAdministrator.setDateOfBirth(Date.valueOf(birthDate));
		updatedAdministrator.setDateOfHire(Date.valueOf(hireDate));
		administratorServiceImpl.updateAdministratorById(id, updatedAdministrator);

		return String.format("redirect:/administrators/%d", id);
	}

	/**
	 * Deletes an administrator entity specified by id
	 *
	 * @param id id of the administrator entity that is beeing deleted
	 * @return the specified template view
	 */
	@PostMapping("/administrators/manage/delete/{id}")
	public String deleteAdministrator(@PathVariable(name = "id") Long id) {
		administratorServiceImpl.deleteAdministratorById(id);
		return "redirect:/administrators";
	}
}
