import jakarta.servlet.*;

import java.io.IOException;

public class Filter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter2");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter2 chain.doFilter");
    }
}
