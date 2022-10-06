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
		final Book savedBook = bookRepository.save(book);
		return ResponseEntity.ok(savedBook);
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
		final Book book = bookRepository.findById(isbn)
			.orElseThrow(() -> new BookNotFoundException(
							String.format("Book with isbn % not found", isbn)
						));
		return ResponseEntity.ok(book);
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
		final Book book = bookRepository.findByName(name)
			.orElseThrow(() -> new BookNotFoundException(
							String.format("book with name %s not found", name)
						));
		return ResponseEntity.ok(book);
	}

	/**
	 * Retrives all the Book entities
	 *
	 * @return the result of the CRUD's retrive operation over Book
	 * @see co.edu.unbosque.repository.BookRepository#findAll()
	 */
	@Override
	public ResponseEntity<List<Book>> getBooks() {
		final List<Book> books = bookRepository.findAll();
		return ResponseEntity.ok(books);
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
		Book book = bookRepository.findById(isbn)
			.orElseThrow(() -> new BookNotFoundException(
							String.format("Book with isbn % not found", isbn)
						));

		book.setName(updatedBook.getName());
		book.setAuthorName(updatedBook.getAuthorName());
		book.setPublisherName(updatedBook.getPublisherName());
		book.setPagesNumber(updatedBook.getPagesNumber());
		book.setPublicationDate(updatedBook.getPublicationDate());
		book.setPrice(updatedBook.getPrice());
		book.setAvailableUnits(updatedBook.getAvailableUnits());

		book = bookRepository.save(book);
		return ResponseEntity.ok(book);
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
		Book book = bookRepository.findById(isbn)
			.orElseThrow(() -> new BookNotFoundException(
							String.format("Book with isbn % not found", isbn)
						));
		bookRepository.delete(book);

		return ResponseEntity.noContent().build();
	}
}
