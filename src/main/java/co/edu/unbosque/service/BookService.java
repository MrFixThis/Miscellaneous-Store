package co.edu.unbosque.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.unbosque.entity.Book;
import co.edu.unbosque.exception.BookNotFoundException;

/**
 * @author Bryan Baron
 */
public interface BookService {

	/**
	 * Creates a new Book
	 *
	 * @param book the creating Book instance
	 * @return the result of the CRUD's create operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#save(Book)
	 */
	ResponseEntity<Book> createBook(Book book);

	/**
	 * Retrives an isbn-specified Book entity
	 *
	 * @param isbn the isbn of the Book entity to retrive
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findById(String)
	 */
	ResponseEntity<Book> getBookByIsbn(String ibsn)
			throws BookNotFoundException;

	/**
	 * Retrives a name-specified Book entity
	 *
	 * @param name the name of the Book entity to retrive
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findByName(String)
	 */
	ResponseEntity<Book> getBookByName(String name)
		throws BookNotFoundException;

	/**
	 * Retrives all the Book entities
	 *
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findAll()
	 */
	ResponseEntity<List<Book>> getBooks();

	/**
	 * Updates an isbn-specified Book entity
	 *
	 * @param isbn the isbn of the Book entity to update
	 * @param updatedBook the Book instance with the updating information
	 * @return the result of the CRUD's update operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#save(Book)
	 */
	ResponseEntity<Book> updateBookByIsbn(String isbn, Book updatedBook)
			throws BookNotFoundException;

	/**
	 * Deletes an isbn-specified Book entity
	 *
	 * @param isbn the isbn of the Book entity to delete
	 * @return the result of the CRUD's delete operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#deleteById(String)
	 */
	ResponseEntity<?> deleteBookByIsbn(String isbn)
			throws BookNotFoundException;
}
