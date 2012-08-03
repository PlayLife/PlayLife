package com.playlife.utility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.context.ManagedSessionContext;

public class HibernateSessionFilter implements Filter {

	private SessionFactory sessionFactory;
	
	public static final String HIBERNATE_SESSION_KEY = "hibernateSession";
	public static final String END_OF_CONVERSATION_FLAG = "endOfConversation";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		org.hibernate.classic.Session currentSession;
		try {
			currentSession = sessionFactory.openSession();
			currentSession.setFlushMode(FlushMode.NEVER);
			ManagedSessionContext.bind(currentSession);
				
			Transaction transaction = currentSession.beginTransaction();
			
			chain.doFilter(request, response);
			
			transaction.commit();
			
			currentSession = ManagedSessionContext.unbind(sessionFactory);
			currentSession.close();
			
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