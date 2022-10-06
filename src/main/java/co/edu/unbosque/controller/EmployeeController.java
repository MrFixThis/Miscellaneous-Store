package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.service.impl.AdministratorServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class EmployeeController {

	private AdministratorServiceImpl administratorServiceImpl;

	/**
	 * 
	 */
	@GetMapping("/administrators")
	public String showAdministrators(Model model) {
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();
		model.addAttribute("endpoint_sfx", "administrators");
		model.addAttribute("type", "administrator");
		model.addAttribute("administrators", administrators);

		return "employees";
	}
}
