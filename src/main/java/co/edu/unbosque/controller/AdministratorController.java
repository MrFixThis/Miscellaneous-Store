package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.unbosque.bean.BirthDate;
import co.edu.unbosque.bean.HireDate;
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
			BirthDate birthDate, HireDate hireDate) {
		newAdministrator.setDateOfBirth(DateManager.createSQLDate(birthDate));
		newAdministrator.setDateOfHire(DateManager.createSQLDate(hireDate));
		administratorServiceImpl.createAdministrator(newAdministrator);

		return "redirect:/administrators";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/{id}")
	public String showAdministrator(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Administrator administrator =
			administratorServiceImpl.getAdministratorById(id).getBody();
		BirthDate bDate = DateManager.transformStringDate(
				administrator.getDateOfBirth().toString(), new BirthDate());
		HireDate hDate = DateManager.transformStringDate(
				administrator.getDateOfHire() .toString(), new HireDate());

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
	public String updateAdministrator(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Administrator administrator =
			administratorServiceImpl.getAdministratorById(id).getBody();
		BirthDate bDate = DateManager.transformStringDate(
				administrator.getDateOfBirth().toString(), new BirthDate());
		HireDate hDate = DateManager.transformStringDate(
				administrator.getDateOfHire() .toString(), new HireDate());

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
	public String updateAdministrator(@PathVariable(value = "id", required = true)
			Long id, Administrator updatedAdministrator, BirthDate birthDate,
			HireDate hireDate) {
		updatedAdministrator.setDateOfBirth(DateManager.createSQLDate(birthDate));
		updatedAdministrator.setDateOfHire(DateManager.createSQLDate(hireDate));
		administratorServiceImpl.updateAdministratorById(id, updatedAdministrator);

		return String.format("redirect:/administrators/%d",
				updatedAdministrator.getId());
	}

	/**
	 * 
	 */
	@PostMapping("/administrators/manage/delete/{id}")
	public String deleteAdministrator(@PathVariable(value = "id", required = true)
			Long id) {
		administratorServiceImpl.deleteAdministratorById(id);
		return "redirect:/administrators";
	}
}
