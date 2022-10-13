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
import co.edu.unbosque.util.DateManager;
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
			@RequestParam(name = "bDay") String bDay,
			@RequestParam(name = "bMonth") String bMonth,
			@RequestParam(name = "bYear") String bYear,
			@RequestParam(name = "hDay") String hDay,
			@RequestParam(name = "hMonth") String hMonth,
			@RequestParam(name = "hYear") String hYear) {

		newAdministrator.setDateOfBirth(Date.valueOf(
					String.format("%s-%s-%s", bYear, bMonth, bDay)));
		newAdministrator.setDateOfHire(Date.valueOf(
					String.format("%s-%s-%s", hYear, hMonth, hDay)));
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
		String[] bDate = DateManager.transformStringDate(administrator
				.getDateOfBirth().toString());
		String[] hDate = DateManager.transformStringDate(administrator
				.getDateOfHire().toString());

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "get");
		model.addAttribute("employee", administrator);
		model.addAttribute("bDate", bDate);
		model.addAttribute("hDate", hDate);

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
		String[] bDate = DateManager.transformStringDate(administrator
				.getDateOfBirth().toString());
		String[] hDate = DateManager.transformStringDate(administrator
				.getDateOfHire().toString());

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "put");
		model.addAttribute("employee", administrator);
		model.addAttribute("bDate", bDate);
		model.addAttribute("hDate", hDate);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/manage/update/{id}")
	public String updateAdministrator(Administrator updatedAdministrator,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "bDay") String bDay,
			@RequestParam(name = "bMonth") String bMonth,
			@RequestParam(name = "bYear") String bYear,
			@RequestParam(name = "hDay") String hDay,
			@RequestParam(name = "hMonth") String hMonth,
			@RequestParam(name = "hYear") String hYear) {

		updatedAdministrator.setDateOfBirth(Date.valueOf(
					String.format("%s-%s-%s", bYear, bMonth, bDay)));
		updatedAdministrator.setDateOfHire(Date.valueOf(
					String.format("%s-%s-%s", hYear, hMonth, hDay)));
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
