package com.playlife.logic.book;

import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.PhotoContent;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.Sheet;
import com.playlife.persistence.domainObject.TextContent;
import com.playlife.utility.exceptions.LogicException;

public interface IBookService {
	public BookSet addBookSet(BookSet new_bookSet, PlayLifeUser user) throws LogicException;
	public Sheet addSheet(Book old_book, Sheet new_sheet, PlayLifeUser user) throws LogicException;
	public PhotoContent addPhotoContent(Sheet old_sheet, PhotoContent new_photoContent, PlayLifeUser user) throws LogicException;
	public TextContent addTextContent(Sheet old_sheet, TextContent new_textContent, PlayLifeUser user) throws LogicException;
	
	public BookSet modifyBookSet(BookSet old_bookSet, PlayLifeUser user, boolean isUpdateAddress, boolean isUpdateTag) throws LogicException;
	public Book modifyBook(Book old_book, PlayLifeUser user) throws LogicException;
	public Sheet modifySheet(Sheet old_sheet, PlayLifeUser user, boolean isUpdateAddress, boolean isUpdatePlace) throws LogicException;
	public PhotoContent modifyPhotoContent(PhotoContent old_photoContent, PlayLifeUser user) throws LogicException;
	public TextContent modifyTextContent(TextContent old_textContent, PlayLifeUser user) throws LogicException;
	
	public void removeBook(Book old_book, PlayLifeUser user) throws LogicException;
	public void removeSheet(Sheet old_sheet, PlayLifeUser user) throws LogicException;
	public void removePhotoContent(PhotoContent old_photoContent, PlayLifeUser user) throws LogicException;
	public void removeTextContent(TextContent old_textContent, PlayLifeUser user) throws LogicException;
	
	public Book getBook(Book old_book, PlayLifeUser user) throws LogicException;
	public Book getBook(BookSet old_bookSet, PlayLifeUser user) throws LogicException;
	public Sheet getSheet(Sheet old_sheet, PlayLifeUser user) throws LogicException;
	public PhotoContent getPhotoContent(PhotoContent old_photoContent, PlayLifeUser user) throws LogicException;
	public TextContent getTextContent(TextContent old_textContent, PlayLifeUser user) throws LogicException;
}
