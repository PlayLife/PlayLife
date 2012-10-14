package com.playlife.logic.access;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistence.DAO.AddressDAO;
import com.playlife.persistence.DAO.BookDAO;
import com.playlife.persistence.DAO.BookSetDAO;
import com.playlife.persistence.DAO.PhotoContentDAO;
import com.playlife.persistence.DAO.PlaceDAO;
import com.playlife.persistence.DAO.PlayLifeUserDAO;
import com.playlife.persistence.DAO.SheetDAO;
import com.playlife.persistence.DAO.TagDAO;
import com.playlife.persistence.DAO.TextContentDAO;
import com.playlife.persistence.DAO.UserLogDAO;
import com.playlife.persistence.domainObject.Address;
import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.Book_Status;
import com.playlife.persistence.domainObject.PhotoContent;
import com.playlife.persistence.domainObject.Place;
import com.playlife.persistence.domainObject.Place_Type;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.Sheet;
import com.playlife.persistence.domainObject.Tag;
import com.playlife.persistence.domainObject.TextContent;
import com.playlife.persistence.domainObject.UserLog;
import com.playlife.persistence.domainObject.UserLog_Type;
import com.playlife.settings.UserSetting;
import com.playlife.utility.exceptions.LogicException;

@Component
@Qualifier("accessService")
public class AccessService {
	/************************
	 * 						*
	 * 		Variable		* 
	 * 						*
	 ************************/
	@Autowired
	@Qualifier("userDAO")
	private PlayLifeUserDAO playLifeUserDAO;
	
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
	@Qualifier("textContentDAO")
	private TextContentDAO textContentDAO;
	
	@Autowired
	@Qualifier("photoContentDAO")
	private PhotoContentDAO photoContentDAO;
	
	@Autowired
	@Qualifier("userLogDAO")
	private UserLogDAO userLogDAO;
	
	@Autowired
	@Qualifier("addressDAO")
	private AddressDAO addressDAO;
	
	@Autowired
	@Qualifier("tagDAO")
	private TagDAO tagDAO;
	
	@Autowired
	@Qualifier("placeDAO")
	private PlaceDAO placeDAO;
	
	/************************
	 * 						*
	 * 	Implementation		* 
	 * 						*
	 ************************/
	public PlayLifeUser checkUser(Long uid) throws LogicException {
		PlayLifeUser user = playLifeUserDAO.get(uid);
		if (user == null)
			/*** TODO ***/
			throw new LogicException(-9999);
		
		return user;
	}
	
	public Book checkBookExists(Long bid) throws LogicException {
		Book book = bookDAO.get(bid);
		if (book == null || book.getStatus() == Book_Status.Deleted)
			/*** TODO ***/
			throw new LogicException(-9999);
		
		return book;
	}

	public Sheet checkSheetExists(Long sid) throws LogicException {
		Sheet sheet = sheetDAO.get(sid);
		if (sheet == null)
			/*** TODO ***/
			throw new LogicException(-9999);
		
		return sheet;
	}

	public PhotoContent checkPhotoContentExists(Long pcid) throws LogicException {
		PhotoContent photoContent = photoContentDAO.get(pcid);
		if (photoContent == null)
			/*** TODO ***/
			throw new LogicException(-9999);
		
		return photoContent;
	}

	public TextContent checkTextContentExists(Long tcid) throws LogicException {
		TextContent textContent = textContentDAO.get(tcid);
		if (textContent == null)
			/*** TODO ***/
			throw new LogicException(-9999);
		
		return textContent;
	}

	public Address checkAddressExists(Long addressId) throws LogicException {
		Address address = addressDAO.get(addressId);
		if (address == null)
			/*** TODO ***/
			throw new LogicException(-9999);
		
		return address;
	}
	
	public void checkUserBook_owner(PlayLifeUser user, Book book) throws LogicException {
		if (!book.getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicException(-9999);
	}
	
	public Book checkUserBook_owner(Long userId, Long bookId) throws LogicException {
		List<Book> bookList = bookDAO.hql_find_ByBookIdAndUserId(bookId, userId);
		if (bookList.size() == 0)
			throw new LogicException(-9999);
		if (bookList.size() > 1)
			throw new LogicException(-9999);
		return bookList.get(0);
	}
	
	public void checkRegisterTrial(String ip) throws LogicException {
		Date date = new Date();
		
		List<UserLog> logList = userLogDAO.hql_find_ByIpAndTypeAndDate(ip, UserLog_Type.REGISTER, date, UserSetting.REGISTER_INTERVAL);
		if (logList.size() > UserSetting.REGISTER_TRAIL)
			/*** TODO ***/
			throw new LogicException(-9999);
	}

	public void checkLoginTrial(String ip) throws LogicException {
		Date date = new Date();
		
		List<UserLog> logList = userLogDAO.hql_find_ByIpAndTypeAndDate(ip, UserLog_Type.LOGIN, date, UserSetting.LOGIN_INTERVAL);
		if (logList.size() > UserSetting.LOGIN_TRAIL)
			/*** TODO ***/
			throw new LogicException(-9999);
	}

	public void checkUserSheet_owner(PlayLifeUser user, Sheet sheet) throws LogicException {
		if (!sheet.getBook().getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicException(-9999);
	}
	
	public void checkUserPhotoContent_owner(PlayLifeUser user, PhotoContent photoContent) throws LogicException {
		if (!photoContent.getSheet().getBook().getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicException(-9999);
	}

	public void checkUserTextContent_owner(PlayLifeUser user, TextContent textContent) throws LogicException {
		if (!textContent.getSheet().getBook().getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicException(-9999);
	}

	public Tag checkTagExists(Long tagId) throws LogicException {
		Tag tag = tagDAO.get(tagId);
		if (tag == null)
			throw new LogicException(-9999);
		
		return tag;
	}

	public Address getOrAddAddress(String name) throws LogicException {
		List<Address> addressList = addressDAO.hql_find_ByName(name);
		if (addressList.size() > 0)
			throw new LogicException(-9999);
		else if (addressList.size() == 1)
			return addressList.get(0); 
			
		Address address = new Address(name);
		addressDAO.save(address);
		return address;
	}

	public Tag getOrAddTag(String name) throws LogicException {
		List<Tag> tagList = tagDAO.hql_find_ByName(name);
		if (tagList.size() > 0)
			throw new LogicException(-9999);
		else if (tagList.size() == 1)
			return tagList.get(0); 
			
		Tag tag = new Tag(name);
		tagDAO.save(tag);
		return tag;
	}

	public Place getOrAddPlace(String name, String addressName, Place_Type type) throws LogicException {
		List<Place> placeList = placeDAO.hql_find_ByNameAndAddressAndType(name, addressName, type);
		if (placeList.size() > 0)
			throw new LogicException(-9999);
		else if (placeList.size() == 1)
			return placeList.get(0); 
			
		Place place = new Place(name, type);
		Address address = this.getOrAddAddress(addressName);
		place.setAddress(address);
		placeDAO.save(place);
		return place;
	}

	public BookSet checkBookSetExists(Long bookSetId) throws LogicException {
		BookSet bookSet = bookSetDAO.get(bookSetId);
		if (bookSet == null || bookSet.getStatus().equals(Book_Status.Deleted))
			throw new LogicException(-9999);
		
		return bookSet;
	}

	public void checkUserBookSet_owner(PlayLifeUser user, BookSet bookSet) throws LogicException {
		if (!bookSet.getUser().getUserId().equals(user.getUserId()))
			throw new LogicException(-9999);
	}
}
