import jakarta.servlet.RequestDispatcher;
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
        //获取请求转发器
        //转发给 servletB
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("servletB");
        //转发给一个视图资源
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcome.html");
        //转发 WEB-INF下的资源
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/view1.html");
        //转发外部资源   报错 404
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("https://www.qq.com");
        //获取请求参数
        String username = req.getParameter("username");
        System.out.println(username);
        //向请求域中添加数据
        req.setAttribute("reqKey","requestMsg");
        //做出转发动作
        requestDispatcher.forward(req,resp);
    }
}
