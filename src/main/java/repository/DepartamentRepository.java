package repository;

import domain.Angajat;
import domain.Departament;
import domain.Departament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.List;

public class DepartamentRepository implements CrudRepository<Integer, Departament> {
    SessionFactory sessionFactory;

    public DepartamentRepository() {
        sessionFactory = HibernateSession.getSessionFactory();
    }
    @Override
    public Departament save(Departament entity) {
        return null;
    }

    @Override
    public Departament delete(Integer id) {
        return null;
    }

    @Override
    public Departament update(Departament entity) {
        return null;
    }

    @Override
    public Departament findOne(Integer id) {
        if (id == null)
            throw new IllegalArgumentException();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Departament> result = session.createQuery("select a from Departament a where id=:id")
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
    public List<Departament> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Departament> result = session.createQuery("select a from Departament a").list();
            session.getTransaction().commit();
            return result;
        }
    }
}
