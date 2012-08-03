package com.playlife.presentationLayer.converters;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistenceLayer.domainObject.Book;
import com.playlife.persistenceLayer.domainObject.BookSet;
import com.playlife.persistenceLayer.domainObject.PhotoContent;
import com.playlife.persistenceLayer.domainObject.Sheet;
import com.playlife.persistenceLayer.domainObject.Tag;
import com.playlife.persistenceLayer.domainObject.TextContent;
import com.playlife.utility.exceptions.PresentationLayerException;

@Component
@Qualifier("errorConverter")
public class JSON_BookConverter implements IBookConverter {
	@Autowired
	@Qualifier("objectConverter")
	IObjectConverter objectConverter;
	
	@Override
	public JSONObject constructBookSet(BookSet bookSet, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationLayerException {
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
			throw new PresentationLayerException(-9999, ex);
		}
	}
	
	@Override
	public JSONArray constructBookSets(Set<BookSet> bookSets, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationLayerException {
		try {
			JSONArray arr_return = new JSONArray();
			for (BookSet bookSet : bookSets){
				arr_return.add(this.constructBookSet(bookSet, showOriginalBook, showAllBooks, showTags, showAddresses, showSheet));
			}
			
			return arr_return;
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}
	
	@Override
	public JSONObject constructBook(Book book, boolean showSheet) throws PresentationLayerException {
		try {
			JSONObject obj_return = (JSONObject) objectConverter.constructObject(book);
			
			if (showSheet)
				obj_return.put("sheets", this.constructSheets(book.getSheets()));
			
			return obj_return;
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}
	
	@Override
	public JSONArray constructBooks(Set<Book> books, boolean showSheet) throws PresentationLayerException {
		try {
			JSONArray arr_return = new JSONArray();
			
			for (Book book : books){
				arr_return.add(this.constructBook(book, showSheet));
			}
			
			return arr_return;
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}

	@Override
	public JSONObject constructSheet(Sheet sheet) throws PresentationLayerException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}

	@Override
	public JSONArray constructSheets(Set<Sheet> sheets) throws PresentationLayerException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}

	@Override
	public JSONObject constructPhotoContent(PhotoContent photoContent) throws PresentationLayerException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}

	@Override
	public JSONArray constructPhotoContents(Set<PhotoContent> photoContent) throws PresentationLayerException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}

	@Override
	public JSONObject constructTextContent(TextContent textContent) throws PresentationLayerException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}

	@Override
	public JSONArray constructTextContents(Set<TextContent> textContent) throws PresentationLayerException {
		try {
			return null;	
		} catch (Exception ex) {
			throw new PresentationLayerException(-9999, ex);
		}
	}
	
	@Override
	public JSONObject constructTag(Tag tag) throws PresentationLayerException {
		return null;
	}

	@Override
	public JSONArray constructTags(Set<Tag> tags) throws PresentationLayerException {
		return null;
	}

}
