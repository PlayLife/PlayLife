package com.playlife.logic.user;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.logic.access.IAccessService;
import com.playlife.persistence.DAO.PlayLifeUserDAO;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.User_Role;
import com.playlife.persistence.domainObject.User_Type;
import com.playlife.settings.UserSetting;
import com.playlife.utility.Random;
import com.playlife.utility.exceptions.LogicException;

@Component
@Qualifier("userService")
public class PlayLifeUserService {
	/************************
	 * 						*
	 * 		Variable		* 
	 * 						*
	 ************************/
	@Autowired
	@Qualifier("userDAO")
	private PlayLifeUserDAO playLifeUserDAO;
	
	@Autowired
	@Qualifier("accessService")
	private IAccessService accessService;
	
	/************************
	 * 						*
	 * 	Implementation		* 
	 * 						*
	 ************************/
	public PlayLifeUser register(String email, String password, String username, User_Type type, User_Role role) throws LogicException {
		try {
			/* Step 3 : submit to DB */
			PlayLifeUser user = new PlayLifeUser(email, password, username, type, role);
			playLifeUserDAO.save(user);
			
			return user;
		} catch (Exception ex){
			throw new LogicException(-30007, ex);
		}
	}
	
	public PlayLifeUser login(PlayLifeUser old_user) throws LogicException {
		try {
			/* Step 1 : Check email and password */
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmailAndPassword(old_user.getEmail(), old_user.getPassword());
			if (userList == null || userList.size() <= 0)
				throw new LogicException(-30010);
			
			PlayLifeUser user = userList.get(0);
			
			return user;
		} catch (Exception ex){
			throw new LogicException(-30011, ex);
		}
	}
	
	public void logout(HttpServletRequest request) throws LogicException {
		try {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.setAttribute(UserSetting.USER_SESSIONNAME_USER, null);
			}
		} catch (Exception ex){
			throw new LogicException(-30012, ex);			
		}
	}

	public PlayLifeUser getUserByUserId(PlayLifeUser user) throws LogicException {
		try {
			return accessService.checkUser(user.getUserId());
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}

	public PlayLifeUser getUserByEmailAllowNull(String email) throws LogicException {
		try {
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmail(email);
			if (userList.size() > 1)
				throw new LogicException(-9999);
			
			if (userList.size() == 0)
				return null;
			
			return userList.get(0);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public boolean checkEmailExists(String email) throws LogicException {
		try {
			List<PlayLifeUser> userList = playLifeUserDAO.hql_find_ByEmail(email);
			return (userList.size() >= 1);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public int getUserCount() {
		try {
			return playLifeUserDAO.count();
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public Long getAllCount(String search) {
		try {
			search = "%" + search + "%";
			return playLifeUserDAO.hql_count_All(search);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public List<PlayLifeUser> getAll(String search, int start, int end, String orderByField, String orderByType){
		try {
			PlayLifeUser.class.getDeclaredField(orderByField);
			if (!orderByType.equalsIgnoreCase("ASC") && !orderByType.equalsIgnoreCase("DESC"))
				throw new LogicException(-9999);
			
			search = "%" + search + "%";
			
			return playLifeUserDAO.hql_find_All(start, end, orderByField, orderByType, search);
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public void setDisable(Long userId, boolean isDisabled) {
		try {
			PlayLifeUser user = accessService.checkUser(userId);
			user.setDisabled(isDisabled);
			playLifeUserDAO.flush();
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public void changePassword(String email, String forgotCode, String password){
		try {
			PlayLifeUser user = this.getUserByEmailAllowNull(email);
			if (user == null || !user.getForgotCode().equals(forgotCode))
				throw new LogicException(-9999);
			
			user.setPassword(password);
			user.setForgotCode(null);
			playLifeUserDAO.flush();
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	public void sendForgotPasswordEmail(String email){
		try {
			PlayLifeUser user = this.getUserByEmailAllowNull(email);
			if (user != null) {
				/* Step 1 : Random Code */
				String code = Integer.toString(Math.round(Math.abs(Random.randomInt())/100));
				user.setForgotCode(code);
				playLifeUserDAO.flush();
				
				/* Step 2 : Send Email */
				Properties props_playlife = new Properties();
				props_playlife.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("../email.properties"));
				
				String from = props_playlife.getProperty("mail.from");
				String username = props_playlife.getProperty("mail.username");
				String password = props_playlife.getProperty("mail.password");
				String smtpAddress = props_playlife.getProperty("mail.host");
				String smtpPort = props_playlife.getProperty("mail.port");
				String requireAuth = props_playlife.getProperty("mail.auth");
				
				Properties props = new Properties();
				props.put("mail.smtp.host", smtpAddress);
				props.put("mail.smtp.port", smtpPort);
				props.put("mail.debug", "" + false);
				props.put("mail.smtp.auth", requireAuth);
				
				final String username_final = username;
				final String password_final = password;
				Authenticator authenticator = new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username_final, password_final);
		            }
		        };
		        Session session = Session.getDefaultInstance(props, authenticator);
		        MimeMessage msg = new MimeMessage(session);
				
				msg.setSubject(s_forgotPassword_subject);
				msg.setContent(String.format(s_forgotPassword_content, user.getUsername(), code), "text/html");
				
				msg.setFrom(new InternetAddress(from));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				msg.saveChanges();
				Transport transport = session.getTransport("smtp");
				transport.connect(smtpAddress, username, password);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
			}
		} catch (Exception ex){
			throw new LogicException(-9999, ex);
		}
	}
	
	private static String s_forgotPassword_subject = "PlayLife - Reset your password";
	private static String s_forgotPassword_content = "<html><body>" +
													 "<p>Hi %s,</p>" +
													 "<p>Please enter your reset code: </p><hr />" +
													 "<p>%s</p><hr />" + 
													 "</body></html>";
}
