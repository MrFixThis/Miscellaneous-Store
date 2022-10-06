package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.unbosque.entity.Administrator;
import co.edu.unbosque.entity.Worker;
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
	@GetMapping("/administrators/{id}")
	public String showAdministrator(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Administrator administrator = new Administrator();
			// administratorServiceImpl.getAdministratorById(id).getBody();

		administrator.setId(1L);
		administrator.setFirstName("Bryan");
		administrator.setPaternalLastName("Baron");
		administrator.setEmailAddress("Bryan.baron55@gmail.com");


		model.addAttribute("type", "administrator");
		model.addAttribute("action", "get");
		model.addAttribute("employee", administrator);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/workers")
	public String showWorkers(Model model) {
		List<Worker> workers =
			workerServiceImpl.getWorkers().getBody();
		model.addAttribute("type", "worker");
		model.addAttribute("employees", workers);

		Worker b = new Worker();
		b.setId(1L);
		b.setFirstName("Bryan");
		b.setPaternalLastName("Baron");
		b.setEmailAddress("Bryan.baron55@gmail.com");

		Worker d = new Worker();
		d.setId(2L);
		d.setFirstName("Dilan");
		d.setPaternalLastName("Baron");
		d.setEmailAddress("Dilan.baron79@gmail.com");

		workers.add(b);
		workers.add(d);

		return "employees";
	}

	/**
	 * 
	 */
	@GetMapping("/workers/{id}")
	public String showWorker(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Worker worker = new Worker();
			// workerServiceImpl.getWorkerById(id).getBody();

		worker.setId(1L);
		worker.setFirstName("Bryan");
		worker.setPaternalLastName("Baron");
		worker.setEmailAddress("Bryan.baron55@gmail.com");


		model.addAttribute("type", "worker");
		model.addAttribute("action", "post");
		model.addAttribute("employee", worker);

		return "employeeActions";
	}
}
