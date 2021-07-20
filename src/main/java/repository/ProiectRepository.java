package repository;

import domain.Proiect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.ArrayList;
import java.util.List;

public class ProiectRepository implements CrudRepository<Integer, Proiect> {
    SessionFactory sessionFactory;

    public ProiectRepository() {
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public Proiect save(Proiect entity) {
        if (entity == null)
            throw new IllegalArgumentException();
        if (findOne(entity.getId()) != null)
            return entity;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            return null;
        }
    }

    @Override
    public Proiect delete(Integer id) {
        return null;
    }

    @Override
    public Proiect update(Proiect entity) {
        return null;
    }

    @Override
    public Proiect findOne(Integer id) {
        if (id == null)
            throw new IllegalArgumentException();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Proiect> result = session.createQuery("select a from Proiect a where id=:id")
                    .setParameter("id", id)
                    .list();
            session.getTransaction().commit();
            if (!result.isEmpty())
                return result.get(0);
            else
                return null;
        }
    }

    @Override
    public List<Proiect> findAll() {
        return new ArrayList<>();
    }
}
