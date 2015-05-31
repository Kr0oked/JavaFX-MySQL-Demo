package de.hsweingarten.dapro.service;

import org.hibernate.SessionFactory;

/**
 * Interface of HibernateService for Dependency Injection
 */
public interface IHibernateService {
    SessionFactory getSessionFactory();
}
