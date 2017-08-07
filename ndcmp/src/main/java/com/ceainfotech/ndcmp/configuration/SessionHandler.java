package com.ceainfotech.ndcmp.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author bosco
 *
 */
public class SessionHandler implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		se.getSession().setMaxInactiveInterval(15 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session Destroyed");// TODO Auto-generated method
	}

}
