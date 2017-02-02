package com.voronovich.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class designed to configure sessionFactory and create
 * Hibernate Session using ThreadLocal object
 *
 * @author Dmitry V
 * @version 1.0
 */
public class HibernateUtil {

    private static HibernateUtil util = null;
    private static Logger log = Logger.getLogger(HibernateUtil.class);
    private SessionFactory sessionFactory = null;
    private final ThreadLocal sessions = new ThreadLocal();

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e) {
            log.error("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Getting Hibernate session
     *
     * @return Hibernate session
     */
    public Session getSession() {
        Session session = (Session) sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    /**
     * Getting singleton HibernateUtil
     *
     * @return HibernateUtil
     */
    public static HibernateUtil getHibernateUtil() {
        if (util != null) {
            return util;
        }
        synchronized (HibernateUtil.class) {
            if (util == null) {
                util = new HibernateUtil();
            }
        }
        return util;
    }
}