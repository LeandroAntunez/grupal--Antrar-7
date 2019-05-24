package ar.edu.unq.eis.antrar.backend.service;

import ar.edu.unq.eis.antrar.backend.dao.CrudDAO;

import java.util.List;

public class CrudService<T> {

    private CrudDAO<T> dao;

    public CrudService(CrudDAO<T> dao) { this.dao = dao; }

    public T guardar(T entity){ return dao.guardar(entity); }

    public T retornarPorId(int idEntity){ return dao.retornar(idEntity); }

    public List<T> retornarTodos(){ return dao.retornarTodos(); }

    public void borrar(T entity){ dao.borrar(entity); }

    public T actualizar(T entity) { return dao.actualizar(entity); }
}
