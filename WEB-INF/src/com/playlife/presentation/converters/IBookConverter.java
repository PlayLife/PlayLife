package com.playlife.presentation.converters;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.PhotoContent;
import com.playlife.persistence.domainObject.Sheet;
import com.playlife.persistence.domainObject.Tag;
import com.playlife.persistence.domainObject.TextContent;
import com.playlife.utility.exceptions.PresentationException;

public interface IBookConverter {
	public JSONObject constructBookSet(BookSet bookSet, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationException;
	public JSONArray constructBookSets(Set<BookSet> bookSets, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationException;
	public JSONObject constructBook(Book book, boolean showSheet) throws PresentationException;
	public JSONArray constructBooks(Set<Book> books, boolean showSheet) throws PresentationException;
	public JSONObject constructSheet(Sheet sheet) throws PresentationException;
	public JSONArray constructSheets(Set<Sheet> sheets) throws PresentationException;
	public JSONObject constructPhotoContent(PhotoContent photoContent) throws PresentationException;
	public JSONArray constructPhotoContents(Set<PhotoContent> photoContent) throws PresentationException;
	public JSONObject constructTextContent(TextContent textContent) throws PresentationException;
	public JSONArray constructTextContents(Set<TextContent> textContent) throws PresentationException;
	public JSONObject constructTag(Tag tag) throws PresentationException;
	public JSONArray constructTags(Set<Tag> tags) throws PresentationException;
}
