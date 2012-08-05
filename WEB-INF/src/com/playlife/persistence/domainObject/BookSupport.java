package com.playlife.persistence.domainObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "BookSupport")
@Table(name = "BookSupport")
public class BookSupport implements Serializable{
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
	public BookSupport(Book book, PlayLifeUser user){
		this.setCreatedDate(new Date());
		this.setBook(book);
		this.setUser(user);
	}
	
	public BookSupport(){
		this(null, null);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getBookSupportId() {
		return bookSupportId;
	}
	public void setBookSupportId(Long bookSupportId) {
		this.bookSupportId = bookSupportId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public PlayLifeUser getUser() {
		return user;
	}
	public void setUser(PlayLifeUser user) {
		this.user = user;
	}
	
	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookSupportId", nullable=false, unique=true)
	private Long bookSupportId;
	
	@Column(name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_BookId", nullable=false)
	@org.hibernate.annotations.ForeignKey(name="FK_BOOK_BOOKSUPPORT")
	private Book book;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_UserId", nullable=false)
	@org.hibernate.annotations.ForeignKey(name="FK_USER_BOOKSUPPORT")
	private PlayLifeUser user;
	
}
