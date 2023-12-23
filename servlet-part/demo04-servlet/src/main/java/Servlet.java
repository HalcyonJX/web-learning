import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String valueA = servletContext.getInitParameter("paramA");
        System.out.println("value:"+valueA);
        //获取所有的参数名
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        while (parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement+":"+servletContext.getInitParameter(nextElement));
        }
    }
}
