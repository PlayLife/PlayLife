package com.playlife.persistence.domainObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity(name = "Book")
@Table(name = "Book")
@NamedNativeQueries({
	@NamedNativeQuery(
		name = "Book.sql_update_DeletedByBookSetId",
		query = "call book_updateDeletedByBookSetId(:bookSetId);",
		resultClass = Sheet.class
	)
})
public class Book implements Serializable{
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
	public Book(String title, boolean isOriginal, Book_Privacy privacy, Book_Status status, Locale language, PlayLifeUser user){
		this.setTitle(title);
		this.setOriginal(isOriginal);
		this.setPrivacy(privacy);
		this.setStatus(status);
		this.setLanguage(language);
		this.setUser(user);
		this.setCreatedDate(new Date());
		this.setLastModifiedDate(new Date());
		this.setReports(new HashSet<Report>());
	}
	
	public Book(String title, boolean isOriginal, Locale locale, PlayLifeUser user){
		this(title, isOriginal, Book_Privacy.Private, Book_Status.Normal, locale, user);
	}

	public Book(){
		this(null, false, Book_Privacy.Unknown, Book_Status.Unknown, Locale.getDefault(), null);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Book_Privacy getPrivacy() {
		return privacy;
	}
	public void setPrivacy(Book_Privacy privacy) {
		this.privacy = privacy;
	}
	public Book_Status getStatus() {
		return status;
	}
	public void setStatus(Book_Status status) {
		this.status = status;
	}
	public Set<Report> getReports() {
		return reports;
	}
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	public void addReport(Report report) {
		this.getReports().add(report);
	}	
	public Set<Sheet> getSheets() {
		return sheets;
	}
	public void setSheets(Set<Sheet> sheets) {
		this.sheets = sheets;
	}
	public void addSheet(Sheet sheet) {
		this.getSheets().add(sheet);
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
	public BookSet getBookSet() {
		return bookSet;
	}
	public void setBookSet(BookSet bookSet) {
		this.bookSet = bookSet;
	}
	public PlayLifeUser getUser() {
		return user;
	}
	public void setUser(PlayLifeUser user) {
		this.user = user;
	}
	public boolean isOriginal() {
		return isOriginal;
	}
	public void setOriginal(boolean isOriginal) {
		this.isOriginal = isOriginal;
	}
	public Locale getLanguage() {
		return language;
	}
	public void setLanguage(Locale language) {
		this.language = language;
	}

	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId", nullable=false, unique=true)
	private Long bookId;
	
	@Column (name = "title")
	private String title;
	
	@Column(name="language") 
	private Locale language;
	
	@Column(name="privacy") 
	@Enumerated(EnumType.STRING) 
	private Book_Privacy privacy;
	
	@Column(name="status") 
	@Enumerated(EnumType.STRING) 
	private Book_Status status;
	
	@Column (name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column (name = "lastModifiedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@OneToMany(mappedBy="book", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Report> reports;
	
	@OneToMany(mappedBy="book", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Sheet> sheets;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_BOOKSETID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_BOOKSET_BOOK")
	private BookSet bookSet;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_USERID", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_USER_BOOK")
	private PlayLifeUser user;
	
	@Column(name = "isOriginal")
	@Type(type="true_false")
	private boolean isOriginal; 
}
