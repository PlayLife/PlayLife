package com.playlife.persistence.domainObjects;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	/********************************
	 * 								*
	 * 			DB Field			*
	 * 								*
	 ********************************/ 
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long userId;
	
	@Persistent
    private String email;
	
	@Persistent
    private String password;
	
	@Persistent
    private String username;
	
	@Persistent
	private Type_UserRole userRole;
	
	/********************************
	 * 								*
	 * 			Constructor			*
	 * 								*
	 ********************************/
	public User(String username, String password, String email, Type_UserRole userRole){
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setUserRole(userRole);
	}
	public User(){
		
	}
	
	/********************************
	 * 								*
	 * 		Getter and Setter		*
	 * 								*
	 ********************************/ 
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Type_UserRole getUserRole() {
		return userRole;
	}
	
	public void setUserRole(Type_UserRole userRole) {
		this.userRole = userRole;
	}
}
