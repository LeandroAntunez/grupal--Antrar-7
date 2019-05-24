package ar.edu.unq.eis.antrar.backend.dao;

import java.util.List;

public interface CrudDAO<T> {

    // CRUD (Create, Return, Update, Delete) de un DAO (Data Access Object).

    /**
     * (C-rud, Create) Persiste una entidad en la base de datos.
     * @param entity entidad a ser persistida.
     * @return entidad persistida.
     */
    T guardar(T entity);

    /**
     * (c-R-ud, Return) Retorna una entidad desde la base de datos buscándola por id.
     * @param id entero.
     * @return entidad encontrada.
     */
    T retornar(int id);

    /**
     * (cr-U-d, Update)Actualiza los datos de una entidad
     * @param entity entidad a ser modificada
     * @return Entidad actualizada
     */
    T actualizar(T entity);

    /**
     * (cru-D, Delete)Borra una entidad.
     * @param entity entidad a ser eliminada.
     */
    void borrar(T entity);


    /**
     * Retorna todas las entidades
     * @return lista de entidades
     */
    List<T> retornarTodos();


}
