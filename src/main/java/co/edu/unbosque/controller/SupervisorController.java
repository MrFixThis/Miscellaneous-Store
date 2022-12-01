package co.edu.unbosque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.unbosque.entity.Supervisor;
import co.edu.unbosque.exception.SupervisorNotFoundException;
import co.edu.unbosque.service.impl.SupervisorServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class SupervisorController {

	private SupervisorServiceImpl supervisorServiceImpl;

	/**
	 * Shows the login page
	 *
	 * @param modelMap holder model for context model's attributes.
	 * @return the login page template view.
	 */
	@GetMapping("/")
	public String showLoginPage(ModelMap modelMap) {

		if(modelMap.getAttribute("isLoged") != null)
			modelMap.addAttribute("isLoged", false);
		else
			modelMap.addAttribute("isLoged", true);

		return "loginPage";
	}

	/**
	 * Shows the sign up page
	 *
	 * @return the sign up page template view.
	 */
	@GetMapping("/supervisor/create")
	public String showSignUpPage() {
		return "signUpPage";
	}

	/**
	 * Creates a new supervisor entity
	 *
	 * @param newSupervisor POJO with the information of the supervisor entity
	 * that is beeing created
	 * @return the specified template view.
	 */
	@GetMapping("/supervisor/manage/create")
	public String createSupervisor(Supervisor newSupervisor) {
		supervisorServiceImpl.createSupervisor(newSupervisor);

		return "redirect:/";
	}

	/**
	 * Authenticates a supervisor by its username and password
	 *
	 * @param modelMap holder model for context model's attributes.
	 * @param username username of the supervisor that is beeing authenticated
	 * @param password password of the supervisor that is beeing authenticated
	 * @return the login page template view.
	 */
	@GetMapping("/supervisor/manage/authenticate")
	public ModelAndView authenticateSupervisor(ModelMap modelMap,
			@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {

			try {
				supervisorServiceImpl
					.getSupervisorByUsernameAndPassword(username, password);
			} catch(SupervisorNotFoundException e) {
				modelMap.addAttribute("isLoged", false);
				return new ModelAndView("loginPage", modelMap);
			}

		return new ModelAndView("redirect:/branch_offices");
	}
}
