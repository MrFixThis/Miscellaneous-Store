package co.edu.unbosque.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Book;
import co.edu.unbosque.exception.BookNotFoundException;
import co.edu.unbosque.repository.BookRepository;
import co.edu.unbosque.service.BookService;
import lombok.AllArgsConstructor;

/**
 * @author Bryan Baron
 */
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;

	/**
	 * Creates a new Book
	 *
	 * @param book the creating Book instance
	 * @return the result of the CRUD's create operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#save(Book)
	 */
	@Override
	public ResponseEntity<Book> createBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrives an isbn-specified Book entity
	 *
	 * @param isbn the isbn of the Book entity to retrive
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findById(String)
	 */
	@Override
	public ResponseEntity<Book> getBookByIsbn(String isbn)
		throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrives a name-specified Book entity
	 *
	 * @param name the name of the Book entity to retrive
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findByName(String)
	 */
	@Override
	public ResponseEntity<Book> getBookByName(String name)
		throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Retrives all the Book entities
	 *
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Book>> getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Updates an isbn-specified Book entity
	 *
	 * @param isbn the isbn of the Book entity to update
	 * @param updatedBook the Book instance with the updating information
	 * @return the result of the CRUD's update operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#save(Book)
	 */
	@Override
	public ResponseEntity<Book> updateBookByIsbn(String isbn, Book updatedBook)
		throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Deletes an isbn-specified Book entity
	 *
	 * @param isbn the isbn of the Book entity to delete
	 * @return the result of the CRUD's delete operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#deleteById(String)
	 */
	@Override
	public ResponseEntity<?> deleteBookByIsbn(String isbn)
		throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
