package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Worker;
import co.edu.unbosque.exception.EmployeeNotFoundException;

/**
 * @author Bryan Baron
 */
public interface WorkerService {

	/**
	 * Creates a new Worker
	 *
	 * @param worker the creating Worker instance
	 * @return the result of the CRUD's create operation over Worker
	 * @see co.edu.unbosque.repository.WorkerRepository#save(Worker)
	 */
	ResponseEntity<Worker> createWorker(
			Worker worker);

	/**
	 * Retrives an id-specified Worker entity
	 *
	 * @param id the id of the Worker entity to retrive
	 * @return the result of the CRUD's retrive operation over Worker
	 * @see co.edu.unbosque.repository.WorkerRepository#findById(Long)
	 */
	ResponseEntity<Worker> getWorkerById(Long id)
			throws EmployeeNotFoundException;

	/**
	 * Retrives all the Worker entities
	 *
	 * @return the result of the CRUD's retrive operation over Worker
	 * @see co.edu.unbosque.repository.WorkerRepository#findAll()
	 */
	ResponseEntity<List<Worker>> getWorkers();

	/**
	 * Updates an id-specified Worker entity
	 *
	 * @param id the id of the Worker entity to update
	 * @param updatedWorker the Worker instance with the updating information
	 * @return the result of the CRUD's update operation over Worker
	 * @see co.edu.unbosque.repository.WorkerRepository#save(Worker)
	 */
	ResponseEntity<Worker> updateWorkerById(Long id,
			Worker updatedWorker)
			throws EmployeeNotFoundException;

	/**
	 * Deletes an id-specified Worker entity
	 *
	 * @param id the id of the Worker entity to delete
	 * @return the result of the CRUD's delete operation over Worker
	 * @see co.edu.unbosque.repository.WorkerRepository#deleteById(Long)
	 */
	ResponseEntity<?> deleteWorkerById(Long id)
			throws EmployeeNotFoundException;
}
