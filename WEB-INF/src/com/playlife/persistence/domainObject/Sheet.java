package com.playlife.persistence.domainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Sheet")
@Table(name = "Sheet")

@NamedNativeQueries({
	@NamedNativeQuery(
		name = "Sheet.sql_update_DeletedByBookId",
		query = "call sheet_updateDeletedByBookId(:bookId);",
		resultClass = Sheet.class
	)
})
public class Sheet implements Serializable{
	/****************
	 * 				*
	 * 	 Constant	*
	 * 				*
	 ****************/
	private static final long serialVersionUID = 1L;

	/********************
	 * 					*
	 * 	 Constructor	*
	 * 					*
	 ********************/
	public Sheet(String title, Date date, float cost, Book book, Book_Status status){
		this.setTitle(title);
		this.setDate(date);
		this.setCost(cost);
		this.setStatus(status);
		this.setBook(book);
		this.setTextContents(new HashSet<TextContent>());
		this.setPhotoContents(new HashSet<PhotoContent>());
		this.setComments(new HashSet<Comment>());
		this.setPlaces(new ArrayList<Place>());
		this.setAddresses(new ArrayList<Address>());
	}
	public Sheet(String title, Date date, float cost, Book book){
		this(title, date, cost, book, Book_Status.Normal);
	}
	public Sheet() {
		this(null, null, 0, null, Book_Status.Unknown);
	}
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getSheetId() {
		return sheetId;
	}
	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public Book_Status getStatus() {
		return status;
	}
	public void setStatus(Book_Status status) {
		this.status = status;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Set<PhotoContent> getPhotoContents() {
		return photoContents;
	}
	public void setPhotoContents(Set<PhotoContent> photoContents) {
		this.photoContents = photoContents;
	}
	public void addPhotoContent(PhotoContent photoContent) {
		this.addPhotoContent(photoContent);
	}
	public Set<TextContent> getTextContents() {
		return textContents;
	}
	public void setTextContents(Set<TextContent> textContents) {
		this.textContents = textContents;
	}
	public void addTextContent(TextContent textContent) {
		this.addTextContent(textContent);
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public void addComment(Comment comment) {
		this.addComment(comment);
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public void addAddress(Address address) {
		this.getAddresses().add(address);
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	public void addPlace(Place place) {
		this.getPlaces().add(place);
	}
	
	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sheetId", nullable=false, unique=true)
	public Long sheetId;
	
	@Column(name = "title")
	public String title;

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	public Date date;
	
	@Column(name = "cost")
	public float cost;
	
	@Column(name = "status")
	public Book_Status status;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_BOOKID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_BOOK_SHEET")
	public Book book;
	
	@OneToMany(mappedBy="sheet", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<PhotoContent> photoContents;
	
	@OneToMany(mappedBy="sheet", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<TextContent> textContents;
	
	@OneToMany(mappedBy="sheet", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public Set<Comment> comments;
	
	@ManyToMany(mappedBy="sheets", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public List<Place> places;
	
	@ManyToMany(mappedBy="sheets", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	public List<Address> addresses;
}
