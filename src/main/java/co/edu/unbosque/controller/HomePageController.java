package co.edu.unbosque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bryan Baron
 */
@Controller
public class HomePageController {

	@GetMapping("/")
	public String homePage() {
		return "index";
	}
}
