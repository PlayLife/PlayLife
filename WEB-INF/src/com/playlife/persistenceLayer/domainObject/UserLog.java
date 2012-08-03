package com.playlife.persistenceLayer.domainObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "UserLog")
@Table(name = "UserLog")
@NamedQueries({
    @NamedQuery(name="UserLog.hql_find_ByIpAndTypeAndDate",
                query="from UserLog l where l.ip = :ip AND l.type = :type AND TIME_TO_SEC(TIMEDIFF(:date , l.createdDate)) <= :interval")
})
public class UserLog implements Serializable{
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
	public UserLog(UserLog_Type type, String ip, String status, String message, String email, String password){
		this.setType(type);
		this.setIp(ip);
		this.setStatus(status);
		this.setMessage(message);
		this.setEmail(email);
		this.setPassword(password);
		this.setCreatedDate(new Date());
	}
	
	/************************
	 * 						*
	 * 	Getter and Setter	*
	 * 						*
	 ************************/
	public Long getUserLogId() {
		return userLogId;
	}

	public void setUserLogId(Long userLogId) {
		this.userLogId = userLogId;
	}

	public UserLog_Type getType() {
		return type;
	}

	public void setType(UserLog_Type type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	/****************
	 * 				*
	 * 	DB Field	*
	 * 				*
	 ****************/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userLogId", nullable=false, unique=true)
	private Long userLogId;
	
	@Column(name="type") 
	@Enumerated(EnumType.STRING) 
	private UserLog_Type type;
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
}
