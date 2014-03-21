package ru.anrusstrans.test.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory() {
//        Configuration configuration = new Configuration();
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.configure();
//        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());

        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}