package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bryan Baron
 */
@Controller
@RequestMapping(path = "/")
public class HomeController {

	/**
	 * Shows the Sign In page.
	 *
	 * @return the Sign In template.
	 */
	@GetMapping
	public String showSignInPage() {
		return "signInPage";
	}

	/**
	 * Shows the Sign Up page.
	 *
	 * @return the Sign Up template.
	 */
	@GetMapping("signUp")
	public String showSignUpPage() {
		return "signUpPage";
	}
}
