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

@Entity(name = "Comment")
@Table(name = "Comment")
public class Comment implements Serializable{
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
	public Comment(PlayLifeUser user, Sheet sheet, String comment) {
		this.setUser(user);
		this.setSheet(sheet);
		this.setComment(comment);
	}
	public Comment(){
		this(null, null, null);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public String getComment() {
		return comment;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Sheet getSheet() {
		return sheet;
	}
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
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
	@Column(name = "commentId", nullable=false, unique=true)
	public Long commentId;

	@Column(name = "comment")
	public String comment;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_SheetId", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_SHEET_COMMENT")
	public Sheet sheet;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="FK_UserId", nullable = false)
	@org.hibernate.annotations.ForeignKey(name="FK_USER_COMMENT")
	public PlayLifeUser user;
}
