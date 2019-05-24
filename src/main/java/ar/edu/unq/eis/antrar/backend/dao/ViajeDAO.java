package ar.edu.unq.eis.antrar.backend.dao;

import ar.edu.unq.eis.antrar.backend.model.Viaje;

import java.time.LocalDate;
import java.util.List;

// ViajeDAO suministra una interfaz común entre la aplicación y los viajes en la base de datos,
// utilizando el patron de diseño Object.
// Es utilizado por su respectivo Service, quien no requiere conocimiento directo
// del destino final de la información que manipula.

public interface ViajeDAO extends CrudDAO<Viaje>{

    <T> List<Viaje> getViajeDelClienteEntreFechas(String nombreCliente, LocalDate fechaDesde, LocalDate fechaHasta);

}
