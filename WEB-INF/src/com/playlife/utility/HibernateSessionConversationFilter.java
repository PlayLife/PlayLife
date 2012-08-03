package com.playlife.utility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.context.ManagedSessionContext;

public class HibernateSessionConversationFilter implements Filter {

	private SessionFactory sessionFactory;
	
	public static final String HIBERNATE_SESSION_KEY = "hibernateSession";
	public static final String END_OF_CONVERSATION_FLAG = "endOfConversation";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		org.hibernate.classic.Session currentSession;
		
		HttpSession httpSession = ((HttpServletRequest) request).getSession();
		Session disconnectedSession = (Session) httpSession.getAttribute(HIBERNATE_SESSION_KEY);
		
		try {
			if (disconnectedSession == null) {
			    currentSession = sessionFactory.openSession();
			    currentSession.setFlushMode(FlushMode.NEVER);
			} else {
			    currentSession = (org.hibernate.classic.Session) disconnectedSession;
			}
			
			ManagedSessionContext.bind(currentSession);
			
			currentSession.beginTransaction();
		
			chain.doFilter(request, response);
		
			currentSession = ManagedSessionContext.unbind(sessionFactory);
		
			if (request.getAttribute(END_OF_CONVERSATION_FLAG) != null || request.getParameter(END_OF_CONVERSATION_FLAG) != null) {
				currentSession.flush();
				currentSession.getTransaction().commit();
				currentSession.close();
				httpSession.setAttribute(HIBERNATE_SESSION_KEY, null);
			} else {
				if (currentSession.getTransaction().isActive())
					currentSession.getTransaction().commit();
				httpSession.setAttribute(HIBERNATE_SESSION_KEY, currentSession);
		    }
	
		} catch (StaleObjectStateException staleEx) {
			throw staleEx;
		} catch (Throwable ex) {
			try {
			    if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
			    	sessionFactory.getCurrentSession().getTransaction().rollback();
			    }
			} catch (Throwable rbEx) {
			} finally {
				currentSession = ManagedSessionContext.unbind(sessionFactory);
				if (currentSession != null) currentSession.close();
				httpSession.setAttribute(HIBERNATE_SESSION_KEY, null);
			}
		    throw new ServletException(ex);
		}
	}

	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}