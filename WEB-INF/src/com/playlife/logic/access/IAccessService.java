package com.playlife.logic.access;

import com.playlife.persistence.domainObject.Address;
import com.playlife.persistence.domainObject.Book;
import com.playlife.persistence.domainObject.BookSet;
import com.playlife.persistence.domainObject.PhotoContent;
import com.playlife.persistence.domainObject.Place;
import com.playlife.persistence.domainObject.Place_Type;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.Sheet;
import com.playlife.persistence.domainObject.Tag;
import com.playlife.persistence.domainObject.TextContent;
import com.playlife.utility.exceptions.LogicException;

public interface IAccessService {
	public PlayLifeUser checkUser(Long uid) throws LogicException;
	public BookSet checkBookSetExists(Long bookSetId) throws LogicException;
	public Book checkBookExists(Long bid) throws LogicException;
	public Sheet checkSheetExists(Long sid) throws LogicException;
	public PhotoContent checkPhotoContentExists (Long pcid) throws LogicException;
	public TextContent checkTextContentExists (Long tcid) throws LogicException;
	public Address checkAddressExists(Long addressId) throws LogicException;
	public Tag checkTagExists(Long tagId) throws LogicException;
	public Address getOrAddAddress(String address) throws LogicException;
	public Tag getOrAddTag(String tag) throws LogicException;
	public Place getOrAddPlace(String name, String address, Place_Type Type) throws LogicException;
	public void checkRegisterTrial(String ip) throws LogicException;
	public void checkLoginTrial(String ip) throws LogicException;
	public void checkUserBookSet_owner(PlayLifeUser user, BookSet bookSet) throws LogicException;
	public void checkUserBook_owner(PlayLifeUser user, Book book) throws LogicException;
	public void checkUserSheet_owner(PlayLifeUser user, Sheet sheet) throws LogicException;
	public void checkUserPhotoContent_owner(PlayLifeUser user, PhotoContent photoContent) throws LogicException;
	public void checkUserTextContent_owner(PlayLifeUser user, TextContent textContent) throws LogicException;
}