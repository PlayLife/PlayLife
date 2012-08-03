package com.playlife.persistenceLayer.domainObject;

import java.io.Serializable;

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

@Entity(name = "PhotoContent")
@Table(name = "PhotoContent")
public class PhotoContent implements Serializable{
	/****************
	 * 				*
	 * 	 Constant	*
	 * 				*
	 ****************/
	private static final long serialVersionUID = 1L;
	private static String DEFAULTCSS = "";
	
	/********************
	 * 					*
	 * 	 Constructor	*
	 * 					*
	 ********************/
	public PhotoContent(String title, String link, String css, Sheet sheet){
		this.setTitle(title);
		this.setLink(link);
		this.setSheet(sheet);
		this.setCss(css);
	}
	public PhotoContent(String title, String link, Sheet sheet){
		this(title, link, DEFAULTCSS, sheet);
	}
	public PhotoContent() {
		this(null, null, null);
	}
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public String getTitle() {
		return title;
	}
	public Long getPhotoContentId() {
		return photoContentId;
	}
	public void setPhotoContentId(Long photoContentId) {
		this.photoContentId = photoContentId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}

	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "photoContentId", nullable=false, unique=true)
	public Long photoContentId;
	
	@Column (name = "title")
	public String title;
	
	@Column (name = "link")
	public String link;
	
	@Column (name = "css")
	public String css;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_SheetId", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_SHEET_PHOTOCONTENT")
	public Sheet sheet;
}
