import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/servletD")
public class ServletD extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从请求域中获取数据
        String reqMsg = (String) req.getAttribute("request");
        System.out.println(reqMsg);
        //从会话域中获取数据
        HttpSession session = req.getSession();
        String sessionMsg = (String) session.getAttribute("session");
        System.out.println(sessionMsg);

        //从应用域中获取数据
        ServletContext application = getServletContext();
        String appMsg = (String) application.getAttribute("application");
        System.out.println(appMsg);
    }
}
