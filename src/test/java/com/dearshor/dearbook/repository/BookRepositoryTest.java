package com.dearshor.dearbook.repository;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dearshor.dearbook.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-beans-data-jpa.xml")
public class BookRepositoryTest {
	
	private @Autowired BookRepository bookRepository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testFindByName() {
		List<Book> bookList = bookRepository.findByName("紫诏天音");
		Assert.assertNotNull(bookList);
		Assert.assertFalse(bookList.isEmpty());
	}
	
	public void testFindOne() {
		Book book = bookRepository.findOne("TODO");
		Assert.assertNotNull(book);
		logger.debug(book.toString());
	}

}
