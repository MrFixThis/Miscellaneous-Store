package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Magazine;
import co.edu.unbosque.exception.MagazineNotFoundException;
import co.edu.unbosque.repository.MagazineRepository;
import co.edu.unbosque.service.MagazineService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class MagazineServiceImpl implements MagazineService {

	private MagazineRepository magazineRepository;

	@Override
	public ResponseEntity<Magazine> createMagazine(Magazine magazine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Magazine> getMagazineByIsbn(String ibsn)
		throws MagazineNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Magazine> getMagazineByName(String name)
		throws MagazineNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Magazine>> getMagazines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Magazine> updateMagazineByIsbn(String isbn,
			Magazine updatedMagazine)
			throws MagazineNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteMagazineByIsbn(String isbn)
		throws MagazineNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
