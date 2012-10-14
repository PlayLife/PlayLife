package com.playlife.presentation.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlife.logic.access.AccessService;
import com.playlife.logic.book.BookService;
import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.presentation.converters.JSONConverter;
import com.playlife.utility.LocaleService;
import com.playlife.utility.exceptions.PresentationException;

@Controller
@RequestMapping (value = "/book")
public class BookController {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	AccessService accessService;
	
	ObjectMapper mapper = JSONConverter.mapper;

	@RequestMapping (value = "/create")
	protected String createBook_mainRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);
		
		return redirect(request, "book/createBook", false);
	}
	
	@RequestMapping (value = "/edit/{bookId}")
	protected String editBook_mainRequest(@PathVariable Long bookId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);
		
		try {
			PlayLifeUser user = (PlayLifeUser) request.getSession().getAttribute("user");
			Book book = accessService.checkUserBook_owner(user.getUserId(), bookId);
			model.put("bookId", book.getBookId());
			model.put("title", book.getTitle());
		} catch (Exception ex){
			return "home";
		}
		
		return redirect(request, "book/editBook", false);
	}
	
	@RequestMapping(value="/create.json")
	@ResponseBody
	protected String createBookRequest(BookSet bookSet, String title, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LocaleService.resolve(request, response);
		redirect(request, "", true);
		
		bookSet.setOriginalBook(new Book(title));
		
		PlayLifeUser user = (PlayLifeUser) request.getSession().getAttribute("user");
		BookSet new_bookSet = bookService.addBookSet(bookSet, user);
		
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("bookId", new_bookSet.getOriginalBook().getBookId());
		map_return.put("title", new_bookSet.getOriginalBook().getTitle());
		map_return.put("status", "ok");
		
		return mapper.writeValueAsString(map_return);
	}
	
	private String redirect(HttpServletRequest request, String path, boolean throwException) {
		PlayLifeUser user = (PlayLifeUser) request.getSession().getAttribute("user");
		if (user == null || user.isDisabled()) {
			if (throwException) throw new PresentationException(-9999);
			return "home";
		}
		else return path;
	}
	
	@ExceptionHandler (Exception.class)
	@ResponseBody
	public String handlerException(HttpServletRequest request, Exception ex) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("error", JSONConverter.constructError(ex, messageSource, LocaleService.getLocale(request)));
		map_return.put("status", "error");
		return mapper.writeValueAsString(map_return);
	}
}
