import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //获取请求域中的数据
        String reqMsg = (String) req.getAttribute("reqKey");
        System.out.println(reqMsg);
        //做出响应
        resp.getWriter().write("ServletB response");
    }
}
