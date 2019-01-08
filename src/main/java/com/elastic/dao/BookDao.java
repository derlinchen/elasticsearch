package com.elastic.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elastic.bean.Book;

@Repository
public interface BookDao extends ElasticsearchRepository<Book, Integer> {

	/**
	 * 通过名称查询
	 * @param name
	 * @return
	 */
	List<Book> getByName(String name);
	
	Page<Book> getPageByName(String name, Pageable pageable);
}
