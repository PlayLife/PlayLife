package com.playlife.logicLayer.book;

import com.playlife.persistenceLayer.domainObject.Book;
import com.playlife.persistenceLayer.domainObject.BookSet;
import com.playlife.persistenceLayer.domainObject.PhotoContent;
import com.playlife.persistenceLayer.domainObject.Sheet;
import com.playlife.persistenceLayer.domainObject.TextContent;
import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.utility.exceptions.LogicLayerException;

public interface IBookService {
	public BookSet addBookSet(BookSet new_bookSet, User user) throws LogicLayerException;
	public Sheet addSheet(Book old_book, Sheet new_sheet, User user) throws LogicLayerException;
	public PhotoContent addPhotoContent(Sheet old_sheet, PhotoContent new_photoContent, User user) throws LogicLayerException;
	public TextContent addTextContent(Sheet old_sheet, TextContent new_textContent, User user) throws LogicLayerException;
	
	public BookSet modifyBookSet(BookSet old_bookSet, User user, boolean isUpdateAddress, boolean isUpdateTag) throws LogicLayerException;
	public Book modifyBook(Book old_book, User user) throws LogicLayerException;
	public Sheet modifySheet(Sheet old_sheet, User user, boolean isUpdateAddress, boolean isUpdatePlace) throws LogicLayerException;
	public PhotoContent modifyPhotoContent(PhotoContent old_photoContent, User user) throws LogicLayerException;
	public TextContent modifyTextContent(TextContent old_textContent, User user) throws LogicLayerException;
	
	public void removeBook(Book old_book, User user) throws LogicLayerException;
	public void removeSheet(Sheet old_sheet, User user) throws LogicLayerException;
	public void removePhotoContent(PhotoContent old_photoContent, User user) throws LogicLayerException;
	public void removeTextContent(TextContent old_textContent, User user) throws LogicLayerException;
	
	public Book getBook(Book old_book, User user) throws LogicLayerException;
	public Book getBook(BookSet old_bookSet, User user) throws LogicLayerException;
	public Sheet getSheet(Sheet old_sheet, User user) throws LogicLayerException;
	public PhotoContent getPhotoContent(PhotoContent old_photoContent, User user) throws LogicLayerException;
	public TextContent getTextContent(TextContent old_textContent, User user) throws LogicLayerException;
}
