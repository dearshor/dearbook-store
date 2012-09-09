package com.dearshor.dearbook.repository;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dearshor.dearbook.domain.Author;
import com.dearshor.dearbook.domain.Book;
import com.dearshor.dearbook.domain.Press;
import com.dearshor.dearbook.domain.Spec;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath: **/*-context.xml")
public class BookRepositoryTest {
	
	private @Autowired BookRepository bookRepository;
	
	private @Autowired AuthorRepository authorRepository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Author buFeiYan;

	private Author martin;

	private Press wanJuan;
	
	@Before
	public  void prepareData() {
		LinkedList<Book> bookList = new LinkedList<Book>();
		
		buFeiYan = new Author("步非烟");
		authorRepository.save(buFeiYan);
		martin = new Author("Martin Fowler");
		authorRepository.save(martin);
		
		wanJuan = new Press("万卷出版公司");
		
		Book ziShaoTianYin = new Book("紫诏天音","9787807594451, 7807594454");
		ziShaoTianYin.setAuthor(buFeiYan);
		ziShaoTianYin.setPress(wanJuan);
		ziShaoTianYin.setSpec(new Spec("平装", 310));
		bookList.add(ziShaoTianYin);
		
		Book ziShaoTianYin_second_edition = new Book("紫诏天音(第二版)", "9787807594451, 7807594454");
		ziShaoTianYin_second_edition.setAuthor(buFeiYan);
		ziShaoTianYin.setAuthor(buFeiYan);
		ziShaoTianYin.setPress(wanJuan);
		ziShaoTianYin.setSpec(new Spec("精装", 350));
		bookList.add(ziShaoTianYin_second_edition);
		
		Book fanHuaZuiYing = new Book("梵花坠影", "9787547010501, 7547010504");
		fanHuaZuiYing.setAuthor(buFeiYan);
		fanHuaZuiYing.setPress(wanJuan);
		fanHuaZuiYing.setSpec(new Spec("豪华",456));
		bookList.add(fanHuaZuiYing);
		
		Book umlDistilled = new Book("UML精粹:标准对象建模语言简明指南", "9787121170492, 7121170493");
		umlDistilled.setAuthor(martin);
		umlDistilled.setPress(new Press("电子工业出版社"));
		umlDistilled.setSpec(new Spec("平装", 207));
		bookList.add(umlDistilled);
		
		bookRepository.save(bookList);
		
	}
	
	@After
	public void cleanup() {
		bookRepository.deleteAll();
	}
	
	@Test
	public void testFindByName() {
		List<Book> bookList = bookRepository.findByName("紫诏天音");
		Assert.assertNotNull(bookList);
		Assert.assertFalse(bookList.isEmpty());
		Assert.assertEquals(1, bookList.size());
		logger.debug("第一个查询结果：{}", bookList.iterator().next());
	}
	
	@Test
	public void testFindByAuthor() {
		List<Book> bookList = bookRepository.findByAuthor(buFeiYan);
		Assert.assertEquals(3, bookList.size());
		logger.debug("{} 作品：", buFeiYan.getName());
		for (Book book : bookList) {
			logger.debug(book.toString());
		}
	}
	
	@Test
	public void testFindOne() {
		Book book = bookRepository.findByName("梵花坠影").iterator().next();
		Book bookCopy = bookRepository.findOne(book.getId());
		Assert.assertEquals(book, bookCopy);
		logger.debug("查询结果：{}", bookCopy.toString());
	}
	
	@Test
	public void testFindAll() {
		Iterable<Book> bookIter = bookRepository.findAll();
		Assert.assertNotNull(bookIter);
		logger.debug("当前数据库里所有图书：");
		for (Book book : bookIter) {
			logger.debug(book.toString());
		}
	}
	
	@Test
	public void testCount() {
		long count = bookRepository.count();
		Assert.assertEquals(4, count);
	}
	
	@Test
	public void testFindPage() {
		Page<Book> page = bookRepository.findAll(new PageRequest(0, 2));
		Assert.assertNotNull(page);
		Assert.assertTrue(page.hasContent());
		Assert.assertEquals(2, page.getSize());
		Assert.assertEquals(0, page.getNumber());
		logger.debug("第 {} 页的图书为：", page.getNumber());
		for (Book book : page.getContent()) {
			logger.debug(book.toString());
		}
		Assert.assertEquals(4, page.getTotalElements());
	}

}
