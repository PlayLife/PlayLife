package com.playlife.presentation.converters;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.PhotoContent;
import com.playlife.persistence.domainObject.Sheet;
import com.playlife.persistence.domainObject.Tag;
import com.playlife.persistence.domainObject.TextContent;
import com.playlife.utility.exceptions.PresentationException;

@Component
@Qualifier("errorConverter")
public class JSON_BookConverter implements IBookConverter {
	@Autowired
	@Qualifier("objectConverter")
	IObjectConverter objectConverter;
	
	@Override
	public JSONObject constructBookSet(BookSet bookSet, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationException {
		try {
			JSONObject obj_return = (JSONObject) objectConverter.constructObject(bookSet);
			
			if (showOriginalBook)
				obj_return.put("originalBook", this.constructBook(bookSet.getOriginalBook(), showSheet));
			if (showAllBooks)
				obj_return.put("books", this.constructBooks(bookSet.getBooks(), showSheet));
			if (showTags)
				obj_return.put("tags", this.constructTags(bookSet.getTags()));
			if (showAddresses)
				obj_return.put("tags", this.constructTags(bookSet.getTags()));
			
			return obj_return;
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}
	
	@Override
	public JSONArray constructBookSets(Set<BookSet> bookSets, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationException {
		try {
			JSONArray arr_return = new JSONArray();
			for (BookSet bookSet : bookSets){
				arr_return.add(this.constructBookSet(bookSet, showOriginalBook, showAllBooks, showTags, showAddresses, showSheet));
			}
			
			return arr_return;
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}
	
	@Override
	public JSONObject constructBook(Book book, boolean showSheet) throws PresentationException {
		try {
			JSONObject obj_return = (JSONObject) objectConverter.constructObject(book);
			
			if (showSheet)
				obj_return.put("sheets", this.constructSheets(book.getSheets()));
			
			return obj_return;
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}
	
	@Override
	public JSONArray constructBooks(Set<Book> books, boolean showSheet) throws PresentationException {
		try {
			JSONArray arr_return = new JSONArray();
			
			for (Book book : books){
				arr_return.add(this.constructBook(book, showSheet));
			}
			
			return arr_return;
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}

	@Override
	public JSONObject constructSheet(Sheet sheet) throws PresentationException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}

	@Override
	public JSONArray constructSheets(Set<Sheet> sheets) throws PresentationException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}

	@Override
	public JSONObject constructPhotoContent(PhotoContent photoContent) throws PresentationException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}

	@Override
	public JSONArray constructPhotoContents(Set<PhotoContent> photoContent) throws PresentationException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}

	@Override
	public JSONObject constructTextContent(TextContent textContent) throws PresentationException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}

	@Override
	public JSONArray constructTextContents(Set<TextContent> textContent) throws PresentationException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationException(-9999, ex);
		}
	}
	
	@Override
	public JSONObject constructTag(Tag tag) throws PresentationException {
		return null;
	}

	@Override
	public JSONArray constructTags(Set<Tag> tags) throws PresentationException {
		return null;
	}

}
