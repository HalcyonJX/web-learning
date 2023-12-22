import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(
        name = "userServlet",
value = "/userServlet"
//        value = "/user",
//        urlPatterns =
//                {"/userServlet1","/userServlet2","/userServlet"},
//        initParams = {@WebInitParam(name = "encoding",value = "UTF-8")},
//        loadOnStartup = 6
)
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的键值对参数
        String username = req.getParameter("username");
        if("halcyon".equals(username)){
            //通过响应对象响应信息
            resp.getWriter().write("NO");
        }else {
            resp.getWriter().write("YES");
        }
    }
}
