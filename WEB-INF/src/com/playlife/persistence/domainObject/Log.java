package com.playlife.persistence.domainObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Log")
@Table(name = "Log")
public class Log implements Serializable{
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
	public Log(String ip, String presentationException, String logicException, String persistenceException){
		this.setIp(ip);
		this.setPresentationException(presentationException);
		this.setLogicException(logicException);
		this.setPersistenceException(persistenceException);
		this.setCreatedDate(new Date());
	}
	
	public Log() {
		this(null, null, null, null);
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPresentationException() {
		return presentationException;
	}

	public void setPresentationException(String presentationException) {
		this.presentationException = presentationException;
	}

	public String getLogicException() {
		return logicException;
	}

	public void setLogicException(String logicException) {
		this.logicException = logicException;
	}

	public String getPersistenceException() {
		return persistenceException;
	}

	public void setPersistenceException(String persistenceException) {
		this.persistenceException = persistenceException;
	}

	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "logId", nullable=false, unique=true)
	private Long logId;
	
	@Column(name = "ip", nullable=false)
	private String ip;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "presentationException")
	private String presentationException;
	
	@Column(name = "logicException")
	private String logicException;
	
	@Column(name = "persistenceException")
	private String persistenceException;
}