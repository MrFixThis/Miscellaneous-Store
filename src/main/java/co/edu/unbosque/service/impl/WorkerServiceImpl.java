package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Worker;
import co.edu.unbosque.exception.EmployeeNotFoundException;
import co.edu.unbosque.repository.WorkerRepository;
import co.edu.unbosque.service.WorkerService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class WorkerServiceImpl implements WorkerService {

	private WorkerRepository workerRepository;

	/**
	 * Creates a new Worker
	 *
	 * @param worker the creating worker instance
	 * @return the result of the CRUD's create operation over Worker
	 */
	@Override
	public ResponseEntity<Worker> createWorker(
			Worker worker) {
		final Worker savedWorker = workerRepository.save(worker);
		return ResponseEntity.ok(savedWorker);
	}

	/**
	 * Retrives an id-specified Worker entity
	 *
	 * @param id the id of the Worker entity to retrive
	 * @return the result of the CRUD's retrive operation over Worker
	 */
	@Override
	public ResponseEntity<Worker> getWorkerById(Long id)
		throws EmployeeNotFoundException {
		final Worker worker = workerRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("Worker with id %d not found",id)));
		return ResponseEntity.ok(worker);
	}

	/**
	 * Retrives all the Worker entities
	 *
	 * @return the result of the CRUD's retrive operation over Worker
	 */
	@Override
	public ResponseEntity<List<Worker>> getWorkers() {
		final List<Worker> workers = workerRepository.findAll();
		return ResponseEntity.ok(workers);
	}

	/**
	 * Updates an id-specified Worker entity
	 *
	 * @param id the id of the Worker entity to update
	 * @param updatedWorker the Worker instance with the updating information
	 * @return the result of the CRUD's update operation over Worker
	 */
	@Override
	public ResponseEntity<Worker> updateWorkerById(
			Long id, Worker updatedWorker)
			throws EmployeeNotFoundException {
		Worker worker = workerRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("Worker with id %d not found",id)));

		worker.setFirstName(updatedWorker.getFirstName());
		worker.setMiddleName(updatedWorker.getMiddleName());
		worker.setPaternalLastName(updatedWorker.getPaternalLastName());
		worker.setMaternalLastName(updatedWorker.getMaternalLastName());
		worker.setIdentificationNumber(updatedWorker
				.getIdentificationNumber());
		worker.setIdentificationType(updatedWorker
				.getIdentificationType());
		worker.setDateOfBirth(updatedWorker.getDateOfBirth());
		worker.setDateOfHire(updatedWorker.getDateOfHire());
		worker.setPhoneNumber(updatedWorker.getPhoneNumber());
		worker.setEmailAddress(updatedWorker.getEmailAddress());
		worker.setResidenceAddress(updatedWorker.getResidenceAddress());
		worker.setRole(updatedWorker.getRole());
		worker.setBasicSalary(updatedWorker.getBasicSalary());
		worker.setBranchOffice(updatedWorker.getBranchOffice());

		worker = workerRepository.save(worker);
		return ResponseEntity.ok(worker);
	}

	/**
	 * Deletes an id-specified Worker entity
	 *
	 * @param id the id of the Worker entity to delete
	 * @return the result of the CRUD's delete operation over Worker
	 */
	@Override
	public ResponseEntity<?> deleteWorkerById(Long id)
		throws EmployeeNotFoundException {
		Worker worker = workerRepository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(
						String.format("Worker with id %d not found",id)));
		workerRepository.delete(worker);

		return ResponseEntity.noContent().build();
	}
}
