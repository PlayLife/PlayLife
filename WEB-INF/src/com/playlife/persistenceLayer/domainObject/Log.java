package com.playlife.persistenceLayer.domainObject;

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
	public Log(String ip, String presentationLayerException, String logicLayerException, String persistenceLayerException){
		this.setIp(ip);
		this.setPresentationLayerException(presentationLayerException);
		this.setLogicLayerException(logicLayerException);
		this.setPersistenceLayerException(persistenceLayerException);
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

	public String getPresentationLayerException() {
		return presentationLayerException;
	}

	public void setPresentationLayerException(String presentationLayerException) {
		this.presentationLayerException = presentationLayerException;
	}

	public String getLogicLayerException() {
		return logicLayerException;
	}

	public void setLogicLayerException(String logicLayerException) {
		this.logicLayerException = logicLayerException;
	}

	public String getPersistenceLayerException() {
		return persistenceLayerException;
	}

	public void setPersistenceLayerException(String persistenceLayerException) {
		this.persistenceLayerException = persistenceLayerException;
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
	
	@Column(name = "presentationLayerException")
	private String presentationLayerException;
	
	@Column(name = "logicLayerException")
	private String logicLayerException;
	
	@Column(name = "persistenceLayerException")
	private String persistenceLayerException;
}