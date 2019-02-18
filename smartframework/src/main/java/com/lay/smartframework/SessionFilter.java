package com.lay.smartframework;

import com.lay.smartframework.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: Session过滤器
 * @Author: lay
 * @Date: Created in 10:49 2019/1/14
 * @Modified By:IntelliJ IDEA
 */
@WebFilter(urlPatterns = "/*",filterName = "Session-Filter",displayName = "Session-Filter")
public class SessionFilter implements Filter {

    private static final Logger log= LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request= (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();
            session.setAttribute("name","lay");
            SessionUtil.setSession(session);
            log.info("SessionFilter，url：{}",request.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
            log.info("after SessionFilter，url：{}",request.getRequestURI());
        }finally {
            SessionUtil.removeSession();
        }
    }

    @Override
    public void destroy() {

    }
}
