package filter;

import com.voronovich.util.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.*;
import java.io.IOException;

public class SessionPerRequestFilter implements Filter {

    private Session session;
    private HibernateUtil util = HibernateUtil.getHibernateUtil();

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(request, response);

    }
    public void init(FilterConfig filterConfig) throws ServletException {
        session = util.getSession();
    }

    public void destroy() {
        if(util.getSession().getTransaction().isActive()){
            util.getSession().getTransaction().rollback();
        }
        util.getSession().close();
    }
}