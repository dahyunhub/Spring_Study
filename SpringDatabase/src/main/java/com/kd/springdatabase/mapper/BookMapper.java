package com.kd.springdatabase.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kd.springdatabase.dto.Book;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookMapper {

	@Autowired
	SqlSessionTemplate sql;	
	
	public List<Book> selectAll() {
		return sql.selectList("Books.selectAll");
	}
	
	public Book get(Integer book_id) {
		return sql.selectOne("Books.get", book_id);
	}
	
	public int delete(Integer book_id) {
		return sql.delete("Books.delete", book_id);
	}

	public int update(Book book) {
		return sql.update("Books.modify", book);
	}
	
	public int add1(Book book) {
		return sql.insert("Books.add1", book);
	}
	
	public Book add2(Book book) {
		int result = sql.insert("Books.add2", book);
		
		if (result > 0) {
			// selectKey를 성공하고 나면 dto에 pk를 채워놓는다.
			return book;
		} else {
			return null;
		}
		
	}

}


