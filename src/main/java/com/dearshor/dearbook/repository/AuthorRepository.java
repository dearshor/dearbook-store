package com.dearshor.dearbook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dearshor.dearbook.domain.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author	, String> {

	Author findByName(String name);
	
}
