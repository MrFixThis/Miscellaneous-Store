package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.unbosque.bean.BirthDate;
import co.edu.unbosque.bean.DataPlaceHolder;
import co.edu.unbosque.bean.HireDate;
import co.edu.unbosque.entity.Worker;
import co.edu.unbosque.service.impl.BranchOfficeServiceImpl;
import co.edu.unbosque.service.impl.WorkerServiceImpl;
import co.edu.unbosque.util.DateManager;
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
		int boQuantity = branchOfficeServiceImpl
			.getBranchOffices().getBody().size();
		model.addAttribute("type", "worker");
		model.addAttribute("action", "post");
		model.addAttribute("employee", null);
		model.addAttribute("boQuantity", boQuantity);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/workers/manage/create")
	public String createWorker(Worker newWorker, DataPlaceHolder<String> wboId,
			BirthDate birthDate, HireDate hireDate) {
		newWorker.setDateOfBirth(DateManager.createSQLDate(birthDate));
		newWorker.setDateOfHire(DateManager.createSQLDate(hireDate));
		newWorker.setBranchOffice(
				branchOfficeServiceImpl.getBranchOfficeById(
					Long.parseLong(wboId.getData())).getBody());
		
		workerServiceImpl.createWorker(newWorker);

		return "redirect:/workers";
	}

	/**
	 * 
	 */
	@GetMapping("/workers/{id}")
	public String showWorker(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Worker worker = workerServiceImpl.getWorkerById(id).getBody();
		BirthDate bDate = DateManager.transformStringDate(worker.getDateOfBirth()
				.toString(), new BirthDate());
		HireDate hDate = DateManager.transformStringDate(worker.getDateOfHire()
				.toString(), new HireDate());

		model.addAttribute("type", "worker");
		model.addAttribute("action", "get");
		model.addAttribute("employee", worker);
		model.addAttribute("bDate", bDate);
		model.addAttribute("hDate", hDate);

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
	public String updateWorker(@PathVariable(value = "id", required = true)
			Long id, Model model) {
		Worker worker = workerServiceImpl.getWorkerById(id).getBody();
		BirthDate bDate = DateManager.transformStringDate(worker.getDateOfBirth()
				.toString(), new BirthDate());
		HireDate hDate = DateManager.transformStringDate(worker.getDateOfHire()
				.toString(), new HireDate());

		model.addAttribute("type", "worker");
		model.addAttribute("action", "put");
		model.addAttribute("employee", worker);
		model.addAttribute("bDate", bDate);
		model.addAttribute("hDate", hDate);

		return "employeeActions";
	}

	/**
	 * 
	 */
	@GetMapping("/workers/manage/update/{id}")
	public String updateWorker( @PathVariable(value = "id", required = true)
			Long id, Worker updatedWorker, BirthDate birthDate,
			HireDate hireDate) {
		updatedWorker.setDateOfBirth(DateManager.createSQLDate(birthDate));
		updatedWorker.setDateOfHire(DateManager.createSQLDate(hireDate));
		workerServiceImpl.updateWorkerById(id, updatedWorker);

		return String.format("redirect:/workers/%d", updatedWorker.getId());
	}

	/**
	 * 
	 */
	@PostMapping("/workers/manage/delete/{id}")
	public String deleteWorker(
			@PathVariable(value = "id", required = true) Long id) {
		workerServiceImpl.deleteWorkerById(id);
		return "redirect:/workers";
	}
}
