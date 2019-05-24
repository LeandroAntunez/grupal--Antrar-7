package ar.edu.unq.eis.antrar.backend.service;

import ar.edu.unq.eis.antrar.backend.dao.CrudDAO;
import ar.edu.unq.eis.antrar.backend.dao.ViajeDAO;
import ar.edu.unq.eis.antrar.backend.dao.impl.ViajeDAOHibernate;
import ar.edu.unq.eis.antrar.backend.model.Viaje;
import ar.edu.unq.eis.antrar.backend.service.runner.Runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViajeService extends CrudService<Viaje> {

    private ViajeDAO viajeDAO;

    public ViajeService(CrudDAO<Viaje> dao) {
        super(dao);
        this.viajeDAO = new ViajeDAOHibernate(Viaje.class);
    }

    public List<Viaje> buscarViajesDelClienteEntreFechas(String nombreCliente, LocalDate fechaDesde, LocalDate fechaHasta) {
        return Runner.runInSession(() ->
                this.viajeDAO.getViajeDelClienteEntreFechas(nombreCliente, fechaDesde, fechaHasta));
    }
}
