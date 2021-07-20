package repository;

import domain.Concediu;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.ArrayList;
import java.util.List;

public class ConcediuRepository implements CrudRepository<Integer, Concediu> {
    SessionFactory sessionFactory;

    public ConcediuRepository() {
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public Concediu save(Concediu entity) {
        return null;
    }

    @Override
    public Concediu delete(Integer id) {
        return null;
    }

    @Override
    public Concediu update(Concediu entity) {
        return null;
    }

    @Override
    public Concediu findOne(Integer id) {
        return null;
    }

    @Override
    public List<Concediu> findAll() {
        return new ArrayList<>();
    }
}
