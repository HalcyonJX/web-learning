import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String username = req.getParameter("username");
        //获取session对象
        HttpSession session = req.getSession();
        //获取Session的ID
        String jSessionId = session.getId();
        System.out.println(jSessionId);
        //判断session是不是新创建的session
        boolean isNew = session.isNew();
        System.out.println(isNew);
        //向session对象中存入数据
        session.setAttribute("username",username);
    }
}
