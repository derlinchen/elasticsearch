package com.elastic.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.elastic.bean.Book;
import com.elastic.dao.BookDao;
import com.elastic.service.EsService;

@Service("esService ")
public class EsServiceImp implements EsService {

	// region Template Methods

	@Autowired
	private ElasticsearchTemplate template;

	@Override
	public void save(String indexname, String type, Object obj) {
		IndexQuery indexquery = new IndexQueryBuilder()
				.withIndexName(indexname).withType(type).withObject(obj).build();
		template.index(indexquery);
	}

	@Override
	public void update(String indexname, String type, Object obj) {
		IndexQuery indexquery = new IndexQueryBuilder()
				.withIndexName(indexname).withType(type).withObject(obj)
				.build();
		template.index(indexquery);
	}
	
	@Override
	public void delete(String indexname, String type, int id) {
		template.delete(indexname, type, String.valueOf(id));
	}
	
	
	
	@Override
	public <T> List<T> findByPageSort(int start, int end, String sortattr, boolean asc, Class<T> clazz) {
		Sort sort = null;
		if(asc){
			sort = new Sort(Sort.Direction.ASC, sortattr);
		} else{
			sort = new Sort(Sort.Direction.DESC, sortattr);
		}
		Pageable pageable = PageRequest.of(start, end, sort);
		SearchQuery searchquery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchAllQuery())
				.withPageable(pageable).build();
		Page<T> items = template.queryForPage(searchquery, clazz);
		return items.getContent();
	}

	// endregion Template Methods

	
	// region Repository Methods
	
	@Resource
	private BookDao bookdao;
	
	@Override
	public void save(Book book) {
		bookdao.save(book);
	}
	
	@Override
	public void delete(Book book) {
		bookdao.delete(book);
	}
	
	@Override
	public void update(Book book) {
		bookdao.save(book);
	}
	
	@Override
	public List<Book> getByName(String name) {
		return bookdao.getByName(name);
	}
	
	@Override
	public List<Book> getPageByName(String name, Pageable pageable) {
		Page<Book> books = bookdao.getPageByName(name, pageable);
		return books.getContent();
	}
	
	@Override
	public List<Book> findAll() {
		List<Book> list = new ArrayList<Book>();
		Iterable<Book> iterable = bookdao.findAll();
		for (Book item : iterable) {
			list.add(item);
		}
		return list;
	}
	
	// endregion Repository Methods
}
