package com.store.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.model.BranchOffice;
import com.store.model.Worker;
import com.store.service.impl.BranchOfficeServiceImpl;
import com.store.service.impl.WorkerServiceImpl;

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
	 * Creates a new worker
	 *
	 * @param model holder model for context model's attributes.
	 * @return the specified template view.
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
	 * Creates a new worker entity
	 *
	 * @param newWorker POJO with the information of the worker entity that is
	 * beeing created.
	 * @param branchOfficeId id of the branch office where will work the
	 * worker entity that is beeing created
	 * @param birthDate date of brith of the worker entity beeing created
	 * @param hireDate date of hire of the worker entity beeing created
	 * @return the specified template view
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
	 * Retrieves a worker entity specified by id
	 *
	 * @param id id of the worker entity that is beeing searched
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
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
	 * Retrieves all the existent worker entities
	 *
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
	 */
	@GetMapping("/workers")
	public String showWorkers(Model model) {
		List<Worker> workers = workerServiceImpl.getWorkers().getBody();

		model.addAttribute("type", "worker");
		model.addAttribute("action", "get");
		model.addAttribute("employees", workers);

		return "employees";
	}

	/**
	 * Updates a worker entity specified by id
	 *
	 * @param id id of the worker entity that is beeing updated
	 * @param model holder model for context model's attributes.
	 * @return the specified template view
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
	 * Updates a worker entity specified by id
	 *
	 * @param updatedWorker POJO with information of the worker entity that is
	 * beeing updated.
	 * @param id id of the worker entity that is beeing updated
	 * @param branchOfficeId id of the branch office where will work the
	 * worker entity that is beeing updated
	 * @param birthDate date of brith of the worker entity beeing updated
	 * @param hireDate date of hire of the worker entity beeing updated
	 * @return the specified template view
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

		return String.format("redirect:/workers/%d", id);
	}

	/**
	 * Deletes a worker entity specified by id
	 *
	 * @param id id of the worker entity that is beeing deleted
	 * @return the specified template view
	 */
	@PostMapping("/workers/manage/delete/{id}")
	public String deleteWorker(@PathVariable(name = "id") Long id) {
		workerServiceImpl.deleteWorkerById(id);
		return "redirect:/workers";
	}
}
