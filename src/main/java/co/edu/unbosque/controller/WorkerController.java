package co.edu.unbosque.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.entity.BranchOffice;
import co.edu.unbosque.entity.Worker;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import co.edu.unbosque.service.impl.WorkerServiceImpl;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Controller
@AllArgsConstructor
public class WorkerController {

	private WorkerServiceImpl workerServiceImpl;
	private BranchOfficeServiceImpl branchOfficeServiceImpl;

	/**
	 *
	 */
	@GetMapping("/workers/create")
	public String createWorker(Model model) {
		List<BranchOffice> branchOffices = branchOfficeServiceImpl
			.getBranchOffices().getBody();

		model.addAttribute("type", "worker");
		model.addAttribute("action", "post");
		model.addAttribute("employee", null);
		model.addAttribute("branchOffices", branchOffices);

		return "employeeActions";
	}

	/**
	 *
	 */
	@GetMapping("/workers/manage/create")
	public String createWorker(Worker newWorker,
			@RequestParam(name = "branchOfficeId") Long branchOfficeId,
			@RequestParam(name = "birthDate") String birthDate,
			@RequestParam(name = "hireDate") String hireDate) {

		BranchOffice workerBranchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(branchOfficeId).getBody();

		newWorker.setDateOfBirth(Date.valueOf(birthDate));
		newWorker.setDateOfHire(Date.valueOf(hireDate));
		newWorker.setBranchOffice(workerBranchOffice);
		workerServiceImpl.createWorker(newWorker);

		return "redirect:/workers";
	}

	/**
	 *
	 */
	@GetMapping("/workers/{id}")
	public String showWorker(@PathVariable(name = "id") Long id, Model model) {
		Worker worker = workerServiceImpl.getWorkerById(id).getBody();

		model.addAttribute("type", "worker");
		model.addAttribute("action", "get");
		model.addAttribute("employee", worker);

		return "employeeActions";
	}

	/**
	 *
	 */
	@GetMapping("/workers")
	public String showWorkers(Model model) {
		List<Worker> workers = workerServiceImpl.getWorkers().getBody();
		model.addAttribute("type", "worker");
		model.addAttribute("employees", workers);

		return "employees";
	}

	/**
	 *
	 */
	@GetMapping("/workers/update/{id}")
	public String updateWorker(@PathVariable(name = "id") Long id,
			Model model) {
		List<BranchOffice> branchOffices = branchOfficeServiceImpl
			.getBranchOffices().getBody();
		Worker worker = workerServiceImpl.getWorkerById(id).getBody();

		model.addAttribute("type", "worker");
		model.addAttribute("action", "put");
		model.addAttribute("employee", worker);
		model.addAttribute("branchOffices", branchOffices);

		return "employeeActions";
	}

	/**
	 *
	 */
	@GetMapping("/workers/manage/update/{id}")
	public String updateWorker(Worker updatedWorker,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "branchOfficeId") Long branchOfficeId,
			@RequestParam(name = "birthDate") String birthDate,
			@RequestParam(name = "hireDate") String hireDate) {

		BranchOffice workerNewBranchOffice =
			branchOfficeServiceImpl.getBranchOfficeById(branchOfficeId).getBody();

		updatedWorker.setDateOfBirth(Date.valueOf(birthDate));
		updatedWorker.setDateOfHire(Date.valueOf(hireDate));
		updatedWorker.setBranchOffice(workerNewBranchOffice);
		workerServiceImpl.updateWorkerById(id, updatedWorker);

		return String.format("redirect:/workers/%d", updatedWorker.getId());
	}

	/**
	 *
	 */
	@PostMapping("/workers/manage/delete/{id}")
	public String deleteWorker(@PathVariable(name = "id") Long id) {
		workerServiceImpl.deleteWorkerById(id);
		return "redirect:/workers";
	}
}
