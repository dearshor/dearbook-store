package com.dearshor.dearbook.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dearshor.dearbook.domain.Author;
import com.dearshor.dearbook.domain.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
	
	List<Book> findByName(String bookName) ;
	
	List<Book> findByIsbn(String ISBN);

	List<Book> findByAuthor(Author buFeiYan);

}
