import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/servletC")
public class ServletC extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向请求域中放入数据
        req.setAttribute("request","req-Msg");
        //向会话域中放入数据
        HttpSession session = req.getSession();
        session.setAttribute("session","ses-Msg");
        //向应用域中放入数据
        ServletContext application = getServletContext();
        application.setAttribute("application","app-Msg");
    }
}
