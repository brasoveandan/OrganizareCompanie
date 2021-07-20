package repository;

import domain.Angajat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.List;

public class AngajatRepository implements CrudRepository<Integer, Angajat> {
    SessionFactory sessionFactory;

    public AngajatRepository() {
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public Angajat save(Angajat entity) {
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
    public Angajat delete(Integer id) {
        if (id == null)
            throw new IllegalArgumentException();
        Angajat angajat = findOne(id);
        if (angajat == null)
            return null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(angajat);
            session.getTransaction().commit();
            return angajat;
        }
    }

    @Override
    public Angajat update(Angajat entity) {
        if (entity == null)
            throw new IllegalArgumentException();
        if (findOne(entity.getId()) == null)
            return entity;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return null;
        }
    }

    @Override
    public Angajat findOne(Integer id) {
        if (id == null)
            throw new IllegalArgumentException();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Angajat> result = session.createQuery("select a from Angajat a where id=:id")
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
    public List<Angajat> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Angajat> result = session.createQuery("select a from Angajat a").list();
            session.getTransaction().commit();
            return result;
        }
    }
}
