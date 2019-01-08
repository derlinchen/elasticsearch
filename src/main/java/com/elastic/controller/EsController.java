package com.elastic.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.bean.Book;
import com.elastic.service.EsService;

@RestController
public class EsController {

	@Resource
	private EsService esService;
	
	// region Template Methods
	
	@RequestMapping("/init")
	public void init(){
		String[] names = {"《西游记》","《红楼梦》","《三国演义》","《水浒传》","《西厢记》"};
		String[] persons = {"猴子","宝玉","曹操","武松","张生"};
		for(int i = 0; i < names.length; i++){
			Book book = new Book(i+1, names[i], persons[i]);
			esService.save("library", "book", book);
		}
	}
	
	@RequestMapping("/save")
	public void save(Book book){
		esService.save("library", "book", book);
	}
	
	@RequestMapping("/update")
	public void update(Book book){
		esService.save("library", "book", book);
	}
	
	@RequestMapping("/delete")
	public void delete(Book book){
		esService.delete("library", "book", book.getId());
	}

	@RequestMapping("/findbypagesort")
	public void findBySort(){
		List<Book> books = esService.findByPageSort(0, 10, "id", true, Book.class);
		System.out.println(books.size());
	}
	
	// endregion Template Methods
	
	
	// region Repository Methods
	
	@RequestMapping("/getbyname")
	public void getByName(Book book){
		List<Book> books = esService.getByName(book.getName());
		System.out.println(books.size());
	}
	
	@RequestMapping("/getbypage")
	public void getPageByName(Book book){
		Pageable pageable = PageRequest.of(0, 2);
		List<Book> books = esService.getPageByName("三", pageable);
		System.out.println(books.size());
	}
	
	// endregion Repository Methods
	
}
