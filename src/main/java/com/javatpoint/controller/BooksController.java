package com.javatpoint.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Books;
import com.javatpoint.service.BooksService;

//mark class as Controller
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="booksproject")
@CrossOrigin(origins = "http://localhost:4200")
public class BooksController {
//autowire the BooksService class
	@Autowired
	BooksService booksService;

//creating a get mapping that retrieves all the books detail from the database
	@GetMapping("/getAll")
	private List<Books> getAllBooks() {
		return booksService.getAllBooks();
	}

//creating a get mapping that retrieves the detail of a specific book
	@GetMapping("/get/{bookid}")
	private Books getBooks(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}
	
	@GetMapping("/getbyname/{bookname}")
	private Books getBooks(@PathVariable("bookname") String bookname) {
		Books books = booksService.getBooksByName(bookname).orElseThrow(() -> new RuntimeException("Exception"));
		return books;
	}

//creating a delete mapping that deletes a specified book
	@DeleteMapping("/delete/{bookid}")
	private void deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
	}

//creating post mapping that post the book detail in the database
	@PostMapping("/create")
	private int saveBook(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books.getBookid();
	}

//creating put mapping that updates the book detail
	@PutMapping("/update")
	private Books update(@RequestBody Books books) {
		booksService.saveOrUpdate(books);
		return books;
	}
}
