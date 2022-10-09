package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.service.impl.AdministratorServiceImpl;
import co.edu.unbosque.service.impl.WorkerServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class EmployeeController {

	private AdministratorServiceImpl administratorServiceImpl;
	private WorkerServiceImpl workerServiceImpl;

	/**
	 * 
	 */
	@GetMapping("/administrators/create")
	public String createAdministrator(Model model) {
		Administrator newAdmin = new Administrator();
		model.addAttribute("type", "administrator");
		model.addAttribute("action", "post");
		model.addAttribute("employee", newAdmin);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@PostMapping("/administrators/manage/create")
	public String createAdministrator(Model model,
			Administrator newAdministrator) {
		administratorServiceImpl.createAdministrator(newAdministrator);
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();

		model.addAttribute("type", "administrator");
		model.addAttribute("employees", administrators);

		return "employees";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/{id}")
	public String showAdministrator(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Administrator administrator = new Administrator();
			// administratorServiceImpl.getAdministratorById(id).getBody();

		administrator.setId(1L);
		administrator.setFirstName("Bryan");
		administrator.setMiddleName("Sneyder");
		administrator.setPaternalLastName("Baron");
		administrator.setMaternalLastName("Murcia");
		administrator.setIdentificationNumber("1000714326");
		administrator.setIdentificationType("Cedula");
		administrator.setPhoneNumber("3007011151");
		administrator.setEmailAddress("Bryan.baron55@gmail.com");
		administrator.setRole("Project Administrator");
		administrator.setBasicSalary(5_000_000L);

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

		Administrator b = new Administrator();
		b.setId(1L);
		b.setFirstName("Bryan");
		b.setPaternalLastName("Baron");
		b.setEmailAddress("Bryan.baron55@gmail.com");

		Administrator d = new Administrator();
		d.setId(2L);
		d.setFirstName("Dilan");
		d.setPaternalLastName("Baron");
		d.setEmailAddress("Dilan.baron79@gmail.com");

		administrators.add(b);
		administrators.add(d);

		return "employees";
	}

	/**
	 * 
	 */
	@GetMapping("/administrators/update/{id}")
	public String updateAdministrator(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Administrator administrator = new Administrator();
			// administratorServiceImpl.getAdministratorById(id).getBody();

		administrator.setId(1L);
		administrator.setFirstName("Bryan");
		administrator.setMiddleName("Sneyder");
		administrator.setPaternalLastName("Baron");
		administrator.setMaternalLastName("Murcia");
		administrator.setIdentificationNumber("1000714326");
		administrator.setIdentificationType("Cedula");
		administrator.setPhoneNumber("3007011151");
		administrator.setEmailAddress("Bryan.baron55@gmail.com");
		administrator.setRole("Project Administrator");
		administrator.setBasicSalary(5_000_000L);

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "put");
		model.addAttribute("employee", administrator);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@PostMapping("/administrators/manage/update/{id}")
	public String updateAdministrator(
			@PathVariable(value = "id", required = true) Long id,
			Model model, Administrator updatedAdministrator) {
		Administrator updateAdministratorResponse =
			administratorServiceImpl.updateAdministratorById(id, updatedAdministrator)
				.getBody();

		model.addAttribute("type", "administrator");
		model.addAttribute("action", "get");
		model.addAttribute("employee", updateAdministratorResponse);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@PostMapping("/administrators/manage/delete/{id}")
	public String deleteAdministrator(
			@PathVariable(value = "id", required = true) Long id) {
		administratorServiceImpl.deleteAdministratorById(id);
		return "employees";
	}
}
