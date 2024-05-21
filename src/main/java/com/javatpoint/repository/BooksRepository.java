package com.javatpoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//repository that extends CrudRepository
import com.javatpoint.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

	
	
    @Query("SELECT b FROM Books b WHERE b.bookname = :bookName")
    Optional<Books> findByBookname(String bookName);

}
