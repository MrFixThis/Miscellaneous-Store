package com.store.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.BranchOffice;
import com.store.model.Worker;
import com.store.service.BranchOfficeService;
import com.store.service.WorkerService;

import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@RestController
@RequestMapping(path = "/api/v1/workers")
@AllArgsConstructor
public class WorkerRestController {

	private WorkerService workerService;
	private BranchOfficeService branchOfficeService;

	/**
	 * Creates a new Worker entity.
	 *
	 * @param worker request body with the information of the Worker
	 * entity being created.
	 * @param branchOfficeId id of the BranchOffice entity related to the Worker
	 * entity being created.
	 * @return the response of the POST request.
	 */
	@PostMapping
	public ResponseEntity<Worker> createWorker(@RequestBody Worker worker,
			@RequestParam(name = "branchOfficeId") Long branchOfficeId) {
		final BranchOffice workerBranchOffice =
			branchOfficeService.getBranchOfficeById(branchOfficeId).getBody();
		ResponseEntity<Worker> createdWorker = null;

		worker.setBranchOffice(workerBranchOffice);
		createdWorker = workerService.createWorker(worker);
		return createdWorker;
	}

	/**
	 * Retrieves a Worker entity specified by id.
	 *
	 * @param id id of the Worker entity being searched.
	 * @return the response of the GET request.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Worker> getWorker(@PathVariable(name = "id") Long id) {
		final ResponseEntity<Worker> worker = workerService.getWorkerById(id);
		return worker;
	}

	/**
	 * Retrieves all the existent Worker entities.
	 *
	 * @return the response of the GET request.
	 */
	@GetMapping
	public ResponseEntity<List<Worker>> getWorkers() {
		final ResponseEntity<List<Worker>> workers = workerService.getWorkers();
		return workers;
	}

	/**
	 * Updates a Worker entity specified by id.
	 *
	 * @param id id of the Worker entity being updated.
	 * @param worker request body with the information of the Worker
	 * entity being updated.
	 * @param branchOfficeId id of the BranchOffice entity related to the Worker
	 * entity being updated.
	 * @return the response of the PUT request.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Worker> updateWorker(@PathVariable(name = "id") Long id,
			@RequestBody Worker worker,
			@RequestParam(name = "branchOfficeId") Long branchOfficeId) {
		final BranchOffice workerNewBranchOffice =
			branchOfficeService.getBranchOfficeById(branchOfficeId).getBody();
		ResponseEntity<Worker> updatedWorker = null;

		worker.setBranchOffice(workerNewBranchOffice);
		updatedWorker =  workerService.updateWorkerById(id, worker);
		return updatedWorker;
	}

	/**
	 * Deletes a Worker entity specified by id.
	 *
	 * @param id id of the Worker entity being deleted.
	 * @return the response of the DELETE request.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteWorker(@PathVariable(name = "id") Long id) {
		final ResponseEntity<?> deletedWorker = workerService.deleteWorkerById(id);
		return deletedWorker;
	}
}
