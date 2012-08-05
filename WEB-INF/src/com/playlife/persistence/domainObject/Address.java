package com.playlife.persistence.domainObject;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "Address")
@Table(name = "Address")
@NamedQueries({
    @NamedQuery(name="Address.hql_find_ByName",
                query="from Address a where a.name = :name")
})
public class Address implements Serializable{
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
	public Address(String name){
		this.setName(name);
		this.setBooks(new HashSet<Book>());
		this.setSheets(new ArrayList<Sheet>());
		this.setPlaces(new HashSet<Place>());
	}
	
	public Address(String name, Book book){
		this(name);
		if (book != null)
			this.addBook(book);
	}

	public Address(String name, Sheet sheet){
		this(name);
		if (sheet != null)
			this.addSheet(sheet);
	}
	
	public Address(String name, Place place){
		this(name);
		if (place != null)
			this.addPlace(place);
	}
	
	public Address(){
		this(null);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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

	public List<Sheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	public void addSheet(Sheet sheet) {
		this.getSheets().add(sheet);
	}
	
	public Set<Place> getPlaces() {
		return places;
	}

	public void setPlaces(Set<Place> places) {
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
	@Column(name = "addressId", nullable=false, unique=true)
	private Long addressId;
	
	@Column(name = "name", unique=true)
	private String name;
	
	@ManyToMany(cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Book_Address",
			joinColumns = {@JoinColumn(name="addressId")},
			inverseJoinColumns = {@JoinColumn(name="bookId")}
	)
	private Set<Book> books;
	
	@ManyToMany(cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Sheet_Address",
			joinColumns = {@JoinColumn(name="addressId")},
			inverseJoinColumns = {@JoinColumn(name="sheetId")}
	)
	@IndexColumn(name = "position")
	private List<Sheet> sheets;
	
	@OneToMany(mappedBy="address", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Place> places;
}
