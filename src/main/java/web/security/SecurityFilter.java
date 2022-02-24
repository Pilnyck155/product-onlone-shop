package web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private  final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
    private final SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Cookie[] cookies = httpServletRequest.getCookies();
        boolean isAuthorized = securityService.checkCookies(cookies);
        if (isAuthorized) {
            logger.info("User is authorized");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            httpServletResponse.sendRedirect("/login");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
