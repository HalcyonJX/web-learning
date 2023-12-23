import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //向请求域中添加数据
        req.setAttribute("reqKey","reqMsg");
        //响应重定向
        //重定向到servlet动态资源
//        resp.sendRedirect("servletB");
        //重定向到视图静态资源
        resp.sendRedirect("welcome.html");
        //重定向到WEB-INF下的资源   不行  404
//        resp.sendRedirect("WEB-INF/views/view1.html");
        //重定向到外部资源
//        resp.sendRedirect("https://www.qq.com");
    }
}
