package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.VinylRecord;
import co.edu.unbosque.exception.VinylRecordNotFoundException;
import co.edu.unbosque.repository.VinylRecordRepository;
import co.edu.unbosque.service.VinylRecordService;
import lombok.AllArgsConstructor;

/**
 * VinylRecordServiceImpl
 */
@Service
@AllArgsConstructor
public class VinylRecordServiceImpl implements VinylRecordService {

	private VinylRecordRepository vinylRecordRepository;

	/**
	 * Creates a new VinylRecord
	 *
	 * @param vinylRecord the creating VinylRecord instance
	 * @return the result of the CRUD's create operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#save(VinylRecord)
	 */
	@Override
	public ResponseEntity<VinylRecord> createVinylRecord(VinylRecord vinylRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrives an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findById(Long)
	 */
	@Override
	public ResponseEntity<VinylRecord> getVinylRecordById(Long id)
		throws VinylRecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrives a name-specified VinylRecord entity
	 *
	 * @param name the name of the VinylRecord entity to retrive
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<VinylRecord> getVinylRecordByName(String name)
		throws VinylRecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrives all the VinylRecord entities
	 *
	 * @return the result of the CRUD's retrive operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<VinylRecord>> getVinylRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Updates an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to update
	 * @param updatedVinylRecord the VinylRecord instance with the updating information
	 * @return the result of the CRUD's update operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#save(VinylRecord)
	 */
	@Override
	public ResponseEntity<VinylRecord> updateVinylRecordById(Long id,
			VinylRecord updatedVinylRecord)
			throws VinylRecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes an id-specified VinylRecord entity
	 *
	 * @param id the id of the VinylRecord entity to delete
	 * @return the result of the CRUD's delete operation over VinylRecord
	 * @see co.edu.unbosque.repository.VinylRecordRepository#deleteById(Long)
	 */
	@Override
	public ResponseEntity<?> deleteVinylRecordById(Long id)
		throws VinylRecordNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
