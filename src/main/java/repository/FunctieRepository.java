package repository;

import domain.Functie;
import org.hibernate.SessionFactory;
import utils.HibernateSession;

import java.util.ArrayList;
import java.util.List;

public class FunctieRepository implements CrudRepository<Integer, Functie> {
    SessionFactory sessionFactory;

    public FunctieRepository() {
        sessionFactory = HibernateSession.getSessionFactory();
    }

    @Override
    public Functie save(Functie entity) {
        return null;
    }

    @Override
    public Functie delete(Integer id) {
        return null;
    }

    @Override
    public Functie update(Functie entity) {
        return null;
    }

    @Override
    public Functie findOne(Integer id) {
        return null;
    }

    @Override
    public List<Functie> findAll() {
        return new ArrayList<>();
    }
}
