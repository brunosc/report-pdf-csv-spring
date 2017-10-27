package br.com.generator.genpdfcsv.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        CustomResponseWrapper brw = new CustomResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, brw);
        String out = new String(brw.getBytes());

        out = out.concat(" response alteraod");

        servletResponse.getWriter().print(out);
    }

    @Override
    public void destroy() {

    }

}
