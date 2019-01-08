package com.elastic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.elastic.bean.Book;


public interface EsService {
	
	// region Template Methods

	/**
	 * 保存数据
	 * @param indexname
	 * @param type
	 * @param obj
	 */
	public void save(String indexname, String type, Object obj);
	
	/**
	 * 更新数据
	 * @param indexname
	 * @param type
	 * @param obj
	 */
	public void update(String indexname, String type, Object obj);
	
	/**
	 * 根据编号删除数据
	 * @param indexname 索引名称
	 * @param type 数据类型
	 * @param id 数据编号
	 */
	public void delete(String indexname, String type, int id);
	
	/**
	 * 分页排序查询
	 * @param start 开始数 从0开始
	 * @param end 结束数
	 * @param sortattr 排序字段
	 * @param clazz
	 * @return
	 */
	public <T> List<T> findByPageSort(int start, int end, String sortattr, boolean asc, Class<T> clazz);
	
	// endregion Template Methods
	
	
	// region Repository Methods
	
	public void save(Book book);
	
	public void delete(Book book);
	
	public void update(Book book);
	
	public List<Book> getByName(String name);
	
	public List<Book> getPageByName(String name, Pageable pageable);
	
	public List<Book> findAll();
	
	// endregion Repository Methods

}
