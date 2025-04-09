package md.faces.backend.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import md.faces.backend.model.Gender;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/profile/*")
public class HiddenHttpMethodFilter implements Filter {


    public static final String METHOD_PARAM = "_method";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            ServletContext servletContext = filterConfig.getServletContext();
            if (servletContext.getAttribute("genders") == null) {
                servletContext.setAttribute("genders", Gender.values());
            }

    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String paramValue = request.getParameter(METHOD_PARAM);


        if ("POST".equals(request.getMethod()) && paramValue != null && !paramValue.isBlank()) {
            String method = paramValue.toUpperCase(Locale.ENGLISH);
            HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }


    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }

}
