package filter;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class designed for correct data encoding mechanism
 *
 * @author Dmitry V
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8",
                        description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private String coding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        coding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        String codingRequest = request.getCharacterEncoding();

        if (coding != null && !coding.equalsIgnoreCase(codingRequest)) {
            request.setCharacterEncoding(coding);
        }

        String codingResponse = response.getCharacterEncoding();
        if (coding != null && !coding.equalsIgnoreCase(codingResponse)) {
            response.setCharacterEncoding(coding);
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        coding = null;
    }
}