package com.dearshor.dearbook.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dearshor.dearbook.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath: **/*-context.xml")
public class BookRepositoryTest {
	
	private @Autowired BookRepository bookRepository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Before
	public  void prepareData() {
		bookRepository.save(new Book("紫诏天音"));
		bookRepository.save(new Book("紫诏天音(第二版)"));
		bookRepository.save(new Book("梵花坠影"));
		bookRepository.save(new Book("UML精粹:标准对象建模语言简明指南"));
	}
	
	@Test
	public void testFindByName() {
		List<Book> bookList = bookRepository.findByName("紫诏天音");
		Assert.assertNotNull(bookList);
		Assert.assertFalse(bookList.isEmpty());
	}
	
	public void testFindOne() {
		Book book = bookRepository.findOne("梵花坠影");
		Assert.assertNotNull(book);
		logger.debug(book.toString());
	}

}
