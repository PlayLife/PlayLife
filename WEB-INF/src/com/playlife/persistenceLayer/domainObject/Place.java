package com.playlife.persistenceLayer.domainObject;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
    @NamedQuery(name="Place.hql_find_ByNameAndAddressAndType",
                query="from Place p where p.name = :name AND p.address.name = :address AND p.type = :type")
})
@Entity(name = "Place")
@Table(name = "Place")
public class Place implements Serializable {
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
	public Place(String name, Place_Type type){
		this.setName(name);
		this.setType(type);
		this.setRatings(new HashSet<PlaceRating>());
		this.setSheets(new ArrayList<Sheet>());
	}
		
	public Place(){
		this(null, Place_Type.Unknown);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PlaceRating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<PlaceRating> ratings) {
		this.ratings = ratings;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public Place_Type getType() {
		return type;
	}

	public void setType(Place_Type type) {
		this.type = type;
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
	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "placeId", nullable=false, unique=true)
	private Long placeId;
	
	@Column (name = "name")
	private String name;
	
	@Column(name="type") 
	@Enumerated(EnumType.STRING) 
	private Place_Type type;
	
	@Column (name = "rating")
	private float rating;
	
	@Column (name = "ratingCount")
	private int ratingCount;
	
	@OneToMany(mappedBy="place", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<PlaceRating> ratings;
	
	@ManyToMany(cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Sheet_Place",
			joinColumns = {@JoinColumn(name="placeId")},
			inverseJoinColumns = {@JoinColumn(name="sheetId")}
	)
	@IndexColumn(name = "position")
	private List<Sheet> sheets;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_AddressId", nullable=false)
	@org.hibernate.annotations.ForeignKey(name="FK_PLACE_ADDRESS")	
	private Address address;
}
