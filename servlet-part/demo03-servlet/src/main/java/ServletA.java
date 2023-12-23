import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        //根据参数名获取单个参数
        String value = servletConfig.getInitParameter("param1");
        System.out.println("param1:"+value);
        //获取所有参数名
        Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
        //迭代并获取参数名
        while(parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            System.out.println(parameterName+":"+servletConfig.getInitParameter(parameterName));
        }
    }
}
