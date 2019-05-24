package ar.edu.unq.eis.antrar.backend.dao.impl;

import ar.edu.unq.eis.antrar.backend.dao.ViajeDAO;
import ar.edu.unq.eis.antrar.backend.model.Viaje;
import ar.edu.unq.eis.antrar.backend.service.runner.Runner;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class ViajeDAOHibernate extends CrudDAOHibernate<Viaje> implements ViajeDAO{

    public ViajeDAOHibernate(Class<Viaje> clazz) { super(Viaje.class); }

    @Override
    public <T> List<Viaje> getViajeDelClienteEntreFechas(String nombreCliente, LocalDate fechaDesde, LocalDate fechaHasta) {
        Session session = Runner.getCurrentSession();

        String hql = "FROM Viajes v "
                + "WHERE v.cliente = :nombre AND (v.fecha BETWEEN :fecha_desde AND :fecha_hasta)";

        Query<Viaje> query = session.createQuery(hql,  Viaje.class);
        query.setParameter("nombre", nombreCliente);
        query.setParameter("fecha_desde", fechaDesde);
        query.setParameter("fecha_hasta", fechaHasta);

        return query.getResultList();
    }
}
