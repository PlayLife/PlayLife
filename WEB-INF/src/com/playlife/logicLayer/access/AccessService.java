package com.playlife.logicLayer.access;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.persistenceLayer.DAO.AddressDAO;
import com.playlife.persistenceLayer.DAO.BookDAO;
import com.playlife.persistenceLayer.DAO.BookSetDAO;
import com.playlife.persistenceLayer.DAO.PhotoContentDAO;
import com.playlife.persistenceLayer.DAO.PlaceDAO;
import com.playlife.persistenceLayer.DAO.SheetDAO;
import com.playlife.persistenceLayer.DAO.TagDAO;
import com.playlife.persistenceLayer.DAO.TextContentDAO;
import com.playlife.persistenceLayer.DAO.UserDAO;
import com.playlife.persistenceLayer.DAO.UserLogDAO;
import com.playlife.persistenceLayer.domainObject.Address;
import com.playlife.persistenceLayer.domainObject.Book;
import com.playlife.persistenceLayer.domainObject.BookSet;
import com.playlife.persistenceLayer.domainObject.Book_Status;
import com.playlife.persistenceLayer.domainObject.PhotoContent;
import com.playlife.persistenceLayer.domainObject.Place;
import com.playlife.persistenceLayer.domainObject.Place_Type;
import com.playlife.persistenceLayer.domainObject.Sheet;
import com.playlife.persistenceLayer.domainObject.Tag;
import com.playlife.persistenceLayer.domainObject.TextContent;
import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.persistenceLayer.domainObject.UserLog;
import com.playlife.persistenceLayer.domainObject.UserLog_Type;
import com.playlife.settings.UserSetting;
import com.playlife.utility.exceptions.LogicLayerException;

@Component
@Qualifier("accessService")
public class AccessService implements IAccessService{
	/************************
	 * 						*
	 * 		Variable		* 
	 * 						*
	 ************************/
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
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
	@Override
	public User checkUser(Long uid) throws LogicLayerException {
		User user = userDAO.get(uid);
		if (user == null)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
		
		return user;
	}
	
	@Override
	public Book checkBookExists(Long bid) throws LogicLayerException {
		Book book = bookDAO.get(bid);
		if (book == null || book.getStatus() == Book_Status.Deleted)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
		
		return book;
	}

	@Override
	public Sheet checkSheetExists(Long sid) throws LogicLayerException {
		Sheet sheet = sheetDAO.get(sid);
		if (sheet == null)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
		
		return sheet;
	}

	@Override
	public PhotoContent checkPhotoContentExists(Long pcid) throws LogicLayerException {
		PhotoContent photoContent = photoContentDAO.get(pcid);
		if (photoContent == null)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
		
		return photoContent;
	}

	@Override
	public TextContent checkTextContentExists(Long tcid) throws LogicLayerException {
		TextContent textContent = textContentDAO.get(tcid);
		if (textContent == null)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
		
		return textContent;
	}

	@Override
	public Address checkAddressExists(Long addressId) throws LogicLayerException {
		Address address = addressDAO.get(addressId);
		if (address == null)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
		
		return address;
	}
	
	public void checkUserBook_owner(User user, Book book) throws LogicLayerException {
		if (!book.getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicLayerException(-9999);
	}
	
	@Override
	public void checkRegisterTrial(String ip) throws LogicLayerException {
		Date date = new Date();
		
		List<UserLog> logList = userLogDAO.hql_find_ByIpAndTypeAndDate(ip, UserLog_Type.REGISTER, date, UserSetting.REGISTER_INTERVAL);
		if (logList.size() > UserSetting.REGISTER_TRAIL)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
	}

	@Override
	public void checkLoginTrial(String ip) throws LogicLayerException {
		Date date = new Date();
		
		List<UserLog> logList = userLogDAO.hql_find_ByIpAndTypeAndDate(ip, UserLog_Type.LOGIN, date, UserSetting.LOGIN_INTERVAL);
		if (logList.size() > UserSetting.LOGIN_TRAIL)
			/*** TODO ***/
			throw new LogicLayerException(-9999);
	}

	@Override
	public void checkUserSheet_owner(User user, Sheet sheet) throws LogicLayerException {
		if (!sheet.getBook().getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicLayerException(-9999);
	}
	
	@Override
	public void checkUserPhotoContent_owner(User user, PhotoContent photoContent) throws LogicLayerException {
		if (!photoContent.getSheet().getBook().getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicLayerException(-9999);
	}

	@Override
	public void checkUserTextContent_owner(User user, TextContent textContent) throws LogicLayerException {
		if (!textContent.getSheet().getBook().getBookSet().getUser().getUserId().equals(user.getUserId()))
			throw new LogicLayerException(-9999);
	}

	@Override
	public Tag checkTagExists(Long tagId) throws LogicLayerException {
		Tag tag = tagDAO.get(tagId);
		if (tag == null)
			throw new LogicLayerException(-9999);
		
		return tag;
	}

	@Override
	public Address getOrAddAddress(String name) throws LogicLayerException {
		List<Address> addressList = addressDAO.hql_find_ByName(name);
		if (addressList.size() > 0)
			throw new LogicLayerException(-9999);
		else if (addressList.size() == 1)
			return addressList.get(0); 
			
		Address address = new Address(name);
		addressDAO.save(address);
		return address;
	}

	@Override
	public Tag getOrAddTag(String name) throws LogicLayerException {
		List<Tag> tagList = tagDAO.hql_find_ByName(name);
		if (tagList.size() > 0)
			throw new LogicLayerException(-9999);
		else if (tagList.size() == 1)
			return tagList.get(0); 
			
		Tag tag = new Tag(name);
		tagDAO.save(tag);
		return tag;
	}

	@Override
	public Place getOrAddPlace(String name, String addressName, Place_Type type) throws LogicLayerException {
		List<Place> placeList = placeDAO.hql_find_ByNameAndAddressAndType(name, addressName, type);
		if (placeList.size() > 0)
			throw new LogicLayerException(-9999);
		else if (placeList.size() == 1)
			return placeList.get(0); 
			
		Place place = new Place(name, type);
		Address address = this.getOrAddAddress(addressName);
		place.setAddress(address);
		placeDAO.save(place);
		return place;
	}

	@Override
	public BookSet checkBookSetExists(Long bookSetId) throws LogicLayerException {
		BookSet bookSet = bookSetDAO.get(bookSetId);
		if (bookSet == null || bookSet.getStatus().equals(Book_Status.Deleted))
			throw new LogicLayerException(-9999);
		
		return bookSet;
	}

	@Override
	public void checkUserBookSet_owner(User user, BookSet bookSet) throws LogicLayerException {
		if (!bookSet.getUser().getUserId().equals(user.getUserId()))
			throw new LogicLayerException(-9999);
	}
}
