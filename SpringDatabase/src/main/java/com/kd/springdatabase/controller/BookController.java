package com.kd.springdatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kd.springdatabase.dto.Book;
import com.kd.springdatabase.dto.Dog;
import com.kd.springdatabase.mapper.BookMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/book")
@Controller
public class BookController {
	
	@Autowired
	BookMapper bookMapper;
	
	@GetMapping("/list")
	public void list(Model model) {
		List<Book> books = bookMapper.selectAll();
		
//		for (Book book : books) {
//			log.info("{}", book);
//		}
		model.addAttribute("books", books);
	}
	
	@GetMapping("/modify")
	public String modifyFrom(@RequestParam Integer book_id, Model model) {
		Book book = bookMapper.get(book_id);
		model.addAttribute("book", book);
		return "/book/modify_form";
	}
	
	@PostMapping("/modify")
	public String modify(Book book) throws Exception {
		int result = bookMapper.update(book);
		log.info("{}번 상품 수정합니다~", book.getBook_id());
		
		if (result > 0) {
			return "redirect:/book/list";
		} else {
			throw new Exception("왜 틀림?");
		}
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam Integer book_id) throws Exception {
		log.info("이제 {}번 상품을 삭제해야합니다!", book_id);
		
		int result = bookMapper.delete(book_id);
		if (result > 0) {
			log.info("{}번 상품 삭제 완료!", book_id);
			return "redirect:/book/list";
		} else {
			throw new Exception("삭제 실패!");
		}
	}
	
	@PostMapping("/timetest")
	public String timetest(
			@RequestParam String regist_date
			) {
		log.info("regist date(type=date): {}", regist_date);
//		log.info("regist date2(type=datetime-local): {}", regist_date2);
		
		return "redirect:/book/list";
	}
	
	@PostMapping("/timetest2")
	public String timetest2(Dog dog) {
//		log.info("dog's regist date(type=date): {}", dog.getRegist_date());
		log.info("dog's regist date2(type=datetime-local): {}", dog.getRegist_date2());
		
		return "redirect:/book/list";
	}
	
	@GetMapping("/add")
	public String addForm(Model model) {
//		DB에서 이런 값을 꺼내와서 활용하는 것이 좋다. (강사님 ex)
//		model.addAttribute("sizes", new String[] {"XXL","XL", "L","M","S"});
		return "/book/add_form";
	}
	
	@PostMapping("/add1")
	public String add1(Book book) throws Exception {
		int result = bookMapper.add1(book);
		
		log.info("insert문 실행 결과: {}", result);
		
		if (result > 0) {
			return "redirect:/book/list";
		} else {
			throw new Exception("insert 실패!");
		}
	}
	
	@PostMapping("/add2")
	public String add2(Book book) throws Exception {
		Book dto = bookMapper.add2(book);
		
		
		if (dto != null) {
			log.info("방금 insert한 행의 기본키: {}", dto.getBook_id());
			return "redirect:/book/list";
		} else {
			throw new Exception("insert 실패!");
		}
		
	}
}
