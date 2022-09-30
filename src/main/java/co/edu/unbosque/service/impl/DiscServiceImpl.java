package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Disc;
import co.edu.unbosque.exception.DiscNotFoundException;
import co.edu.unbosque.repository.DiscRepository;
import co.edu.unbosque.service.DiscService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class DiscServiceImpl implements DiscService {

	private DiscRepository discRepository;

	@Override
	public ResponseEntity<Disc> createDisc(Disc disc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Disc> getDiscById(Long id)
		throws DiscNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Disc> getDiscByName(String name)
		throws DiscNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Disc>> getDiscs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Disc> updateDiscById(Long id, Disc updatedDisc)
			throws DiscNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteDiscById(Long id)
		throws DiscNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
