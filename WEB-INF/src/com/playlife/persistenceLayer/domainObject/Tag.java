package com.playlife.persistenceLayer.domainObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "Tag")
@Table(name = "Tag")
@NamedQueries({
    @NamedQuery(name="Tag.hql_find_ByName",
                query="from Tag t where t.name = :name")
}) 
public class Tag implements Serializable{
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
	public Tag(String name){
		this.setName(name);
		this.setBooks(new HashSet<Book>());
	}
	
	public Tag(String name, Book book){
		this(name);
		if (book != null)
			this.addBook(book);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tagId", nullable=false, unique=true)
	public Long tagId;
		
	@Column(name = "name")
	public String name;
	
	@ManyToMany(cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Book_Tag",
			joinColumns = {@JoinColumn(name="tagId")},
			inverseJoinColumns = {@JoinColumn(name="bookId")}
	)
	public Set<Book> books;
}
