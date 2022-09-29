package com.edu.unbosque.repository;

import java.util.Optional;

import com.edu.unbosque.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bryan Baron
 */
public interface BookRespository extends JpaRepository<Book, String> {

	/**
	 * Retrieves a book by its name.
	 *
	 * @param name must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
	Optional<Book> findByName(String name);
}
