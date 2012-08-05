package com.playlife.persistence.domainObject;

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

@Entity(name = "TextContent")
@Table(name = "TextContent")
public class TextContent implements Serializable{
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
	public TextContent(String content, String css, Sheet sheet){
		this.setContent(content);
		this.setCss(css);
		this.setSheet(sheet);
	}
	public TextContent(String content, Sheet sheet){
		this(content, null, sheet);
	}
	public TextContent() {
		this(null, null);
	}
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getTextContentId() {
		return textContentId;
	}
	public void setTextContentId(Long textContentId) {
		this.textContentId = textContentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "textContentId", nullable=false, unique=true)
	public Long textContentId;
	
	@Column (name = "content")
	public String content;
	
	@Column (name = "css")
	public String css;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_SheetId", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_SHEET_TEXTCONTENT")
	public Sheet sheet;
}
