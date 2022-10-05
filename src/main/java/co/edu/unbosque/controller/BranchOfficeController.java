package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.service.impl.AdministratorServiceImpl;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class BranchOfficeController {

	BranchOfficeServiceImpl branchOfficeServiceImpl;
	AdministratorServiceImpl administratorServiceImpl;

	/**
	 * 
	 */
	@GetMapping({"/", "/branch_offices"})
	public String showBranchOffices(Model model) {
		List<BranchOffice> branchOffices =
			branchOfficeServiceImpl.getBranchOffices().getBody();
		model.addAttribute("branchOffices", branchOffices);

		return "branchOffices";
	}

	@GetMapping("/branch_offices/new")
	public String registerNewBranchOffice(Model model) {
		List<Administrator> administrators =
			administratorServiceImpl.getAdministrators().getBody();
		model.addAttribute("administrators", administrators);

		Administrator a = new Administrator();
		a.setId(1L);
		a.setFirstName("Bryan");
		a.setPaternalLastName("Baronesuhasoetu");

		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);
		administrators.add(a);

		return "branchOfficeActions";
	}
}
