import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        //根据数据名获得单个参数
        String value = servletConfig.getInitParameter("param3");
        System.out.println("param3:"+value);
        //获取所有参数名
        Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
        while(parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement+":"+servletConfig.getInitParameter(nextElement));
        }
    }
}
