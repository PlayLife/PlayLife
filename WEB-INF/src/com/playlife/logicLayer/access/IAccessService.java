package com.playlife.logicLayer.access;

import com.playlife.persistenceLayer.domainObject.Address;
import com.playlife.persistenceLayer.domainObject.Book;
import com.playlife.persistenceLayer.domainObject.BookSet;
import com.playlife.persistenceLayer.domainObject.PhotoContent;
import com.playlife.persistenceLayer.domainObject.Place;
import com.playlife.persistenceLayer.domainObject.Place_Type;
import com.playlife.persistenceLayer.domainObject.Sheet;
import com.playlife.persistenceLayer.domainObject.Tag;
import com.playlife.persistenceLayer.domainObject.TextContent;
import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.utility.exceptions.LogicLayerException;

public interface IAccessService {
	public User checkUser(Long uid) throws LogicLayerException;
	public BookSet checkBookSetExists(Long bookSetId) throws LogicLayerException;
	public Book checkBookExists(Long bid) throws LogicLayerException;
	public Sheet checkSheetExists(Long sid) throws LogicLayerException;
	public PhotoContent checkPhotoContentExists (Long pcid) throws LogicLayerException;
	public TextContent checkTextContentExists (Long tcid) throws LogicLayerException;
	public Address checkAddressExists(Long addressId) throws LogicLayerException;
	public Tag checkTagExists(Long tagId) throws LogicLayerException;
	public Address getOrAddAddress(String address) throws LogicLayerException;
	public Tag getOrAddTag(String tag) throws LogicLayerException;
	public Place getOrAddPlace(String name, String address, Place_Type Type) throws LogicLayerException;
	public void checkRegisterTrial(String ip) throws LogicLayerException;
	public void checkLoginTrial(String ip) throws LogicLayerException;
	public void checkUserBookSet_owner(User user, BookSet bookSet) throws LogicLayerException;
	public void checkUserBook_owner(User user, Book book) throws LogicLayerException;
	public void checkUserSheet_owner(User user, Sheet sheet) throws LogicLayerException;
	public void checkUserPhotoContent_owner(User user, PhotoContent photoContent) throws LogicLayerException;
	public void checkUserTextContent_owner(User user, TextContent textContent) throws LogicLayerException;
}