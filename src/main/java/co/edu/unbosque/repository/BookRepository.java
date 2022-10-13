package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.entity.Book;

/**
 * @author Bryan Baron
 */
public interface BookRepository extends JpaRepository<Book, String> {

	/**
	 * Retrieves a book by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none is found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<Book> findByName(String name);
}
