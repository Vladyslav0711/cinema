package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.CinemaHall;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dev.cinema.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {

    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add CinemaHall entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CinemaHall> cr = cb.createQuery(CinemaHall.class);
            Root<CinemaHall> root = cr.from(CinemaHall.class);
            cr.select(root);
            Query<CinemaHall> query = session.createQuery(cr);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all CinemaHalls entityes", e);
        }
    }

    @Override
    public Optional<CinemaHall> getById(Long cinemaHallId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CinemaHall> cr = cb.createQuery(CinemaHall.class);
            Root<CinemaHall> root = cr.from(CinemaHall.class);
            cr.select(root).where(cb.equal(root.get("id"), cinemaHallId));
            return session.createQuery(cr).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Cinema Hall", e);
        }

    }

}
