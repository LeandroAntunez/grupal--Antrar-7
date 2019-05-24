package ar.edu.unq.eis.antrar.backend.dao.impl;

import ar.edu.unq.eis.antrar.backend.dao.CrudDAO;
import ar.edu.unq.eis.antrar.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CrudDAOHibernate<T> implements CrudDAO<T> {

    private final Class<T> clazz;

    public CrudDAOHibernate(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public T guardar(T entity) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            session.save(entity);
            return entity;
        });
    }

    @Override
    public T retornar(int id) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            return session.get(clazz, id);
        });
    }

    @Override
    public T actualizar(T entity) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            session.update(entity);
            return entity;
        });
    }

    @Override
    public void borrar(T entity) {
        Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            session.delete(entity);
            return null;
        });
    }

    @Override
    public List<T> retornarTodos() {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            Query query = session.createQuery("from " + clazz.getName());
            return query.getResultList();
        });
    }

}
