import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletContextAttributeListener {
    //初始化监听器
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        System.out.println("application"+application.hashCode()+"initialized");
    }
    //监听器销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        System.out.println("application"+application.hashCode()+"destroyed");
    }
    //监听器数据增加
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        ServletContext application = event.getServletContext();
        System.out.println("application"+application.hashCode()+"add:"+name+"="+value);
    }
    //监听数据移除
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        ServletContext servletContext = event.getServletContext();
        System.out.println("servletContext"+servletContext.hashCode()+"remove:"+name+"="+value);
    }
    //监听数据修改
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        ServletContext servletContext = event.getServletContext();
        Object newValue = servletContext.getAttribute(name);
        System.out.println("servletContext"+servletContext.hashCode()+"change:"+name+"="+value+"to"+newValue);
    }
}
