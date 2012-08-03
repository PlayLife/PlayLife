package com.playlife.presentationLayer.converters;

import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.playlife.persistenceLayer.domainObject.Book;
import com.playlife.persistenceLayer.domainObject.BookSet;
import com.playlife.persistenceLayer.domainObject.PhotoContent;
import com.playlife.persistenceLayer.domainObject.Sheet;
import com.playlife.persistenceLayer.domainObject.Tag;
import com.playlife.persistenceLayer.domainObject.TextContent;
import com.playlife.utility.exceptions.PresentationLayerException;

public interface IBookConverter {
	public JSONObject constructBookSet(BookSet bookSet, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationLayerException;
	public JSONArray constructBookSets(Set<BookSet> bookSets, boolean showOriginalBook, boolean showAllBooks, boolean showTags, boolean showAddresses, boolean showSheet) throws PresentationLayerException;
	public JSONObject constructBook(Book book, boolean showSheet) throws PresentationLayerException;
	public JSONArray constructBooks(Set<Book> books, boolean showSheet) throws PresentationLayerException;
	public JSONObject constructSheet(Sheet sheet) throws PresentationLayerException;
	public JSONArray constructSheets(Set<Sheet> sheets) throws PresentationLayerException;
	public JSONObject constructPhotoContent(PhotoContent photoContent) throws PresentationLayerException;
	public JSONArray constructPhotoContents(Set<PhotoContent> photoContent) throws PresentationLayerException;
	public JSONObject constructTextContent(TextContent textContent) throws PresentationLayerException;
	public JSONArray constructTextContents(Set<TextContent> textContent) throws PresentationLayerException;
	public JSONObject constructTag(Tag tag) throws PresentationLayerException;
	public JSONArray constructTags(Set<Tag> tags) throws PresentationLayerException;
}
