package com.playlife.persistenceLayer.domainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "BookSet")
@Table(name = "BookSet")
public class BookSet implements Serializable{
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
	public BookSet(Date startDate, Date endDate, String coverPhoto, float cost, Book_TravelWith travelWith, User user, Book_Status status){
		this.setUser(user);
		this.setStatus(status);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setCoverPhoto(coverPhoto);
		this.setCost(cost);
		this.setTravelWith(travelWith);
		this.setBookSupports(new HashSet<BookSupport>());
		this.setAddresses(new ArrayList<Address>());
		this.setTags(new HashSet<Tag>());
		this.setBooks(new HashSet<Book>());
		this.setCreatedDate(new Date());
		this.setLastModifiedDate(new Date());
	}

	public BookSet(Date startDate, Date endDate, String coverPhoto, float cost, Book_TravelWith travelWith, User user){
		this(startDate, endDate, coverPhoto, cost, travelWith, user, Book_Status.Normal);
	}
	
	public BookSet(){
		this(null, null, null, -1.0f, Book_TravelWith.Unknown, null, Book_Status.Unknown);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getBookSetId() {
		return bookSetId;
	}

	public void setBookSetId(Long bookSetId) {
		this.bookSetId = bookSetId;
	}

	public Book getOriginalBook() {
		return originalBook;
	}

	public void setOriginalBook(Book originalBook) {
		this.originalBook = originalBook;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		this.getBooks().add(book);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book_Status getStatus() {
		return status;
	}

	public void setStatus(Book_Status status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCoverPhoto() {
		return coverPhoto;
	}
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public Book_TravelWith getTravelWith() {
		return travelWith;
	}
	public void setTravelWith(Book_TravelWith travelWith) {
		this.travelWith = travelWith;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public void addTag(Tag tag) {
		this.getTags().add(tag);
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public Set<BookSupport> getBookSupports() {
		return bookSupports;
	}
	public void setBookSupports(Set<BookSupport> bookSupports) {
		this.bookSupports = bookSupports;
	}
	public void addBookSupport(BookSupport bookSupport) {
		this.getBookSupports().add(bookSupport);
	}
	public void addAddress(Address address) {
		this.getAddresses().add(address);
	}
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookSetId", nullable=false, unique=true)
	private Long bookSetId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_BOOKID", nullable = true)
	@org.hibernate.annotations.ForeignKey(name="FK_BOOKSET_BOOK")
	private Book originalBook;
	
	@OneToMany(mappedBy="bookSet", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Book> books;
	
	@Column(name="status") 
	@Enumerated(EnumType.STRING) 
	private Book_Status status;
	
	@Column (name = "startDate")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column (name = "endDate")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column (name = "coverPhoto")
	private String coverPhoto;
	
	@Column (name = "cost")
	private float cost;
	
	@Column(name="travelWith") 
	@Enumerated(EnumType.STRING) 
	private Book_TravelWith travelWith;

	@Column (name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column (name = "lastModifiedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@ManyToMany(mappedBy = "books", cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<Tag> tags;
	
	@OneToMany(mappedBy="book", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<BookSupport> bookSupports;
	
	@ManyToMany(mappedBy = "books", cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<Address> addresses;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_USERID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_USER_BOOKSET")
	private User user;
}
