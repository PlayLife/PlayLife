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

@Entity(name = "PlaceRating")
@Table(name = "PlaceRating")
public class PlaceRating implements Serializable{
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
	public PlaceRating(int rate, Place place, PlayLifeUser user){
		this.setRate(rate);
		this.setPlace(place);
		this.setUser(user);
		this.setCreatedDate(new Date());
	}
		
	public PlaceRating(){
		this(0, null, null);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getPlaceRatingId() {
		return placeRatingId;
	}

	public void setPlaceRatingId(Long placeRatingId) {
		this.placeRatingId = placeRatingId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
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
	@Column(name = "placeRatingId", nullable=false, unique=true)
	private Long placeRatingId;
	
	@Column (name = "createdDate")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

	@Column(name="rate")
    public int rate; 
    
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_PlaceId", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_PLACE_PLACERATING")
    private Place place;

	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_UserId", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_USER_PLACERATING")
    private PlayLifeUser user;
}
