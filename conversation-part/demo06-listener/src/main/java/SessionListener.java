import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    //监听session的创建
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("session:"+session.hashCode()+"created");
    }
    //监听session销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("session:"+ session.hashCode()+"destroyed");
    }
    //监听数据增加
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        HttpSession session = event.getSession();
        System.out.println("session"+ session.hashCode()+" add:"+name+"="+value);
    }
    //监听数据删除
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        HttpSession session = event.getSession();
        System.out.println("session"+ session.hashCode()+" remove:"+name+"="+value);
    }
    //监听数据改变
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        String name = event.getName();
        Object value = event.getValue();
        HttpSession session = event.getSession();
        Object newValue = session.getAttribute(name);
        System.out.println("session:"+session.hashCode()+" change:"+name+"="+value+" to "+newValue);
    }
}
