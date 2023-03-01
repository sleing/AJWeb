package org.ddd.web.listener;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class EncodingListener implements ServletRequestListener {
public void requestDestroyed(ServletRequestEvent req) {
}
 
public void requestInitialized(ServletRequestEvent req) {
	try {		
		((HttpServletRequest) req.getServletRequest()).setCharacterEncoding("UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
}
}