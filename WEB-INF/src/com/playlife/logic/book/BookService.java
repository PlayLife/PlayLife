package com.playlife.logic.book;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.logic.access.IAccessService;
import com.playlife.persistence.DAO.BookDAO;
import com.playlife.persistence.DAO.BookSetDAO;
import com.playlife.persistence.DAO.PhotoContentDAO;
import com.playlife.persistence.DAO.SheetDAO;
import com.playlife.persistence.DAO.TextContentDAO;
import com.playlife.persistence.domainObject.Address;
import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.Book_Status;
import com.playlife.persistence.domainObject.Book_TravelWith;
import com.playlife.persistence.domainObject.PhotoContent;
import com.playlife.persistence.domainObject.Place;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.Sheet;
import com.playlife.persistence.domainObject.Tag;
import com.playlife.persistence.domainObject.TextContent;
import com.playlife.utility.exceptions.LogicException;

@Component
@Qualifier("bookService")
public class BookService implements IBookService{
	@Autowired
	@Qualifier("accessService")
	private IAccessService accessService;
	@Autowired
	@Qualifier("bookSetDAO")
	private BookSetDAO bookSetDAO;
	@Autowired
	@Qualifier("bookDAO")
	private BookDAO bookDAO;
	@Autowired
	@Qualifier("sheetDAO")
	private SheetDAO sheetDAO;
	@Autowired
	@Qualifier("photoContentDAO")
	private PhotoContentDAO photoContentDAO;	
	@Autowired
	@Qualifier("textContentDAO")
	private TextContentDAO textContentDAO;
	
	@Override
	public BookSet addBookSet(BookSet new_bookSet, PlayLifeUser old_user) throws LogicException {
		try {
			PlayLifeUser user = accessService.checkUser(old_user.getUserId());
			BookSet bookSet = new BookSet(new_bookSet.getStartDate(), new_bookSet.getEndDate(), new_bookSet.getCoverPhoto(), new_bookSet.getCost(), new_bookSet.getTravelWith(), user);
			Book book = new Book(new_bookSet.getOriginalBook().getTitle(), true, user.getLanguage(), user);
			book.setBookSet(bookSet);
			bookSet.addBook(book);
			user.addBookSet(bookSet);
			bookSetDAO.save(bookSet);
			
			return bookSet;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public Sheet addSheet(Book old_book, Sheet new_sheet, PlayLifeUser user) throws LogicException {
		try {
			Book book = accessService.checkBookExists(old_book.getBookId());
			accessService.checkUserBook_owner(user, book);
			
			Sheet sheet = new Sheet(new_sheet.getTitle(), new_sheet.getDate(), new_sheet.getCost(), book);
			sheetDAO.save(sheet);
			return sheet;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public PhotoContent addPhotoContent(Sheet old_sheet, PhotoContent new_photoContent, PlayLifeUser user) throws LogicException {
		try {
			Sheet sheet = accessService.checkSheetExists(old_sheet.getSheetId());
			accessService.checkUserSheet_owner(user, sheet);
			
			PhotoContent photoContent = new PhotoContent(new_photoContent.getTitle(), new_photoContent.getLink(), new_photoContent.getCss(), sheet);
			photoContentDAO.save(photoContent);
			return photoContent;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public TextContent addTextContent(Sheet old_sheet, TextContent new_textContent, PlayLifeUser user) throws LogicException {
		try {
			Sheet sheet = accessService.checkSheetExists(old_sheet.getSheetId());
			accessService.checkUserSheet_owner(user, sheet);
			
			TextContent textContent = new TextContent(new_textContent.getContent(), new_textContent.getCss(), sheet);
			textContentDAO.save(textContent);
			
			return textContent;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public BookSet modifyBookSet(BookSet old_bookSet, PlayLifeUser user, boolean isUpdateAddress, boolean isUpdateTag) throws LogicException {
		try {
			BookSet bookSet = accessService.checkBookSetExists(old_bookSet.getBookSetId());
			accessService.checkUserBookSet_owner(user, bookSet);
			
			if (isUpdateAddress){
				bookSet.getAddresses().clear();
				for (Address old_address : old_bookSet.getAddresses()){
					Address address = accessService.getOrAddAddress(old_address.getName());
					bookSet.addAddress(address);
				}
			}
			
			if (old_bookSet.getCost() != -1f)
				bookSet.setCost(old_bookSet.getCost());
			
			if (old_bookSet.getCoverPhoto() != null)
				bookSet.setCoverPhoto(old_bookSet.getCoverPhoto());
			
			if (old_bookSet.getEndDate() != null)
				bookSet.setEndDate(old_bookSet.getEndDate());
			
			if (old_bookSet.getStartDate() != null)
				bookSet.setStartDate(old_bookSet.getStartDate());
			
			if (old_bookSet.getStatus() != Book_Status.Unknown)
				bookSet.setStatus(old_bookSet.getStatus());
			
			if (isUpdateTag){
				bookSet.getTags().clear();
				for (Tag old_tag : old_bookSet.getTags()){
					Tag tag = accessService.getOrAddTag(old_tag.getName());
					bookSet.addTag(tag);
				}
			}
			
			if (old_bookSet.getOriginalBook() != null && old_bookSet.getOriginalBook().getTitle() != null)
				bookSet.getOriginalBook().setTitle(old_bookSet.getOriginalBook().getTitle());
			if (old_bookSet.getTravelWith() != Book_TravelWith.Unknown)
				bookSet.setTravelWith(old_bookSet.getTravelWith());
			
			bookSet.setLastModifiedDate(new Date());
			
			bookSetDAO.update(bookSet);
			return bookSet;	
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}
	

	@Override
	public Book modifyBook(Book old_book, PlayLifeUser user) throws LogicException {
		try {
			Book book = accessService.checkBookExists(old_book.getBookId());
			accessService.checkUserBook_owner(user, book);
			
			if (old_book.getTitle() != null)
				book.setTitle(old_book.getTitle());
			
			book.setLastModifiedDate(new Date());
			
			bookDAO.update(book);
			return book;	
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}


	@Override
	public Sheet modifySheet(Sheet old_sheet, PlayLifeUser user, boolean isUpdateAddress, boolean isUpdatePlace) throws LogicException {
		try {
			Sheet sheet = accessService.checkSheetExists(old_sheet.getSheetId());
			accessService.checkUserSheet_owner(user, sheet);
			
			if (isUpdateAddress){
				sheet.getAddresses().clear();
				for (Address old_address : sheet.getAddresses()){
					Address address = accessService.getOrAddAddress(old_address.getName());
					sheet.addAddress(address);
				}
			}
			
			if (old_sheet.getCost() != -1f)
				sheet.setCost(old_sheet.getCost());
			if (old_sheet.getDate() != null)
				sheet.setDate(old_sheet.getDate());

			if (isUpdatePlace){
				sheet.getPlaces().clear();
				for (Place old_place : sheet.getPlaces()){
					Place place = accessService.getOrAddPlace(old_place.getName(), old_place.getAddress().getName(), old_place.getType());
					sheet.addPlace(place);
				}
			}
			
			if (old_sheet.getStatus() != Book_Status.Unknown)
				sheet.setStatus(old_sheet.getStatus());
			if (old_sheet.getTitle() != null)
				sheet.setTitle(old_sheet.getTitle());
			
			sheetDAO.update(sheet);
			return sheet;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public PhotoContent modifyPhotoContent(PhotoContent old_photoContent, PlayLifeUser user) throws LogicException {
		try {
			PhotoContent photoContent = accessService.checkPhotoContentExists(old_photoContent.getPhotoContentId());
			accessService.checkUserPhotoContent_owner(user, photoContent);
			
			if (old_photoContent.getCss() != null)
				photoContent.setCss(old_photoContent.getCss());
			if (old_photoContent.getLink() != null)
				photoContent.setLink(old_photoContent.getLink());
			if (old_photoContent.getTitle() != null)
				photoContent.setTitle(old_photoContent.getTitle());
			
			photoContentDAO.update(photoContent);
			return photoContent;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public TextContent modifyTextContent(TextContent old_textContent, PlayLifeUser user) throws LogicException {
		try {
			TextContent textContent = accessService.checkTextContentExists(old_textContent.getTextContentId());
			accessService.checkUserTextContent_owner(user, textContent);
			
			if (old_textContent.getContent() != null)
				textContent.setContent(old_textContent.getContent());
			if (old_textContent.getCss() != null)
				textContent.setCss(old_textContent.getCss());
			
			textContentDAO.update(textContent);
			return textContent;
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public void removeBook(Book old_book, PlayLifeUser user) throws LogicException {
		try {
			Book book = accessService.checkBookExists(old_book.getBookId());
			accessService.checkUserBook_owner(user, book);
			
			book.setStatus(Book_Status.Deleted);
			bookDAO.update(book);
			sheetDAO.sql_update_DeletedByBookId(book.getBookId());
			
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public void removeSheet(Sheet old_sheet, PlayLifeUser user) throws LogicException {
		try {
			Sheet sheet = accessService.checkSheetExists(old_sheet.getSheetId());
			accessService.checkUserSheet_owner(user, sheet);
			
			sheet.setStatus(Book_Status.Deleted);
			sheetDAO.update(sheet);
		} catch (Exception ex){
			throw new LogicException(-9999);
		}
	}

	@Override
	public void removePhotoContent(PhotoContent old_photoContent, PlayLifeUser user) throws LogicException {
		
	}

	@Override
	public void removeTextContent(TextContent old_textContent, PlayLifeUser user) throws LogicException {
		
	}

	@Override
	public Book getBook(Book old_book, PlayLifeUser user) throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(BookSet old_bookSet, PlayLifeUser user)
			throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Sheet getSheet(Sheet old_sheet, PlayLifeUser user)
			throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhotoContent getPhotoContent(PhotoContent old_photoContent, PlayLifeUser user)
			throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextContent getTextContent(TextContent old_textContent, PlayLifeUser user)
			throws LogicException {
		// TODO Auto-generated method stub
		return null;
	}

}
