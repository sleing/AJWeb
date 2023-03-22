package org.ddd.app.login;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by gao on 16-4-14.
 */
public class OnlineListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {
    private ServletContext application = null;
    //往会话中添加属性时回调的方法
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        //取得用户名列表
        List<String> online = (List<String>) this.application.getAttribute("online");
        if ("username".equals(httpSessionBindingEvent.getName())) {
            //将当前用户名添加到列表中
            online.add((String) httpSessionBindingEvent.getValue());
        }
        //将添加后的列表重新设置到application属性中
        this.application.setAttribute("online", online);
    }
    //应用上下文初始化会回调的方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化一个application对象
        this.application = servletContextEvent.getServletContext();
        //设置一个列表属性，用于保存在线用户名
        this.application.setAttribute("online",new LinkedList<String>());
    }
    //会话销毁时会回调的方法
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //取得用户名列表
        List<String> online = (List<String>) this.application.getAttribute("online");
        //取得当前用户名
        String username = (String) httpSessionEvent.getSession().getAttribute("username");
        //将此用户名从列表中删除
        online.remove(username);
        //将删除后的列表重新设置到application属性中
        this.application.setAttribute("online", online);
    }
    //以下方法用空实现
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
    }
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}